package pe.gob.mtpe.sovio.util;

/**
 * Clase utilitaria para encriptar y desencriptar.
 * @author Jonatan Pozo
 * @since 4 Junio 2015
 * @version 1.0
 *
 */


import java.io.*;
import javax.crypto.*;
import java.security.spec.*;
import javax.crypto.spec.*; 

public class Labeler {

	Cipher ecipher;
	Cipher dcipher;
	
    // 8-byte Salt
    byte[] salt = {
            (byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32,
            (byte)0x56, (byte)0x35, (byte)0xE3, (byte)0x03
    };
    
    // Iteration count
    int iterationCount = 19;

    /**
     * Metodo constructor.
     * */
    Labeler() {
      	String passPhrase = "MINTRA";
        try {
             // Create the key
             KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt, iterationCount);
             SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
             ecipher = Cipher.getInstance(key.getAlgorithm());
             dcipher = Cipher.getInstance(key.getAlgorithm());

             // Prepare the parameter to the ciphers
             AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
    
             // Create the ciphers
             ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
             dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

        } catch (java.security.InvalidAlgorithmParameterException e) {
        } catch (java.security.spec.InvalidKeySpecException e) {
        } catch (javax.crypto.NoSuchPaddingException e) {
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch (java.security.InvalidKeyException e) {
        }
    }
    
    /**
     * Metodo que encripta un texto.
     * @param str texto a encriptar, tipo String.
     * @return String texto encriptado.
     * */
    public String encrypt(String str) {
         try {
                // Encode the string into bytes using utf-8
                byte[] utf8 = str.getBytes("UTF8");
    
                // Encrypt
                byte[] enc = ecipher.doFinal(utf8);
    
                // Encode bytes to base64 to get a string
                return new sun.misc.BASE64Encoder().encode(enc);
         } catch (javax.crypto.BadPaddingException e) {
         } catch (IllegalBlockSizeException e) {
         } catch (UnsupportedEncodingException e) {
         } catch (Exception e) {
		 }
            return null;
    }
    
    /**
     * Metodo que desencripta un texto.
     * @param str texto a desencriptar, tipo String.
     * @return String texto desencriptado.
     * */
    public String decrypt(String str) {
         try {
                // Decode base64 to get bytes
                byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
    
                // Decrypt
                byte[] utf8 = dcipher.doFinal(dec);
    
                // Decode using utf-8
                return new String(utf8, "UTF8");
         } catch (javax.crypto.BadPaddingException e) {
         } catch (IllegalBlockSizeException e) {
         } catch (UnsupportedEncodingException e) {
         } catch (java.io.IOException e) {
         }
            return null;
    }

    /**
     * Metodo que encripta un texto.
     * @param label texto a encriptar, tipo String.
     * @return String texto encriptado.
     * */
    public String encodeLabel(String label) throws Exception {
         if (label == null) return null;
    		String encrypted = encrypt(label);
    		return encrypted;
    }
    
    /**
     * Metodo que desencripta un texto.
     * @param labelEncoded texto a desencriptar, tipo String.
     * @return String texto desencriptado.
     * */
    public String decodeLabel(String labelEncoded) throws Exception {
    	if (labelEncoded == null) return null;
    	String decrypted = decrypt(labelEncoded.replaceAll(" ", "\\+"));
    		return decrypted;
    }
}

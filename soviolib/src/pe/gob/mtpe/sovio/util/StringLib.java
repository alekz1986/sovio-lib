package pe.gob.mtpe.sovio.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Clase utilitaria para trabajar con cadenas de texto.
 * @author Jonatan Pozo
 * @since 4 Junio 2015
 * @version 1.0
 *
 */

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public final class  StringLib {
	
	public static String NUMEROS = "0123456789";
	 
	public static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
 
	public static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
 
	public static String ESPECIALES = "ñÑ";

	private StringLib() { }

    /**
     * Metodo que encripta un texto.
     * @param label texto a encriptar, tipo String.
     * @return String texto encriptado.
     * */
	public static String encodeLabel(String label) throws Exception {
		Labeler labeler = new Labeler();
		String cod = labeler.encodeLabel(label);
		
		if (cod==null) return null;
		
		StringBuffer buffer = new StringBuffer(); 
		for (int i=0; i<cod.length(); i++) {
			char c = cod.charAt(i);
			if(c!='\r' && c!='\n' && c!='\t') buffer.append(c);
		}
		return buffer.toString();
	}

    /**
     * Metodo que desencripta un texto.
     * @param labelEncoded texto a encriptar, tipo String.
     * @return String texto desencriptado.
     * */
	public static String decodeLabel(String labelEncoded) throws Exception {
		Labeler labeler = new Labeler();
		return labeler.decodeLabel(labelEncoded);
	}  

    /**
     * Metodo que encripta un texto grande.
     * @param label texto a encriptar, tipo String.
     * @return String texto encriptado.
     * @throws Exception
     * */
	public static String encodeLongLabel(String label) throws Exception {
		if (label == null) return null;
		String uspass    = label;
		String uspass2   = null;
		String uspasse   = new sun.misc.BASE64Encoder().encode (uspass.getBytes());
		uspass2   = new sun.misc.BASE64Encoder().encode (uspasse.getBytes());			
		return uspass2;
	}

    /**
     * Metodo que desencripta un texto grande.
     * @param labelEncoded texto a encriptar, tipo String.
     * @return String texto desencriptado.
     * */
	public static String decodeLongLabel(String labelEncoded) throws Exception {
		if (labelEncoded == null) return null;
		sun.misc.BASE64Decoder dec = new sun.misc.BASE64Decoder();
		String uspassd  = new String(dec.decodeBuffer(labelEncoded))  ;
		String uspassd2 = new String(dec.decodeBuffer(uspassd))  ;
		String label = uspassd2;
		return label;
	}  

    /**
     * Metodo que genera una contraseña.
     * @param length tamaño de la contraseña, tipo int.
     * @return String contraseña generada.
     * */
    public static String generatePassword (int length) {
    	 Random generator = new Random();

    	 String[][] letter = { {"a","e","i","o","u"},
    			 {"b","c","d","f","g","h","j","k","l","m","n","p","q","r","s","t","v","w","x","y","z"},
    			 {"br","ch","cr","fr","gr","pr","pl","tr"},
    			 {"lt","lm","ln","ls","mn","st","sm"}};

    	 // first character
    	 int first = generator.nextInt(3);
    	 int component= generator.nextInt( letter[first].length ) ;
    	 String password = letter[first][component];
    	 boolean cons = false;
    	 if (first!=0) cons=true;

    	 while ( password.length() < length) {
    		 if (cons) {
    			 int index = generator.nextInt(5);
    			 password += letter[0][index];
    			 cons=false;
    		 }
    		 else {
    			 int[] prob = {1,1,1,2,3};
    			 // has more probability 3/5 for a consonant
    			 int index      = generator.nextInt( prob.length );
    			 int element    = generator.nextInt( letter[prob[index]].length ) ;
    			 password += letter[prob[index]][element];
    			 cons = !cons;
    		 }
    	 }
    	 // if the last adding is a double constant, it can have an extra character
    	 return password.substring(0,length); 
     }

    /**
     * Metodo que reemplaza los caracteres especiales por su codigo html.
     * @param text texto html, tipo String.
     * @return String texto reemplazado.
     * */
	public static String formatHtml(String text) {
		String UNICODE_SMALL_A_GRAVE = "\u00E0";
		String UNICODE_SMALL_A_ACUTE = "\u00E1";
		String UNICODE_SMALL_A_CIRC = "\u00E2";
		String UNICODE_SMALL_A_TILDE = "\u00E3";
		String UNICODE_SMALL_A_UML = "\u00E4";
		String UNICODE_SMALL_A_RING = "\u00E5";
		String UNICODE_SMALL_C_CEDIL = "\u00E7"; 
		String UNICODE_SMALL_E_GRAVE = "\u00E8";
		String UNICODE_SMALL_E_ACUTE = "\u00E9";
		String UNICODE_SMALL_E_CIRC = "\u00EA";
		String UNICODE_SMALL_E_UML = "\u00EB";
		String UNICODE_SMALL_I_GRAVE = "\u00EC";
		String UNICODE_SMALL_I_ACUTE = "\u00ED";
		String UNICODE_SMALL_I_CIRC = "\u00EE";
		String UNICODE_SMALL_I_UML = "\u00EF";
		String UNICODE_SMALL_O_GRAVE = "\u00F2";
		String UNICODE_SMALL_O_ACUTE = "\u00F3";
		String UNICODE_SMALL_O_CIRC = "\u00F4";
		String UNICODE_SMALL_O_TILDE = "\u00F5";
		String UNICODE_SMALL_O_UML = "\u00F6";
		String UNICODE_SMALL_U_GRAVE = "\u00F9";
		String UNICODE_SMALL_U_ACUTE = "\u00FA";
		String UNICODE_SMALL_U_CIRC = "\u00FB";
		String UNICODE_SMALL_U_UML = "\u00FC";
		String UNICODE_SMALL_N_TILDE = "\u00F1";
		String UNICODE_CAPITAL_A_GRAVE = "\u00C0";
		String UNICODE_CAPITAL_A_ACUTE = "\u00C1";
		String UNICODE_CAPITAL_A_CIRC = "\u00C2";
		String UNICODE_CAPITAL_A_TILDE = "\u00C3";
		String UNICODE_CAPITAL_A_UML = "\u00C4";
		String UNICODE_CAPITAL_A_RING = "\u00C5";
		String UNICODE_CAPITAL_C_CEDIL = "\u00C7"; 
		String UNICODE_CAPITAL_E_GRAVE = "\u00C8";
		String UNICODE_CAPITAL_E_ACUTE = "\u00C9";
		String UNICODE_CAPITAL_E_CIRC = "\u00CA";
		String UNICODE_CAPITAL_E_UML = "\u00CB";
		String UNICODE_CAPITAL_I_GRAVE = "\u00CC";
		String UNICODE_CAPITAL_I_ACUTE = "\u00CD";
		String UNICODE_CAPITAL_I_CIRC = "\u00CE";
		String UNICODE_CAPITAL_I_UML = "\u00CF";
		String UNICODE_CAPITAL_O_GRAVE = "\u00D2";
		String UNICODE_CAPITAL_O_ACUTE = "\u00D3";
		String UNICODE_CAPITAL_O_CIRC = "\u00D4";
		String UNICODE_CAPITAL_O_TILDE = "\u00D5";
		String UNICODE_CAPITAL_O_UML = "\u00D6";
		String UNICODE_CAPITAL_U_GRAVE = "\u00D9";
		String UNICODE_CAPITAL_U_ACUTE = "\u00DA";
		String UNICODE_CAPITAL_U_CIRC = "\u00DB";
		String UNICODE_CAPITAL_U_UML = "\u00DC";
		String UNICODE_CAPITAL_N_TILDE = "\u00D1";
		String UNICODE_IEXCLAMATION = "\u00A1";
		String UNICODE_GRADE = "\u00B0";
		String UNICODE_IQUESTION = "\u00BF";
		String UNICODE_RETURN = "\\u000D";
		String UNICODE_NEWLINE = "\\u000A";
		String UNICODE_DOBLEQUOTE = "\\u0022";
		String UNICODE_EURO = "\u0080";
		String UNICODE_PLUS = "\\u002B";
		String UNICODE_PLUS_MINUS = "\u00B1";
		return text.replaceAll(UNICODE_SMALL_A_GRAVE, "&agrave;")
					.replaceAll(UNICODE_SMALL_A_ACUTE, "&aacute;")
					.replaceAll(UNICODE_SMALL_A_CIRC, "&acirc;")
					.replaceAll(UNICODE_SMALL_A_TILDE, "&atilde;")
					.replaceAll(UNICODE_SMALL_A_UML, "&auml;")
					.replaceAll(UNICODE_SMALL_A_RING, "&aring;")
					.replaceAll(UNICODE_SMALL_C_CEDIL, "&ccedil;")
					.replaceAll(UNICODE_SMALL_E_GRAVE, "&egrave;")
					.replaceAll(UNICODE_SMALL_E_ACUTE, "&eacute;")
					.replaceAll(UNICODE_SMALL_E_CIRC, "&ecirc;")
					.replaceAll(UNICODE_SMALL_E_UML, "&euml;")
					.replaceAll(UNICODE_SMALL_I_GRAVE, "&igrave;")
					.replaceAll(UNICODE_SMALL_I_ACUTE, "&iacute;")
					.replaceAll(UNICODE_SMALL_I_CIRC, "&icirc;")
					.replaceAll(UNICODE_SMALL_I_UML, "&iuml;")
					.replaceAll(UNICODE_SMALL_O_GRAVE, "&ograve;")
					.replaceAll(UNICODE_SMALL_O_ACUTE, "&oacute;")
					.replaceAll(UNICODE_SMALL_O_CIRC, "&ocirc;")
					.replaceAll(UNICODE_SMALL_O_TILDE, "&otilde;")
					.replaceAll(UNICODE_SMALL_O_UML, "&ouml;")
					.replaceAll(UNICODE_SMALL_U_GRAVE, "&ugrave;")
					.replaceAll(UNICODE_SMALL_U_ACUTE, "&uacute;")
					.replaceAll(UNICODE_SMALL_U_CIRC, "&ucirc;")
					.replaceAll(UNICODE_SMALL_U_UML, "&uuml;")
					.replaceAll(UNICODE_SMALL_N_TILDE, "&ntilde;")
					.replaceAll(UNICODE_CAPITAL_A_GRAVE, "&Agrave;")
					.replaceAll(UNICODE_CAPITAL_A_ACUTE, "&Aacute;")
					.replaceAll(UNICODE_CAPITAL_A_CIRC, "&Acirc;")
					.replaceAll(UNICODE_CAPITAL_A_TILDE, "A")
					.replaceAll(UNICODE_CAPITAL_A_UML, "&Atilde;")
					.replaceAll(UNICODE_CAPITAL_A_RING, "&Auml;")
					.replaceAll(UNICODE_CAPITAL_C_CEDIL, "&Ccedil;")
					.replaceAll(UNICODE_CAPITAL_E_GRAVE, "&Egrave;")
					.replaceAll(UNICODE_CAPITAL_E_ACUTE, "&Eacute;")
					.replaceAll(UNICODE_CAPITAL_E_CIRC, "&Ecirc;")
					.replaceAll(UNICODE_CAPITAL_E_UML, "&Euml;")
					.replaceAll(UNICODE_CAPITAL_I_GRAVE, "&Igrave;")
					.replaceAll(UNICODE_CAPITAL_I_ACUTE, "&Iacute;")
					.replaceAll(UNICODE_CAPITAL_I_CIRC, "&Icirc;")
					.replaceAll(UNICODE_CAPITAL_I_UML, "&Iuml;")
					.replaceAll(UNICODE_CAPITAL_O_GRAVE, "&Ograve;")
					.replaceAll(UNICODE_CAPITAL_O_ACUTE, "&Oacute;")
					.replaceAll(UNICODE_CAPITAL_O_CIRC, "&Ocirc;")
					.replaceAll(UNICODE_CAPITAL_O_TILDE, "&Otilde;")
					.replaceAll(UNICODE_CAPITAL_O_UML, "&Ouml;")
					.replaceAll(UNICODE_CAPITAL_U_GRAVE, "&Ugrave;")
					.replaceAll(UNICODE_CAPITAL_U_ACUTE, "&Uacute;")
					.replaceAll(UNICODE_CAPITAL_U_CIRC, "&Ucirc;")
					.replaceAll(UNICODE_CAPITAL_U_UML, "&Uuml;")
					.replaceAll(UNICODE_CAPITAL_N_TILDE, "&Ntilde;")
					.replaceAll(UNICODE_IEXCLAMATION, "&iexcl;")
					.replaceAll(UNICODE_GRADE, "&deg;")
					.replaceAll(UNICODE_IQUESTION, "&iquest;")
					.replaceAll(UNICODE_RETURN, "<br/>")
					.replaceAll(UNICODE_NEWLINE, "<br/>")
					.replaceAll(UNICODE_DOBLEQUOTE, "&quot;")
					.replaceAll(UNICODE_EURO, "&euro;")
					.replaceAll(UNICODE_PLUS, "&plusmn;")
					.replaceAll(UNICODE_PLUS_MINUS, "&plusmn;");
	}

    /**
     * Metodo que capitaliza el texto.
     * @param textoSinFormato texto a capitalizar, tipo String.
     * @return String texto capitalizado.
     * */
    public static String capitalizarTexto(String textoSinFormato){
        String []palabras = textoSinFormato.split("\\s+");
        StringBuilder textoFormateado = new StringBuilder();
        
        for(String palabra : palabras){
            textoFormateado.append(palabra.substring(0,1).toUpperCase().concat( palabra.substring(1,palabra.length()).toLowerCase()).concat(" "));
        }
        
        return textoFormateado.toString();        
    }
    
    /**
     * Metodo que encripta a MD5 un texto.
     * @param cadena texto a encriptar, tipo String.
     * @return String texto encriptado.
     * */
    public static String getMD5(String cadena) throws Exception {

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] b = md.digest(cadena.getBytes());
        int size = b.length;
        StringBuilder h = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            int u = b[i] & 255;
            if (u < 16) {
                h.append("0").append(Integer.toHexString(u));
            } else {
                h.append(Integer.toHexString(u));
            }
        }
        return h.toString();
    }
    
    /**
     * Metodo que obtiene un aleatorio en base a un texto y un tamaño.
     * @param key texto con los valores a conbinar, tipo String.
     * @param length tamaño del texto, tipo int.
     * @return String texto aleatorio.
     * */
    public static String getAleatorio(String key, int length) {
		String pswd = "";
 
		for (int i = 0; i < length; i++) {
			pswd+=(key.charAt((int)(Math.random() * key.length())));
		}
 
		return pswd;
	}
    
    /**
     * Metodo que obtiene el valor de un campo. util para los filtros en datatable paginado.
     * @param objeto objeto para obtener el valor, tipo Object.
     * @param filterProperty nombre de la propiedad, tipo String.
     * @return String valor.
     * */
    public static String getFieldValue(Object objeto, String filterProperty) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
    	
    	String resultado = "";
    	String[] arreglo = filterProperty.split("\\.");
    	
    	if(arreglo.length > 1){
    		
	    	int i = 0;
	        for (String string : arreglo) {
	        	
				if(string.startsWith("v") || string.startsWith("n")){
				   arreglo[i] = "get" + string;
				}else{
				   arreglo[i] = "get" + string.substring(0, 1).toUpperCase() + string.subSequence(1, string.length());
				}
				i++;
			}
	        
	        switch (arreglo.length) {
			
	         case 2: Object objetoTmp1 = objeto.getClass().getMethod(arreglo[0]).invoke(objeto);
	                 if(objetoTmp1 != null)
			         resultado = (String) objetoTmp1.getClass().getMethod(arreglo[1]).invoke(objetoTmp1);
			         break;
			 case 3: Object objetoTmp2 = null;
			         Object objetoTmp3 = null;
				     objetoTmp2 = objeto.getClass().getMethod(arreglo[0]).invoke(objeto);
			         if(objetoTmp2 != null)
			         objetoTmp3 = objetoTmp2.getClass().getMethod(arreglo[1]).invoke(objetoTmp2);
			         if(objetoTmp3 != null)
			         resultado = (String) objetoTmp3.getClass().getMethod(arreglo[2]).invoke(objetoTmp3);
	                 break;
			}
        
    	}else{
    		if(arreglo.length == 1){
    			resultado = (String)objeto.getClass().getMethod("get" + arreglo[0]).invoke(objeto);	
    		}
    	}
    	
    	return resultado;
    }
	
    /**
     * Metodo que obtiene el nombre de reporte para que sea unico en base a la fecha.
     * @return String valor.
     * */
    public static String obtenerNombreReporteDate(){
    	SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
    	return sdf.format(Calendar.getInstance().getTime());
    }
    
    /**
     * Metodo que valida si una cadena es número.
     * @param cadena texto a validar, tipo String.
     * @return boolean valor.
     * */
    public static boolean isNumeric(String cadena){
    	return cadena.matches("^[0-9]+$");
    	/*
    	try {
    		//Integer.parseInt(cadena);
    		BigInteger.valueOf(Long.parseLong(cadena));
    		//new BigInteger(cadena);
    		return true;
    	} catch (NumberFormatException nfe){
    		return false;
    	} catch (Exception e){
    		return false;
    	}
    	*/
    }

    /**
     * Metodo que recibe una cadena y devuelve un valor entero en caso cumpla las condiciones
     * de ser un texto con caraceteres numericos, en caso contrario devolverá cero
     * @param sint valor que sera transformado a un entero.
     * @return
     */
    public static int toInt(String sint) {
    	return StringLib.isNumeric(sint) ? Integer.parseInt(sint) : 0;
    }

    /**
     * Metodo que devuelve el stackTrace de una exception en formato String
     * @param ex Exception del que se extraera la informacion
     */
    public static String getExceptionStackTrace(Exception ex) {
    	StringWriter sw = new StringWriter();
    	PrintWriter pw = new PrintWriter(sw);
    	ex.printStackTrace(pw);
    	return sw.toString(); 
    }
	 
}


package pe.gob.mtpe.sovio.util;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;


public class SendMail extends JavaMailSenderImpl {
	
	private final Log logger = LogFactory.getLog(this.getClass());

    private Map<String, Object> attachFile;
    private String from;
    private String[] to;
    private String text;
    private String subject;
    private boolean textHtml = false;

    /**
     * Metodo para modificar una lista de archivo adjunto.
     * @param attachFile nombre de la lista de archivos adjuntos, tipo Map<String, Object>.
     * */
    public void setAttachFile(Map<String, Object> attachFile) {
		this.attachFile = attachFile;
	}

    /**
     * Metodo para modificar los correos destino.
     * @param to nombre de los correos destino, tipo String[].
     * */
	public void setTo(String[] to) {
		this.to = to;
	}

	/**
     * Metodo para modificar el correo destino.
     * @param to nombre del correo destino, tipo String.
     * */
	public void setTo(String to) {
		this.to = new String[]{to};
	}

	/**
     * Metodo para modificar el correo desde.
     * @param from nombre del correo desde, tipo String.
     * */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
     * Metodo para modificar el mensaje del correo.
     * @param text nombre del mensaje del correo, tipo String.
     * */
	public void setText(String text) {
		this.text = text;
	}

	/**
     * Metodo para modificar el estado del mensaje del correo en formato html.
     * @param textHtml nombre estado del mensaje del correo en formato html, tipo boolean.
     * */
	public void setTextHtml(boolean textHtml) {
		this.textHtml = textHtml;
	}

	/**
     * Metodo para modificar el asunto del correo.
     * @param subject nombre del asunto del correo, tipo String.
     * */
	public void setSubject(String subject) {
		this.subject = subject;
	}

    /**
     * Metodo utilitario para enviar correos.
     * */
	public void sendMail() throws Exception {
		logger.info("");
    	MimeMessage message = this.createMimeMessage();

    	message.setFrom(new InternetAddress(from));
    	message.setSubject(subject);
    	MimeMessageHelper helper = new MimeMessageHelper(message, true);
    	
    	if(attachFile != null){
        	Iterator<?> it = attachFile.keySet().iterator();
        	while(it.hasNext()){
        		Object key = it.next();
        		Object file = attachFile.get(key);
        		if(ClassUtils.isAssignable(file.getClass(), File.class)){
	        		File attach = (File)file;
	        		logger.info("attaching File: ".concat(attach.getName()));
	        		helper.addAttachment(attach.getName(), attach);
        		}else if(ClassUtils.isAssignable(file.getClass(), String.class)){
        				File attach = new File((String)file);
        				logger.info("attaching File: ".concat(attach.getName()));
        				helper.addAttachment(attach.getName(), attach);
        		}else{
        				logger.warn("Attached not valid");
        		}
        	}
    	}

    	helper.setText(text, textHtml);
    	helper.setTo(to);
    	
    	this.send(message);
		logger.info("correo enviado");

    }
	
    /**
     * Metodo para establecer el texto desde un archivo.
     * @param input ruta del archivo , tipo String.
     * @param encoding codificacion del archivo, tipo String.
     * @throws IOException para el manejo de errores.
     * */
	public void setText(String input, String encoding) throws IOException {
		text = this.fileToString(this.getResourceAsStream(input), encoding);
	}
	
    /**
     * Metodo para obtener el Stream del archivo.
     * @param input ruta del archivo , tipo String.
     * */
	private InputStream getResourceAsStream(String input){
		return SendMail.class.getResourceAsStream(input);
	}
	
    /**
     * Metodo para obtener el texto de un archivo desde un Stream.
     * @param input stream del archivo , tipo InputStream.
     * @param encoding codificacion del archivo, tipo String.
     * @throws IOException para el manejo de errores.
     * */
	private String fileToString(InputStream input, String encoding) throws IOException {		
		StringWriter	  sw = new StringWriter();
		InputStreamReader in = new InputStreamReader(input, encoding);

		char[]	buffer = new char[1024 * 2];
		int		n	   = 0;
		while (-1 != (n = in.read(buffer))) {
			sw.write(buffer, 0, n);
		}    		
		return sw.toString();
	}
	
    /**
     * Metodo para reemplazar las variables del archivo de texto.
     * @param valueMap parametros, tipo Map<String, String>.
     * */
	public void replaceExpressionForValue(Map<String, String> valueMap){
		StrSubstitutor strSubstitutor = new StrSubstitutor(valueMap);
		text = strSubstitutor.replace(text);
	}
	
}
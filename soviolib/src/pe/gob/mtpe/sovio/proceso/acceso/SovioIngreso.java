package pe.gob.mtpe.sovio.proceso.acceso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javassist.compiler.ast.StringL;
import pe.gob.mtpe.sovio.bean.simintra1.SITBPais;
import pe.gob.mtpe.sovio.bean.simintra1.SITBPersonaExt;
import pe.gob.mtpe.sovio.bean.simintra1.SITBUsuario;
import pe.gob.mtpe.sovio.bean.simintra1.enums.UsuarioFlgExt;
import pe.gob.mtpe.sovio.bean.tramite.PRTBCPersonal;
import pe.gob.mtpe.sovio.datos.Acceso;
import pe.gob.mtpe.sovio.proceso.Commons;
import pe.gob.mtpe.sovio.proceso.util.ValorObligatorio;
import pe.gob.mtpe.sovio.util.Constantes;
import pe.gob.mtpe.sovio.util.Mensajes;
import pe.gob.mtpe.sovio.util.SendMail;
import pe.gob.mtpe.sovio.util.StringLib;
import pe.gob.mtpe.sovio.util.Validar;
import pe.gob.mtpe.sovio.util.log.InjectLogger;
import pe.gob.mtpe.sovio.util.process.MessageType;
import pe.gob.mtpe.sovio.util.process.ProcessResponse;
import pe.gob.mtpe.sovio.util.process.SovioProcess;
import pe.gob.mtpe.sovio.util.process.SovioProcessException;


@Component
public class SovioIngreso extends ProcessResponse {

	
	@Autowired
	private Acceso datosAcceso;
	
	@Autowired
	private static SendMail sendMail;
	
	@InjectLogger
	private Logger log;

	
	/**
	 * Metodo que obtiene el usuario por codigo de usuario y clave, la clave
	 * no debe estar encriptada
	 * @param desUsu usuario que usa para ingresar al sistema
	 * @param passUsu Clave del usuario sin encriptar
	 * @return El usuario que da como resultado el codigo de usuario y 
	 * contraseña ingresada.
	 * @throws Exception 
	 */
	@SovioProcess
	public SITBUsuario ingresar(String codUsu, String passUs) {
		log.info("La clave sera encriptada");
		return ingresar(codUsu, passUs, false);
	}

	
	/**
	 * Metodo que obtiene el usuario por codigo de usuario y clave
	 * @param desUsu usuario que usa para ingresar al sistema
	 * @param passUsu Clave del usuario, puede ser encriptada o sin encriptar
	 * @param esEncriptado Determina si la clave es encriptada o no. Si no lo 
	 *  es, se realizará la encriptacion durante el proceso. 
	 * @return Retorna el usuario que da como resultado el codigo de usuario y 
	 * contraseña ingresada.
	 */
	@SovioProcess
	public SITBUsuario ingresar(String desUsu, String passUsu, 
			boolean esEncriptado) {
		
		try {
			passUsu = !esEncriptado ? StringLib.encodeLabel(passUsu) : passUsu;
		} catch (Exception ex) {
			log.error("Error durante la encriptacion de la contraseña");
			log.error(StringLib.getExceptionStackTrace(ex));
			setMensaje(Mensajes.get(Mensajes.Err.USU_CLAVE_ERROR),
					MessageType.ERROR_MESSAGE);
			return null;
		}

		SITBUsuario usuario = datosAcceso.getUsuarioExterno(desUsu, passUsu);
		if(usuario==null) {
			setMensaje(
					Mensajes.get(Mensajes.Err.USU_CLAVE_ERROR),
					MessageType.ERROR_MESSAGE);
			log.debug("Usuario y/o contraseña incorrectos");
			return null;
		}
	
		if(!tieneRegistroCompleto(this, usuario)) {
			return null;
		}
	
		return usuario;
	}

	
	
	/**
	 * Metodo que valida si el usuario ha completado su proceso de registro
	 * @param proceso Objecto que hace referencia al proceso que requiere de esta funcion,  
	 * se usa para establecer los valores que dependen de un proceso SOVIO (por ejemplo 
	 * asignar un mensaje, el tipo de mensaje, etc)
	 * @param usuario usuario que sera validado
	 * @return verdadero en caso de ser un usuario sin pendientes de registro.
	 */
	private boolean tieneRegistroCompleto(ProcessResponse proceso, 
			SITBUsuario usuario) {
		
		log.info("Validando estado de usuario");
		if( StringLib.toInt(usuario.getFlgEstExt()) == 
				UsuarioFlgExt.ESTADO_DESACTIVO.id() ) {
			proceso.setMensaje(
					Mensajes.get(Mensajes.Adv.USUARIO_DESACTIVADO), 
					MessageType.ERROR_MESSAGE);
			log.info("Usuario desactivado");
			return false;
		}
		
		if(StringLib.toInt(usuario.getFlgEstExt()) == 
				UsuarioFlgExt.ESTADO_POR_VALIDAR.id()) {
			proceso.setMensaje(
					Mensajes.get(Mensajes.Adv.USU_FALTA_VALIDA_CORREO),
					MessageType.ERROR_MESSAGE);
			log.info("Falta validar el correo electrónico");
			return false;
		}
		
		if(StringLib.toInt(usuario.getFlgEstExt()) == 
				UsuarioFlgExt.ESTADO_POR_COMPLETAR.id()) {
			proceso.setMensaje(
					Mensajes.get(Mensajes.Adv.USU_FALTA_COMPLET_REGISTRO),
					MessageType.ERROR_MESSAGE);
			log.info("Falta completar el registro de usuario");
			return false;
		}

		return true;
	}



	@SovioProcess
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean registrarUsuario(String codUsu, String desUsu, String codPais, 
			String codTDocIde, String correoe, String ip) throws Exception {

		log.info("iniciando validaciones de campos obligatorios");
		Map<String, Object> resultado = 
			Commons.validaCamposObligatorios(Arrays.asList(
				new ValorObligatorio(codUsu, "codUsu", "Nro de Documento"),
				new ValorObligatorio(desUsu, "desUsu", "Usuario"),
				new ValorObligatorio(codPais, "codPais", "Pais"),
				new ValorObligatorio(codTDocIde, "codTDocIde", "Tipo de Doc."),
				new ValorObligatorio(correoe, "correoe", "Email")
				));
		if(!resultado.isEmpty()) {
			getResultados().putAll(resultado);
			setMensaje(Mensajes.get(Mensajes.Err.OBLIGATORIO), 
					MessageType.ERROR_MESSAGE);
			return false;
		}

		log.info("validando consistencia de los datos");
		if(!StringLib.isNumeric(codUsu))
			setMensaje( 
					Mensajes.get(Mensajes.Err.NUMERICO, "Nro. de Documento"),
					MessageType.ERROR_MESSAGE);
		if(!Validar.formatoCorreo(correoe))
			setMensaje(
					Mensajes.get(Mensajes.Err.FORMATO, "un email", "o"), 
					MessageType.ERROR_MESSAGE);
		if(datosAcceso.existeCodUsuario(codUsu))
			setMensaje(
					Mensajes.get(Mensajes.Err.FUE_REGISTRADO, "Nro. de Doc."),
					MessageType.ERROR_MESSAGE);
		if(datosAcceso.existeDesUsu(desUsu))
			setMensaje(
					Mensajes.get(Mensajes.Err.FUE_REGISTRADO, "usuario"),
					MessageType.ERROR_MESSAGE);
		if(datosAcceso.existeCorreoRegistradoParaExt(correoe))
			setMensaje(
					Mensajes.get(Mensajes.Err.FUE_REGISTRADO, "correo"),
					MessageType.ERROR_MESSAGE);
		if(getTipoMensaje() == MessageType.ERROR_MESSAGE)
			return false;
		
		
		log.info("generando codigo de activacion para nuevo usuario");
		String codValidacion = null;
		try {
			do {		
				codValidacion = StringLib.getMD5( StringLib.generatePassword(10) );
			} while( datosAcceso.existeCodActivacion(codValidacion) );
		} catch(Exception ex) {
			log.error("No se pudo generar el codigo de activacion");
			log.error(StringLib.getExceptionStackTrace(ex));
			setMensaje(Mensajes.get(Mensajes.Err.GRAVE), 
					MessageType.ERROR_MESSAGE);
			return false;
		}	
		
		log.info("generando contraseña nueva");
		Date hoy = Calendar.getInstance().getTime();
		String password = StringLib.generatePassword(6);
		String passwordEncoded = null;
		try {
			passwordEncoded = StringLib.encodeLabel(password);
		} catch (Exception ex) {
			log.error("Error generando sus accesos, intentelo nuevamente.");
			log.error(StringLib.getExceptionStackTrace(ex));
			setMensaje(Mensajes.get(Mensajes.Err.GRAVE), 
					MessageType.ERROR_MESSAGE);
			return false;
		}
		
		log.info("registrando nuevo usuario");
		SITBPais pais = new SITBPais();
		pais.setCodPais(codPais);
		
		SITBPersonaExt personaExt = new SITBPersonaExt();
		personaExt.setCodPerExt(codUsu);
		personaExt.setPais(pais);
		personaExt.setDesApePat("temporal");
		personaExt.setDesApeMat("temporal");
		personaExt.setDesNombres("temporal");
		personaExt.setCodUsuReg(codUsu);
		personaExt.setCorreoe(correoe);
		personaExt.setCodTDocIde(codTDocIde);
		personaExt.setFecReg(hoy);
		if(ip!=null) personaExt.setHostReg(ip);
		
		SITBUsuario usuario = new SITBUsuario();
		usuario.setCodUsu(codUsu);
		usuario.setCodValidacion(codValidacion);
		usuario.setCodUsuReg(codUsu);
		usuario.setFecReg(hoy);
		if(ip!=null) usuario.setHostReg(ip);
		usuario.setPassUsu(passwordEncoded);
		usuario.setPersonaExt(personaExt);
		usuario.setFlgEstExt( 
				String.valueOf(UsuarioFlgExt.ESTADO_POR_VALIDAR.id()) );
		usuario.setNombreImagen(Constantes.IMAGEN_DEFAULT);

		try {
			datosAcceso.nuevoUsuario(usuario);
		} catch (Exception ex) {
			log.error("Error durante el registro de usuario");
			log.error(StringLib.getExceptionStackTrace(ex));
			setMensaje(Mensajes.get(Mensajes.Err.GRAVE), 
					MessageType.ERROR_MESSAGE);
			return false;
		}
		
		log.info("Enviando correo electronico");
		try { 
			enviarCorreoCreacionCuenta(usuario, personaExt.getCorreoe());
		} catch (Exception ex) {
			log.error("Error enviando correo electronico");
			log.error(StringLib.getExceptionStackTrace(ex));
			setMensaje(Mensajes.get(Mensajes.Err.GRAVE), 
					MessageType.ERROR_MESSAGE);
			throw ex;
		}
		
		setMensaje(Mensajes.get(Mensajes.Sat.REGISTRO),
				MessageType.INFORMATION_MESSAGE);
		log.info("Usuario registrado satisfactoriamente");
		return true;
	}

	
	private void enviarCorreoCreacionCuenta(SITBUsuario usuario, String correo) throws Exception {
		log.info("enviar Correo");
		sendMail.setFrom( sendMail.getUsername() );
		sendMail.setSubject("Te has creado una cuenta en el Portal del "
				+ "Servicio de Orientación Vocacional e Información "
				+ "Ocupacional (SOVIO) del Ministerio de Trabajo y Promoción "
				+ "del Empleo (MTPE)");
		sendMail.setTextHtml(true);
		
		sendMail.setText(Constantes.EMAIL_PAQUETE + 
				"mailConfirmacionCorreo.html", "utf-8");
		Map<String, String> valueMap = new HashMap<String, String>();
		valueMap.put("usuario", usuario.getDesUsu());
		valueMap.put("clave", usuario.getPassUsu());
		valueMap.put("ruta", Constantes.RegistroUsuario.RUTA_VALIDA_CORREO + 
				usuario.getCodValidacion());
		valueMap.put("rutaFVSOVIO", Constantes.RegistroUsuario.RUTA_FVSOVIO);
		sendMail.replaceExpressionForValue(valueMap);
		
		String[] to = {correo};
		sendMail.setTo(to);
		sendMail.sendMail();
	}
	
	

	public void setLogueo(Acceso logueo) {
		this.datosAcceso = logueo;
	}
	
	
}

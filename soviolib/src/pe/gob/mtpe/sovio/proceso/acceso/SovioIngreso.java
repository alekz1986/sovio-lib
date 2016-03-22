package pe.gob.mtpe.sovio.proceso.acceso;

import java.io.IOException;
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
import pe.gob.mtpe.sovio.util.Constantes;
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
	 * Metodo que obtiene el usuario por codigo de usuario y clave
	 * @param desUsu usuario que usa para ingresar al sistema
	 * @param passUsu Clave del usuario sin encriptar
	 * @return El usuario que da como resultado el codigo de usuario y 
	 * contrase�a ingresada.
	 * @throws Exception 
	 */
	@SovioProcess
	public SITBUsuario ingresar(String codUsu, String passUs) {
		log.debug("La clave sera encriptada para su validacion correspondiente");
		return ingresar(codUsu, passUs, false);
	}

	
	/**
	 * Metodo que obtiene el usuario por codigo de usuario y clave
	 * @param desUsu usuario que usa para ingresar al sistema
	 * @param passUsu Clave del usuario, puede ser encriptada o sin encriptar
	 * @param esEncriptado Determina si la clave es encriptada o no. Si no lo 
	 *  es, se realizar� la encriptacion durante el proceso. 
	 * @return Retorna el usuario que da como resultado el codigo de usuario y 
	 * contrase�a ingresada.
	 */
	@SovioProcess
	public SITBUsuario ingresar(String desUsu, String passUsu, boolean esEncriptado) {
		try {
			passUsu = !esEncriptado ? StringLib.encodeLabel(passUsu) : passUsu;
		} catch (Exception ex) {
			log.error("Error durante la encriptacion de la contrase�a");
			log.error(StringLib.getExceptionStackTrace(ex));
			setMensaje("usuario y/o contrase�a incorrectos", 
					MessageType.ERROR_MESSAGE);
			setException(ex);
			return null;
		}

		SITBUsuario usuario = datosAcceso.getUsuarioExterno(desUsu, passUsu);
		if(usuario==null) {
			setMensaje("Usuario y/o contrase�a incorrectos", MessageType.ERROR_MESSAGE);
			log.debug("Usuario y/o contrase�a incorrectos");
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
	private boolean tieneRegistroCompleto(ProcessResponse proceso, SITBUsuario usuario) {
		if(StringLib.toInt(usuario.getFlgEstExt()) == 
				UsuarioFlgExt.ESTADO_DESACTIVO.id()) {
			proceso.setMensaje("Usuario desactivado", MessageType.ERROR_MESSAGE);
			log.debug("Usuario desactivado");
			return false;
		}
		if(StringLib.toInt(usuario.getFlgEstExt()) == 
				UsuarioFlgExt.ESTADO_POR_VALIDAR.id()) {
			proceso.setMensaje("Falta validar el correo electr�nico", MessageType.ERROR_MESSAGE);
			log.debug("Falta validar el correo electr�nico");
			return false;
		}
		if(StringLib.toInt(usuario.getFlgEstExt()) == 
				UsuarioFlgExt.ESTADO_POR_COMPLETAR.id()) {
			proceso.setMensaje("Falta validar el correo electr�nico", MessageType.ERROR_MESSAGE);
			log.debug("Falta validar el correo electr�nico");
			return false;
		}
		return true;
	}
	

	@SovioProcess
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean registrarUsuario(String codUsu, String desUsu, String codPais, 
			String codTDocIde, String correoe, String ip) {

		Commons.validaCamposObligatorios(this, codUsu, "Nro de Documento", "codUsu");
		Commons.validaCamposObligatorios(this, desUsu, "Usuario", "desUsu");
		Commons.validaCamposObligatorios(this, codPais, "Pais", "codPais");
		Commons.validaCamposObligatorios(this, codTDocIde, "Tipo de Documento", "codTDocIde");
		Commons.validaCamposObligatorios(this, correoe, "Correo Electronico", "correoe");
		if(getTipoMensaje() == MessageType.ERROR_MESSAGE) {
			setMensaje("Debe Ingresar los campos obligatorios", MessageType.ERROR_MESSAGE);
			return false;
		}
		
		log.debug("Validando si el codigo de usuario es numerico");
		if(!StringLib.isNumeric(codUsu)) {
			setMensaje("El Nro de Documento debe ser numerico", MessageType.ERROR_MESSAGE);
			return false;
		}
		log.debug("Validando formato del correo electronico");
		if(!Validar.formatoCorreo(correoe)) {
			setMensaje("Ingrese un correo electronico correcto", 
					MessageType.ERROR_MESSAGE);
			return false;
		}
		log.debug("validando si existe el documento ingresado (codigo de usuario)");
		if(datosAcceso.existeCodUsuario(codUsu)) {
			setMensaje("El Nro de Documento ya fue registrado, ingrese otro.",
					MessageType.ERROR_MESSAGE);
			return false;
		}
		log.debug("validando si existe el usuario ingresado");
		if(datosAcceso.existeDesUsu(desUsu)) {
			setMensaje("El usuario ya fue registrado, ingrese otro", 
					MessageType.ERROR_MESSAGE);
			return false;
		}
		log.debug("validando si existe el correo registrado");
		if(datosAcceso.existeCorreoRegistradoParaExt(correoe)) {
			setMensaje("El correo ya se encuentra registrado", 
					MessageType.ERROR_MESSAGE);
			return false;
		}
		
		
		String codValidacion = null;
		try {
			do {		
				codValidacion = StringLib.getMD5( StringLib.generatePassword(10) );
			} while( datosAcceso.existeCodActivacion(codValidacion) );
		} catch(Exception ex) {
			log.error(StringLib.getExceptionStackTrace(ex));
			setMensaje("No se pudo generar su codigo de validaci�n, intente registrarse"
					+ " nuevamente", MessageType.ERROR_MESSAGE);
			setException(ex);
			return false;
		}	
		
		
		Date hoy = Calendar.getInstance().getTime();
		String password = StringLib.generatePassword(6);
		String passwordEncoded = null;
		try {
			passwordEncoded = StringLib.encodeLabel(password);
		} catch (Exception ex) {
			log.error(StringLib.getExceptionStackTrace(ex));
			setMensaje("Error generando sus accesos, intentelo nuevamente.", 
					MessageType.ERROR_MESSAGE);
			setException(ex);
			return false;
		}
			
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
		usuario.setFlgEstExt( String.valueOf(UsuarioFlgExt.ESTADO_POR_VALIDAR.id()) );
		usuario.setNombreImagen(Constantes.IMAGEN_DEFAULT);

		try {
			log.debug("Inicia registro de usuario");
			datosAcceso.nuevoUsuario(usuario);
		} catch (Exception ex) {
			log.error(StringLib.getExceptionStackTrace(ex));
			setMensaje("Ocurrio un error durante el registro de su usuario. "
					+ "Intentelo nuevamente", MessageType.ERROR_MESSAGE);
			setException(ex);
			return false;
		}
		
		try { 
			enviarCorreoCreacionCuenta(usuario, personaExt.getCorreoe());
		} catch (Exception ex) {
			log.error(StringLib.getExceptionStackTrace(ex));
			setMensaje("No se pudo enviar", MessageType.ERROR_MESSAGE);
			setException(ex);
			return false;
		}
		
		log.info("Usuario registrado satisfactoriamente");
		return true;
	}

	
	private void enviarCorreoCreacionCuenta(SITBUsuario usuario, String correo) throws Exception {
		log.info("enviar Correo");
		sendMail.setFrom( sendMail.getUsername() );
		sendMail.setSubject("Te has creado una cuenta en el Portal del "
				+ "Servicio de Orientaci�n Vocacional e Informaci�n "
				+ "Ocupacional (SOVIO) del Ministerio de Trabajo y Promoci�n "
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
	
	
	@SovioProcess
	@Transactional
	public void procesoDePrueba() {
		System.out.println("proceso de prueba");
	}
	

	public void setLogueo(Acceso logueo) {
		this.datosAcceso = logueo;
	}
	
	
}

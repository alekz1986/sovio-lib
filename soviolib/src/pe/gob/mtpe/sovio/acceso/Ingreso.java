package pe.gob.mtpe.sovio.acceso;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pe.gob.mtpe.sovio.bean.simintra1.SITBUsuario;
import pe.gob.mtpe.sovio.bean.tramite.PRTBCPersonal;
import pe.gob.mtpe.sovio.datos.Logueo;
import pe.gob.mtpe.sovio.util.StringLib;
import pe.gob.mtpe.sovio.util.log.InjectLogger;
import pe.gob.mtpe.sovio.util.process.ProcessResponse;
import pe.gob.mtpe.sovio.util.process.SovioProcess;

@Component
public class Ingreso extends ProcessResponse {

	
	@Autowired
	private Logueo logueo;
	
	@InjectLogger
	private Logger log;

	
	/**
	 * Metodo que obtiene el usuario por codigo de usuario y clave
	 * @param codUsu Codigo del usuario
	 * @param passUsu Clave del usuario sin encriptar
	 * @return El usuario.
	 * @throws Exception 
	 */
	@SovioProcess
	public SITBUsuario ingresar(String codUsu, String passUs) {
		log.debug("La clave sera encriptada para su validacion correspondiente");
		return ingresar(codUsu, passUs, false);
	}

	
	/**
	 * Metodo que obtiene el usuario por codigo de usuario y clave
	 * @param codUsu Codigo del usuario
	 * @param passUsu Clave del usuario, puede ser encriptada o sin encriptar
	 * @param esEncriptado Determina si la clave es encriptada o no. Si no lo 
	 *  es, se realizará la encriptacion durante el proceso. 
	 * @return
	 * @throws Exception 
	 */
	@SovioProcess
	public SITBUsuario ingresar(String codUsu, String passUsu, boolean esEncriptado) {
		try {
			passUsu = !esEncriptado ? StringLib.encodeLabel(passUsu) : passUsu;
		} catch (Exception ex) {
			log.error("Error durante la encriptacion de la contraseña");
			setMensaje("Error durante la encriptación de la contraseña");
			log.error(StringLib.getExceptionStackTrace(ex));
			return null;
		}
		
		log.debug("contraseña encriptada: " + passUsu);
		SITBUsuario usuario = logueo.obtenerUsuarioExterno(codUsu, passUsu);
		if(usuario != null) {
			return usuario;
		}
		setMensaje("");
		return null;
	}


	public void nuevoUsuario() {
		logueo.nuevoUsuario();
	}
	


	public void setLogueo(Logueo logueo) {
		this.logueo = logueo;
	}
	
	
}

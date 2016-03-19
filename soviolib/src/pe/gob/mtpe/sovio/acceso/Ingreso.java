package pe.gob.mtpe.sovio.acceso;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import antlr.StringUtils;
import pe.gob.mtpe.sovio.bean.simintra1.SITBUsuario;
import pe.gob.mtpe.sovio.datos.Logueo;
import pe.gob.mtpe.sovio.util.StringLib;
import pe.gob.mtpe.sovio.util.log.InjectLogger;
import pe.gob.mtpe.sovio.util.process.ProcessResponse;

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
	 */
	//@SovioProcess
	public SITBUsuario ingresar(String codUsu, String passUs) {
		return ingresar(codUsu, passUs, false);
	}
	
	
	public SITBUsuario ingresar(String codUsu, String passUsu, boolean esEncriptado) {
		SITBUsuario usuario = null;
		//mensaje
		try {
			passUsu = !esEncriptado ? StringLib.encodeLabel(passUsu) : passUsu;
			usuario = logueo.obtenerUsuarioExterno(codUsu, passUsu);
			//logueo.obtenerUsuarioAdministrador("AS");
			
			if(usuario!=null) {
				System.out.println("Usuario encontrado");
				System.out.println(usuario.getCodUsu());
				System.out.println(usuario.getPersonal().getDesApePat());
				System.out.println(usuario.getPersonal().getDesApeMat());
				System.out.println(usuario.getPersonal().getDesNombres());
			} else {
				System.out.println("No se encontro el usuario");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuario;
	}


	public void nuevoUsuario() {
		logueo.nuevoUsuario();
	}
	


	public void setLogueo(Logueo logueo) {
		this.logueo = logueo;
	}
	
	
}

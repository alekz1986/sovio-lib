package pe.gob.mtpe.sovio.acceso;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.gob.mtpe.sovio.bean.simintra1.SITBUsuario;
import pe.gob.mtpe.sovio.datos.Logueo;
import pe.gob.mtpe.sovio.util.process.ProcessResponse;

@Component
public class Ingreso extends ProcessResponse {

	
	@Autowired
	private Logueo logueo;
	

	
	public SITBUsuario ingresar(String codusu, String clave) {
		return ingresar(codusu, clave, false);
	}
	
	public SITBUsuario ingresar(String codusu, String clave, boolean esEncriptado) {
		//SITBUsuario usuario = logueo.obtenerUsuario(codusu);
		
		SITBUsuario usuario = logueo.obtenerUsuario();
		
		if(usuario!=null) {
			System.out.println("Usuario encontrado");
			System.out.println(usuario.getCodUsu());
			System.out.println(usuario.getPersonal().getDesApePat());
			System.out.println(usuario.getPersonal().getDesApeMat());
			System.out.println(usuario.getPersonal().getDesNombres());
		} else {
			System.out.println("No se encontro el usuario");
		}
		return null;
	}


	public void nuevoUsuario() {
		logueo.nuevoUsuario();
	}
	


	public void setLogueo(Logueo logueo) {
		this.logueo = logueo;
	}
	
	
}

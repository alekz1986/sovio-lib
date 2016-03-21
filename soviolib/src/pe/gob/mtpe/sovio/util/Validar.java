package pe.gob.mtpe.sovio.util;

public class Validar {

	
	public static boolean formatoCorreo(String email) {
		String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		return email.matches(regex);
	} 
	
}

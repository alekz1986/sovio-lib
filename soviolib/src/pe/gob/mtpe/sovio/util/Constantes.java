package pe.gob.mtpe.sovio.util;

public class Constantes {

	public static final String IMAGEN_DEFAULT = "default.jpg";
	public static final String EMAIL_PAQUETE = "/";
	public static long nanos = 0;
	public static long milisegundos = 0;
	
	public class Pais {
		public static final String PERU = "036";
	}
	
	public class TipoDocumento {
		public static final String CODIGO_DNI = "03";		
	}
	
	public class RegistroUsuario {
		public static final String RUTA_VALIDA_CORREO = 
				"http://portalregistro.trabajo.gob.pe/sovio/validarCorreo?entityId=";
		
		public static final String RUTA_FVSOVIO = 
				"http://portalregistro.trabajo.gob.pe/sovio/forovida";
		public static final String RUTA_TESOVIO = 
				"http://portalregistro.trabajo.gob.pe/sovio/testelige";
	}
	
}

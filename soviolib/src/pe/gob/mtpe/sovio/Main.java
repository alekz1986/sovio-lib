package pe.gob.mtpe.sovio;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.gob.mtpe.sovio.acceso.Ingreso;

public class Main {

	
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/beans.xml");
		Ingreso ingreso = context.getBean(Ingreso.class);
		
		//ingreso.ingresar("JROJASV", "clave");
		ingreso.nuevoUsuario();
		
		System.out.println("test");

	}
	


}

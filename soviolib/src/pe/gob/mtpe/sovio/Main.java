package pe.gob.mtpe.sovio;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.gob.mtpe.sovio.acceso.Ingreso;
import pe.gob.mtpe.sovio.util.process.SovioProcessFactory;

public class Main {

	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/beans.xml");
		
		Ingreso ingreso = SovioProcessFactory.get(Ingreso.class);
		ingreso.ingresar("40411112", "123456");
		
		((AbstractApplicationContext) context).close();
	}
	


}

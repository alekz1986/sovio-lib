package pe.gob.mtpe.sovio;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.gob.mtpe.sovio.bean.simintra1.SITBPais;
import pe.gob.mtpe.sovio.bean.simintra1.SITBTDocIde;
import pe.gob.mtpe.sovio.datos.Acceso;
import pe.gob.mtpe.sovio.proceso.Commons;
import pe.gob.mtpe.sovio.proceso.acceso.SovioIngreso;
import pe.gob.mtpe.sovio.proceso.acceso.SovioGeneral;
import pe.gob.mtpe.sovio.util.Constantes;
import pe.gob.mtpe.sovio.util.StringLib;
import pe.gob.mtpe.sovio.util.Validar;
import pe.gob.mtpe.sovio.util.process.ProcessState;
import pe.gob.mtpe.sovio.util.process.SovioProcessFactory;

public class Main {

	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/beans.xml");
		//Ingreso ingreso = SovioProcessFactory.get(Ingreso.class);
		//ingreso.ingresar("40609014", "123456");

		SovioGeneral sovioGeneral = SovioProcessFactory.get(SovioGeneral.class);
		List<SITBTDocIde> lista = sovioGeneral.getTipoDocumentosParaRegUsuario( "84" );	
		
		System.out.println(lista.size());
		for(SITBTDocIde tipoDocIde: lista) {
			System.out.print(tipoDocIde.getDesAbr());
			System.out.println(" | " + tipoDocIde.getDesTDocIde());
		}
		
		((AbstractApplicationContext) context).close();
		
	}
	


}

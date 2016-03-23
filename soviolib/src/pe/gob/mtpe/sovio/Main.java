package pe.gob.mtpe.sovio;

import java.util.List;

import javax.persistence.RollbackException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

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
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/conf/spring/beans.xml");
		
		//Ingreso ingreso = SovioProcessFactory.get(Ingreso.class);
		//ingreso.ingresar("40609014", "123456");

		/*
		SovioIngreso sovioIngreso = SovioProcessFactory.get(SovioIngreso.class);
		sovioIngreso.registrarUsuario("43872977", "MIUSUARIO", Constantes.Pais.PERU, 
				Constantes.TipoDocumento.CODIGO_DNI, "alekznder@gmail.com", null);
		
		System.out.println(sovioIngreso.getTipoMensaje());
		System.out.println(sovioIngreso.getMensajePrincipal());
		/*-*/

		
		
		//*
		System.out.println("nanos: " + Constantes.nanos);
		System.out.println("mili: " + Constantes.milisegundos);
		SovioIngreso sovioIngreso = context.getBean(SovioIngreso.class);
		try {
			sovioIngreso.registrarUsuario("43872977", "MIUSUARIO", Constantes.Pais.PERU, 
					Constantes.TipoDocumento.CODIGO_DNI, "alekznder@gmail.com", null);
		} catch(Exception ex) {
			System.out.println("=========");
			System.out.println(sovioIngreso.getEstado());
		}
		
		System.out.println("Acabo el proceso");
		System.out.println(sovioIngreso.getMensajePrincipal());
		
		
		
		/*
		SovioGeneral sovioGeneral = SovioProcessFactory.get(SovioGeneral.class);
		List<SITBTDocIde> lista = sovioGeneral.getTipoDocumentosParaRegUsuario( "84" );	
		
		System.out.println(lista.size());
		for(SITBTDocIde tipoDocIde: lista) {
			System.out.print(tipoDocIde.getDesAbr());
			System.out.println(" | " + tipoDocIde.getDesTDocIde());
		}
		*/
		
		((AbstractApplicationContext) context).close();
		
	}
	


}

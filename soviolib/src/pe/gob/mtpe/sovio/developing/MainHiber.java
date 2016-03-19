package pe.gob.mtpe.sovio.developing;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.gob.mtpe.sovio.acceso.Ingreso;
import pe.gob.mtpe.sovio.bean.simintra1.SITBUsuario;
import pe.gob.mtpe.sovio.bean.tramite.PRTBCPersonal;
import pe.gob.mtpe.sovio.datos.Logueo;

public class MainHiber {


	private static SessionFactory factory;
	
	public static void main(String[] args) {
		//Logueo logueo = new Logueo();
		//logueo.obtenerUsuario("usuario");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/beans.xml");
		//Logueo logueo = context.getBean("logueo", Logueo.class);
		//logueo.obtenerUsuario("usuario");
		Ingreso ingreso = context.getBean(Ingreso.class);
		ingreso.ingresar("JROJASV", "clave");
		
		
		
		
		//Ingreso ingreso = new Ingreso();
		//ingreso.ingresar("usuario", "clave");
		
		//Logueo logueo = new Logueo();
		//logueo.obtenerUsuario("usuario");
		
		//SITBUsuario usuario = new SITBUsuario();
		//usuario.obtenerUsuario();
		
		/*
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Exception ex) {
			System.err.println("Exception Error");
			ex.printStackTrace();
		}
		
		try {
			Main main = new Main();
			main.listEmployees();
		} finally {
			factory.close();
		}
		*/
	}
	
	
	public void listEmployees( ){
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			//List usuarios = session.createQuery("FROM SITB_USUARIO").list();
			//List<PRTBCPersonal> lista = session.createQuery("FROM PRTBC_PERSONAL").list();
			
			//List<SITBUsuario> lista = session.createCriteria(SITBUsuario.class).list();
			//List<SITBUsuario> lista = session.createCriteria(PRTBCPersonal.class).list();
			
			/*
			Criteria cr = session.createCriteria(SITBUsuario.class)
					.setProjection(Projections.projectionList()
						.add(Projections.property("codUsu").as("codUsu"))
						.add(Projections.property("codUsuMod").as("codUsuMod"))
						.add(Projections.property("prtbcPersonal").as("prtbcPersonal")))
					.setResultTransformer(Transformers.aliasToBean(SITBUsuario.class));
			/*-*/
			
			
			//*
			Criteria cr = session.createCriteria(SITBUsuario.class);
			//cr.createCriteria("prtbcPersonal", "per", CriteriaSpecification.INNER_JOIN);
			//cr.createAlias("prtbcPersonal", "per");
			cr.setFetchMode("prtbcPersonal.codPersonal", FetchMode.JOIN);
			cr.setProjection(Projections.projectionList()
					.add(Projections.property("codUsu").as("codUsu"))
					.add(Projections.property("codUsuMod").as("codUsuMod"))
					.add(Projections.property("per.desApePat"))
					.add(Projections.property("per.desApeMat"))
					.add(Projections.property("per.desNombres")))
				.setResultTransformer(Transformers.aliasToBean(SITBUsuario.class));
			
			List<SITBUsuario> lista = cr.list();
			/*-*/
			
			/*
			List<SITBUsuario> lista = session.createQuery(
					"FROM SITB_USUARIO usu, PRTBC_PERSONAL per " + 
					"WHERE usu.prtbcPersonal=per.codPersonal").list();
			*/
			
			
			
			//Hibernate 5.1
			
			
			
			
			System.out.println("count: " + lista.size());
			System.out.println("modificado1");
			for (Iterator iterator = lista.iterator(); iterator.hasNext();){
				//*
				Object obj = iterator.next();
				//SITBUsuario sitbUsuario = (SITBUsuario) iterator.next();
				SITBUsuario sitbUsuario = (SITBUsuario) obj;
				System.out.print("getCodUsu: " + sitbUsuario.getCodUsu());
				System.out.print(" | getCodUsuMod: " + sitbUsuario.getCodUsuMod());
 				//System.out.print(" | getCodPersonal: " + sitbUsuario.getPrtbcPersonal().getCodPersonal()); //System.out.print("| getDesApePat: " + sitbUsuario.getPrtbcPersonal().getDesApePat()); //System.out.print("| getDesApeMat: " + sitbUsuario.getPrtbcPersonal().getDesApeMat()); ,  
 				//System.out.print(" | getDesNombres: " + sitbUsuario.getPrtbcPersonal().getDesNombres());
 				//System.out.print(" | getDesApePat: " + sitbUsuario.getPrtbcPersonal().getDesApePat());
				//System.out.print(" | getDesApeMat: " + sitbUsuario.getPrtbcPersonal().getDesApeMat());
				//System.out.print(" | getDesNombres: " + sitbUsuario.getPrtbcPersonal().getDesNombres());
				System.out.println("\n");
				/*-*/
				
				/*
				PRTBCPersonal personal = (PRTBCPersonal) iterator.next();
				System.out.print("getDesApePat: " + personal.getDesApePat() + " | ");
				System.out.print("getDesApeMat: " + personal.getDesApeMat() + " | ");
				System.out.print("getDesApeMat: " + personal.getDesNombres() + "\n");
				/*-*/
	         }
	         tx.commit();
	      } catch (HibernateException e) {
	    	  if (tx!=null) tx.rollback();
	    	  e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	}
	
	
}

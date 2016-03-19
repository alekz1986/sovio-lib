package pe.gob.mtpe.sovio.developing;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
//import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import org.hibernate.sql.JoinType;

import pe.gob.mtpe.sovio.bean.simintra1.SITBUsuario;
import pe.gob.mtpe.sovio.bean.tramite.PRTBCPersonal;



public class MainJPA {

	//private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("SITBUsuario");
	//@PersistenceContext(name="org.hibernate.simintra1.jpa")
	private static EntityManagerFactory factory; 
	
	public static void main(String[] args) {
		try {
			factory = Persistence.createEntityManagerFactory("org.hibernate.simintra1.jpa");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		MainJPA main = new MainJPA();
		//main.newUsuario();
		//main.updateUsuario();
		main.list();
		//main.listInnerJoin();
		
	}
	
	
	public void updateUsuario() {
		try {
			EntityManager em = factory.createEntityManager();
			
			//SITBUsuario usuario = em.find(SITBUsuario.class, "TEST1");
			SITBUsuario usuario = new SITBUsuario();
			usuario.setCodUsu("TEST1");
			usuario.setPassUsu("otraclave3");
			
			PRTBCPersonal personal = new PRTBCPersonal();
			personal.setCodPersonal("43872977");
			personal.setDesApePat("Chilque");
			personal.setDesApeMat("Alejos");
			personal.setDesNombres("Alex");
			usuario.setPersonal(personal);
			
			em.getTransaction().begin();
			em.persist(personal);
			em.merge(usuario);
			em.getTransaction().commit();
		} finally {
			factory.close();
		}
	}
	
	
	/*
	public void listInnerJoin() {
		try {
			EntityManager em = factory.createEntityManager();
			em.getTransaction().begin();
			
			//EntityType<SITBUsuario>  em.getMetamodel().entity(SITBUsuario.class);
			
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<SITBUsuario> query = builder.createQuery(SITBUsuario.class);
			Root<SITBUsuario> root = query.from(SITBUsuario.class);
			//query.select(root);
			Join<SITBUsuario, PRTBCPersonal> join = root.join("prtbcPersonal");
			//query.where( builder.isNotEmpty(root.get("prtbcPersonal")) );
			
			List<SITBUsuario> lista = em.createQuery(query).getResultList();
			
			
			//Join<SITBUsuario, PRTBCPersonal> personalJoin = root.join(SITBUsuario_.prtbcPersonal, JoinType.INNER_JOIN);

			
			//TypedQuery<SITBUsuario> typedQuery = em.createQuery(query);
			//List<SITBUsuario> lista = typedQuery.getResultList();
			//Join<PRTBCPersonal, SITBUsuario> personalJoin = root.join()
			
			
			System.out.println("lista.size: " + lista.size());
			Iterator ite = lista.iterator();
			while(ite.hasNext()) {
				Object obj = ite.next();
				
				SITBUsuario usuario = (SITBUsuario) obj;
				System.out.print("codusu: " + usuario.getCodUsu());
				
				
				PRTBCPersonal personal = usuario.getPrtbcPersonal();
				System.out.print("| apepat: " + (personal!=null?personal.getDesApePat():"") );
				System.out.println("");
				//System.out.println(obj.toString());
			}
			
			em.getTransaction().commit();
			em.close();
			System.out.println("close");
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			factory.close();
		}
	}
	*/
	
	
	public void newUsuario() {
		try {
			EntityManager em = factory.createEntityManager();
			em.getTransaction().begin();
			
			SITBUsuario usuario = new SITBUsuario();
			usuario.setCodUsu("TEST1");
			usuario.setPassUsu("claveencriptada");
			
			em.persist(usuario);
			em.getTransaction().commit();
			
		} finally {
			factory.close();			
		}
	} 
	
	
	public void list() {
		try {
			EntityManager em = factory.createEntityManager();
			em.getTransaction().begin();
				
			/*
			TypedQuery<SITBUsuario> lquery = em.createQuery(""
					+ ""
					+ "FROM SITB_USUARIO usu, PRTBC_PERSONAL per "
					+ "WHERE usu.prtbcPersonal=per.codPersonal", SITBUsuario.class);
			/*-*/
			
			//TypedQuery<SITBUsuario> lquery = em.createQuery("FROM SITB_USUARIO", SITBUsuario.class);
			//List<SITBUsuario> lista = lquery.getResultList();
			
			/*
			List lista = (List) em.createQuery("FROM SITB_USUARIO usu, PRTBC_PERSONAL per "
					+ "WHERE usu.prtbcPersonal=per.codPersonal").getResultList();
			*/

			//System.out.println("_ : " + PRTBCPersonal_.codEscala);
			
			List lista = (List) em.createQuery(
						"FROM SITB_USUARIO usu "
						+ "	INNER JOIN PRTBC_PERSONAL per ON per.codPersonal = usu.personal "
						+ "	LEFT JOIN SITB_PERCARGO car ON car.codCargo = per.perCargo "
						+ "	INNER JOIN SITB_ESCALAREMUN rem ON rem.codEscala = per.escalaRemun "
						+ "	INNER JOIN PRTBC_TIPOPERS tper ON tper.codTipo = per.tipoPers "
						+ "	INNER JOIN TDTBC_DEPENDENCIA dep ON dep.numDep = per.dependencia "
						+ "	INNER JOIN SITB_ZONAL zon ON "
						+ "			zon.key.codReg = dep.zonal.key.codReg AND"
						+ "			zon.key.codZon = dep.zonal.key.codZon "
						+ "	INNER JOIN SITB_REGIONAL reg ON reg.codReg = zon.key.codReg"
					).getResultList();


			System.out.println("lista.size: " + lista.size());
			Iterator ite = lista.iterator();
			while(ite.hasNext()) {
				Object obj = ite.next();
				System.out.print(obj.toString());
				System.out.println("");
			}
			
			em.getTransaction().commit();
			em.close();
			System.out.println("close");
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			factory.close();
		}
	}
	
}

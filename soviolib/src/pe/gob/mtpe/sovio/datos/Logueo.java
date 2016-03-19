package pe.gob.mtpe.sovio.datos;


import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mtpe.sovio.bean.simintra1.SITBUsuario;
import pe.gob.mtpe.sovio.util.log.InjectLogger;


public class Logueo {
	
	
	@InjectLogger
	private Logger logger;

	@PersistenceContext
	private EntityManager entityManager;
	
	
	//@Transactional(propagation = Propagation.REQUIRED)
	public SITBUsuario obtenerUsuarioAdministrador(String codusu) {
		SITBUsuario usuario = null;
		List lista = (List) entityManager.createQuery(
				"FROM SITB_USUARIO usu "
				+ "	INNER JOIN PRTBC_PERSONAL per ON per.codPersonal = usu.personal "
				+ "	LEFT JOIN SITB_PERCARGO car ON car.codCargo = per.perCargo "
				+ "	INNER JOIN SITB_ESCALAREMUN rem ON rem.codEscala = per.escalaRemun "
				+ "	INNER JOIN PRTBC_TIPOPERS tper ON tper.codTipo = per.tipoPers "
				+ "	INNER JOIN TDTBC_DEPENDENCIA dep ON dep.numDep = per.dependencia "
				+ "	INNER JOIN SITB_ZONAL zon ON "
				+ "			zon.key.codReg = dep.zonal.key.codReg AND"
				+ "			zon.key.codZon = dep.zonal.key.codZon "
				+ "	INNER JOIN SITB_REGIONAL reg ON reg.codReg = zon.key.codReg "
				+ "WHERE usu.codUsu=:codusu"
			)
			.setParameter("codusu", codusu)
			.getResultList();
		Iterator ite = lista.iterator();
		while(ite.hasNext()) {
			Object[] obj = (Object[])ite.next();
			System.out.print(obj.toString());
			usuario = (SITBUsuario) obj[0];
			
			break;
		}
		return usuario;
	}

	

	/*
	@Transactional
	public SITBUsuario obtenerUsuarioExterno(String codUsu, String passUsu) {
		Object[] obj = null;
		try {
			obj = (Object[])entityManager.createQuery(
					"FROM SITB_USUARIO usu "
					+ "	INNER JOIN SITB_PERSONAEXT ext ON ext.codPerExt=usu.personaExt "
					+ "WHERE usu.codUsu=:codUsu AND usu.passUsu=:passUsu")
				.setParameter("codUsu", codUsu)
				.setParameter("passUsu", passUsu)
				.getSingleResult();
		} catch (NoResultException nrex) {
			logger.debug("Obtener Usuario Externo no genera resultados para el usuario: "
					+ codUsu);
		}
		return (obj != null) ? (SITBUsuario) obj[0] : null;
	} 
	/*-*/
	
	
	@Transactional
	public SITBUsuario obtenerUsuarioExterno(String codUsu, String passUsu) {
		Object[] obj = null;
		try {
			obj = (Object[]) entityManager.createQuery(
					"FROM SITB_USUARIO usu "
					+ "JOIN usu.personaExt pex "
					+ "WHERE usu.codUsu=:codUsu AND usu.passUsu=:passUsu")
				.setParameter("codUsu", codUsu)
				.setParameter("passUsu", passUsu)
				.getSingleResult();
		} catch (NoResultException nrex) {
			logger.debug("Obtener Usuario Externo no genera resultados para el usuario: "
					+ codUsu);
		}
		return (obj != null) ? (SITBUsuario) obj[0] : null;
	} 
	/*-*/
	
	

	public SITBUsuario insertarUsuario() {
		SITBUsuario usuario = null;
		List lista = (List) entityManager.createQuery(
				"FROM SITB_USUARIO usu "
				+ "	INNER JOIN PRTBC_PERSONAL per ON per.codPersonal = usu.personal "
			)
			.getResultList();
		Iterator ite = lista.iterator();
		while(ite.hasNext()) {
			Object[] obj = (Object[])ite.next();
			System.out.print(obj.toString());
			usuario = (SITBUsuario) obj[0];
			break;
		}
		return usuario;
	}
	
	
	@Transactional
	public void nuevoUsuario() {
		SITBUsuario usuario = new SITBUsuario();
		usuario.setCodUsu("TEST1");
		usuario.setPassUsu("miclave");
		entityManager.persist(usuario);
	}
	

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	
	
}

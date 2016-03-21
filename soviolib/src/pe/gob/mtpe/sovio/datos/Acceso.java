package pe.gob.mtpe.sovio.datos;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mtpe.sovio.bean.simintra1.SITBTDocIde;
import pe.gob.mtpe.sovio.bean.simintra1.SITBUsuario;
import pe.gob.mtpe.sovio.bean.simintra1.enums.UsuarioFlgExt;
import pe.gob.mtpe.sovio.util.log.InjectLogger;


public class Acceso {
	
	
	@InjectLogger
	private Logger log;

	@PersistenceContext
	private EntityManager entityManager;
	
	
	/* 
	//Hibernate 5
	//@Transactional(propagation = Propagation.REQUIRED)
	public SITBUsuario getUsuarioAdministrador(String codusu) {
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

	
	@Transactional
	public SITBUsuario getUsuarioExterno(String codUsu, String passUsu) {
		log.debug("Hibernate 5");
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
			log.debug("Obtener Usuario Externo no genera resultados para el usuario: "
					+ codUsu);
		}
		return (obj != null) ? (SITBUsuario) obj[0] : null;
	} 
	/*-*/


	//*@Transactional
	public SITBUsuario getUsuarioExterno(String desUsu, String passUsu) {
		Object[] obj = null;
		try {
			obj = (Object[]) entityManager.createQuery(
					" FROM SITB_USUARIO usu "
					+ "JOIN usu.personaExt pex "
					+ "WHERE usu.desUsu=:desUsu AND usu.passUsu=:passUsu")
				.setParameter("desUsu", desUsu)
				.setParameter("passUsu", passUsu)
				.getSingleResult();
		} catch (NoResultException nrex) {
			log.debug("Obtener Usuario Externo no genera resultados para el usuario: "
					+ desUsu);
		}
		return (obj != null) ? (SITBUsuario) obj[0] : null;
	} 
	
	
	
	public List<SITBTDocIde> getTipoDocParaRegistrarUsuario() {
		List<SITBTDocIde> tipoDocumentos = new ArrayList<SITBTDocIde>();
		try {
			tipoDocumentos = entityManager.createQuery(
					"FROM SITB_TDOCIDE tdoc "
					+ "WHERE tdoc.codTDocIde IN :list ")
				.setParameter("list", Arrays.asList("03","06","08"))
				.getResultList();
		} catch (NoResultException nrex) {
			log.debug("No se encontraron lista de documentos");
		}
		return tipoDocumentos;
	}
	
	
	public boolean existeCodUsuario(String codUsu) {
		int count = (int) entityManager.createQuery(
				"SELECT count(1) FROM SITB_USUARIO "
				+ "WHERE codUsu = :codUsu")
			.setParameter("codUsu", codUsu)
			.getSingleResult();
		return (count > 0);
	}
	
	public boolean existeDesUsu(String desUsu) {
		int count = (int) entityManager.createQuery(
				"SELECT count(1) FROM SITB_USUARIO "
				+ "WHERE desUsu = :desUsu")
			.setParameter("desUsu", desUsu)
			.getSingleResult();
		return (count > 0);
	}

	
	public boolean existeCorreoRegistradoParaExt(String correoe) {
		int count = (int) entityManager.createQuery(
				"SELECT count(1) FROM SITB_PERSONAEXT pex "
				+ "WHERE pex.correoe=:correoe")
			.setParameter("correoe", correoe)
			.getSingleResult();
		return (count > 0);
	}
	
	
	public boolean existeCodActivacion(String codValidacion) {
		int count = (int) entityManager.createQuery(
				"SELECT count(1) FROM SITB_USUARIO usu "
				+ "WHERE usu.codValidacion=:codValidacion")
			.setParameter("codValidacion", codValidacion)
			.getSingleResult();
		return (count > 0);
	}
	
	
	@Transactional
	public void nuevoUsuario(SITBUsuario usuario) {
		entityManager.persist(usuario);
	}
	

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	
	
}

package pe.gob.mtpe.sovio.datos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mtpe.sovio.bean.simintra1.SITBPais;
import pe.gob.mtpe.sovio.util.log.InjectLogger;
import pe.gob.mtpe.sovio.util.process.ProcessResponse;

public class General {

	@InjectLogger
	private Logger log;
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	public List<SITBPais> getPaises() {
		List<SITBPais> paises = new ArrayList<SITBPais>();
		try {
			paises = (List<SITBPais>) entityManager.createQuery(
					"FROM SITB_PAIS pa "
					+ "ORDER BY pa.desPais"
				)
				.getResultList();
		} catch (NoResultException ex) {
			log.debug("No se encontraron paises");
		}
		return paises;
	}

	

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
}

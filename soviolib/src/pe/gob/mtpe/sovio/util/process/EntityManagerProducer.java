package pe.gob.mtpe.sovio.util.process;

/*
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;*/
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


//@ApplicationScoped
public class EntityManagerProducer {

	/*
	@PersistenceContext(name="org.hibernate.simintra1.jpa")
    private EntityManager entityManager;

    @Produces
    @RequestScoped
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void closeEntityManager(@Disposes EntityManager em) {
        if (em != null && em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
    */
}

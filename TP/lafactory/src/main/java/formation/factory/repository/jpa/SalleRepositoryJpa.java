package formation.factory.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import formation.factory.FactorySingleton;
import formation.factory.exception.FactoryException;
import formation.factory.model.Salle;
import formation.factory.repository.ISalleRepository;

public class SalleRepositoryJpa implements ISalleRepository {

	@Override
	public List<Salle> findAll() {
		List<Salle> salles = new ArrayList<>();
		
		EntityManager em = null;
		try {
			em = FactorySingleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();

			TypedQuery<Salle> query = em.createQuery("from Salle", Salle.class);
			salles = query.getResultList();
			
			em.getTransaction().commit();
		} catch (Exception e) {
			if(em.getTransaction() != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			
			throw new FactoryException(e);
		} finally {
			if(em != null) { 
				em.close();
			}
		}

		return salles;
	}

	@Override
	public Salle findById(Long id) {
		Salle salle = null;
		
		EntityManager em = null;
		try {
			em = FactorySingleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();

			salle = em.find(Salle.class, id);
			
//			TypedQuery<Salle> query = em.createQuery("select s from Salle s where s.id = :id", Salle.class);
//			query.setParameter("id", id);
//			
//			salle = query.getSingleResult();
			
			em.getTransaction().commit();
		} catch (Exception e) {
			if(em.getTransaction() != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			
			throw new FactoryException(e);
		} finally {
			if(em != null) { 
				em.close();
			}
		}
		return salle;
	}

	@Override
	public void create(Salle obj) {
		EntityManager em = null;
		try {
			em = FactorySingleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();

			em.persist(obj);
			
			em.getTransaction().commit();
		} catch (Exception e) {
			if(em.getTransaction() != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			
			throw new FactoryException(e);
		} finally {
			if(em != null) { 
				em.close();
			}
		}
	}

	@Override
	public Salle update(Salle obj) {
		EntityManager em = null;
		try {
			em = FactorySingleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();

			obj = em.merge(obj);
			
			em.getTransaction().commit();
		} catch (Exception e) {
			if(em.getTransaction() != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			
			throw new FactoryException(e);
		} finally {
			if(em != null) { 
				em.close();
			}
		}
		
		return obj;
	}

	@Override
	public void deleteById(Long id) {
		EntityManager em = null;
		try {
			em = FactorySingleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();

//			Salle salle = em.find(Salle.class, id);
//			em.remove(salle);
			
			TypedQuery<Salle> query = em.createQuery("delete from Salle s where s.id = :id", Salle.class);
			query.setParameter("id", id);
			
			query.executeUpdate();
			
			em.getTransaction().commit();
		} catch (Exception e) {
			if(em.getTransaction() != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			
			throw new FactoryException(e);
		} finally {
			if(em != null) { 
				em.close();
			}
		}
	}

}

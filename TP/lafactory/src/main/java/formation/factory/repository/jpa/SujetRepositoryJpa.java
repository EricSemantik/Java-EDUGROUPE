package formation.factory.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import formation.factory.FactorySingleton;
import formation.factory.exception.FactoryException;
import formation.factory.model.Sujet;
import formation.factory.repository.ISujetRepository;

public class SujetRepositoryJpa implements ISujetRepository {

	@Override
	public List<Sujet> findAll() {
		List<Sujet> sujets = new ArrayList<>();
		
		EntityManager em = null;
		try {
			em = FactorySingleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();

			TypedQuery<Sujet> query = em.createQuery("from Sujet", Sujet.class);
			sujets = query.getResultList();
			
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

		return sujets;
	}

	@Override
	public Sujet findById(String id) {
		Sujet sujet = null;
		
		EntityManager em = null;
		try {
			em = FactorySingleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();

			sujet = em.find(Sujet.class, id);
			
//			TypedQuery<Sujet> query = em.createQuery("select s from Sujet s where s.id = :id", Sujet.class);
//			query.setParameter("id", id);
//			
//			sujet = query.getSingleResult();
			
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
		return sujet;
	}

	@Override
	public void create(Sujet obj) {
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
	public Sujet update(Sujet obj) {
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
	public void deleteById(String id) {
		EntityManager em = null;
		try {
			em = FactorySingleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();

//			Sujet sujet = em.find(Sujet.class, id);
//			em.remove(sujet);
			
			TypedQuery<Sujet> query = em.createQuery("delete from Sujet s where s.id = :id", Sujet.class);
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
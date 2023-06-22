package formation.factory.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import formation.factory.FactorySingleton;
import formation.factory.exception.FactoryException;
import formation.factory.model.Participation;
import formation.factory.repository.IParticipationRepository;

public class ParticipationRepositoryJpa implements IParticipationRepository {

	@Override
	public List<Participation> findAll() {
		List<Participation> participations = new ArrayList<>();
		
		EntityManager em = null;
		try {
			em = FactorySingleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();

			TypedQuery<Participation> query = em.createQuery("from Participation", Participation.class);
			participations = query.getResultList();
			
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

		return participations;
	}

	@Override
	public Participation findById(Long id) {
		Participation participation = null;
		
		EntityManager em = null;
		try {
			em = FactorySingleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();

			participation = em.find(Participation.class, id);
			
//			TypedQuery<Participation> query = em.createQuery("select p from Participation p where p.id = :id", Participation.class);
//			query.setParameter("id", id);
//			
//			participation = query.getSingleResult();
			
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
		return participation;
	}

	@Override
	public void create(Participation obj) {
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
	public Participation update(Participation obj) {
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

//			Participation participation = em.find(Participation.class, id);
//			em.remove(participation);
			
			TypedQuery<Participation> query = em.createQuery("delete from Participation p where p.id = :id", Participation.class);
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

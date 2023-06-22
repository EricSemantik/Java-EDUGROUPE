package formation.factory.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import formation.factory.FactorySingleton;
import formation.factory.exception.FactoryException;
import formation.factory.model.Formation;
import formation.factory.repository.IFormationRepository;

public class FormationRepositoryJpa implements IFormationRepository {

	@Override
	public List<Formation> findAll() {
		List<Formation> formations = new ArrayList<>();

		EntityManager em = null;
		try {
			em = FactorySingleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();

			TypedQuery<Formation> query = em.createQuery("from Formation", Formation.class);
			formations = query.getResultList();

			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction() != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

			throw new FactoryException(e);
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return formations;
	}

	@Override
	public Formation findById(Long id) {
		Formation formation = null;

		EntityManager em = null;
		try {
			em = FactorySingleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();

			formation = em.find(Formation.class, id);

//			TypedQuery<Formation> query = em.createQuery("select f from Formation f where f.id = :id", Formation.class);
//			query.setParameter("id", id);
//			
//			formation = query.getSingleResult();

			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction() != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

			throw new FactoryException(e);
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return formation;
	}

	@Override
	public void create(Formation obj) {
		EntityManager em = null;
		try {
			em = FactorySingleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();

			em.persist(obj);

			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction() != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

			throw new FactoryException(e);
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public Formation update(Formation obj) {
		EntityManager em = null;
		try {
			em = FactorySingleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();

			obj = em.merge(obj);

			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction() != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

			throw new FactoryException(e);
		} finally {
			if (em != null) {
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

//			Formation formation = em.find(Formation.class, id);
//			em.remove(formation);

			TypedQuery<Formation> query = em.createQuery("delete from Formation f where f.id = :id", Formation.class);
			query.setParameter("id", id);

			query.executeUpdate();

			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction() != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

			throw new FactoryException(e);
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

}

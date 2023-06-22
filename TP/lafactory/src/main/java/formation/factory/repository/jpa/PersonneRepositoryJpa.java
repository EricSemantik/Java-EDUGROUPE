package formation.factory.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import formation.factory.FactorySingleton;
import formation.factory.exception.FactoryException;
import formation.factory.model.Formateur;
import formation.factory.model.Participant;
import formation.factory.model.Personne;
import formation.factory.repository.IPersonneRepository;

public class PersonneRepositoryJpa implements IPersonneRepository {

	@Override
	public List<Personne> findAll() {
		List<Personne> personnes = new ArrayList<>();

		EntityManager em = null;
		try {
			em = FactorySingleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();

			TypedQuery<Personne> query = em.createQuery("from Personne", Personne.class);
			personnes = query.getResultList();

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

		return personnes;
	}

	@Override
	public Personne findById(Long id) {
		Personne personne = null;

		EntityManager em = null;
		try {
			em = FactorySingleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();

			personne = em.find(Personne.class, id);

//			TypedQuery<Personne> query = em.createQuery("select p from Personne p where p.id = :id", Personne.class);
//			query.setParameter("id", id);
//			
//			personne = query.getSingleResult();

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
		return personne;
	}

	@Override
	public void create(Personne obj) {
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
	public Personne update(Personne obj) {
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

//			Personne personne = em.find(Personne.class, id);
//			em.remove(personne);

			TypedQuery<Personne> query = em.createQuery("delete from Personne p where p.id = :id", Personne.class);
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

	@Override
	public List<Formateur> findAllFormateur() {
		List<Formateur> formateurs = new ArrayList<>();

		EntityManager em = null;
		try {
			em = FactorySingleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();

			TypedQuery<Formateur> query = em.createQuery("from Formateur", Formateur.class);
			formateurs = query.getResultList();

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

		return formateurs;
	}

	@Override
	public List<Participant> findAllParticipant() {
		List<Participant> participants = new ArrayList<>();

		EntityManager em = null;
		try {
			em = FactorySingleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();

			TypedQuery<Participant> query = em.createQuery("from Participant", Participant.class);
			participants = query.getResultList();

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

		return participants;
	}

	@Override
	public List<Participant> findAllParticipantBySujet(String code) {
		List<Participant> participants = new ArrayList<>();

		EntityManager em = null;
		try {
			em = FactorySingleton.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();

//			TypedQuery<Participant> query = em.createQuery(
//					"select p from Participant p join p.participations pp join pp.id.formation f join f.sujet s where s.code = :code",
//					Participant.class);
			
//			TypedQuery<Participant> query = em.createQuery(
//					"select pp.id.participant from Formation f join f.participations pp where f.sujet.code = :code",
//					Participant.class);
			
			TypedQuery<Participant> query = em.createQuery(
					"select pp.id.participant from Participation pp where pp.id.formation.sujet.code = :code",
					Participant.class);
			query.setParameter("code", code);

			participants = query.getResultList();

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

		return participants;
	}

}

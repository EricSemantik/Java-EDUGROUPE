package formation.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import formation.factory.exception.FactoryException;
import formation.factory.repository.IFormationRepository;
import formation.factory.repository.IParticipationRepository;
import formation.factory.repository.IPersonneRepository;
import formation.factory.repository.ISalleRepository;
import formation.factory.repository.ISujetRepository;
import formation.factory.repository.jpa.FormationRepositoryJpa;
import formation.factory.repository.jpa.ParticipationRepositoryJpa;
import formation.factory.repository.jpa.PersonneRepositoryJpa;
import formation.factory.repository.jpa.SalleRepositoryJpa;
import formation.factory.repository.jpa.SujetRepositoryJpa;

public class FactorySingleton {
	private static FactorySingleton instance = null;

	private final String jdbcUrl = "jdbc:postgresql://localhost:5432/factory";

	private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("factory-unit");

	private final IFormationRepository formationRepository = new FormationRepositoryJpa();
	private final IPersonneRepository personneRepository = new PersonneRepositoryJpa();
	private final IParticipationRepository participationRepository = new ParticipationRepositoryJpa();
	private final ISalleRepository salleRepository = new SalleRepositoryJpa();
	private final ISujetRepository sujetRepository = new SujetRepositoryJpa();

	private FactorySingleton() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new FactoryException(e);
		}
	}

	public static FactorySingleton getInstance() {
		if (instance == null) {
			instance = new FactorySingleton();
		}

		return instance;
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(this.jdbcUrl, "postgres", "admin");
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public IFormationRepository getFormationRepository() {
		return formationRepository;
	}

	public IPersonneRepository getPersonneRepository() {
		return personneRepository;
	}

	public IParticipationRepository getParticipationRepository() {
		return participationRepository;
	}

	public ISalleRepository getSalleRepository() {
		return salleRepository;
	}

	public ISujetRepository getSujetRepository() {
		return sujetRepository;
	}

}

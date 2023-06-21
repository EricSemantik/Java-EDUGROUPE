package formation.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import formation.factory.exception.FactoryException;
import formation.factory.repository.ISalleRepository;
import formation.factory.repository.jpa.SalleRepositoryJpa;

public class FactorySingleton {
	private static FactorySingleton instance = null;

	private final String jdbcUrl = "jdbc:postgresql://localhost:5432/factory";

	private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("factory-unit");

	private final ISalleRepository salleRepository = new SalleRepositoryJpa();

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

	public ISalleRepository getSalleRepository() {
		return salleRepository;
	}

}

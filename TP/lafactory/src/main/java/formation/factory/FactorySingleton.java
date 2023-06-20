package formation.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import formation.factory.exception.FactoryException;

public class FactorySingleton {
	private static FactorySingleton instance = null;
	
	private String jdbcUrl = "jdbc:postgresql://localhost:5432/factory";
	
	private FactorySingleton() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new FactoryException(e);
		}
	}
	
	public static FactorySingleton getInstance() {
		if(instance == null) {
			instance = new FactorySingleton();
		}

		return instance;
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(this.jdbcUrl, "postgres", "admin");
	}
}

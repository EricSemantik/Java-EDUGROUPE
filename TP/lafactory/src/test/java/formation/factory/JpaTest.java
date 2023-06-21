package formation.factory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaTest {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("factory-unit");
		
		
		emf.close();

	}

}

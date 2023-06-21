package formation.factory;

import formation.factory.model.Adresse;
import formation.factory.model.Salle;
import formation.factory.repository.ISalleRepository;

public class JpaTest {

	public static void main(String[] args) {
		ISalleRepository salleRepo = FactorySingleton.getInstance().getSalleRepository();
		
		Salle ambre = new Salle("Ambre");
		ambre.setAdresse(new Adresse("152 avenue Malakoff", "4ème étage", "75016", "Paris"));
		
		ambre = salleRepo.update(ambre);
		
		System.out.println(ambre.getId());

	}

}

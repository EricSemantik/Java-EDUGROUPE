package formation.factory;

import formation.factory.model.Sujet;
import formation.factory.repository.ISujetRepository;
import formation.factory.repository.jdbc.SujetRepositoryJdbc;

public class SujetTest {

	public static void main(String[] args) {
		ISujetRepository sujetRepo = new SujetRepositoryJdbc();
		
		Sujet javaAvancee = new Sujet("JAVA_ADV", "Formation Java Avancée", "Java : Les bases", 4); // JAVA_ADV;Formation Java Avancée;Java : Les bases;4
		
		sujetRepo.create(javaAvancee);
		
		javaAvancee.setDuree(5);
		
		sujetRepo.update(javaAvancee);
		
//		sujetRepo.deleteById(javaAvancee.getCode());

	}

}

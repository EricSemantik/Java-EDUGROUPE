package formation.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import formation.factory.model.Formation;
import formation.factory.model.Sujet;
import formation.factory.repository.IFormationRepository;
import formation.factory.repository.ISujetRepository;

public class SujetTest {
	
	private static ISujetRepository sujetRepo;
	private static IFormationRepository formationRepo;
	
	@BeforeAll
	public static void init() {
		sujetRepo = FactorySingleton.getInstance().getSujetRepository();
		formationRepo = FactorySingleton.getInstance().getFormationRepository();
	}

	@Test
	public void testCreation() {
		// ARRANGE
		
		// ACT
		Sujet javaBase = new Sujet("JAVA_BASE", "Java les bases", "ALGO", 3);
		sujetRepo.create(javaBase);
		
		// ASSERT
		Sujet javaBaseFind = sujetRepo.findById(javaBase.getCode());
		assertEquals("JAVA_BASE", javaBaseFind.getCode());
		assertEquals("Java les bases", javaBaseFind.getNom());
		assertEquals("ALGO", javaBaseFind.getPreRequis());
		assertEquals(3, javaBaseFind.getDuree());
	}
	
	@Test
	public void testLienFormations() {
		// ARRANGE
		Sujet javaAdv = new Sujet("JAVA_ADV", "Java avancée", "JAVA_ADV", 4);
		sujetRepo.create(javaAdv);
		
		Formation formation1 = new Formation(new Date(), javaAdv, null, null);
		formationRepo.create(formation1);
		
		Formation formation2 = new Formation(new Date(), null, null, null);
		formationRepo.create(formation2);
		
		Formation formation3 = new Formation(new Date(), javaAdv, null, null);
		formationRepo.create(formation3);
		
		// ACT 
		Sujet javaBaseFind = sujetRepo.findByIdWithFormations(javaAdv.getCode());
		
		
		// ASSERT 
		assertEquals(2, javaBaseFind.getFormations().size());
		
		
	}
	

}

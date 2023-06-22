package formation.factory;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import formation.factory.model.Adresse;
import formation.factory.model.Civilite;
import formation.factory.model.Formateur;
import formation.factory.model.Formation;
import formation.factory.model.Participant;
import formation.factory.model.Participation;
import formation.factory.model.Salle;
import formation.factory.model.Sujet;
import formation.factory.repository.IFormationRepository;
import formation.factory.repository.IParticipationRepository;
import formation.factory.repository.IPersonneRepository;
import formation.factory.repository.ISalleRepository;
import formation.factory.repository.ISujetRepository;

public class JpaTest {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		IFormationRepository formationRepo = FactorySingleton.getInstance().getFormationRepository();
		IPersonneRepository personneRepo = FactorySingleton.getInstance().getPersonneRepository();
		IParticipationRepository participationRepository = FactorySingleton.getInstance().getParticipationRepository();
		ISalleRepository salleRepo = FactorySingleton.getInstance().getSalleRepository();
		ISujetRepository sujetRepo = FactorySingleton.getInstance().getSujetRepository();
		
		Salle ambre = new Salle("Ambre");
		ambre.setAdresse(new Adresse("152 avenue Malakoff", "4ème étage", "75016", "Paris"));
		
		ambre = salleRepo.update(ambre);
		
		Participant david = new Participant(Civilite.M, "ADAMS", "David", sdf.parse("26/06/1992"), 15);
		personneRepo.create(david);
		
		Participant charlotte = new Participant(Civilite.MME, "GAUTHIER", "Charlotte", sdf.parse("28/07/1995"), 16);
		personneRepo.create(charlotte);
		
		Participant camille = new Participant(Civilite.M, "FALIGOT", "Camille", sdf.parse("25/12/1993"), 14);
		personneRepo.create(camille);
		
		Formateur eric = new Formateur(Civilite.M, "SULTAN", "Eric", 25, false);
		eric = (Formateur) personneRepo.update(eric);
		
		Sujet javaBase = new Sujet("JAVA_BASE", "Java les bases", "ALGO", 3);
		sujetRepo.create(javaBase);
		
		Sujet javaAvancee = new Sujet("JAVA_ADV", "Java Avancée", "JAVA_BASE", 4);
		sujetRepo.create(javaAvancee);
		
		Sujet springCore = new Sujet("SRING_CORE", "Spring Core", "JAVA_ADV", 4);
		sujetRepo.create(springCore);
		
		Formation javaADV20230619 = new Formation(sdf.parse("19/06/2023"), javaAvancee, eric, ambre);
		
		
		
		formationRepo.create(javaADV20230619);
		
//		javaADV20230619.getParticipants().add(david);
//		javaADV20230619.getParticipants().add(camille);
//		
//		
//		javaADV20230619 = formationRepo.update(javaADV20230619);
//		
//		javaADV20230619.getParticipants().add(charlotte);
//		
//		javaADV20230619 = formationRepo.update(javaADV20230619);
		
		Participation javaADV20230619David = new Participation(javaADV20230619, david);
		participationRepository.update(javaADV20230619David);
		
//		Participation javaADV20230619DavidBis = new Participation(javaADV20230619, david);
//		participationRepository.create(javaADV20230619DavidBis);
		
		Participation javaADV20230619Charlotte = new Participation(javaADV20230619, charlotte);
		participationRepository.update(javaADV20230619Charlotte);
		
		Participation javaADV20230619Camille = new Participation(javaADV20230619, camille);
		participationRepository.update(javaADV20230619Camille);
	}

}

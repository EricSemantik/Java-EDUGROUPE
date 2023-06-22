package formation.factory.repository;

import java.util.List;

import formation.factory.model.Formateur;
import formation.factory.model.Participant;
import formation.factory.model.Personne;

public interface IPersonneRepository extends IRepository<Personne, Long>{
	List<Formateur> findAllFormateur();
	
	List<Participant> findAllParticipant();
	
	List<Participant> findAllParticipantBySujet(String code);
}


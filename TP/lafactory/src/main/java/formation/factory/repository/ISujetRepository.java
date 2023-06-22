package formation.factory.repository;

import java.util.Date;
import java.util.List;

import formation.factory.model.Sujet;

public interface ISujetRepository extends IRepository<Sujet, String>{
	Sujet findByIdWithFormations(String code);
	
	List<Sujet> findAllByPreRequis(String prequis); // TEST UNIT
	
	List<Sujet> findAllByDateDeFormation(Date dtFormation);
}

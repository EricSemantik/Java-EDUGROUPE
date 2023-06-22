package formation.factory.repository;

import java.util.List;

import formation.factory.model.Salle;

public interface ISalleRepository extends IRepository<Salle, Long>{
	List<Salle> findAllByVille(String ville);
	
	List<Salle> findAllWithFormations(); // pré chargement sur les formations + TEST UNIT
}

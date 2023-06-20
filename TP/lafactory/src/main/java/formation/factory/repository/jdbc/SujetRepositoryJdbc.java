package formation.factory.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import formation.factory.FactorySingleton;
import formation.factory.exception.FactoryException;
import formation.factory.model.Sujet;
import formation.factory.repository.ISujetRepository;

public class SujetRepositoryJdbc implements ISujetRepository {

	@Override
	public List<Sujet> findAll() {
		List<Sujet> sujets = new ArrayList<>();
		try(Connection conn = FactorySingleton.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement("SELECT code, nom, pre_requis, duree FROM sujet");	
				ResultSet rs = ps.executeQuery();	
			) {
			
			while(rs.next()) {
				String code = rs.getString(1);
				String nom = rs.getString(2);
				String preRequis = rs.getString(3);
				int duree = rs.getInt(4);
				
				Sujet sujet = new Sujet(code, nom, preRequis, duree);
				sujets.add(sujet);
			}
			
		} catch (SQLException e) {
			throw new FactoryException("Connexion error", e);
		} 
		
		return sujets;
	}

	@Override
	public Sujet findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Sujet obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Sujet obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

}

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
		try (Connection conn = FactorySingleton.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement("SELECT code, nom, pre_requis, duree FROM sujet");
				ResultSet rs = ps.executeQuery();) {

			while (rs.next()) {
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
		Sujet sujet = null;

		try (Connection conn = FactorySingleton.getInstance().getConnection();
				PreparedStatement ps = conn
						.prepareStatement("SELECT nom, pre_requis, duree FROM sujet WHERE code = ?");) {

			ps.setString(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				if(rs.next()) {
					String nom = rs.getString(1);
					String preRequis = rs.getString(2);
					int duree = rs.getInt(3);

					sujet = new Sujet(id, nom, preRequis, duree);
				}
			}

		} catch (SQLException e) {
			throw new FactoryException("Connexion error", e);
		}

		return sujet;
	}

	@Override
	public void create(Sujet obj) {
		try (Connection conn = FactorySingleton.getInstance().getConnection();
				PreparedStatement ps = conn
						.prepareStatement("INSERT INTO sujet (code, nom, pre_requis, duree) VALUES (?, ?, ?, ?)");) {

			ps.setString(1, obj.getCode());
			ps.setString(2, obj.getNom());
			ps.setString(3, obj.getPreRequis());
			ps.setInt(4, obj.getDuree());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new FactoryException("Connexion error", e);
		}
	}

	@Override
	public Sujet update(Sujet obj) {
		try (Connection conn = FactorySingleton.getInstance().getConnection();
				PreparedStatement ps = conn
						.prepareStatement("UPDATE sujet SET nom = ?, pre_requis = ?, duree = ? WHERE code = ?");) {

			ps.setString(1, obj.getNom());
			ps.setString(2, obj.getPreRequis());
			ps.setInt(3, obj.getDuree());
			ps.setString(4, obj.getCode());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new FactoryException("Connexion error", e);
		}
		
		return obj;
	}

	@Override
	public void deleteById(String id) {
		try (Connection conn = FactorySingleton.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement("DELETE FROM sujet WHERE code = ?");) {

			ps.setString(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new FactoryException("Connexion error", e);
		}
	}

}

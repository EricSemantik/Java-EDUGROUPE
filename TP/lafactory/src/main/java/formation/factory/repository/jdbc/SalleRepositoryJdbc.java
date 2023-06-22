package formation.factory.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import formation.factory.FactorySingleton;
import formation.factory.exception.FactoryException;
import formation.factory.model.Adresse;
import formation.factory.model.Salle;
import formation.factory.repository.ISalleRepository;

public class SalleRepositoryJdbc implements ISalleRepository {

	@Override
	public List<Salle> findAll() {
		List<Salle> salles = new ArrayList<>();
		try (Connection conn = FactorySingleton.getInstance().getConnection();
				PreparedStatement ps = conn
						.prepareStatement("SELECT id, nom, rue, complement, code_postal, ville FROM salle");
				ResultSet rs = ps.executeQuery();) {

			while (rs.next()) {
				Long id = rs.getLong(1);
				String nom = rs.getString(2);
				String rue = rs.getString(3);
				String complement = rs.getString(4);
				String codePostal = rs.getString(5);
				String ville = rs.getString(6);

				Salle salle = new Salle(id, nom);
				salle.setAdresse(new Adresse(rue, complement, codePostal, ville));
				salles.add(salle);
			}

		} catch (SQLException e) {
			throw new FactoryException("Connexion error", e);
		}

		return salles;
	}

	@Override
	public Salle findById(Long id) {
		Salle salle = null;

		try (Connection conn = FactorySingleton.getInstance().getConnection();
				PreparedStatement ps = conn
						.prepareStatement("SELECT nom, rue, complement, code_postal, ville FROM salle WHERE id = ?");) {

			ps.setLong(1, id);

			try (ResultSet rs = ps.executeQuery()) {

				if(rs.next()) {
					String nom = rs.getString(1);
					String rue = rs.getString(2);
					String complement = rs.getString(3);
					String codePostal = rs.getString(4);
					String ville = rs.getString(5);

					salle = new Salle(id, nom);
					salle.setAdresse(new Adresse(rue, complement, codePostal, ville));
				}
			}

		} catch (SQLException e) {
			throw new FactoryException("Connexion error", e);
		}

		return salle;
	}

	@Override
	public void create(Salle obj) {
		try (Connection conn = FactorySingleton.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(
						"INSERT INTO salle (nom, rue, complement, code_postal, ville) VALUES (?, ?, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, obj.getNom());

			if (obj.getAdresse() != null) {
				ps.setString(2, obj.getAdresse().getRue());
				ps.setString(3, obj.getAdresse().getComplement());
				ps.setString(4, obj.getAdresse().getCodePostal());
				ps.setString(5, obj.getAdresse().getVille());
			} else {
				ps.setNull(2, Types.VARCHAR);
				ps.setNull(3, Types.VARCHAR);
				ps.setNull(4, Types.VARCHAR);
				ps.setNull(5, Types.VARCHAR);
			}

			ps.executeUpdate();

			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					Long key = rs.getLong(1);
					obj.setId(key);
				}
			}
		} catch (SQLException e) {
			throw new FactoryException("Connexion error", e);
		}
	}

	@Override
	public Salle update(Salle obj) {
		try (Connection conn = FactorySingleton.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(
						"UPDATE salle SET nom = ?, rue = ?, complement = ?, code_postal = ?, ville = ? WHERE id = ?");) {

			ps.setString(1, obj.getNom());

			if (obj.getAdresse() != null) {
				ps.setString(2, obj.getAdresse().getRue());
				ps.setString(3, obj.getAdresse().getComplement());
				ps.setString(4, obj.getAdresse().getCodePostal());
				ps.setString(5, obj.getAdresse().getVille());
			} else {
				ps.setNull(2, Types.VARCHAR);
				ps.setNull(3, Types.VARCHAR);
				ps.setNull(4, Types.VARCHAR);
				ps.setNull(5, Types.VARCHAR);
			}

			ps.setLong(6, obj.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new FactoryException("Connexion error", e);
		}
		
		return obj;
	}

	@Override
	public void deleteById(Long id) {
		try (Connection conn = FactorySingleton.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement("DELETE FROM salle WHERE id = ?");) {

			ps.setLong(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new FactoryException("Connexion error", e);
		}
	}

	@Override
	public List<Salle> findAllByVille(String ville) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Salle> findAllWithFormations() {
		// TODO Auto-generated method stub
		return null;
	}

}

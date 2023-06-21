package formation.factory.repository.csv;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import formation.factory.exception.FactoryException;
import formation.factory.model.Sujet;
import formation.factory.repository.ISujetRepository;

public class SujetRepositoryCsv implements ISujetRepository {

	private final Path chemin;

	public SujetRepositoryCsv(String chemin) {
		super();
		this.chemin = Paths.get(chemin);
	}

	@Override
	public List<Sujet> findAll() {
		return readAll();
	}

	@Override
	public Sujet findById(String id) {
		for (Sujet sujet : readAll()) {
			if (sujet.getCode().equals(id)) {
				return sujet;
			}
		}

		return null;
	}

	@Override
	public void create(Sujet obj) {
		List<Sujet> sujets = readAll();

		for (Sujet sujet : readAll()) {
			if (sujet.getCode().equals(obj.getCode())) {
				throw new FactoryException("Code du sujet déjà existant " + obj.getCode());
			}
		}

		sujets.add(obj);

		writeAll(sujets);
	}

	@Override
	public Sujet update(Sujet obj) {
		List<Sujet> sujets = readAll();

		boolean find = false;
		int i = 0;
		for (; i < sujets.size(); i++) {
			if (sujets.get(i).getCode().equals(obj.getCode())) {
				find = true;
				break;
			}
		}

		if (find) {
			sujets.set(i, obj);
		}

		writeAll(sujets);
		
		return obj;
	}

	@Override
	public void deleteById(String id) {
		List<Sujet> sujets = readAll();

		boolean find = false;
		int i = 0;
		for (; i < sujets.size(); i++) {
			if (sujets.get(i).getCode().equals(id)) {
				find = true;
				break;
			}
		}

		if (find) {
			sujets.remove(i);
		}

		writeAll(sujets);
	}

	private List<Sujet> readAll() {
		List<Sujet> sujets = new ArrayList<>();

		if (Files.exists(chemin)) {
			List<String> lignes;
			try {
				lignes = Files.readAllLines(chemin, StandardCharsets.UTF_8);

				for (String ligne : lignes) {
					String[] items = ligne.split(";");

					Sujet sujet = new Sujet(items[0], items[1], items[2], Integer.valueOf(items[3]));
					sujets.add(sujet);
				}
			} catch (IOException e) {
				throw new FactoryException("Erreur de lecture sur Sujet", e);
			}
		}

		return sujets;
	}

	private void writeAll(List<Sujet> sujets) {
		List<String> lignes = new ArrayList<>();

		for (Sujet sujet : sujets) {
			StringBuilder sb = new StringBuilder();
			sb.append(sujet.getCode()).append(";");
			sb.append(sujet.getNom()).append(";");
			sb.append(sujet.getPreRequis()).append(";");
			sb.append(sujet.getDuree());

			lignes.add(sb.toString());
		}

		try {
			Files.write(chemin, lignes, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			throw new FactoryException("Erreur d'écriture sur Sujet", e);
		}

	}

}

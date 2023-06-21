package formation.factory.repository.xml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import formation.factory.exception.FactoryException;
import formation.factory.model.Sujet;
import formation.factory.repository.ISujetRepository;
import formation.factory.repository.xml.wrapper.SujetWrapper;

public class SujetRepositoryXml implements ISujetRepository {

	private final Path chemin;
	private final Marshaller marshaller;
	private final Unmarshaller unmarshaller;

	public SujetRepositoryXml(String chemin) {
		super();
		this.chemin = Paths.get(chemin);

		try {

			JAXBContext context = JAXBContext.newInstance(SujetWrapper.class);
			marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			unmarshaller = context.createUnmarshaller();
		} catch (JAXBException e) {
			throw new FactoryException("Erreur init JAXB", e);
		}

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
		SujetWrapper sujetWrapper = new SujetWrapper();

		if (this.chemin.toFile().exists()) {
			try {
				sujetWrapper = (SujetWrapper) this.unmarshaller.unmarshal(new FileReader(this.chemin.toFile()));
			} catch (FileNotFoundException e) {
				throw new FactoryException("Fichier non trouvé", e);
			} catch (JAXBException e) {
				throw new FactoryException("Erreur unmarshalling", e);
			}
		}

		return sujetWrapper.getSujets();
	}

	private void writeAll(List<Sujet> sujets) {

		try {
			SujetWrapper sujetWrapper = new SujetWrapper(sujets);

			this.marshaller.marshal(sujetWrapper, new FileWriter(this.chemin.toFile(), StandardCharsets.UTF_8));
		} catch (JAXBException e) {
			throw new FactoryException("Erreur marshalling", e);
		} catch (IOException e) {
			throw new FactoryException("Erreur écriture fichier", e);
		}
	}

}

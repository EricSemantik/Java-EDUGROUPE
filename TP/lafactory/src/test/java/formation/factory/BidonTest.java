package formation.factory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.TreeSet;

import formation.factory.model.Sujet;

public class BidonTest {

	public static void main(String[] args) throws IOException {
		Sujet javaAvancee = new Sujet("JAVA_ADV", "Formation Java Avancée", "JAVA_INIT", 4);

		Sujet javascript = new Sujet("Javascript", "Formation JS", "HTML5", 3);

		Sujet html = new Sujet("HTML5", "Formation HTML5", "", 2);

		Set<Sujet> sujets = new TreeSet<>(new Comparator<Sujet>() {
			@Override
			public int compare(Sujet o1, Sujet o2) {
				return o1.getDuree() - o2.getDuree();
			}
		});

		sujets.add(javaAvancee);
		sujets.add(html);
		sujets.add(javascript);

//		for (Sujet sujet : sujets) {
//			System.out.println(sujet.getCode() + ":" + sujet.getDuree());
//		}

		sujets.stream().filter(s -> "Javascript".equals(s.getCode())).forEach(System.out::println);

		List<String> products = Arrays.asList("produit1;12.3;20", "produit2;5.3;5.5", "produit4;123.23;20");

		OptionalDouble moyenne = products.stream().map(s -> Produit.parse(s))
				.mapToDouble(p -> p.getPrixHT() * (1 + p.getTaux() / 100)).average();

		if (moyenne.isPresent()) {
			System.out.println(moyenne.getAsDouble());
		}

		products.stream().map(s -> Produit.parse(s)).filter(p -> p.getTaux() == 5.5).filter(p -> p.getPrixHT() < 100)
				.forEach(System.out::println);

		
		FactorySingleton singleton1 = FactorySingleton.getInstance();
		FactorySingleton singleton2 = FactorySingleton.getInstance();
		
	}

}

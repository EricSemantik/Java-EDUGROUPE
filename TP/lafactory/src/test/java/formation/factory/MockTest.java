package formation.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import formation.factory.model.Sujet;
import formation.factory.repository.ISujetRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MockTest {
	@Mock
	private ISujetRepository sujetRepo;
	
	@Test
	public void testMock() {
		when(sujetRepo.findById("BIDON")).thenReturn(new Sujet("BIDON", "Bidonnage", "rien", 2));
	
		assertEquals("Bidonddage", sujetRepo.findById("BIDON").getNom());
	}
}

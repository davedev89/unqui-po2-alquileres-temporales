package ar.edu.unq.po2.alquilerestemporales.publicacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ObserverPublicacionTestCase {
	
	private Observer observador;
	private IPriceObserver iobservador1;
	private IPriceObserver iobservador2;
	private Publicacion publicacion;
	
	@BeforeEach
	void setUp() throws Exception {
		observador = new Observer();
		iobservador1 = mock(IPriceObserver.class);
		iobservador2 = mock(IPriceObserver.class);
		publicacion = mock(Publicacion.class);
	}

	@Test
	void testCreation() {
		assertNotNull(this.observador);
	}
	
	@Test
	void testObservadorAgregaUnIObservador() {
		this.observador.attach(iobservador1);
		
		int resultado = this.observador.getObservadores().size();
		assertEquals(resultado , 1);
	}
	
	@Test
	void testObservadorEliminaUnIObservador() {
		this.observador.attach(iobservador1);
		this.observador.attach(iobservador2);
		
		int resultado = this.observador.getObservadores().size();
		assertEquals(resultado , 2);
		
		this.observador.detach(iobservador2);
		
		int resultado2 = this.observador.getObservadores().size();
		assertEquals(resultado2, 1);
	}
	
	@Test
	void testObservadorRecibePublicacionEInformaAIObservador() {
		this.observador.attach(iobservador1);
		this.observador.notificar(publicacion);
		
		verify(iobservador1).update(publicacion);
	}

}

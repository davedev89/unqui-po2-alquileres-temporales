package ar.edu.unq.po2.alquilerestemporales.filtro;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.alquilerestemporales.publicacion.Publicacion;

class FiltroPrecioTestCase {

	private Filtro filtro;
	private float precioMax;
	private float precioMin;
	private Publicacion publi1;

	
	@BeforeEach
	void setUp() throws Exception {
		precioMin= 1000.00f;
		precioMax= 2000.00f;
		filtro = new FiltroPrecio(precioMax,precioMin);
		publi1= mock(Publicacion.class);

		
	}
	
	@Test
	void testCreation() {
		assertNotNull(this.filtro);
	}
	
	@Test
	void testPrecioCumple() {
		when(publi1.getPrecioBase()).thenReturn(1500.00f);
		assertTrue(filtro.cumpleFiltrado(publi1));
	}
	
	@Test
	void testPrecioNoCumple() {
		when(publi1.getPrecioBase()).thenReturn(3500.00f);
		assertFalse(filtro.cumpleFiltrado(publi1));
	}

}

package ar.edu.unq.po2.alquilerestemporales.filtro;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.alquilerestemporales.publicacion.Publicacion;

class FiltroCantidadDeHabitantesTestCase {

	private Filtro filtro;
	private int cantidad;
	private Publicacion publi1;

	
	@BeforeEach
	void setUp() throws Exception {	
		cantidad=4;
		filtro = new FiltroCantidadDeHabitantes(cantidad);
		publi1= mock(Publicacion.class);
	}
	
	@Test
	void testCreation() {
		assertNotNull(this.filtro);
	}
	
	@Test
	void testCantidadDeHabitantesCumple() {
		when(publi1.getCantHabitantes()).thenReturn(4);
		assertTrue(filtro.cumpleFiltrado(publi1));
	}
	
	@Test
	void testCantidadDeHabitantesNoCumple() {
		when(publi1.getCantHabitantes()).thenReturn(6);
		assertFalse(filtro.cumpleFiltrado(publi1));
	}
}

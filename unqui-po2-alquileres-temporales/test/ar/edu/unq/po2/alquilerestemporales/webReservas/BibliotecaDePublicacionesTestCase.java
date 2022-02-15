package ar.edu.unq.po2.alquilerestemporales.webReservas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.alquilerestemporales.publicacion.Publicacion;
import ar.edu.unq.po2.alquilerestemporales.publicacion.calificable.Inmueble;

class BibliotecaDePublicacionesTestCase {
	
	private BibliotecaDePublicaciones biblioteca;
	private Publicacion publicacion1;
	private Publicacion publicacion2;
	private Inmueble inmueble1;
	
	
	@BeforeEach
	void setUp() throws Exception {
		biblioteca = new BibliotecaDePublicaciones();
		publicacion1 = mock(Publicacion.class);
		publicacion2 = mock(Publicacion.class);
		inmueble1 = mock(Inmueble.class);
		
	}
	
	@Test
	void testConstructor() {
		assertNotNull(this.biblioteca);
	}
	
	@Test
	void testCargarPublicacionNueva() {
		biblioteca.cargarPublicacion(publicacion1);
		int resultado = biblioteca.getPublicaciones().size();
		assertEquals(resultado,1);
	}
	
	@Test
	void testNoSeCarganPublicacionesDelMismoInmueble() {
		when(this.publicacion1.getInmueble()).thenReturn(inmueble1);
		when(this.publicacion2.getInmueble()).thenReturn(inmueble1);
		biblioteca.cargarPublicacion(publicacion1);
		biblioteca.cargarPublicacion(publicacion2);
		int resultado = biblioteca.getPublicaciones().size();
		assertEquals(resultado,1);
	}
	
}

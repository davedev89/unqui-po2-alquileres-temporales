package ar.edu.unq.po2.alquilerestemporales.publicacion.calificable;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalificacionTestCase {
	private Calificacion calificacion;
	private Calificacion calificacion2;
	private int puntuacionDeCalificacionNegativa;
	private int puntuacionDeCalificacion2;
	private String comentarioCalificacion1;
	private String comentarioCalificacion2;
	private String categoriaUsuario;
	private Usuario usuario;

	@BeforeEach
	void setUp() throws Exception {
		puntuacionDeCalificacionNegativa = -2;
		puntuacionDeCalificacion2 = 3;
		comentarioCalificacion1 = "Mal propietario, contestaba mal.";
		comentarioCalificacion2 = "Propietario normal, me dejo las llaves y se fue.";	
		categoriaUsuario = "Usuario";
		usuario = mock(Usuario.class);
	}
	
	@Test
	void testConstructor() {
		calificacion = new Calificacion(puntuacionDeCalificacion2, comentarioCalificacion2, categoriaUsuario,usuario);
		assertNotNull(this.calificacion);
	}
	
	@Test
	void testPuntuacionCorrecta() {
		calificacion = new Calificacion(puntuacionDeCalificacion2, comentarioCalificacion2, categoriaUsuario,usuario);
		assertEquals(puntuacionDeCalificacion2, calificacion.getCalificacion());
	}
	@Test
	void testPuntuacionIncorrecta() throws Exception {
		calificacion2 = new Calificacion(puntuacionDeCalificacion2, comentarioCalificacion1,categoriaUsuario,usuario);
		assertThrows(Exception.class, () -> {
			this.calificacion2.setCalificacion(puntuacionDeCalificacionNegativa);
		});
	}
	
	@Test
	void testComentarioDeCalificacion() {
		calificacion = new Calificacion(puntuacionDeCalificacion2, comentarioCalificacion2,categoriaUsuario,usuario);
		assertEquals(comentarioCalificacion2, calificacion.getComentario());
	}
	
	@Test
	void testGetCategoriaDeCalificacion() {
		calificacion = new Calificacion(puntuacionDeCalificacion2, comentarioCalificacion2,categoriaUsuario,usuario);
		assertEquals(categoriaUsuario, this.calificacion.getCategoria());
	}
	
	@Test
	void testCalificacionTieneUsuario() {
		calificacion = new Calificacion(puntuacionDeCalificacion2, comentarioCalificacion2,categoriaUsuario,usuario);
		Usuario usuarioDeCalificacion = calificacion.getAutor();
		assertEquals(this.usuario, usuarioDeCalificacion);
	}

}

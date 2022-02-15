package ar.edu.unq.po2.alquilerestemporales.publicacion.calificable;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BibliotecaDeCalificacionesTestCase {

	private BibliotecaDeCalificaciones bibliotecaDeCalificaciones;
	private Calificacion calificacion1;
	private Calificacion calificacion2;
	private Calificacion calificacion3;
	private String comentarioCalificacionBaja;
	private String comentarioCalificacionMedia;
	private String comentarioCalificacionAlta;
	private ArrayList<String> comentarios;
	private ArrayList<Calificacion> listaDeCalificaciones;
	
	@BeforeEach
	void setUp() throws Exception {
		bibliotecaDeCalificaciones = new BibliotecaDeCalificaciones();
		calificacion1 = mock(Calificacion.class);
		calificacion2 = mock(Calificacion.class);
		calificacion3 = mock(Calificacion.class);
		comentarioCalificacionBaja = "Mal propietario, contestaba mal.";
		comentarioCalificacionMedia = "Propietario normal, me dejó las llaves y se fue.";
		comentarioCalificacionAlta = "Buen propietario, me hizo la cena y todo, re copado el tipo.";
		this.bibliotecaDeCalificaciones.addCalificacion(calificacion1);
		this.bibliotecaDeCalificaciones.addCalificacion(calificacion2);
		this.bibliotecaDeCalificaciones.addCalificacion(calificacion3);
		
		comentarios = new ArrayList<String>();
		comentarios.add(comentarioCalificacionBaja);
		comentarios.add(comentarioCalificacionMedia);
		comentarios.add(comentarioCalificacionAlta);
		listaDeCalificaciones = new ArrayList<Calificacion>();
		this.listaDeCalificaciones.add(calificacion1);
		this.listaDeCalificaciones.add(calificacion2);
		this.listaDeCalificaciones.add(calificacion3);
	}
	
	@Test
	void testConstructor() {
		assertNotNull(this.bibliotecaDeCalificaciones);
	}
	
	@Test
	void testComentariosDeCalificaciones() {
		when(this.calificacion1.getComentario()).thenReturn(comentarioCalificacionBaja);
		when(this.calificacion2.getComentario()).thenReturn(comentarioCalificacionMedia);
		when(this.calificacion3.getComentario()).thenReturn(comentarioCalificacionAlta);
		assertEquals(comentarios, bibliotecaDeCalificaciones.getComentarios());
	}
	@Test
	void testDatosCalificaciones() {
		//CalificacionBaja
		when(this.calificacion1.getCalificacion()).thenReturn(1);
		//CalificacionMedia
		when(this.calificacion2.getCalificacion()).thenReturn(3);
		//CalificacionAlta
		when(this.calificacion3.getCalificacion()).thenReturn(5);
		//PromedioCalificacion
		float promedioUsuario = this.bibliotecaDeCalificaciones.getPromedioCalificacion();
		float promedioCalificaciones = 3;
		// Verify
		assertEquals(promedioCalificaciones, promedioUsuario);
	}
	
	@Test
	void testListaDeCalificaciones() {
		assertEquals(listaDeCalificaciones, this.bibliotecaDeCalificaciones.getCalificaciones());
	}
}

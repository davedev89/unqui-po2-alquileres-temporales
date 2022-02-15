package ar.edu.unq.po2.alquilerestemporales.publicacion.calificable;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UsuarioTestCase {
	private String nombreYApellidoDeUsuario;
	private String nombreYApellidoDeUsuario2;
	private String direccion;
	private String telefono;
	private LocalDate fechaDeAlta;
	private Usuario usuario;
	private String direccion2;
	private String telefono2;
	private Calificacion calificacion1;
	private Calificacion calificacion2;
	private Calificacion calificacion3;
	private String comentarioCalificacionBaja;
	private String comentarioCalificacionMedia;
	private String comentarioCalificacionAlta;
	private CasillaEmail casillaEmail;
	ArrayList<String> comentarios;
	
	
	@BeforeEach
	void setUp() throws Exception {
		this.nombreYApellidoDeUsuario = "Pepito Gomez";
		this.direccion = "Calle Falsa 123";
		this.telefono = "123654789";
		this.fechaDeAlta = LocalDate.now();
		this.nombreYApellidoDeUsuario2 = "Pepita Gomez";
		this.direccion2="Avenida Siempre Viva 742";
		this.telefono2= "987456321";
		calificacion1 = mock(Calificacion.class);
		calificacion2 = mock(Calificacion.class);
		calificacion3 = mock(Calificacion.class);
		comentarioCalificacionBaja = "Mal propietario, contestaba mal.";
		comentarioCalificacionMedia = "Propietario normal, me dejo las llaves y se fue.";
		comentarioCalificacionAlta = "Buen propietario, me hizo la cena y todo, re copado el tipo.";
		casillaEmail = mock(CasillaEmail.class);
		this.usuario = new Usuario(nombreYApellidoDeUsuario, direccion, telefono, fechaDeAlta, casillaEmail);	
	}
	
	@Test
	void testConstructor() {
		assertNotNull(this.usuario);
	}
	
	@Test
	void testGetDatos() {
		assertEquals(this.nombreYApellidoDeUsuario, this.usuario.getNombreYApellido());
		assertEquals(this.direccion, this.usuario.getDireccion());
		assertEquals(this.telefono, this.usuario.getTelefono());
		assertEquals(this.fechaDeAlta, this.usuario.getFechaDeAlta());
	}
	
	@Test
	void testSetDatos() {
		this.usuario.setNombreYApellido(this.nombreYApellidoDeUsuario2);
		this.usuario.setDireccion(this.direccion2);
		this.usuario.setTelefono(this.telefono2);
		assertEquals(this.nombreYApellidoDeUsuario2, this.usuario.getNombreYApellido());
		assertEquals(this.direccion2, this.usuario.getDireccion());
		assertEquals(this.telefono2, this.usuario.getTelefono());
	}
	
	@Test
	void testUsuarioRecibeCalificacion() {
		this.usuario.addCalificacion(calificacion1);
		this.usuario.addCalificacion(calificacion2);
		int cantidadDeCalificaciones = this.usuario.getCalificaciones().size();
		assertEquals(2, cantidadDeCalificaciones);
	}
	
	@Test
	void testUsuarioRecibeCalificacionYTieneUnPromedioDeCalificaciones() {
		//CalificacionBaja
		when(this.calificacion1.getCalificacion()).thenReturn(1);
		//CalificacionMedia
		when(this.calificacion2.getCalificacion()).thenReturn(3);
		//CalificacionAlta
		when(this.calificacion3.getCalificacion()).thenReturn(5);
		//Recibir calificaciones
		this.usuario.addCalificacion(calificacion1);
		this.usuario.addCalificacion(calificacion2);
		this.usuario.addCalificacion(calificacion3);
		float promedioUsuario = this.usuario.getPromedioCalificacion();
		float promedioCalificaciones = 3;
		assertEquals(promedioCalificaciones, promedioUsuario);
	}
	@Test
	void testUsuarioRecibeCalificacionYObtieneUnaListaDeComentarios() {
		when(this.calificacion1.getComentario()).thenReturn(comentarioCalificacionBaja);
		when(this.calificacion2.getComentario()).thenReturn(comentarioCalificacionMedia);
		when(this.calificacion3.getComentario()).thenReturn(comentarioCalificacionAlta);
		
		comentarios = new ArrayList<String>();
		comentarios.add(comentarioCalificacionBaja);
		comentarios.add(comentarioCalificacionMedia);
		comentarios.add(comentarioCalificacionAlta);
		
		this.usuario.addCalificacion(calificacion1);
		this.usuario.addCalificacion(calificacion2);
		this.usuario.addCalificacion(calificacion3);
		
		assertEquals(comentarios, usuario.getComentarios());
		
	}	
	@Test
	void testUsuarioTieneCasillaDeMail(){
		CasillaEmail casillaDeUsuario = this.casillaEmail;
		assertNotNull(this.usuario.getCasillaEmail());
		assertEquals(this.usuario.getCasillaEmail(), casillaDeUsuario );
	}
	
	@Test
	void testUsuarioRecibeMailALaCasillaDeMail() {
		CasillaEmail casillaDeUsuario = this.casillaEmail;
		this.usuario.recibirMail("Mensaje de prueba 1");
		verify(casillaDeUsuario, times(1)).recibirMail("Mensaje de prueba 1");
		
	}
}

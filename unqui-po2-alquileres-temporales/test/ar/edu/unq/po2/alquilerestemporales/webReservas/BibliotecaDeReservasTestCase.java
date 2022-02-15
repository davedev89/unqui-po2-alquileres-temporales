package ar.edu.unq.po2.alquilerestemporales.webReservas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.alquilerestemporales.publicacion.calificable.Calificacion;
import ar.edu.unq.po2.alquilerestemporales.publicacion.calificable.Usuario;
import ar.edu.unq.po2.alquilerestemporales.reserva.Reserva;

class BibliotecaDeReservasTestCase {

	private BibliotecaDeReservas biblioteca;
	private Reserva reserva1;
	private Reserva reserva2;
	private Usuario usuario;
	private String ciudad1;
	private String ciudad2;
	private LocalDate hoy;
	private LocalDate mañana;
	private Calificacion calificacionInquilino;
	private Calificacion calificacionPropietario;
	private Calificacion calificacionInmueble;
	
	
	@BeforeEach
	void setUp() throws Exception {
		biblioteca= new BibliotecaDeReservas();
		reserva1 = mock(Reserva.class);
		reserva2 = mock(Reserva.class);
		usuario = mock(Usuario.class);
		ciudad1 = "Fondo de Bikini";
		ciudad2 = "Mar del Plata";
		hoy = LocalDate.now();
		mañana = hoy.plusDays(1);
		calificacionInquilino = mock(Calificacion.class);
		calificacionPropietario = mock(Calificacion.class);
		calificacionInmueble = mock(Calificacion.class);
	}
	
	@Test
	void testConstructor() {
		assertNotNull(this.biblioteca);
	}
	
	@Test
	void testAgregarReservas() {
		biblioteca.crearReserva(reserva1);
		biblioteca.crearReserva(reserva2);
		int resultado = biblioteca.getTodasLasReservas().size();
		assertEquals(2,resultado);
	}
		
	@Test
	void testConcretarReserva() {
		this.biblioteca.concretarReserva(reserva1);
		verify(reserva1).aceptar();
	}
		
	@Test
	void testCancelarReserva() {
		this.biblioteca.declinarReserva(reserva1);
		verify(reserva1).cancelar();
	}
	
	@Test
	void testConcluirReservasEnLaFecha() {
		when(this.reserva1.getFechaDeSalida()).thenReturn(this.hoy);
		when(this.reserva2.getFechaDeSalida()).thenReturn(this.mañana);
		biblioteca.crearReserva(reserva1);
		biblioteca.crearReserva(reserva2);
		biblioteca.concluirReservas();
		verify(reserva1).concluir();
		verify(reserva2,never()).concluir();
	}
	
	@Test
	void testRechazarReserva() {
		biblioteca.crearReserva(reserva1);
		this.biblioteca.rechazarReserva(reserva1);
		verify(reserva1).rechazar();
	}

	@Test
	void testReservasDeUsuario() {
		when(this.reserva1.esReservaDeUsuario(usuario)).thenReturn(true);
		when(this.reserva2.esReservaDeUsuario(usuario)).thenReturn(false);
		this.biblioteca.crearReserva(reserva1);
		this.biblioteca.crearReserva(reserva2);
		int resultado = this.biblioteca.getReservasDeUsuario(usuario).size();
		assertEquals(1,resultado);
	}
	
	@Test
	void testReservasFuturasDeUnUsuario() {
		when(this.reserva1.esFutura()).thenReturn(true);
		when(this.reserva2.esFutura()).thenReturn(false);
		when(this.reserva1.esReservaDeUsuario(usuario)).thenReturn(true);
		when(this.reserva2.esReservaDeUsuario(usuario)).thenReturn(true);
		this.biblioteca.crearReserva(reserva1);
		this.biblioteca.crearReserva(reserva2);
		int resultado = this.biblioteca.getReservasFuturas(usuario).size();
		assertEquals(1,resultado);
	}
	
	@Test
	void testCiudadesReservadasPorUnUsuario() {
		when(this.reserva1.getCiudad()).thenReturn(ciudad1);
		when(this.reserva2.getCiudad()).thenReturn(ciudad2);
		when(this.reserva1.esReservaDeUsuario(usuario)).thenReturn(true);
		when(this.reserva2.esReservaDeUsuario(usuario)).thenReturn(false);
		this.biblioteca.crearReserva(reserva1);
		this.biblioteca.crearReserva(reserva2);
		int resultado = this.biblioteca.getCiudadesReservadas(usuario).size();
		assertEquals(1,resultado);
	}
	
	@Test
	void testReservasDelUsuarioEnUnaCiudad() {
		when(this.reserva1.esEnCiudad(ciudad1)).thenReturn(true);
		when(this.reserva2.esEnCiudad(ciudad1)).thenReturn(false);
		when(this.reserva1.esReservaDeUsuario(usuario)).thenReturn(true);
		when(this.reserva2.esReservaDeUsuario(usuario)).thenReturn(true);
		this.biblioteca.crearReserva(reserva1);
		this.biblioteca.crearReserva(reserva2);
		int resultado = this.biblioteca.getReservasEnCiudadDelUsuario(usuario,ciudad1).size();
		assertEquals(1,resultado);
	}
	
	@Test
	void testSeCreaUnaReservaCondicional() {
		when(this.reserva1.seSuporponeCon(reserva2)).thenReturn(true);
		this.biblioteca.crearReserva(reserva1);
		this.biblioteca.crearReserva(reserva2);
		assertTrue(this.biblioteca.estaOcupadaEnFecha(reserva2));
		int resultado = this.biblioteca.getReservasCondicionales().size();
		assertEquals(resultado,1);
	}
	
	@Test
	void testUnaReservaPendienteQueSeConcreta() {
		when(this.reserva1.seSuporponeCon(reserva2)).thenReturn(true);
		when(this.reserva1.enMismoPeriodoQueReserva(reserva2)).thenReturn(true);
		this.biblioteca.crearReserva(reserva1);
		this.biblioteca.crearReserva(reserva2);
		this.biblioteca.concretarPendienteALaReserva(reserva1);
		verify(reserva2).aceptar();
		int resultado = this.biblioteca.getReservasCondicionales().size();
		assertEquals(resultado,0);
	}
	
	@Test
	void testCalificarAEstadiaEnUnaReserva() {
		this.biblioteca.calificarEstadia(reserva1, calificacionPropietario, calificacionInmueble);
		verify(reserva1).calificarEstadia(calificacionPropietario, calificacionInmueble);
	}
	
	@Test
	void testCalificarElInquilinatoDeUnaReserva() {
		this.biblioteca.calificarInquilinato(reserva1, calificacionInquilino);
		verify(reserva1).calificarInquilinato(calificacionInquilino);
	}
	
	
}

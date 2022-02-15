package ar.edu.unq.po2.alquilerestemporales.webReservas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.alquilerestemporales.filtro.Filtro;
import ar.edu.unq.po2.alquilerestemporales.filtro.FiltroBasico;
import ar.edu.unq.po2.alquilerestemporales.publicacion.Publicacion;
import ar.edu.unq.po2.alquilerestemporales.publicacion.calificable.Calificacion;
import ar.edu.unq.po2.alquilerestemporales.publicacion.calificable.Usuario;
import ar.edu.unq.po2.alquilerestemporales.reserva.Reserva;

class WebReservasTestCase {
	
	private WebReservas web;
	private Usuario usu1;
	private Publicacion publi1;
	private Reserva reserva1;
	private BibliotecaDeReservas bibliotecaDeReserva;
	private BibliotecaDePublicaciones bibliotecaDePublicaciones;
	private Buscador buscador;
	private FiltroBasico filtroBasico;
	private ArrayList<Filtro> filtrosExtra;
	@SuppressWarnings("unused")
	private ArrayList<Publicacion> resultado;
	private String ciudad1;
	private Calificacion calificacionInquilino;
	private Calificacion calificacionPropietario;
	private Calificacion calificacionInmueble;
	
	
	@BeforeEach
	void setUp() throws Exception {
		
		usu1= mock(Usuario.class);
		publi1= mock(Publicacion.class);
		reserva1=mock(Reserva.class);
		bibliotecaDeReserva=mock(BibliotecaDeReservas.class);
		bibliotecaDePublicaciones=mock(BibliotecaDePublicaciones.class);
		buscador = mock(Buscador.class);
		web= new WebReservas();
		web.setBibliotecaDePublicaciones(bibliotecaDePublicaciones);
		web.setBibliotecaDeReservas(bibliotecaDeReserva);
		web.setBuscador(buscador);
		filtroBasico= mock(FiltroBasico.class);
		resultado = new ArrayList<Publicacion>();
		filtrosExtra = new ArrayList<Filtro>();
		ciudad1="Fondo de Bikini";
		this.calificacionInmueble = mock(Calificacion.class);
		this.calificacionInquilino = mock(Calificacion.class);
		this.calificacionPropietario = mock(Calificacion.class);
	}
	
	@Test
	void testCreation() {
		assertNotNull(this.web);
	}
	
	@Test
	void testAgregarUsuario() {
		this.web.registrarUsuario(usu1);
		int resultado = this.web.getUsuarios().size();
		assertEquals(1, resultado);
	}
				
	@Test
	void testPublicar() {
		this.web.publicar(publi1);
		verify(bibliotecaDePublicaciones).cargarPublicacion(publi1);
	}
	
	
	@Test
	void testSeObtieneListadoDePublicaciones() {
		this.web.getPublicaciones();
		verify(bibliotecaDePublicaciones).getPublicaciones();
	}
	
	@Test
	void testUsuarioCreaUnaReserva() {
		this.web.solicitarReserva(reserva1);	
		verify(bibliotecaDeReserva).crearReserva(reserva1);
	}
		
	@Test
	void testAceptarUnaReserva() {
		this.web.aceptarReserva(reserva1);	
		verify(bibliotecaDeReserva).concretarReserva(reserva1);
	}
	
	
	@Test
	void testRechazarUnaReserva() {
		this.web.rechazarReserva(reserva1);	
		verify(bibliotecaDeReserva).rechazarReserva(reserva1);
	}
		
	@Test
	void testUnInquilinoCancelaUnaReservaPropia() {
		this.web.cancelarReserva(reserva1);	
		verify(bibliotecaDeReserva).declinarReserva(reserva1);
	}
	
	@Test
	void testSeDioDeAltaUnTipoDeInmueble() {
		this.web.agregarTipoDeInmueble("PH");
		this.web.agregarTipoDeInmueble("Casa");
		int resultado = web.getTiposDeInmueble().size();
		assertEquals(2,resultado);
	}
	
	@Test
	void testSeDioDeAltaUnServicio() {
		this.web.agregarServicio("Agua Corriente");
		this.web.agregarServicio("Wi-Fi");
		int resultado = web.getServicios().size();
		assertEquals(2,resultado);
	}
	
	@Test
	void testSeDioDeAltaUnaCategoriaCalificable() {
		this.web.agregarCategoriaCalificable("Inquilino");
		int resultado = web.getCategoriasCalificables().size();
		assertEquals(1,resultado);
	}
	
	@Test
	void testUsuarioHaceBusqueda() {
		this.web.registrarUsuario(usu1);
		web.hacerBusqueda(filtroBasico, filtrosExtra);
		verify(buscador).buscar(web.getPublicaciones(),filtroBasico,filtrosExtra);
	}
	
	
	@Test
	void testSeAgregaUnaCategoriaNueva() {
		web.agregarCategoriaCalificable("Propietario");
		int resultado = web.getCategoriasCalificables().size();
		assertEquals(1,resultado);
	}
	
	@Test
	void testNoSeAgregaUnaCategoriaRepetida() {
		web.agregarCategoriaCalificable("Inquilino");
		web.agregarCategoriaCalificable("Inquilino");
		int resultado = web.getCategoriasCalificables().size();
		assertEquals(1,resultado);
	}
	
	@Test
	void testSeAgregaUnServicioNuevo() {
		web.agregarServicio("Wi-Fi");
		int resultado = web.getServicios().size();
		assertEquals(1,resultado);
	}
	
	@Test
	void testNoAgregaUnServicioRepetido() {
		web.agregarServicio("Gas");
		web.agregarServicio("Gas");
		int resultado = web.getServicios().size();
		assertEquals(1,resultado);
	}
	
	@Test
	void testSeAgregaUnNuevoTipoDeInmueble() {
		web.agregarTipoDeInmueble("Casa");
		int resultado = web.getTiposDeInmueble().size();
		assertEquals(1,resultado);
	}
	
	@Test
	void testNoSeAgregaUnTipoDeInmuebleRepetido() {
		web.agregarTipoDeInmueble("Departamento");
		web.agregarTipoDeInmueble("Departamento");
		int resultado = web.getTiposDeInmueble().size();
		assertEquals(1,resultado);
	}
	
	@Test
	void testTodasLasRservasDelSistema() {
		web.getTodasLasReservas();
		verify(this.bibliotecaDeReserva).getTodasLasReservas();
	}
	
	@Test
	void testUsuarioRegistradoBuscaReservasFuturas() {
		this.web.registrarUsuario(usu1);
		web.reservasFuturas(usu1);
		verify(this.bibliotecaDeReserva).getReservasFuturas(usu1);
	}

	@Test
	void testUsuarioRegistradoBuscaReservasPropias() {
		web.todasLasReservas(usu1);
		verify(this.bibliotecaDeReserva).getReservasDeUsuario(usu1);
	}
	
	@Test
	void testCiudadesConReservaDelUsuario() {
		web.ciudadesConReserva(usu1);
		verify(this.bibliotecaDeReserva).getCiudadesReservadas(usu1);
	}
	
	@Test
	void testReservasEnLaCiudadPorElUsuario() {
		web.reservasDeUsuarioEnCiudad(usu1,ciudad1);
		verify(this.bibliotecaDeReserva).getReservasEnCiudadDelUsuario(usu1,ciudad1);
	}
	
	@Test
	void testWebReservasCalificaPropietario() {
		web.calificarPropietario(reserva1, calificacionPropietario, calificacionInmueble);
		verify(this.bibliotecaDeReserva).calificarEstadia(reserva1, calificacionPropietario, calificacionInmueble);
	}
	
	@Test
	void testWebReservasCalificaInquilino() {
		web.calificarInquilino(reserva1, calificacionInquilino);
		verify(this.bibliotecaDeReserva).calificarInquilinato(reserva1, calificacionInquilino);
	}

}

package ar.edu.unq.po2.alquilerestemporales.reserva;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.alquilerestemporales.publicacion.calificable.Calificacion;
import ar.edu.unq.po2.alquilerestemporales.publicacion.calificable.Usuario;

class AceptadaTestCase {
	
	private Aceptada state;
	private Reserva reserva;
	private EstadoReserva concluida;
	private Usuario propietario;
	private LocalDate fecIng;
	private LocalDate fecSal;
	private LocalDate fechaIngresoDeReserva;
	private LocalDate fechaSalidaDeReserva;
	private Calificacion calificacionInmueble;
	private Calificacion calificacionInquilino;
	private Calificacion calificacionPropietario;
	
	@BeforeEach
	void setUp() throws Exception {
		state = new Aceptada();
		reserva = mock(Reserva.class);
		mock(Cancelada.class);
		concluida = mock(Concluida.class);
		propietario = mock(Usuario.class);
		fecIng = LocalDate.parse("2022-01-15");
		fecSal = LocalDate.parse("2022-01-18");
		fechaIngresoDeReserva= LocalDate.parse("2022-01-16");
		fechaSalidaDeReserva= LocalDate.parse("2022-01-17");
		this.calificacionInmueble = mock(Calificacion.class);
		this.calificacionInquilino = mock(Calificacion.class);
		this.calificacionPropietario = mock(Calificacion.class);
	}
	
	@Test
	void testCreation() {
		assertNotNull(state);
	}
	
	@Test
	void testCancelarAceptada() {
		when(this.reserva.getPropietario()).thenReturn(propietario);
		state.cancelar(reserva);
		verify(reserva).informarUsuario("Se ha cancelada una de sus reservas",propietario);
		verify(reserva).notificarCancelada();
		verify(reserva).aplicarPoliticaDeCancelacion();
	}
	
	@Test
    void testAceptarAceptada() throws Exception {
        assertThrows(Exception.class, () -> {
            this.state.aceptar(reserva);
        });
    }
	
	@Test
    void testRechazarAceptada() throws Exception {
        assertThrows(Exception.class, () -> {
            this.state.rechazar(reserva);
        });
    }
	
	@Test
    void testEstaOcupadaConOtraReserva()  {
		when(this.reserva.getFechaDeIngreso()).thenReturn(fechaIngresoDeReserva);
		when(this.reserva.getFechaDeSalida()).thenReturn(fechaSalidaDeReserva);
        boolean resultado = state.estaOcupadaCon(fecIng, fecSal, reserva);
        assertTrue(resultado);
    }
	
	@Test
    void testAceptadaConcluida()  {
		when(this.reserva.getEstadoDeReserva()).thenReturn(concluida);
		this.state.concluir(reserva);
		assertTrue(reserva.getEstadoDeReserva().getClass().equals(concluida.getClass()));
    }
	
	@Test
    void testEstaOcupadaConOtraReservaJustoElMismoDia()  {
		when(this.reserva.getFechaDeIngreso()).thenReturn(fecIng);
		when(this.reserva.getFechaDeSalida()).thenReturn(fecSal);
        boolean resultado = state.estaOcupadaCon(fecIng, fecSal, reserva);
        assertTrue(resultado);
	}
	
	@Test
    void testNoEstaOcupadaConOtraReserva()  {
		when(this.reserva.getFechaDeIngreso()).thenReturn(fecIng);
		when(this.reserva.getFechaDeSalida()).thenReturn(fechaIngresoDeReserva);
        boolean resultado = state.estaOcupadaCon(fechaSalidaDeReserva, fecSal, reserva);
        assertFalse(resultado);
    }
	
	@Test
	void testEstadoDeReservaAceptadaCalificaPropietario() {
		assertThrows(Exception.class, () -> {
			this.state.calificarInquilinato(reserva, calificacionInquilino);
		});
	}
	
	@Test
	void testEstadoDeReservaAceptadaCalificaInquilino() {
		assertThrows(Exception.class, () -> {
			this.state.calificarEstadia(reserva, calificacionPropietario, calificacionInmueble);
		});
	}
}

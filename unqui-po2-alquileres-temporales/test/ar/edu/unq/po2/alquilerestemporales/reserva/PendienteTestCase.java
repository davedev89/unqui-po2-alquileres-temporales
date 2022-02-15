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

class PendienteTestCase {

	private Pendiente state;
	private Reserva reserva;
	private EstadoReserva cancelada;
	private LocalDate fecIng;
	private LocalDate fecSal;
	private Calificacion calificacionInmueble;
	private Calificacion calificacionInquilino;
	private Calificacion calificacionPropietario;
	
	@BeforeEach
	void setUp() throws Exception {
		state = new Pendiente();
		reserva = mock(Reserva.class);
		cancelada = mock(Cancelada.class);
		fecIng = LocalDate.parse("2022-01-15");
		fecSal = LocalDate.parse("2022-01-18");
		this.calificacionInmueble = mock(Calificacion.class);
		this.calificacionInquilino = mock(Calificacion.class);
		this.calificacionPropietario = mock(Calificacion.class);
	}
	
	@Test
	void testCreation() {
		assertNotNull(state);
	}
	
		
	@Test
    void testAceptarPendiente() {
		state.aceptar(reserva);
		
		verify(reserva).informarUsuario("Se ha aceptado una de sus reservas", reserva.getInquilino());
		verify(reserva).notificarConcretada();
    }
	
	@Test
    void testRechazarPendiente(){
		state.rechazar(reserva);
		
		verify(reserva).informarUsuario("Se ha rechazado una de sus reservas", reserva.getInquilino());
    }
	
	@Test
	void testCancelarPendiente() {
		when(this.reserva.getEstadoDeReserva()).thenReturn(cancelada);
		this.state.cancelar(reserva);
		
		assertTrue(reserva.getEstadoDeReserva().getClass().equals(cancelada.getClass()));
	}
	
	
	@Test
    void testConcluirPendiente() throws Exception  {
		assertThrows(Exception.class, () -> {
            this.state.concluir(reserva);
        });
    }
		
	@Test
	void testEstadoDeReservaPendienteCalificaPropietario() {
		assertThrows(Exception.class, () -> {
			this.state.calificarInquilinato(reserva, calificacionInquilino);
		});
	}
	
	@Test
	void testEstadoDeReservaPendienteCalificaInquilino() {
		assertThrows(Exception.class, () -> {
			this.state.calificarEstadia(reserva, calificacionPropietario, calificacionInmueble);
		});
	}
	
	@Test
	void testEstadoDeReservaRechazadaNoEstaOcupadaConOtraReserva() {
		assertFalse(this.state.estaOcupadaCon(fecIng, fecSal, reserva));
	}
		
}
package ar.edu.unq.po2.alquilerestemporales.politicasDeCancelacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.alquilerestemporales.reserva.Reserva;

class CancelacionIntermediaTestCase {

	private CancelacionIntermedia cancelacion;
	private Reserva reserva;
		
	@BeforeEach
	void setUp() throws Exception {
		cancelacion= new CancelacionIntermedia();
		reserva= mock(Reserva.class);
		
	}
		
	@Test
	void testCancelacionIntermediaEnPlazoEsGratis() {
		when(this.reserva.diasQueFaltan()).thenReturn(25);
		when(this.reserva.costoTotal()).thenReturn(1000f);
		
		float resultado = this.cancelacion.aplicarCostosDeCancelacion(reserva);
		assertEquals(0f, resultado);
	}
	
	@Test
	void testCancelacionIntermediaEnPlazoIntermedioSeCobraLaMitad() {
		when(this.reserva.diasQueFaltan()).thenReturn(15);
		when(this.reserva.costoTotal()).thenReturn(1000f);
		
		float resultado = this.cancelacion.aplicarCostosDeCancelacion(reserva);
		assertEquals(500f, resultado);
		
	}
	
	@Test
	void testCancelacionIntermediaFueraDePlazoSePagaCompleta() {
		when(this.reserva.diasQueFaltan()).thenReturn(5);
		when(this.reserva.costoTotal()).thenReturn(1000f);
		
		float resultado = this.cancelacion.aplicarCostosDeCancelacion(reserva);
		assertEquals(1000f, resultado);
	}
	
}

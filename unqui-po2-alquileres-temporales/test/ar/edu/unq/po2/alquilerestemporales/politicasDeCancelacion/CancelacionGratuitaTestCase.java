package ar.edu.unq.po2.alquilerestemporales.politicasDeCancelacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.alquilerestemporales.reserva.Reserva;

class CancelacionGratuitaTestCase {

	private PoliticaDeCancelacion cancelacion; 
	private Reserva reserva;
	
	@BeforeEach
	void setUp() throws Exception {
		cancelacion= new CancelacionGratuita();
		reserva= mock(Reserva.class);

	}
	
	@Test
	void testCancelacionGratuitaEnPlazo() {
		when(this.reserva.diasQueFaltan()).thenReturn(10);
		
		float resultado = this.cancelacion.aplicarCostosDeCancelacion(reserva);
		assertEquals(0f, resultado);
	}
	
	@Test
	void testCancelacionGratuitaFueraDePlazoSeCobraDosDias() {
		when(this.reserva.diasQueFaltan()).thenReturn(9);
		when(this.reserva.costoPorDia()).thenReturn(200f);
		
		float resultado = this.cancelacion.aplicarCostosDeCancelacion(reserva);
		assertEquals(400f, resultado);
	}
	

}
package ar.edu.unq.po2.alquilerestemporales.publicacion;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PrecioTemporalTestCase {
	
	private PrecioTemporal precioTemporal;
	LocalDate desde;
	LocalDate hasta;
	

	@BeforeEach
	void setUp() throws Exception {
		desde = LocalDate.parse("2022-05-10");
		hasta = LocalDate.parse("2022-06-10");
		precioTemporal = new PrecioTemporal(200, desde, hasta);
		
	}
	
	@Test
	void testCreation() {
		assertNotNull(this.precioTemporal);
	}

	@Test
	void testTemporadaTieneUnPrecio() {
		double precio = this.precioTemporal.getPrecio();
		
		assertEquals(precio, 200);
	}
	
	@Test
	void testTemporadaTieneUnInicioYUnFinal() {
		LocalDate comienzo = this.precioTemporal.getInicio();
		LocalDate finalizacion = this.precioTemporal.getFinal();
		
		assertEquals(comienzo, LocalDate.parse("2022-05-10"));
		assertEquals(finalizacion, LocalDate.parse("2022-06-10"));
	}

}

package ar.edu.unq.po2.alquilerestemporales.publicacion.formasDePago;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreditoTestCase {

	
	private Credito credito;
	private String tipo;
	private int numeroTarjeta;
	
	
	@BeforeEach
	void setUp() throws Exception {
		
		credito = new Credito(numeroTarjeta);
		tipo = "Credito";
			
	}
	
	@Test
	void testConstructor() {
		assertNotNull(this.credito);
	}
	
	@Test
	void testTipoDeTarjeta() {
		assertEquals(this.tipo, this.credito.getTipo());
	}
	
	@Test
	void testNumeroDeTarjeta() {
		this.credito.setNumeroTarjeta(1234);
		
		int resultado = this.credito.getNumeroTarjeta();
		assertEquals(resultado , 1234);
		
	}
	
	
	@Test
	void testCantidadDeCuotas() {
		this.credito.setCuotas(3);
		
		int resultado = this.credito.getCuotasElegidas();
		
		assertEquals(resultado, 3);
	}

}

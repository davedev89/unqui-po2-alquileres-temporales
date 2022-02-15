package ar.edu.unq.po2.alquilerestemporales.publicacion.formasDePago;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DebitoTestCase {

	private Debito debito;
	private String tipo;
	private int numeroTarjeta;
	
	@BeforeEach
	void setUp() throws Exception {
		
		debito = new Debito(numeroTarjeta);
		tipo = "Debito";
	}
	
	@Test
	void testConstructor() {
		assertNotNull(this.debito);
	}
	
	@Test
	void testTipoDeTarjeta() {
		assertEquals(this.tipo, this.debito.getTipo());
	}
	
	@Test
	void testNumeroDeTarjeta() {
		this.debito.setNumeroTarjeta(1234);
		
		int resultado = this.debito.getNumeroTarjeta();
		assertEquals(resultado , 1234);
		
	}

}

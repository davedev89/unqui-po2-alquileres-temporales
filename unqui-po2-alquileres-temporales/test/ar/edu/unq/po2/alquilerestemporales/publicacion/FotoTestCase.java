package ar.edu.unq.po2.alquilerestemporales.publicacion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FotoTestCase {

	private Foto foto;
	private double altura;
	private double ancho;
	@BeforeEach
	void setUp() throws Exception {
		this.altura = 800.00;
		this.ancho = 600.00;
		foto = new Foto(this.altura, this.ancho);
	}
	
	@Test
	void testConstructor() {
		assertNotNull(this.foto);
	}
	
	@Test
	void testAlturaYAnchoDeFoto() {
		assertEquals(this.altura, this.foto.getAltura());
		assertEquals(this.ancho,this.foto.getAncho());
	}

}

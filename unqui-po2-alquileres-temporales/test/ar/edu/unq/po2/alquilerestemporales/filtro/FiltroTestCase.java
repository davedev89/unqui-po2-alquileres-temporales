package ar.edu.unq.po2.alquilerestemporales.filtro;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.alquilerestemporales.publicacion.Publicacion;

public class FiltroTestCase {
	
	private Filtro filtro;
	private float precioMax;
	private float precioMin;
	private int maxHabitante;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private String ciudad;
	private Publicacion publi1;

	
	@BeforeEach
	void setUp() throws Exception {
		precioMin= 1000.00f;
		precioMax= 2000.00f;
		maxHabitante = 5;
		fechaInicio= LocalDate.of(2021,01,01);
		fechaFin= LocalDate.of(2021,12,30);
		ciudad= "Wilde";
		publi1= mock(Publicacion.class);
	}
	
	@Test
	void testCreationPolimorficoParaPrecio() {
		filtro = new FiltroPrecio(precioMax,precioMin);
		assertNotNull(this.filtro);
	}
	
	@Test
	void testCreationPolimorficoParaHabitantes() {
		filtro = new FiltroCantidadDeHabitantes(maxHabitante);
		assertNotNull(this.filtro);
	}
	
	@Test
	void testCreationPolimorficoParaBasico() {
		filtro = new FiltroBasico(fechaInicio,fechaFin,ciudad);
		assertNotNull(this.filtro);
	}
	
	@Test
	void testFiltroPorPrecio() {
		when(publi1.getPrecioBase()).thenReturn(1500.00f);
		filtro = new FiltroPrecio(precioMax,precioMin);
		assertTrue(filtro.cumpleFiltrado(publi1));
	}
}

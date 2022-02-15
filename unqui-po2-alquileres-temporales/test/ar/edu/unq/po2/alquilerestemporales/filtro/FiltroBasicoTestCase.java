package ar.edu.unq.po2.alquilerestemporales.filtro;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.alquilerestemporales.publicacion.Publicacion;

class FiltroBasicoTestCase {
	
	private Filtro filtro;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private LocalDate fechaInicioTest;
	private LocalDate fechaFinTest;
	private String ciudad1;
	private Publicacion publi1;
	
	@BeforeEach
	void setUp() throws Exception {
		fechaInicio= LocalDate.of(2021,01,01);
		fechaFin= LocalDate.of(2021,12,30);
		fechaInicioTest= LocalDate.of(2020,01,01);
		fechaFinTest= LocalDate.of(2020,12,30);
		ciudad1= "Avellaneda";
		filtro = new FiltroBasico(fechaInicio,fechaFin,ciudad1);
		publi1= mock(Publicacion.class);
	}
	
	@Test
	void testCreation() {
		assertNotNull(this.filtro);
	}
		
	@Test
	void testFiltroCiudadYFecha() {
		when(publi1.getCiudad()).thenReturn("Avellaneda");
		when(publi1.getFechaInicio()).thenReturn(this.fechaInicio);
		when(publi1.getFechaFin()).thenReturn(this.fechaFin);
		assertTrue(filtro.cumpleFiltrado(publi1));
	}
	
	@Test
	void testFiltroCiudadNoCumple() {
		when(publi1.getCiudad()).thenReturn("Avellaneda");
		when(publi1.getFechaInicio()).thenReturn(this.fechaInicioTest);
		when(publi1.getFechaFin()).thenReturn(this.fechaFinTest);
		
		assertFalse(filtro.cumpleFiltrado(publi1));
	}
	
}

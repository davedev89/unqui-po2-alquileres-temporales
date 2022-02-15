package ar.edu.unq.po2.alquilerestemporales.publicacion.calificable;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InmuebleTestCase {
	
	private Inmueble inmueble;
	private double superficie;
	private String pais;
	private String ciudad;
	private String direccion;
	private int capacidad;
	private String tipoDeInmueble;
	private List <String>servicios;
	private Calificacion calificacion;
	private Calificacion calificacion2;
	
	@BeforeEach
	void setUp() throws Exception {
		this.inmueble = new Inmueble(superficie, pais, ciudad, direccion, capacidad, tipoDeInmueble, servicios);
		superficie = 100;
		pais = "Argentina";
		ciudad = "Avellaneda";
		direccion = "Mitre 123";
		capacidad = 5;
		tipoDeInmueble = "Departamento";
		servicios = new ArrayList<String>();
		calificacion = mock(Calificacion.class);
		calificacion2 = mock(Calificacion.class);
		new ArrayList<String>();
	}

	@Test
	void testSettersYGetters() {
		this.inmueble.setSuperficie(superficie);
		this.inmueble.setPais(pais);
		this.inmueble.setCiudad(ciudad);
		this.inmueble.setDireccion(direccion);
		this.inmueble.setCapacidad(capacidad);
		this.inmueble.setTipoDeInmueble(tipoDeInmueble);
		this.inmueble.setServicios(servicios);
		this.inmueble.agregarServicio("Wifi");
		
		assertEquals(this.inmueble.getSuperficie(), 100);
		assertEquals(this.inmueble.getPais(), "Argentina");
		assertEquals(this.inmueble.getCiudad(), "Avellaneda");
		assertEquals(this.inmueble.getDireccion(), "Mitre 123");
		assertEquals(this.inmueble.getCapacidad(), 5);
		assertEquals(this.inmueble.getTipoDeInmueble(), "Departamento");
		assertEquals(this.inmueble.getServicios(), servicios);
	}
	
	@Test
	void testInmuebleRecibeCalificacion() {
		this.inmueble.addCalificacion(calificacion);
		
		assertEquals(this.inmueble.getCalificaciones().size(), 1);
	}
	
	@Test
	void testInmuebleRecibeCalificacionYArmaListaDeComentarios() {
		when(calificacion.getComentario()).thenReturn("Excelente lugar");
		
		this.inmueble.addCalificacion(calificacion);
		
		List<String> comentarios = this.inmueble.getComentarios();
		assertEquals(this.inmueble.getComentarios(), comentarios);
	}
	
	@Test
	void testInmuebleDevuelveSuPromedioDeCalificaciones() {
		when(calificacion.getCalificacion()).thenReturn(10);
		when(calificacion2.getCalificacion()).thenReturn(20);
		
		this.inmueble.addCalificacion(calificacion);
		this.inmueble.addCalificacion(calificacion2);
		
		float resultado = this.inmueble.getPromedioCalificacion();
		assertEquals(resultado, 15);
	}

}

package ar.edu.unq.po2.alquilerestemporales.webReservas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.alquilerestemporales.filtro.Filtro;
import ar.edu.unq.po2.alquilerestemporales.filtro.FiltroBasico;
import ar.edu.unq.po2.alquilerestemporales.filtro.FiltroCantidadDeHabitantes;
import ar.edu.unq.po2.alquilerestemporales.filtro.FiltroPrecio;
import ar.edu.unq.po2.alquilerestemporales.publicacion.Publicacion;

class BuscadorTestCase {

	private Buscador buscador;
	private FiltroBasico filtroB;
	private Filtro filtroP;
	private Filtro filtroH;
	private ArrayList<Publicacion> publicaciones;
	private ArrayList<Filtro> filtrosExtra;
	private Publicacion publi1;
	private Publicacion publi2;
	private Publicacion publi3;

	@BeforeEach
	void setUp() throws Exception {
		buscador = new Buscador();
		filtroB = mock(FiltroBasico.class);
		filtroP = mock(FiltroPrecio.class);
		filtroH = mock(FiltroCantidadDeHabitantes.class);
		publicaciones= new ArrayList<Publicacion>();
		filtrosExtra= new ArrayList<Filtro>();
		//
		publi1 = mock(Publicacion.class);
		publi2 = mock(Publicacion.class);
		publi3 = mock(Publicacion.class);
		//
		publicaciones.add(publi1);
		publicaciones.add(publi2);
		publicaciones.add(publi3);
	}
	
	@Test
	void testConstructor() {
		assertNotNull(this.buscador);
	}
	
	@Test
	void testBuscadorConFiltroBasico() {
		when(this.filtroB.cumpleFiltrado(publi1)).thenReturn(true);
		when(this.filtroB.cumpleFiltrado(publi2)).thenReturn(false);
		when(this.filtroB.cumpleFiltrado(publi3)).thenReturn(true);
		int resultado = buscador.buscar(publicaciones,filtroB,filtrosExtra).size();
		assertEquals(resultado,2);
	}
	
	@Test
	void testBuscadorConFiltroBasicoYDePrecio() {
		when(this.filtroB.cumpleFiltrado(publi1)).thenReturn(true);
		when(this.filtroB.cumpleFiltrado(publi2)).thenReturn(true);
		when(this.filtroB.cumpleFiltrado(publi3)).thenReturn(true);
		/**/
		when(this.filtroP.cumpleFiltrado(publi1)).thenReturn(true);
		when(this.filtroP.cumpleFiltrado(publi2)).thenReturn(false);
		when(this.filtroP.cumpleFiltrado(publi3)).thenReturn(true);
		filtrosExtra.add(filtroP);
		int resultado = buscador.buscar(publicaciones,filtroB,filtrosExtra).size();
		assertEquals(resultado,2);
	}
	
	@Test
	void testBuscadorConFiltroBasicoDePrecioYDeHuspedes() {
		when(this.filtroB.cumpleFiltrado(publi1)).thenReturn(true);
		when(this.filtroB.cumpleFiltrado(publi2)).thenReturn(true);
		when(this.filtroB.cumpleFiltrado(publi3)).thenReturn(true);
		/**/
		when(this.filtroH.cumpleFiltrado(publi1)).thenReturn(true);
		when(this.filtroH.cumpleFiltrado(publi2)).thenReturn(false);
		when(this.filtroH.cumpleFiltrado(publi3)).thenReturn(true);
		filtrosExtra.add(filtroH);
		int resultado = buscador.buscar(publicaciones,filtroB,filtrosExtra).size();
		assertEquals(resultado,2);
	}
	
	@Test
	void testBuscadorConTodosLosFiltros() {
		when(this.filtroB.cumpleFiltrado(publi1)).thenReturn(true);
		when(this.filtroB.cumpleFiltrado(publi2)).thenReturn(true);
		when(this.filtroB.cumpleFiltrado(publi3)).thenReturn(true);
		/**/
		when(this.filtroH.cumpleFiltrado(publi1)).thenReturn(true);
		when(this.filtroH.cumpleFiltrado(publi2)).thenReturn(false);
		when(this.filtroH.cumpleFiltrado(publi3)).thenReturn(true);
		/**/
		when(this.filtroP.cumpleFiltrado(publi1)).thenReturn(true);
		when(this.filtroP.cumpleFiltrado(publi2)).thenReturn(true);
		when(this.filtroP.cumpleFiltrado(publi3)).thenReturn(false);
		/**/
		filtrosExtra.add(filtroH);
		filtrosExtra.add(filtroP);
		int resultado = buscador.buscar(publicaciones,filtroB,filtrosExtra).size();
		assertEquals(resultado,1);
	}
}

package ar.edu.unq.po2.alquilerestemporales.reserva;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.alquilerestemporales.publicacion.calificable.Calificacion;

class CanceladaTestCase {

	private EstadoReserva estadoReservaCancelada;
	private Reserva reserva;
	private String fechaDeIngreso;
	private String fechaDeSalida;
	private LocalDate fechaDeIngresoDate;
	private LocalDate fechaDeSalidaDate;
	private Calificacion calificacionInmueble;
	private Calificacion calificacionInquilino;
	private Calificacion calificacionPropietario;
	
	@BeforeEach
	void setUp() throws Exception {
		estadoReservaCancelada = new Cancelada();
		reserva = mock(Reserva.class);
		this.fechaDeIngreso = "2022-01-28";
		this.fechaDeSalida = "2022-01-30";
		this.fechaDeIngresoDate = LocalDate.parse(fechaDeIngreso);
		this.fechaDeSalidaDate = LocalDate.parse(fechaDeSalida);
		this.calificacionInmueble = mock(Calificacion.class);
		this.calificacionInquilino = mock(Calificacion.class);
		this.calificacionPropietario = mock(Calificacion.class);
	}
	
	@Test
	void testEstadoDeReservaCanceladaAceptaReserva() {
		assertThrows(Exception.class, () -> {
			this.estadoReservaCancelada.aceptar(reserva);
		});
	}
	
	@Test
	void testEstadoDeReservaCanceladaRechazaReserva() {
		assertThrows(Exception.class, () -> {
			this.estadoReservaCancelada.rechazar(reserva);
		});
	}
	
	@Test
	void testEstadoDeReservaCanceladaCancelaReserva() {
		assertThrows(Exception.class, () -> {
			this.estadoReservaCancelada.cancelar(reserva);
		});
	}
	
	@Test
	void testEstadoDeReservaCanceladaConcluirReserva() {
		assertThrows(Exception.class, () -> {
			this.estadoReservaCancelada.concluir(reserva);
		});
	}
	
	@Test
	void testEstadoDeReservaRechazadaNoEstaOcupadaConOtraReserva() {
		assertFalse(this.estadoReservaCancelada.estaOcupadaCon(fechaDeIngresoDate, fechaDeSalidaDate, reserva));
	}
	
	@Test
	void testEstadoDeReservaCanceladaCalificaPropietario() {
		assertThrows(Exception.class, () -> {
			this.estadoReservaCancelada.calificarInquilinato(reserva, calificacionInquilino);
		});
	}
	
	@Test
	void testEstadoDeReservaCanceladaCalificaInquilino() {
		assertThrows(Exception.class, () -> {
			this.estadoReservaCancelada.calificarEstadia(reserva, calificacionPropietario, calificacionInmueble);
		});
	}
	
}

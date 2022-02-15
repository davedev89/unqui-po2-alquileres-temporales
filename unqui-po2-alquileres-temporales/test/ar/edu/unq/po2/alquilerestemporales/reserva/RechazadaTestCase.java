package ar.edu.unq.po2.alquilerestemporales.reserva;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.alquilerestemporales.publicacion.calificable.Calificacion;

class RechazadaTestCase {

	private EstadoReserva estadoReservaRechazada;
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
		estadoReservaRechazada = new Rechazada();
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
	void testEstadoDeReservaRechazadaAceptaReserva() {
		assertThrows(Exception.class, () -> {
			this.estadoReservaRechazada.aceptar(reserva);
		});
	}
	
	@Test
	void testEstadoDeReservaRechazadaRechazaReserva() {
		assertThrows(Exception.class, () -> {
			this.estadoReservaRechazada.rechazar(reserva);
		});
	}
	
	@Test
	void testEstadoDeReservaRechazadaCancelaReserva() {
		assertThrows(Exception.class, () -> {
			this.estadoReservaRechazada.cancelar(reserva);
		});
	}
	
	@Test
	void testEstadoDeReservaRechazadaConcluirReserva() {
		assertThrows(Exception.class, () -> {
			this.estadoReservaRechazada.concluir(reserva);
		});
	}
	
	@Test
	void testEstadoDeReservaRechazadaNoEstaOcupadaConOtraReserva() {
		assertFalse(this.estadoReservaRechazada.estaOcupadaCon(fechaDeIngresoDate, fechaDeSalidaDate, reserva));
	}
	
	@Test
	void testEstadoDeRechazadaAceptadaCalificaPropietario() {
		assertThrows(Exception.class, () -> {
			this.estadoReservaRechazada.calificarInquilinato(reserva, calificacionInquilino);
		});
	}
	
	@Test
	void testEstadoDeReservaRechazadaCalificaInquilino() {
		assertThrows(Exception.class, () -> {
			this.estadoReservaRechazada.calificarEstadia(reserva, calificacionPropietario, calificacionInmueble);
		});
	}
}

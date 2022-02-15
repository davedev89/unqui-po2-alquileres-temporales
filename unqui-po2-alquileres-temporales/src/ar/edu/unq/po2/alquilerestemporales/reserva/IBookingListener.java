package ar.edu.unq.po2.alquilerestemporales.reserva;

import java.time.LocalDate;

public interface IBookingListener {

	public void reservaCancelada(Reserva reserva, LocalDate fechaInicio, LocalDate fechaFin);

	public void reservaConcretada(Reserva reserva, LocalDate fechaIngreso, LocalDate fechaDeSalida);
}

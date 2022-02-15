package ar.edu.unq.po2.alquilerestemporales.reserva;

import java.time.LocalDate;

import ar.edu.unq.po2.alquilerestemporales.publicacion.calificable.Calificacion;

public interface EstadoReserva{
	
	public void aceptar(Reserva reserva);
	public void rechazar(Reserva reserva);
	public void cancelar(Reserva reserva);
	public void concluir(Reserva reserva);
	public boolean estaOcupadaCon(LocalDate fechaIngreso, LocalDate fechaSalida, Reserva reservaNueva);
	public void calificarEstadia(Reserva reserva, Calificacion calificacionPropietario, Calificacion calificacionInmueble);
	public void calificarInquilinato(Reserva reserva, Calificacion calificacion);
}

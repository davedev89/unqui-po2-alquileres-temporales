package ar.edu.unq.po2.alquilerestemporales.reserva;

import java.time.LocalDate;

import ar.edu.unq.po2.alquilerestemporales.publicacion.calificable.Calificacion;

public class Pendiente implements EstadoReserva{
	
	@Override
	public void aceptar(Reserva reserva) {
		reserva.setEstado(new Aceptada());
		reserva.informarUsuario("Se ha aceptado una de sus reservas", reserva.getInquilino());
		reserva.notificarConcretada();
	}
	
	@Override
	public void rechazar(Reserva reserva) {
		reserva.setEstado(new Rechazada());
		reserva.informarUsuario("Se ha rechazado una de sus reservas", reserva.getInquilino());
	}

	@Override
	public void cancelar(Reserva reserva) {
		reserva.setEstado(new Cancelada());
	}
	
	@Override
	public void concluir(Reserva reserva) {
		throw new RuntimeException("No se puede concluir una reserva que está pendiente");	
	}

	@Override
	public boolean estaOcupadaCon(LocalDate fechaIngreso, LocalDate fechaSalida, Reserva reserva) {
		return false;
	}

	@Override
	public void calificarEstadia(Reserva reserva,Calificacion calificacionPropietario, Calificacion calificacionInmueble) {
		throw new RuntimeException("La reserva no se confirmo todavia, no se puede calificar");
	}

	@Override
	public void calificarInquilinato(Reserva reserva,Calificacion calificacion) {
		throw new RuntimeException("La reserva no se confirmo todavia, no se puede calificar");
	}
}

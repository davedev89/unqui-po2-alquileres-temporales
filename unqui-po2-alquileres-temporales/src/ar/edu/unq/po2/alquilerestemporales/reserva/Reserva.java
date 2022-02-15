package ar.edu.unq.po2.alquilerestemporales.reserva;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.alquilerestemporales.publicacion.PrecioTemporal;
import ar.edu.unq.po2.alquilerestemporales.publicacion.Publicacion;
import ar.edu.unq.po2.alquilerestemporales.publicacion.calificable.Calificacion;
import ar.edu.unq.po2.alquilerestemporales.publicacion.calificable.Inmueble;
import ar.edu.unq.po2.alquilerestemporales.publicacion.calificable.Usuario;
import ar.edu.unq.po2.alquilerestemporales.publicacion.formasDePago.FormaDePago;

public class Reserva {
	private LocalDate fechaIngreso;
	private LocalDate fechaSalida;
	private EstadoReserva estadoDeReserva;
	private Usuario inquilino;
	private Publicacion publicacion;
	private FormaDePago formaDePago;
	private LocalDate fechaRealizacionDeReserva;
	private List<IBookingListener> listeners;
	private float precioFinal;
	
	public Reserva(LocalDate fechaRealizacionDeReserva, Usuario inquilino, LocalDate fechaIngreso, LocalDate fechaSalida, EstadoReserva estadoDeReserva, Publicacion publicacion,FormaDePago formaDePago) {
		this.fechaRealizacionDeReserva = fechaRealizacionDeReserva;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.estadoDeReserva = estadoDeReserva;
		this.inquilino = inquilino;
		this.publicacion = publicacion;
		this.formaDePago = formaDePago;
		this.listeners = new ArrayList<IBookingListener>();
		this.estadoDeReserva = new Pendiente();
		this.precioFinal = this.costoTotal();
	}

	public float costoPorDia() {
		return (this.costoTotal()/this.getDiferenciaDeDias());
	}

	public int diasQueFaltan() {
		Long periodo = Duration.between(this.getFechaRealizacionDeReserva().atStartOfDay(), this.getFechaDeIngreso().atStartOfDay()).toDays();
		return periodo.intValue();
	}

	private LocalDate getFechaRealizacionDeReserva() {
		return this.fechaRealizacionDeReserva;
	}

	public float costoTotal() {
		float costoTotal = 0.00f;
		LocalDate dateInicio = this.fechaIngreso;
		LocalDate dateSalida= this.fechaSalida;
		for(LocalDate date = dateInicio; date.isBefore(dateSalida); date = date.plusDays(1)) {
			costoTotal += precioEnElDia(date);
		}		
		return costoTotal;
	}

	public float precioEnElDia(LocalDate dia) {
		float costo = publicacion.getPrecioBase();
		for (PrecioTemporal temporada : this.publicacion.getTemporadasEspeciales()) {
			if(dia.isAfter(temporada.getInicio())
					&& dia.isBefore(temporada.getFinal())) {
				costo = temporada.getPrecio();
			break;
			}
		}
		return costo;
	}

	public Inmueble getInmueble() {
		return this.publicacion.getInmueble();
	}

	public Usuario getPropietario() {
		return this.publicacion.getPropietario();
	}

	public void setEstado(EstadoReserva estado) {
		this.estadoDeReserva = estado;		
	}

	public Usuario getInquilino() {
		return inquilino;
	}

	public LocalDate getFechaDeIngreso() {
		return this.fechaIngreso;
	}


	public LocalDate getFechaDeSalida() {
		return this.fechaSalida;
	}


	public int getDiferenciaDeDias() {	
		Long periodo = Duration.between(this.getFechaDeIngreso().atStartOfDay(), this.getFechaDeSalida().atStartOfDay()).toDays();
		return Math.abs(periodo.intValue());
	}


	public EstadoReserva getEstadoDeReserva() {
		return this.estadoDeReserva;
	}
	
	public FormaDePago getFormaDePago(){
		return this.formaDePago;
	}
	
	public void addListener(IBookingListener listener) {
		this.listeners.add(listener);
	}
	
	public List<IBookingListener> getListeners() {
		return this.listeners;
	}
	
	public void removeListener(IBookingListener listener) {
		this.listeners.remove(listener);
	}

	public void aceptar() {
		this.estadoDeReserva.aceptar(this);
	}

	public void cancelar() {
		this.estadoDeReserva.cancelar(this);
	}

	public void concluir() {
		this.estadoDeReserva.concluir(this);
	}

	public void rechazar() {
		this.estadoDeReserva.rechazar(this);
	}

	public boolean esReservaDeUsuario(Usuario usuario) {
		return this.getInquilino().equals(usuario);
	}

	public boolean esFutura() {
		return LocalDate.now().compareTo(this.getFechaDeIngreso())<0;
	}

	public String getCiudad() {
		return this.publicacion.getCiudad();
	}

	public boolean esEnCiudad(String ciudad) {
		return getCiudad().equals(ciudad);
	}

	public void informarUsuario(String mensaje, Usuario usuario) {
		usuario.recibirMail(mensaje);
	}

	public void notificarConcretada() {
		for(IBookingListener listener : this.listeners) {
			listener.reservaConcretada(this, this.getFechaDeIngreso(),this.getFechaDeSalida());
		}
	}

	public void notificarCancelada() {
		for(IBookingListener listener : this.listeners) {
			listener.reservaCancelada(this, this.getFechaDeIngreso(),this.getFechaDeSalida());
		}
	}

	public void aplicarPoliticaDeCancelacion() {
		precioFinal = this.publicacion.aplicarPoliticaDeCancelacion(this);
	}
	
	public float getPrecioFinal() {
		return this.precioFinal;
	}

	public boolean seSuporponeCon(Reserva reservaNueva) {
		return this.estadoDeReserva.estaOcupadaCon(this.fechaIngreso,this.fechaSalida,reservaNueva);
	}

	public boolean enMismoPeriodoQueReserva(Reserva reserva) {
		return (fechaIngreso.compareTo(reserva.getFechaDeIngreso())>=0) && (fechaSalida.compareTo(reserva.getFechaDeSalida())<=0);
	}

	public void calificarEstadia(Calificacion calificacionPropietario, Calificacion calificacionInmueble) {
		this.estadoDeReserva.calificarEstadia(this,calificacionPropietario, calificacionInmueble);
	}

	public void calificarInquilinato(Calificacion calificacion) {
		this.estadoDeReserva.calificarInquilinato(this,calificacion);
	}

	public void calificarPropietario(Calificacion calificacionPropietario) {
		this.getPropietario().addCalificacion(calificacionPropietario);
	}

	public void calificarInmueble(Calificacion calificacionInmueble) {
		this.getInmueble().addCalificacion(calificacionInmueble);
	}

	public void calificarInquilino(Calificacion calificacion) {
		this.getInquilino().addCalificacion(calificacion);
	}
}
package ar.edu.unq.po2.alquilerestemporales.webReservas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import ar.edu.unq.po2.alquilerestemporales.publicacion.calificable.Calificacion;
import ar.edu.unq.po2.alquilerestemporales.publicacion.calificable.Usuario;
import ar.edu.unq.po2.alquilerestemporales.reserva.Reserva;

public class BibliotecaDeReservas {

	private List<Reserva> reservas;
	private Queue<Reserva> reservasCondicionales;
	
	public BibliotecaDeReservas() {
		this.reservas = new ArrayList<Reserva>();
		this.reservasCondicionales = new LinkedList<Reserva>();
	}

	public List<Reserva> getTodasLasReservas() {
		return this.reservas;
	}
	
	public List<Reserva> getReservasDeUsuario(Usuario usuario){
        return this.getTodasLasReservas().stream()
        			.filter(reserva-> reserva.esReservaDeUsuario(usuario))
        			.collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Reserva> getReservasFuturas(Usuario usuario){
        return this.getReservasDeUsuario(usuario).stream()
        			.filter(reserva-> reserva.esFutura())
        			.collect(Collectors.toCollection(ArrayList::new));
    }

    public List<String> getCiudadesReservadas(Usuario usuario){
        List<String> ciudades = new ArrayList<String>();
        for(Reserva reserva : this.getReservasDeUsuario(usuario)) {
        	ciudades.add(reserva.getCiudad());
        }
        return ciudades;
    }

    public List<Reserva> getReservasEnCiudadDelUsuario(Usuario usuario, String ciudad){
    	return this.getReservasDeUsuario(usuario).stream()
    			.filter(reserva-> reserva.esEnCiudad(ciudad))
    			.collect(Collectors.toCollection(ArrayList::new));
    }

    public void crearReserva(Reserva reserva){
        if(!estaOcupadaEnFecha(reserva)) {
        	this.reservas.add(reserva);
        }
        else {
        	this.reservasCondicionales.add(reserva);
        }
    }
    
    public boolean estaOcupadaEnFecha(Reserva reservaNueva) {
    	return this.getTodasLasReservas().stream().
    				anyMatch(reserva-> reserva.seSuporponeCon(reservaNueva));
    }
    
    public void concretarReserva(Reserva reserva){
        reserva.aceptar();
    }

    public void rechazarReserva(Reserva reserva){
        reserva.rechazar();
    }

    public void declinarReserva(Reserva reserva){
        reserva.cancelar();
        this.concretarPendienteALaReserva(reserva);
    }
    
    public void concretarPendienteALaReserva(Reserva reserva) {
    	for(Reserva pendiente : this.getReservasCondicionales()) {
    		if(reserva.enMismoPeriodoQueReserva(pendiente)) {
    			pendiente.aceptar();
    			this.getReservasCondicionales().remove(pendiente);
    		}
    	}
    }

    public Queue<Reserva> getReservasCondicionales() {
		return this.reservasCondicionales;
	}

	public void concluirReservas(){
		for(Reserva reserva : this.getTodasLasReservas()) {
			if(LocalDate.now().compareTo(reserva.getFechaDeSalida())>=0) {
				reserva.concluir();
			}
		}
    }

	public void calificarEstadia(Reserva reserva, Calificacion calificacionPropietario, Calificacion calificacionInmueble) {
		reserva.calificarEstadia(calificacionPropietario,calificacionInmueble);
	}

	public void calificarInquilinato(Reserva reserva, Calificacion calificacion) {
		reserva.calificarInquilinato(calificacion);
	}
}

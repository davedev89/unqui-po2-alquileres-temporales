package ar.edu.unq.po2.alquilerestemporales.publicacion;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.alquilerestemporales.politicasDeCancelacion.PoliticaDeCancelacion;
import ar.edu.unq.po2.alquilerestemporales.publicacion.calificable.Inmueble;
import ar.edu.unq.po2.alquilerestemporales.publicacion.calificable.Usuario;
import ar.edu.unq.po2.alquilerestemporales.publicacion.formasDePago.FormaDePago;
import ar.edu.unq.po2.alquilerestemporales.reserva.Reserva;

public class Publicacion {
	
	private LocalTime checkIn;
	private LocalTime checkOut;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private float precioBase;
	private Inmueble inmueble;
	private List <Foto> fotos;
	private Usuario usuario;
	private List <FormaDePago> formasDePago;
	private List <PrecioTemporal> temporadasEspeciales;
	private Observer observador;
	private PoliticaDeCancelacion politicaCancelacion;
	
	public Publicacion(Inmueble inmueble, Usuario usuario, float precioBase, LocalTime checkIn, 
			LocalTime checkOut, List<Foto> fotos, List <FormaDePago> formasDePago,
			LocalDate fecIni, LocalDate fecFin, PoliticaDeCancelacion politCancelacion) {
		
		this.setInmueble(inmueble);
		this.usuario = usuario;
		this.setCheckIn(checkIn);
		this.setCheckOut(checkOut);
		this.fechaInicio=fecIni;
		this.fechaFin=fecFin;
		this.setPrecioBase(precioBase);
		this.setFormasDePago(formasDePago);
		this.setFotos(fotos);
		this.temporadasEspeciales = new ArrayList<PrecioTemporal>();
		this.observador = new Observer();
		this.politicaCancelacion = politCancelacion;
	}
	
	
		
	public void establecerPrecioTemporal(PrecioTemporal precioTemporal) {
		this.getTemporadasEspeciales().add(precioTemporal);
	}
	
	public void verificadorDePrecio() {
		
		for (PrecioTemporal temporada : this.getTemporadasEspeciales()) {
			if(LocalDate.now().isAfter(temporada.getInicio())
					&& LocalDate.now().isBefore(temporada.getFinal())) {
				this.setPrecioBase(temporada.getPrecio());
			}
		}
	}
		
	public void agregarUnaFoto(Foto foto) {
		
		if((!this.getFotos().contains(foto)) &&
				(!espacioParaFotosLleno())) {
			this.getFotos().add(foto);
		}
	}

	private boolean espacioParaFotosLleno() {
		return this.getFotos().size() >= 5;
	}
	
	public String mostrarDatos() {
		
		String mensaje = "";
		mensaje = "La publicacion seleccionada tiene un precio de: $"+ this.getPrecioBase() ;
		mensaje	= mensaje + " El inmueble cuenta con "+ this.getInmueble().getServicios();
		mensaje = mensaje + " Tiene como horario de checkIn las "+ this.getCheckIn()+ " hs";
		mensaje = mensaje + " y como checkOut las "+ this.getCheckOut()+ "hs";
		
		if (!this.getTemporadasEspeciales().isEmpty()) {
			mensaje = mensaje + "Actualmente cuenta con "+ this.getTemporadasEspeciales().size()+ " temporadas con precio especial.";			
		}
		return mensaje;
		
	}
	
	public void bajarDePrecio(float nuevoPrecio) {
		if (nuevoPrecio < this.getPrecioBase()) { //Cumple estrictamente la funcion de bajar, caso contrario 
			this.setPrecioBase(nuevoPrecio);      //se deberia utilizar otro metodo.
			this.notificar();	
		}
	}
	
	public void notificar() {
		this.observador.notificar(this);		
	}

	public void setFormasDePago(List<FormaDePago> formDePago) {
		this.formasDePago = formDePago;
	}
	
	public void setFotos(List<Foto> fotos2) {
		this.fotos = fotos2;
	}

	public void setInmueble(Inmueble inm) {
			this.inmueble = inm;
	}

	public LocalTime getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(LocalTime chIn) {
		
			this.checkIn = chIn;
	}

	public LocalTime getCheckOut() {
		return checkOut;
	}
	
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	
	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setCheckOut(LocalTime chOut) {
		
		if(chOut.isBefore(checkIn)) {
			this.checkOut = chOut;
		} 
	}

	public float getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(float precioBase) {
		this.precioBase = precioBase;
	}
	
	public List<Foto> getFotos() {
		return fotos;
	}

	public Inmueble getInmueble() {
		return inmueble;
	}

	public Usuario getPropietario() {
		return usuario;
	}

	public List<FormaDePago> getFormasDePago() {
		
		return formasDePago;
	}
	
	public List<PrecioTemporal> getTemporadasEspeciales() {
		return temporadasEspeciales;
	}

	public void setObervador(Observer observador){
		this.observador = observador;
	}

	public Observer getObservador() {
		return observador;
	}

	public String getCiudad() {
		
		return this.inmueble.getCiudad();
	}

	public int getCantHabitantes() {
		
		return this.inmueble.getCapacidad();
	}
	
	public PoliticaDeCancelacion getPoliticaDeCancelacion() {
		return politicaCancelacion;
	}
	
	public void setPoliticaDeCancelacion(PoliticaDeCancelacion politCancelacion) {
		this.politicaCancelacion = politCancelacion;
	}

	public float aplicarPoliticaDeCancelacion(Reserva reserva) {
		return this.politicaCancelacion.aplicarCostosDeCancelacion(reserva);
	}

}

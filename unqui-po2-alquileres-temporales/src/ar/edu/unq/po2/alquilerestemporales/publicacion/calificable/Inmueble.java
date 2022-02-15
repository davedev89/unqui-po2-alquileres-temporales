package ar.edu.unq.po2.alquilerestemporales.publicacion.calificable;

import java.util.List;

public class Inmueble{
	
	private double superficie;
	private String pais;
	private String ciudad;
	private String direccion;
	private int capacidad;
	private String tipoDeInmueble;
	private List <String>servicios;
	private BibliotecaDeCalificaciones bibliotecaDeCalificaciones;

	public Inmueble(double superficie, String pais, String ciudad, String direccion, int capacidad,
			String tipoDeInmueble, List<String> servicios) {
		this.setSuperficie(superficie);
		this.setPais(pais);
		this.setCiudad(ciudad);
		this.setDireccion(direccion);
		this.setCapacidad(capacidad);
		this.setTipoDeInmueble(tipoDeInmueble);
		this.setServicios(servicios);
		this.bibliotecaDeCalificaciones = new BibliotecaDeCalificaciones();
	}
		
	public float getPromedioCalificacion() {
		return this.bibliotecaDeCalificaciones.getPromedioCalificacion();
	}

	public List<String> getComentarios() {
		return this.bibliotecaDeCalificaciones.getComentarios();
	}

	public void addCalificacion(Calificacion calificacion) {
		this.bibliotecaDeCalificaciones.addCalificacion(calificacion);
	}
	
	public List<Calificacion> getCalificaciones(){
		return this.bibliotecaDeCalificaciones.getCalificaciones();
	}

	public double getSuperficie() {
		return superficie;
	}

	public void setSuperficie(double superficie) {
		this.superficie = superficie;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public String getTipoDeInmueble() {
		return tipoDeInmueble;
	}

	public void setTipoDeInmueble(String tipoDeInmueble) {
		this.tipoDeInmueble = tipoDeInmueble;
	}

	public void setServicios(List<String> servicios) {
		this.servicios = servicios;
	}
	
	public List<String> getServicios() {
		return servicios;
	}
	
	public void agregarServicio(String servicio) {
		this.getServicios().add(servicio);
	}
	
}

package ar.edu.unq.po2.alquilerestemporales.publicacion.calificable;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaDeCalificaciones {
	
	private List<Calificacion> calificaciones;
	
	public BibliotecaDeCalificaciones() {
		this.calificaciones = new ArrayList<Calificacion>();
	}
	
	public void addCalificacion(Calificacion calificacion) {
		this.calificaciones.add(calificacion);
	}

	public List<String> getComentarios() {
		ArrayList<String> comentarios = new ArrayList<String>();
		calificaciones.stream().forEach(calificacion -> comentarios.add(calificacion.getComentario()));
		return comentarios;
	}

	public float getPromedioCalificacion() {
		return (float) calificaciones.stream().mapToDouble(calificacion-> calificacion.getCalificacion()).average().orElse(0.0);
	}

	public List<Calificacion> getCalificaciones() {
		return calificaciones;
	}

}

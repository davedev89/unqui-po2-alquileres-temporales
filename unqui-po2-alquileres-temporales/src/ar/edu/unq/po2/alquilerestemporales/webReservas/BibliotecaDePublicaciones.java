package ar.edu.unq.po2.alquilerestemporales.webReservas;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.alquilerestemporales.publicacion.Publicacion;

public class BibliotecaDePublicaciones {

	private List<Publicacion> publicaciones;

	public BibliotecaDePublicaciones() {
		this.publicaciones = new ArrayList<Publicacion>();
	}
	
	public void cargarPublicacion(Publicacion publicacion) {
		if(!estaPublicada(publicacion)) {
			this.publicaciones.add(publicacion);
		}
	}
	
	public boolean estaPublicada(Publicacion publicacion) {
		return this.getPublicaciones().stream().
						anyMatch(publi-> (publicacion.getInmueble().equals(publi.getInmueble())));
	}

	public List<Publicacion> getPublicaciones() {
		return this.publicaciones;
	}

}

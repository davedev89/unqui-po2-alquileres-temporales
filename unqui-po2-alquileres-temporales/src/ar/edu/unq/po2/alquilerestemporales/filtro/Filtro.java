package ar.edu.unq.po2.alquilerestemporales.filtro;

import ar.edu.unq.po2.alquilerestemporales.publicacion.Publicacion;

public interface Filtro {
	
	public boolean cumpleFiltrado(Publicacion publicacion);

}

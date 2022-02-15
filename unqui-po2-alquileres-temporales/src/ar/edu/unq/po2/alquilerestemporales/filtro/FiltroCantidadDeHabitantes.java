package ar.edu.unq.po2.alquilerestemporales.filtro;

import ar.edu.unq.po2.alquilerestemporales.publicacion.Publicacion;

public class FiltroCantidadDeHabitantes implements Filtro {

	private int cantidadDeHuespedes;
	
	public FiltroCantidadDeHabitantes(int cantidadDeHuespedes) {
		this.cantidadDeHuespedes = cantidadDeHuespedes;
	}
	
	@Override
	public boolean cumpleFiltrado(Publicacion publicacion) {
		return publicacion.getCantHabitantes() == cantidadDeHuespedes;
	}

	
}

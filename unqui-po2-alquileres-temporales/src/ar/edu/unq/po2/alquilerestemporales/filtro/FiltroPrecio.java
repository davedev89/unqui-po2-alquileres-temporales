package ar.edu.unq.po2.alquilerestemporales.filtro;

import ar.edu.unq.po2.alquilerestemporales.publicacion.Publicacion;

public class FiltroPrecio implements Filtro {
	
	float precioMax;
	float precioMin;
	
    public FiltroPrecio(float precioMax, float precioMin) {
		this.precioMax = precioMax;
		this.precioMin = precioMin;
	}

    @Override
    public boolean cumpleFiltrado(Publicacion publicacion){
        return publicacion.getPrecioBase()>= this.precioMin && publicacion.getPrecioBase()<=this.precioMax;
    }
}

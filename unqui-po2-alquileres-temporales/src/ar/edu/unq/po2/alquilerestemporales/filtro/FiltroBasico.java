package ar.edu.unq.po2.alquilerestemporales.filtro;

import java.time.LocalDate;
import ar.edu.unq.po2.alquilerestemporales.publicacion.Publicacion;

public class FiltroBasico implements Filtro {
	
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private String ciudad;
	
	public FiltroBasico(LocalDate fecIni, LocalDate fecFin, String ciudad){
		this.fechaInicio = fecIni;
		this.fechaFin = fecFin;
		this.ciudad = ciudad;
	}
	
	@Override
	public boolean cumpleFiltrado(Publicacion publicacion){
        return estaEnCiudad(publicacion.getCiudad()) && estaEnFecha(publicacion);  
    }

    public boolean estaEnCiudad(String ciudadPublicada){
        return this.ciudad.equals(ciudadPublicada);
    }

    public boolean estaEnFecha(Publicacion publicacion) {
		return (publicacion.getFechaInicio().compareTo(fechaInicio)>=0) && (publicacion.getFechaFin().compareTo(fechaFin)<=0);
	}


}

package ar.edu.unq.po2.alquilerestemporales.publicacion;

import java.time.LocalDate;


public class PrecioTemporal {
	
	private float precioEspecial;
	private LocalDate desde;
	private LocalDate hasta;

	public PrecioTemporal(float precioEsp, LocalDate dsd, LocalDate hst) {
		
		this.precioEspecial = precioEsp;
		this.desde = dsd;
		this.hasta = hst;
		
	}

	public LocalDate getInicio() {
		
		return desde;
	}

	public LocalDate getFinal() {
		
		return hasta;
	}

	public float getPrecio() {
		
		return precioEspecial;
	}

}

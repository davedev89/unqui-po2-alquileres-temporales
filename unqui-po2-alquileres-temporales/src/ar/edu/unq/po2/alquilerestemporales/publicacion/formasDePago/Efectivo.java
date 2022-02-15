package ar.edu.unq.po2.alquilerestemporales.publicacion.formasDePago;

public class Efectivo implements FormaDePago{
		
	String tipo;
	public Efectivo() {
		this.tipo = "Efectivo";
	}

	@Override
	public String getTipo() {
		return this.tipo;
	}

}

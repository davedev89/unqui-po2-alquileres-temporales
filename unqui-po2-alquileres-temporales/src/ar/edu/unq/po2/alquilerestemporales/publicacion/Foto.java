package ar.edu.unq.po2.alquilerestemporales.publicacion;

public class Foto {
	
	private double height;
	private double width;
	
	public Foto(double height, double width) {
		super();
		this.height = height;
		this.width = width;
	}
	
	public double getAltura() {
		return this.height;
	}
	
	public double getAncho() {
		return this.width;
	}
}

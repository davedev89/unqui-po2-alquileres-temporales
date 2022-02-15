package ar.edu.unq.po2.alquilerestemporales.publicacion.formasDePago;

public class Credito implements FormaDePago {
	

	private String tipo;
	private int numeroTarjeta;
	private int cuotasElegidas;
	
	public Credito(int numTarjeta) {
		this.tipo = "Credito";
		this.setNumeroTarjeta(numTarjeta);
	}

	public int getNumeroTarjeta(){
		return this.numeroTarjeta;
	}
	
	public void setNumeroTarjeta(int numero) {
		this.numeroTarjeta = numero;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	public void setCuotas(int cuotas) {
		this.cuotasElegidas = cuotas;
	}
	
	public int getCuotasElegidas() {
		return this.cuotasElegidas;
	}

}

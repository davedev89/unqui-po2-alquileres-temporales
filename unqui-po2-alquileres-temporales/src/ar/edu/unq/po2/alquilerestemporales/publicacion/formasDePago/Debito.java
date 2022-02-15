package ar.edu.unq.po2.alquilerestemporales.publicacion.formasDePago;

public class Debito implements FormaDePago{

		
	private String tipo;
	private int numeroTarjeta;
	
	public Debito(int numTarjeta) {
		this.tipo = "Debito";
		this.setNumeroTarjeta(numTarjeta);
	}

		
	public String getTipo() {
		return this.tipo;
	}
	
	public int getNumeroTarjeta(){
		return this.numeroTarjeta;
	}
	
	public void setNumeroTarjeta(int numero) {
		this.numeroTarjeta = numero;
	}

}

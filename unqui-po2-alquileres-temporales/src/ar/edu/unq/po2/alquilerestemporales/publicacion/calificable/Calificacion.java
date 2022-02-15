package ar.edu.unq.po2.alquilerestemporales.publicacion.calificable;

public class Calificacion {
	
	private int calificacion;
	private String comentario;
	private String categoria;
	private Usuario autor;
	
	public Calificacion(int numeroCalificacion,String comentario, String categoria, Usuario autor) {
		this.setCalificacion(numeroCalificacion);
		this.setComentario(comentario);
		this.categoria = categoria;
		this.autor=autor;
	}
	
	public int getCalificacion() {
		return calificacion;
	}
	
	public String getComentario() {
		return comentario;
	}
	
	public void setCalificacion(int numeroCalificacion){
		if (numeroCalificacion>=1 && numeroCalificacion<=5) {
			this.calificacion = numeroCalificacion;
		}
		else {
			throw new RuntimeException("No se ha indicado el valor correcto. Ingrese un número entre 1 y 5");
		}
	}
	
	public void setComentario(String comentario) {
		this.comentario=comentario;
	}
	
	public String getCategoria() {
		return this.categoria;
	}
	
	public Usuario getAutor() {
		return this.autor;
	}

	

}

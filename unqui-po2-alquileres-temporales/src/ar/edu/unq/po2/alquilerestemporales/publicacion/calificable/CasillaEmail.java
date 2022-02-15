package ar.edu.unq.po2.alquilerestemporales.publicacion.calificable;

import java.util.ArrayList;
import java.util.List;

public class CasillaEmail {

	String direccion;
	List<String> inbox;
	
	public CasillaEmail(String direccion) {
		this.direccion = direccion;
		inbox = new ArrayList<String>();
	}

	public String getDireccion() {
		return this.direccion;
	}

	public List<String> getInbox() {
		return this.inbox;
	}

	public void recibirMail(String nuevoMail) {
		this.inbox.add(nuevoMail);		
	}	
}
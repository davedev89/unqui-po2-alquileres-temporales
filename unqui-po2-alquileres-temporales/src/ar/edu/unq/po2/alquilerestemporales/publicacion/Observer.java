package ar.edu.unq.po2.alquilerestemporales.publicacion;

import java.util.ArrayList;
import java.util.List;

public class Observer {
	
	private List <IPriceObserver> observadores;
	
	public Observer() {
		observadores = new ArrayList<IPriceObserver>();
	}

	public void notificar(Publicacion publicacion) {
		for(IPriceObserver observador : this.getObservadores()) {
			observador.update(publicacion);
		}
	}
	
	public void attach(IPriceObserver obs) {
		this.getObservadores().add(obs);
	}
	
	public void detach(IPriceObserver obs) {
		this.getObservadores().remove(obs);
	}

	public List <IPriceObserver> getObservadores() {
		return observadores;
	}
	
}

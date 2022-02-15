package ar.edu.unq.po2.alquilerestemporales.politicasDeCancelacion;

import ar.edu.unq.po2.alquilerestemporales.reserva.Reserva;

public interface PoliticaDeCancelacion {
	
	public float aplicarCostosDeCancelacion(Reserva reserva);
}

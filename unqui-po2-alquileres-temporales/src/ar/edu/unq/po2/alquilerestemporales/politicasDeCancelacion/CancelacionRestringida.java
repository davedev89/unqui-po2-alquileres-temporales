package ar.edu.unq.po2.alquilerestemporales.politicasDeCancelacion;

import ar.edu.unq.po2.alquilerestemporales.reserva.Reserva;

public class CancelacionRestringida implements PoliticaDeCancelacion{

	public float aplicarCostosDeCancelacion(Reserva reserva) {

		float costo = reserva.costoTotal();
		return costo;
		
	}
}

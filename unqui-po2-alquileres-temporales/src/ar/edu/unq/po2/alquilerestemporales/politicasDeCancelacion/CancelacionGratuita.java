package ar.edu.unq.po2.alquilerestemporales.politicasDeCancelacion;

import ar.edu.unq.po2.alquilerestemporales.reserva.Reserva;

public class CancelacionGratuita implements PoliticaDeCancelacion {

	public float aplicarCostosDeCancelacion(Reserva reserva) {
		float importeAPagar = 0;
		if(!estaEnPlazoDeCancelacionGratuita(reserva)) {
			importeAPagar = reserva.costoPorDia() * 2;
		}
		return importeAPagar;
	}
	
	private boolean estaEnPlazoDeCancelacionGratuita(Reserva reserva) {
		return reserva.diasQueFaltan() >= 10;
	}
}

package ar.edu.unq.po2.alquilerestemporales.politicasDeCancelacion;

import ar.edu.unq.po2.alquilerestemporales.reserva.Reserva;

public class CancelacionIntermedia implements PoliticaDeCancelacion{

	@Override
	public float aplicarCostosDeCancelacion(Reserva reserva) {
		float importeAPagar = reserva.costoTotal();
		if(estaEnPlazoDeCancelacionGratuita(reserva)) {
			importeAPagar = 0.0f;
		}
		else if (estaEnPlazoDeCancelacionIntermedia(reserva)) {
			importeAPagar = reserva.costoTotal()*(0.5f);
		}
		
		return importeAPagar;
	}

	private boolean estaEnPlazoDeCancelacionGratuita(Reserva reserva) {
		return reserva.diasQueFaltan() >=20;
	}

	private boolean estaEnPlazoDeCancelacionIntermedia(Reserva reserva) {
		return reserva.diasQueFaltan()<=19 && reserva.diasQueFaltan()>=10;
	}

	
	
}

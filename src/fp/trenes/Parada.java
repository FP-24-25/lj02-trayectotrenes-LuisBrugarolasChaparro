package fp.trenes;

import java.time.LocalTime;

import fp.utiles.Checkers;

public record Parada(String estacion, LocalTime horaSalida,
		LocalTime horaLlegada) {

	public Parada {
		Checkers.check("Hora de llegada no valida",
				((horaSalida == null && horaLlegada != null)
						|| (horaLlegada == null && horaSalida != null)
						|| (horaSalida != null && horaSalida != null
								&& horaSalida.isAfter(horaLlegada))));
	}

}

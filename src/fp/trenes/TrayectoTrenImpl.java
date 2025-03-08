package fp.trenes;

import java.time.Duration;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import fp.utiles.Checkers;

public class TrayectoTrenImpl
		implements
			TrayectoTren,
			Comparable<TrayectoTrenImpl> {

	String codigoTren;
	String nombreTrayecto;
	TipoTren tipo;
	List<String> estaciones;
	List<LocalTime> horasSalida;
	List<LocalTime> horasLlegada;

	public TrayectoTrenImpl(String codigoTren, String nombreTrayecto,
			TipoTren tipo, String origen, String destino, LocalTime horaLlegada,
			LocalTime horaSalida) {
		this.codigoTren = codigoTren;
		Checkers.check("Codigo no valido",
				(codigoTren.length() == 5 && codigoValido(codigoTren)));
		this.nombreTrayecto = nombreTrayecto;
		this.tipo = tipo;
		this.estaciones = new LinkedList<String>();
		estaciones.add(origen);
		estaciones.add(destino);
		this.horasSalida = new LinkedList<LocalTime>();
		horasSalida.add(horaSalida);
		horasSalida.add(null);
		Checkers.checkNoNull(horasSalida.getFirst());
		this.horasLlegada = new LinkedList<LocalTime>();
		horasLlegada.add(null);
		horasLlegada.add(horaLlegada);
		Checkers.checkNoNull(horasLlegada.getLast());
		Checkers.check("Hora de salida no valida",
				(horaSalida.isBefore(horaLlegada)));

	}

	// Atributos

	private Boolean codigoValido(String codigoTren) {
		Boolean res = true;
		for (int i = 0; i < codigoTren.length(); i++) {
			Character c = codigoTren.charAt(i);
			if (!(Character.isDigit(c))) {
				res = false;
				break;
			}
		}
		return res;
	}

	@Override
	public String getCodigoTren() {
		return codigoTren;
	}

	@Override
	public String getNombre() {
		return nombreTrayecto;
	}

	@Override
	public TipoTren getTipo() {
		return tipo;
	}

	@Override
	public List<String> getEstaciones() {
		return estaciones;
	}

	@Override
	public List<LocalTime> getHorasSalida() {
		return horasSalida;
	}

	@Override
	public List<LocalTime> getHorasLlegada() {
		return horasLlegada;
	}

	@Override
	public LocalTime getHoraSalida() {
		return horasSalida.getFirst();
	}

	@Override
	public LocalTime getHoraLlegada() {
		return horasLlegada.getLast();
	}

	@Override
	public Duration getDuracionTrayecto() {
		LocalTime horaSalida = horasSalida.getFirst();
		LocalTime horaLlegada = horasLlegada.getLast();
		return Duration.between(horaSalida, horaLlegada);
	}

	@Override
	public LocalTime getHoraSalida(String estacion) {
		LocalTime res = null;
		if (estaciones.contains(estacion) && estacion != estaciones.getLast()) {
			res = horasSalida.get(estaciones.indexOf(estacion));
		}
		return res;
	}

	@Override
	public LocalTime getHoraLlegada(String estacion) {
		LocalTime res = null;
		if (estaciones.contains(estacion)
				&& estacion != estaciones.getFirst()) {
			res = horasSalida.get(estaciones.indexOf(estacion));
		}
		return res;
	}

	@Override
	public void anadirEstacionIntermedia(int posicion, String estacion,
			LocalTime horaLlegada, LocalTime horaSalida) {
		if (1 <= posicion && posicion < estaciones.size()
				&& horaSalida.isAfter(horaLlegada)
				&& horaLlegada.isAfter(horasSalida.get(posicion - 1))
				&& horaSalida.isBefore(horasLlegada.get(posicion))) {

			estaciones.add(posicion, estacion);
			horasSalida.add(posicion, horaSalida);
			horasLlegada.add(posicion, horaLlegada);
		} else {
			throw new IllegalArgumentException("Parametros no validos");
		}

	}

	@Override
	public void eliminarEstacionIntermedia(String estacion) {
		if (estacion != estaciones.getFirst()
				&& estacion != estaciones.getLast()) {
			int posicion = estaciones.indexOf(estacion);
			estaciones.remove(posicion);
			horasSalida.remove(posicion);
			horasLlegada.remove(posicion);
		} else {
			throw new IllegalArgumentException(
					"La estacion es la ultima o la primera");
		}

	}

	@Override
	public String toString() {
		String nulo = "     ";
		String cadena = nombreTrayecto + "-" + tipo + " (" + codigoTren
				+ ")\n\t" + estaciones.getFirst() + "\t" + nulo + "\t"
				+ horasSalida.getFirst() + "\n";
		for (int i = 1; i < estaciones.size() - 1; i++) {
			cadena += "\t" + estaciones.get(i) + "\t"  + horasLlegada.get(i) + "\t"
					+ horasSalida.get(i) + "\n";
		}
		cadena += "\t" + estaciones.getLast() + "\t" + horasLlegada.getLast() + "\t";
		return cadena;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoTren == null) ? 0 : codigoTren.hashCode());
		result = prime * result
				+ ((getHoraSalida() == null) ? 0 : getHoraSalida().hashCode());
		result = prime * result
				+ ((nombreTrayecto == null) ? 0 : nombreTrayecto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TrayectoTrenImpl))
			return false;
		TrayectoTrenImpl other = (TrayectoTrenImpl) obj;
		if (codigoTren == null) {
			if (other.codigoTren != null)
				return false;
		} else if (!codigoTren.equals(other.codigoTren))
			return false;
		if (getHoraSalida() == null) {
			if (other.getHoraSalida() != null)
				return false;
		} else if (!getHoraSalida().equals(other.getHoraSalida()))
			return false;
		if (nombreTrayecto == null) {
			if (other.nombreTrayecto != null)
				return false;
		} else if (!nombreTrayecto.equals(other.nombreTrayecto))
			return false;
		return true;
	}

	@Override
	public int compareTo(TrayectoTrenImpl t) {
		int res = this.getNombre().compareTo(t.getNombre());
		if (res == 0) {
			res = this.getHoraSalida().compareTo(t.getHoraSalida());
			if (res == 0) {
				res = this.getCodigoTren().compareTo(t.getCodigoTren());
			}
		}
		return res;
	}

}

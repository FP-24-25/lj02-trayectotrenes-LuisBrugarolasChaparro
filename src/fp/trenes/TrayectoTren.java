package fp.trenes;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public interface TrayectoTren {
	String getCodigoTren();
	
	String getNombre();
	
	TipoTren getTipo();
	
	List<String> getEstaciones();
	
	List<LocalTime> getHorasSalida();
	
	List<LocalTime> getHorasLlegada();
	
	LocalTime getHoraSalida();
	
	LocalTime getHoraLlegada();
	
	Duration getDuracionTrayecto();
}

package fp.trenes.test;
import java.time.LocalTime;

import fp.trenes.TipoTren;
import fp.trenes.TrayectoTrenImpl2;
public class TestTrayectoTren {

	public static void main(String[] args) {
		TrayectoTrenImpl2 t1 = new TrayectoTrenImpl2("02471", "Sevilla-Madrid",
				TipoTren.AV_CITY, "SEVILLA", "MADRID", LocalTime.of(7, 0),
				LocalTime.of(10, 02));
		
		t1.anadirEstacionIntermedia(1, "PUERTOLLANO", LocalTime.of(8, 41),
				LocalTime.of(8, 40));
		t1.anadirEstacionIntermedia(1, "CORDOBA", LocalTime.of(7, 50),
				LocalTime.of(7, 45));
		System.out.println(t1);
	}

}

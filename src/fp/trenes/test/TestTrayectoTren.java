package fp.trenes.test;
import java.time.LocalTime;

import fp.trenes.TipoTren;
import fp.trenes.TrayectoTrenImpl;
public class TestTrayectoTren {

	public static void main(String[] args) {
		TrayectoTrenImpl t1 = new TrayectoTrenImpl("02471", "Sevilla-Madrid",
				TipoTren.AV_CITY, "SEVILLA", "MADRID", LocalTime.of(10, 02),
				LocalTime.of(7, 0));
		
		t1.anadirEstacionIntermedia(1, "PUERTOLLANO", LocalTime.of(8, 40),
				LocalTime.of(8, 41));
		t1.anadirEstacionIntermedia(1, "CORDOBA", LocalTime.of(7, 45),
				LocalTime.of(7, 50));
		System.out.println(t1);
	}

}

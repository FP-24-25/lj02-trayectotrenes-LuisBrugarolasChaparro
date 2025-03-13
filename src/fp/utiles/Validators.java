package fp.utiles;

public class Validators {
	public static Boolean sonDigitos(String codigoTren) {
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
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ValidacionCorreo {

  public static void main(String args[]) throws IOException {
    String mensajeError = "ERROR";
    Boolean correoIncorrecto = false;

    do {
      String mensajeCorreo = leerString("Introduce el correo");
      if (correoInvalido(mensajeCorreo).equals(mensajeError)) {
        correoIncorrecto = true;
      } else {
        correoIncorrecto = false;
        System.out.println("Correo correcto");
      }
    } while (correoIncorrecto == true);
  }

  public static String correoInvalido(String _correo) {
    String errorCorreo = "ERROR";
    boolean exPun = _correo.contains(".");
    boolean exAt = _correo.contains("@");
    String atributoTemporal = null;
    boolean caracterProhibido = false;
    if (
      _correo == null ||
      exPun == false ||
      exAt == false ||
      _correo.length() > 256 ||
      _correo.length() <= 6
    ) {
      _correo = errorCorreo;
    } else {
      String[] atributosCorr = _correo.split("[@\\.]");
      for (int i = 0; i < atributosCorr.length; i++) {
        if (atributosCorr[i].length() < 1) {
          _correo = errorCorreo;
          caracterProhibido = true;
          break;
        } else {
          atributoTemporal = atributosCorr[i];
          for (int k = 0; k < atributoTemporal.length(); k++) {
            int codigoCaracter = atributoTemporal.charAt(k);
            if (
              !(
                codigoCaracter >= 48 &&
                codigoCaracter <= 57 ||
                codigoCaracter >= 65 &&
                codigoCaracter <= 90 ||
                codigoCaracter >= 97 &&
                codigoCaracter <= 122 ||
                codigoCaracter == 95 ||
                codigoCaracter == 45 ||
                codigoCaracter == 46 ||
                codigoCaracter == 65 ||
                codigoCaracter == 90
              )
            ) {
              caracterProhibido = true;
              _correo = errorCorreo;
              break;
            }
          }
        }
      }
      if (caracterProhibido == false) {
        convertirMinusculas(_correo);
      }
    }
    return _correo;
  }

  public static String convertirMinusculas(String _correoVerificado) {
    char[] arregloChars = null;
    String nuevoCorreo = "Default";
    arregloChars = new char[_correoVerificado.length()];
    _correoVerificado.getChars(0, _correoVerificado.length(), arregloChars, 0);
    for (int m = 0; m < arregloChars.length; m++) {
      char caracterPrueba = arregloChars[m];
      if (Character.isUpperCase(caracterPrueba)) {
        char caracterCambio = Character.toLowerCase(caracterPrueba);
        arregloChars[m] = caracterCambio;
      }
      nuevoCorreo = new String(arregloChars);
    }
    _correoVerificado = nuevoCorreo;
    System.out.println("Correo corregido: " + _correoVerificado);
    return _correoVerificado;
  }

  public static String leerString(String mensaje) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println(mensaje);
    String datoStr3 = br.readLine();
    return datoStr3;
  }
}

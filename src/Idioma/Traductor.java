package Idioma;

import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Traductor {
  private static final String CLIENT_ID = "azuelgamelot3@gmail.com";
  private static final String CLIENT_SECRET = "e2e9f12786a94f629a980374c86ad6fa";
  private static final String ENDPOINT = "http://api.whatsmate.net/v1/translation/translate";

  /**
   * Método para traducir una cadena de texto de un idioma a otro.
   * @param fromLang - El idioma de la cadena de texto.
   * @param toLang - El idioma al que queremos que se traduzca la cadena de texto.
   * @param text - La cadena de texto a traducir.
   * @return String - La cadena de texto al idioma especificado.
   * @throws Exception - Si ocurre un error.
   */
  public static String translate(String fromLang, String toLang, String text) throws Exception {

    String jsonPayload = new StringBuilder()
        .append("{")
        .append("\"fromLang\":\"")
        .append(fromLang)
        .append("\",")
        .append("\"toLang\":\"")
        .append(toLang)
        .append("\",")
        .append("\"text\":\"")
        .append(text)
        .append("\"")
        .append("}")
        .toString();

    URL url = new URL(ENDPOINT);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setDoOutput(true);
    conn.setRequestMethod("POST");
    conn.setRequestProperty("X-WM-CLIENT-ID", CLIENT_ID);
    conn.setRequestProperty("X-WM-CLIENT-SECRET", CLIENT_SECRET);
    conn.setRequestProperty("Content-Type", "application/json");

    OutputStream os = conn.getOutputStream();
    os.write(jsonPayload.getBytes());
    os.flush();
    os.close();

    int statusCode = conn.getResponseCode();
    BufferedReader br = new BufferedReader(new InputStreamReader(
        (statusCode == 200) ? conn.getInputStream() : conn.getErrorStream()));

    StringBuffer output = new StringBuffer();
    String curr;
    while ((curr = br.readLine()) != null) {
      output.append(curr);
    }
    conn.disconnect();
    return output.toString();
  }

}

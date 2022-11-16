package Idioma;

import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Traductor {
  // TODO: If you have your own Premium account credentials, put them down here:
  private static final String CLIENT_ID = "azuelgamelot3@gmail.com";
  private static final String CLIENT_SECRET = "e2e9f12786a94f629a980374c86ad6fa";
  private static final String ENDPOINT = "http://api.whatsmate.net/v1/translation/translate";

  /**
   * Entry Point
   */
  public static void main(String[] args) throws Exception {
    // TODO: Specify your translation requirements here:
    String fromLang = "es";
    String toLang = "en";

    Scanner scanner = new Scanner(System.in);
    System.out.print("Escribe en Español: ");
    String text = scanner.nextLine().strip();

    String translated = Traductor.translate(fromLang, toLang, text);
    System.out.println(translated);
    scanner.close();
  }

  /**
   * Sends out a WhatsApp message via WhatsMate WA Gateway.
   */
  public static String translate(String fromLang, String toLang, String text) throws Exception {
    // TODO: Should have used a 3rd party library to make a JSON string from an
    // object
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
    // System.out.println("Status Code: " + statusCode);
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

package Chat;

import java.util.*;
import java.net.*;
import java.io.*;

public class Cliente implements Runnable {
    private String mensaje;
    private int puerto;

    public Cliente(int puerto, String mensaje) {
        this.puerto = puerto;
        this.mensaje = mensaje;
    }

    public void run() {

        try {

            Socket s = new Socket("localhost", puerto);
            RemoteMessagePassing rmp = new RemoteMessagePassing(s);
            rmp.send(mensaje);
            rmp.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
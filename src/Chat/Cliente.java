package Chat;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente implements Runnable {
    private String mensaje;
    private int puerto;

    /**
     * Método para crear un objeto Cliente.
     * @param puerto - Puerto del Cliente.
     * @param mensaje - Mensaje del Cliente.
     */
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
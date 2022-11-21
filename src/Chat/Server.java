package Chat;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Sujeto, Runnable, Serializable {

    private int puerto;
    private ArrayList<Usuario> usuariosChat = new ArrayList<Usuario>();
    private String mensaje;
    private static Server unicoServer;
    private ServerSocket servidor;

    /**
     * Método para obtener un objeto Server
     * @param puerto - Puerto del server.
     */
    private Server(int puerto) {
        this.puerto = puerto;
        try {
            ServerSocket server = new ServerSocket(puerto);
            servidor = server;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para obtener el Servidor.
     * @param puerto - Puerto del Server.
     * @return Server - Server ha usar.
     */
    public static Server getServer(int puerto) {
        if (unicoServer == null) {
            System.out.println("iniciando puerto");
            unicoServer = new Server(puerto);
            return unicoServer;
        } else {
            System.out.println("server ya iniciado");
            return unicoServer;
        }

    }

    public void agregar(Usuario u) {
        usuariosChat.add(u);
    }

    public void remover(Usuario usuario) {
        usuariosChat.remove(usuario);
    }

    public void notificar() {
        for (Usuario usuario : usuariosChat) {
            usuario.actualizar();
        }
    }

    /**
     * Método para obtener el mensaje recibido.
     * @return String - Mensaje recibido.
     */
    public String getMensaje() {
        return mensaje;
    }

    public void run() {
        try {
            while (true) {
                Socket s = servidor.accept();
                RemoteMessagePassing rmp = new RemoteMessagePassing(s);
                mensaje = (String) rmp.receive();

                this.notificar();
                rmp.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
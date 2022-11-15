package Chat;

import java.util.*;
import java.net.*;
import java.io.*;

public class Server implements Sujeto, Runnable, Serializable {

    private int puerto;
    private ArrayList<Usuario> usuariosChat = new ArrayList<Usuario>();
    private String mensaje;
    private static Server unicoServer;
    private ServerSocket servidor;

    private Server(int puerto) {
        this.puerto = puerto;
        try {
            ServerSocket server = new ServerSocket(puerto);
            servidor = server;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Server getServer(int puerto) {
        if (unicoServer == null) {
            System.out.println("iniciando puerto");
            unicoServer = new Server(puerto);
            return unicoServer;
        } else {
            System.out.println("server ya inciado");
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

    public String getMensaje() {
        return mensaje;
    }

    public void run() {
        try {
            // ServerSocket server = new ServerSocket(puerto);
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
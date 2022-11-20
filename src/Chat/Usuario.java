package Chat;

import UI.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Usuario implements Observador, Serializable {
    private final String idUsuario;

    private final String nombre;

    private Server servidor;
    private int puertoAmigo;
    private String nombreAmigo;

    private final int miPuerto;
    private String lang;

    private Map<String, Usuario> contactos;

    public Usuario(String nombreIn, String idU, int miPuertoIn, String lang) {
        nombre = nombreIn;
        idUsuario = idU;
        this.miPuerto = miPuertoIn;
        this.lang = lang;
        contactos = new HashMap<>();
    }

    public void iniciarServer() {
        servidor = Server.getServer(miPuerto);
        servidor.agregar(this);
        Thread t = new Thread(servidor);
        t.start();
    }

    public void enviarMensaje(String mensaje) {
        Cliente c = new Cliente(puertoAmigo, mensaje);
        Thread t = new Thread(c);
        t.start();

    }

    public void agregarContacto(Usuario usuario) {
        contactos.put(usuario.getIdUsuario(), usuario);
    }

    public void eliminarContacto(String idUsuarioIn) {
        contactos.remove(idUsuarioIn);
    }

    public Usuario getContacto(String idUsuario) {
        return contactos.get(idUsuario);
    }

    public boolean usuarioIn(String idUsuario) {
        return contactos.containsKey(idUsuario);
    }

    public void actualizar() {
        // System.out.print(servidor.getMensaje());
        Controlador.receiveMessage(servidor.getMensaje());

    }

    public String getNombre() {
        return nombre;
    }

    public void setPuertoAmigo(int puertoAmigoIn) {
        puertoAmigo = puertoAmigoIn;
    }

    public int getPuertoAmigo() {
        return puertoAmigo;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public int getMiPuerto() {
        return miPuerto;
    }

    public String getLang() {
        return lang;
    }

    public String getNombreAmigo() {
        return nombreAmigo;
    }

    public Map<String, Usuario> getContactos() {
        return contactos;
    }

    public void setNombreAmigo(String nombreAmigo) {
        this.nombreAmigo = nombreAmigo;
    }

}

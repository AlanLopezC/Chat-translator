package Chat;

import Idioma.*;
import UI.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Usuario implements Observador, Serializable {
    private final String idUsusario;

    private final String nombre;

    private Server servidor;
    private int puertoAmigo;
    private final int miPuerto;
    private String lang;

    private Map<String, Usuario> contactos;

    public Usuario(String nombreIn, String idU, int miPuertoIn, String lang) {
        nombre = nombreIn;
        idUsusario = idU;
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
        // {lang-code}-{mensaje} "es-hola"
        String codedMessage = lang + "-" + mensaje;
        Cliente c = new Cliente(puertoAmigo, codedMessage);
        Thread t = new Thread(c);
        t.start();

        /*
         * c = new Cliente(puertoServer,mensaje);
         * t = new Thread(c);
         * t.start();
         */

    }

    public void agregarContacto(Usuario usuario){
        contactos.put(usuario.getIdUsuario(), usuario);
    }

    public void eliminarContacto(String idUsusarioIn){
        contactos.remove(idUsusarioIn);
    }

    public Usuario getContacto(String idUsusario){
        return contactos.get(idUsusario);
    }

    public boolean usuarioIn(String idUsusariou){
        return contactos.containsKey(idUsusariou);
    }

    public void actualizar() {
        // System.out.print(servidor.getMensaje());
        Vista.receiveMessage(servidor.getMensaje());


    }

    public String getNombre(){
        return nombre;
    }
     
    public void setPuertoAmigo(int puertoAmigoIn){
        puertoAmigo = puertoAmigoIn;
    }

    public int getPuertoAmigo(){
        return puertoAmigo;
    }

    public String getIdUsuario() {
        return idUsusario;
    }

    public int getMiPuerto() {
        return miPuerto;
    }

    public String getLang() {
        return lang;
    }



}

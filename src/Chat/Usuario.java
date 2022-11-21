package Chat;

import UI.MVC.Controlador;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Usuario implements Observador, Serializable {
    private final String idUsusario;

    private final String nombre;

    private Server servidor;
    private int puertoAmigo;
    private String idAmigo;

    private final int miPuerto;
    private String lang;

    private Map<String, Usuario> contactos;

    /**
     * Método constructor de la clase Usuario.
     * @param nombreIn - Nombre del Usuario.
     * @param idU - UserName del Usuario.
     * @param miPuertoIn - Puerto del Usuario.
     * @param lang - Lenguaje o Idioma del Usuario.
     */
    public Usuario(String nombreIn, String idU, int miPuertoIn, String lang) {
        nombre = nombreIn;
        idUsusario = idU;
        this.miPuerto = miPuertoIn;
        this.lang = lang;
        contactos = new HashMap<>();
    }

    /**
     * Método para iniciar el server del Usuario.
     */
    public void iniciarServer() {
        servidor = Server.getServer(miPuerto);
        servidor.agregar(this);
        Thread t = new Thread(servidor);
        t.start();
    }

    /**
     * Método para enviar mensaje al usuario que tiene como puerto el mismo puerto que tiene almacenado el usuario que
     * invoca este método en la atributo puertoAmigo.
     * @param mensaje - Mensaje a enviar.
     */
    public void enviarMensaje(String mensaje) {
        Cliente c = new Cliente(puertoAmigo, mensaje);
        Thread t = new Thread(c);
        t.start();

    }

    /**
     * Método para agregar contactos de la lista de contactos.
     * @param usuario - Usuario a agregar.
     */
    public void agregarContacto(Usuario usuario) {
        contactos.put(usuario.getIdUsuario(), usuario);
    }

    /**
     * Método para eliminar contactos de la lista de contactos.
     * @param idUsusarioIn - UserName del usuario a eliminar.
     */
    public void eliminarContacto(String idUsusarioIn) {
        contactos.remove(idUsusarioIn);
    }

    /**
     * Método para obtener un contacto de tu lista de contactos.
     * @param idUsusario - UserName del contacto que quieres agregar.
     * @return Usuario - contacto encontrado o null si no encuentra el contacto.
     */
    public Usuario getContacto(String idUsusario) {
        return contactos.get(idUsusario);
    }

    /**
     * Método para saber si un usuario está en tu lista de contactos.
     * @param idUsusariou - UserName del usuario.
     * @return Booelan - True si está presente el usuario, False cualquier otro caso.
     */
    public boolean usuarioIn(String idUsusariou) {
        return contactos.containsKey(idUsusariou);
    }

    public void actualizar() {
        Controlador.receiveMessage(servidor.getMensaje());
    }

    /**
     * Método para obtener el nombre del Usuario.
     * @return String - Nombre del Usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método para poner el puerto de la persona con la que deseas hablar.
     * @param puertoAmigoIn - Puerto de la persona.
     */
    public void setPuertoAmigo(int puertoAmigoIn) {
        puertoAmigo = puertoAmigoIn;
    }

    /**
     * Método para obtener el puerto de la persona con la que deseas hablar.
     * @return Int - Puerto.
     */
    public int getPuertoAmigo() {
        return puertoAmigo;
    }

    /**
     * Método para obtener el UserName del usuario.
     * @return String - UserName.
     */
    public String getIdUsuario() {
        return idUsusario;
    }

    /**
     * Método para obtener el puerto del usuario.
     * @return Int - Puerto.
     */
    public int getMiPuerto() {
        return miPuerto;
    }

    /**
     * Método para obtener el idioma del usuario.
     * @return String - Idioma.
     */
    public String getLang() {
        return lang;
    }

    /**
     * Método para obtener los contactos del usuario.
     * @return Map<String, Usuario> - los contactos.
     */
    public Map<String, Usuario> getContactos() {
        return contactos;

    }

    /**
     * Método para obtener el nombre de Usuario de la persona con la que estoy hablando.
     * @return String - Username.
     */
    public String getIdAmigo() {
        return idAmigo;
    }

    /**
     * Método para poner el nombre de usuario de la persona con la que estoy hablando.
     * @param nombreAmigo - UserName.
     */
    public void setIdAmigo(String nombreAmigo) {
        this.idAmigo = nombreAmigo;
    }

}

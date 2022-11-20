package Chat;

import java.io.Serializable;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class UsuarioProxy implements Observador, Serializable, InterfazUsuario{


    private  Usuario usuarioReal;
    private Map<String, Usuario> contactos;

    private int puertoAmigoAgregado;

    private Usuario usuarioAgregado;

    public UsuarioProxy(Usuario usuarioRealIn){
        usuarioReal = usuarioRealIn;
        contactos = new HashMap<>();
    }


    // GETTERS

    public String getNombre(){
        return usuarioReal.getNombre();
    }

    public String getIdUsuario(){
        return usuarioReal.getIdUsuario();
    }

    public int getMiPuerto(){
        return usuarioReal.getMiPuerto();
    }

    public String getLang(){
        return usuarioReal.getLang();
    }

    public int getPuertoAmigo(){
        return usuarioReal.getPuertoAmigo();
    }

    public Map<String, Usuario> getContactos(){
        return usuarioReal.getContactos();
    }

    public boolean usuarioIn(String idUsuario){
        return usuarioReal.usuarioIn(idUsuario);
    }


    // PROXY.

    @Override
    public void actualizarContactos(Usuario usuario) {
        usuarioAgregado = usuario;
        contactos.put(usuario.getIdUsuario(), usuario);
    }

    @Override
    public void actualizarPuertoAmigo(int puertoAmigoIn) {
        puertoAmigoAgregado = puertoAmigoIn;
    }

    public void actualizarContactosEnReales(){
        usuarioReal.actualizarContactos(usuarioAgregado);
    }

    public void actualizarPuertoAmigoEnReal(){
        usuarioReal.actualizarPuertoAmigo(puertoAmigoAgregado);
    }


    // ENVIAR Y RECIBIR MENSAJES

    public void iniciarServer(){
        usuarioReal.iniciarServer();
    }

    public void enviarMensaje(String mensaje){
        usuarioReal.enviarMensaje(mensaje);
    }


    @Override
    public void actualizar() {
        usuarioReal.actualizar();
    }


}

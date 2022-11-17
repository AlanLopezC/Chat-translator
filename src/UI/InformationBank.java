package UI;

import Chat.Usuario;

import java.util.HashMap;
import java.util.Map;

public class InformationBank {

    private Map<String, Usuario> usuarios;

    public InformationBank(){

        usuarios = new HashMap<>();
        usuarios.put("alan123", new Usuario("Alan", "alan123", 5000, "en"));
        usuarios.put("axl123", new Usuario("Axel", "axl123", 5001, "es"));
        usuarios.put("jona123", new Usuario("Jonathan", "jona123", 4000, "es"));
    }

    public void agregarUsuario(Usuario usuario){
        usuarios.put(usuario.getNombre(), usuario);
    }

    public void eliminarUsuario(String usuario){
        usuarios.remove(usuario);
    }

    public Usuario getUsuario(String usuarioId){
        return usuarios.get(usuarioId);
    }
}

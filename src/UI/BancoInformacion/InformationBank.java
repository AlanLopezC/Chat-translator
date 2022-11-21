package UI.BancoInformacion;

import Chat.Usuario;

import java.util.HashMap;
import java.util.Map;

public class InformationBank implements InterfaceIBank {

    private Map<String, Usuario> usuarios;

    /**
     * Método para crear un objeto InformationBank.
     */
    public InformationBank() {

        usuarios = new HashMap<>();
        usuarios.put("alan123", new Usuario("Alan", "alan123", 5000, "en"));
        usuarios.put("axl123", new Usuario("Axel", "axl123", 5001, "es"));
        usuarios.put("jona123", new Usuario("Jonathan", "jona123", 4000, "it"));
    }

    /**
     * Método para agregar usuario.
     * 
     * @param usuario usuario a agregar
     */
    @Override
    public void agregarUsuario(Usuario usuario) {
        usuarios.put(usuario.getNombre(), usuario);
    }

    /**
     * Método para eliminar usuario.
     * 
     * @param usuario usuario a eliminar
     */
    @Override
    public void eliminarUsuario(String usuario) {
        usuarios.remove(usuario);
    }

    /**
     * Método para obtener usuario.
     * 
     * @param usuarioId usuario id.
     * @return Usuario usuario
     */
    @Override
    public Usuario getUsuario(String usuarioId) {
        return usuarios.get(usuarioId);
    }

    /**
     * Método para agregar de verdad el usuario agregado al Banco de Información.
     */
    public void agregarUsuarioReal(Usuario usuario) {
        usuarios.put(usuario.getIdUsuario(), usuario);
    }

    /**
     * Método para agregar de verdad el usuario agregado al Banco de Información.
     */
    public void eliminarUsuarioReal(String usuario) {
        usuarios.remove(usuario);
    }

}

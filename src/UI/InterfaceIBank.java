package UI;

import Chat.Usuario;

public interface InterfaceIBank {

    void agregarUsuario(Usuario usuario);
    void eliminarUsuario(String usuario);
    Usuario getUsuario(String usuarioId);

}

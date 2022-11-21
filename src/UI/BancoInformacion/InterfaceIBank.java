package UI.BancoInformacion;

import Chat.Usuario;

public interface InterfaceIBank {

    /**
     * Método para agregar un usuario al Banco de Información.
     * @param usuario - Usuario a agregar
     */
    void agregarUsuario(Usuario usuario);

    /**
     * Método para eliminar un usuario del Banco de Información.
     * @param usuario - Usuario a eliminar.
     */
    void eliminarUsuario(String usuario);

    /**
     * Método para obtener un usuario del Banco de Información.
     * @param usuarioId
     * @return
     */
    Usuario getUsuario(String usuarioId);

}

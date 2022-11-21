package Chat;

public interface Sujeto {

    /**
     * Método para agregar usuarios al Chat.
     * @param usuario - Usuario a agregar.
     */
    public void agregar(Usuario usuario);

    /**
     * Método para remover usuarios del Chat.
     * @param usuario - Usuario a remover.
     */
    public void remover(Usuario usuario);

    /**
     * Método para notificar al Observador.
     */
    public void notificar();

}
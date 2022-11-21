package UI.MVC;

import Chat.Usuario;
import UI.BancoInformacion.IbankProxy;
import UI.BancoInformacion.InformationBank;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Modelo {

    private IbankProxy bank = new IbankProxy(new InformationBank());
    private TextFlow messagesLog = new TextFlow();

    /**
     * Método que regresa el log de mensajes.
     * 
     * @return TextFlow regresa el messagesLog.
     */
    public TextFlow getMessagesLog() {
        return messagesLog;
    }

    /**
     * Método que agrega mensaje al log.
     * 
     * @param message texto a agregar al log.
     */
    public void addMessage(String message) {
        messagesLog.getChildren().add(new Text(message));
    }

    /**
     * Método que agrega nodo al log. Generalmente texto o linea separadora.
     * 
     * @param node texto a agregar al log.
     */
    public void addMessage(Node node) {
        messagesLog.getChildren().add(node);
    }

    /**
     * Método que regresa el usuario por id.
     * 
     * @param idU id de usuario.
     * @return Usuario Usuario encontrado.
     */
    public Usuario getUsuario(String idU) {
        return bank.getUsuario(idU);
    }

    /**
     * Método que agrega usuarios al banco.
     * 
     * @param usuario Usuario a agregar.
     */
    public void agregarUsuario(Usuario usuario) {
        bank.agregarUsuario(usuario);
    }

    /**
     * Método que elimina usuarios del banco.
     * 
     * @param usuario Usuario a eliminar.
     */
    public void eliminarUsuario(String usuario) {
        bank.eliminarUsuario(usuario);
    }

    /**
     * Método que regresa banco.
     * 
     * @return IbankProxy regresa Banco.
     */
    public IbankProxy getBank() {
        return bank;
    }

}

package UI;

import Chat.Usuario;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Modelo {

    private InformationBank bank = new InformationBank();
    private TextFlow messagesLog = new TextFlow();

    public TextFlow getMessagesLog() {
        return messagesLog;
    }

    public void addMessage(String message) {
        messagesLog.getChildren().add(new Text(message));
    }

    public void addMessage(Node node) {
        messagesLog.getChildren().add(node);
    }

    public Usuario getUsuario(String idU) {
        return bank.getUsuario(idU);
    }

    public void agregarUsuario(Usuario usuario) {
        bank.agregarUsuario(usuario);
    }

    public void eliminarUsuario(String usuario) {
        bank.eliminarUsuario(usuario);
    }
}

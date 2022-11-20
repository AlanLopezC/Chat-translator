package UI;

import Chat.Usuario;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Modelo {

    private InformationBank bank = new InformationBank();
    private TextFlow messageLog = new TextFlow();

    public TextFlow getMessageLog() {
        return messageLog;
    }

    public void addToMessageLog(String message) {
        messageLog.getChildren().add(new Text(message));
    }

    public void addToMessageLog(Node text) {
        messageLog.getChildren().add(text);
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

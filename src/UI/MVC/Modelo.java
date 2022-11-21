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

    public IbankProxy getBank(){
        return bank;
    }

}

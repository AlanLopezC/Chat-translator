package UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import Chat.*;
import Idioma.Traductor;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class Controlador {

    // private InformationBank informationBank = new InformationBank();
    private Vista vista;
    private static TextFlow messageLog;
    private Usuario usuario; // = informationBank.getUsuario("jona123");

    public Controlador(Stage stage) {
        Controlador.messageLog = new TextFlow();
        vista = new Vista(this, stage, messageLog);
        vista.CrearComponentesyVista();
    }

    public Usuario getUsuario(String idU) {

        usuario = Modelo.getUsuario(idU);
        // return informationBank.getUsuario(un);
        return usuario;
    }

    public void salir() {
        System.exit(0);
    }

    public void entrarChat() {
        vista.crearElementos();
        vista.iniciarVistaChat();
    }

    public static void receiveMessage(String message) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                Separator separator = new Separator(Orientation.HORIZONTAL);
                separator.setStyle("-fx-background-color: black;");
                messageLog.getChildren().add(new Text(System.lineSeparator()));

                Text text = new Text(message + "\n"
                        + DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(LocalDateTime.now()) + "\n");
                text.setStroke(Color.BLACK);
                // messageLog.getChildren(new Text(System.lineSeparator()));
                messageLog.getChildren().add(text);
                messageLog.getChildren().add(new Text(System.lineSeparator()));

                separator.prefWidthProperty().bind(messageLog.widthProperty());
                messageLog.getChildren().add(separator);

            }
        });
    }

    public void enviarMensaje(TextFlow messageLog, TextField textField) {
        String message = textField.getText();
        if (!message.isEmpty()) {

            Separator separator = new Separator(Orientation.HORIZONTAL);
            separator.setStyle("-fx-background-color: black;");
            messageLog.getChildren().add(new Text(System.lineSeparator()));

            Text nombre = new Text(usuario.getNombre() + ": ");
            nombre.setFill(Color.BLUE);

            messageLog.getChildren().add(nombre);
            Text text = new Text(message + "\n"
                    + DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(LocalDateTime.now()) + "\n");
            text.setStroke(Color.BLACK);
            // messageLog.getChildren(new Text(System.lineSeparator()));
            messageLog.getChildren().add(text);
            messageLog.getChildren().add(new Text(System.lineSeparator()));

            separator.prefWidthProperty().bind(messageLog.widthProperty());
            messageLog.getChildren().add(separator);
            textField.clear();

            if (usuario.getPuertoAmigo() != 0) {

                String otherUserLang = Modelo.getUsuario(usuario.getNombreAmigo()).getLang();

                if (otherUserLang != usuario.getLang()) {

                    try {
                        message = translateMessage(message, usuario.getLang(), otherUserLang);
                    } catch (Exception e) {
                        System.err.println("Couldn't translate message, error: " + e);
                    }
                }

                usuario.enviarMensaje(usuario.getNombre() + ": " + message);
                System.out.print("mensaje enviado");
            }
        }
    }

    private static String translateMessage(String message, String fromLang, String toLang) throws Exception {
        return Traductor.translate(fromLang, toLang, message);
    }

}

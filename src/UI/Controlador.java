package UI;

import Chat.*;
import Idioma.Traductor;
import UI.Languages.LanguageInterface;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.application.Platform;
import javafx.geometry.*;
import javafx.scene.paint.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Controlador {

    private Vista vista;
    // MessageLog in Modelo
    private static TextFlow messageLog;
    private Usuario usuario; // = informationBank.getUsuario("jona123");
    private Modelo modelo;

    public Controlador(Stage stage) {
        Controlador.messageLog = new TextFlow();
        vista = new Vista(this, stage, messageLog);
        vista.CrearComponentesyVista();
        modelo = new Modelo();
    }

    public Usuario logIn(String userName) {
        Usuario usuario = modelo.getUsuario(userName);
        if (usuario == null) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Aviso de información");
            alert.setContentText("Usuario Incorrecto");
            alert.showAndWait();
        }
        return usuario;
    }

    public Usuario getUsuario(String idU) {
        usuario = modelo.getUsuario(idU);
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

                String otherUserLang = modelo.getUsuario(usuario.getNombreAmigo()).getLang();

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

    public Usuario agregarUsuario(LanguageInterface languageI, Usuario usuario) {

        try {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText(languageI.enterUserName());
            dialog.setTitle(languageI.addContact());

            Optional<String> response = dialog.showAndWait();
            String userName = response.get();

            if (userName.isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(languageI.informationNotice());
                alert.setContentText(languageI.fieldEmpty());
                alert.showAndWait();

                return null;
            }

            Usuario usuario1 = this.getUsuario(userName); // OBTENIENDO DEL BANCO DE INFORMACIÓN.

            if (usuario1 == null) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(languageI.informationNotice());
                alert.setContentText(languageI.userNameDontExit());
                alert.showAndWait();

                return null;
            }

            if (usuario.usuarioIn(usuario1.getIdUsuario())) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(languageI.informationNotice());
                alert.setContentText(languageI.userHasAdded());
                alert.showAndWait();

                return null;

            }

            if (usuario1.getIdUsuario().equals(usuario.getIdUsuario())) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(languageI.informationNotice());
                alert.setContentText(languageI.cannotAddYourself());
                alert.showAndWait();

                return null;
            }

            return usuario1;

        } catch (NoSuchElementException ignored) {

        }
        return null;
    }

    public void agregarContactoMenu(Usuario usuario) {
        if (usuario != null) {
            vista.agregarContacto(usuario);
        }
    }

    public void toggle(RadioMenuItem rmi, LanguageInterface li, Usuario usuario, Label label) {
        String s = rmi.getText();

        Usuario userToTalk = this.getUsuario(s);
        label.setText(li.senderDescription() + userToTalk.getNombre());

        usuario.setPuertoAmigo(userToTalk.getMiPuerto());
        usuario.setNombreAmigo(userToTalk.getIdUsuario());
        usuario.iniciarServer();

    }

    public void regresarLogin() {
        vista.regresarLogin();
    }

    public void cargarContactos(Menu contactos, Usuario usuario) {
        for (String contacto : usuario.getContactos().keySet()) {
            contactos.getItems().add(new RadioMenuItem(contacto));
        }
    }

}

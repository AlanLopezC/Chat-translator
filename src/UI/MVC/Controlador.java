package UI.MVC;

import UI.Languages.*;
import Chat.*;
import Idioma.*;
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
    private static Modelo modelo;

    /**
     * Método para crear un objeto controlador
     * @param stage - Stage
     */
    public Controlador(Stage stage) {
        modelo = new Modelo();
        vista = new Vista(this, stage, modelo.getMessagesLog());
        vista.CrearComponentesyVista();
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

    public Usuario getUsuario(String un) {
        return modelo.getUsuario(un);
    }

    public void salir() {
        System.exit(0);
    }

    public void entrarChat() {
        vista.crearElementos();
        vista.iniciarVistaChat();
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
        usuario.setIdAmigo(userToTalk.getIdUsuario());
        usuario.iniciarServer();

    }

    public static void receiveMessage(String message) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                Separator separator = new Separator(Orientation.HORIZONTAL);
                separator.setStyle("-fx-background-color: black;");
                modelo.addMessage(new Text(System.lineSeparator()));

                Text text = new Text(message + "\n"
                        + DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(LocalDateTime.now()) + "\n");
                text.setStroke(Color.BLACK);
                // textFlow.getChildren(new Text(System.lineSeparator()));
                modelo.addMessage(text);
                modelo.addMessage(new Text(System.lineSeparator()));

                separator.prefWidthProperty().bind(modelo.getMessagesLog().widthProperty());
                modelo.addMessage(separator);

            }
        });
    }

    private static String translateMessage(String message, String fromLang, String toLang) throws Exception {
        return Traductor.translate(fromLang, toLang, message);
    }

    public void Chatear(TextField textField, TextFlow textFlow, Usuario usuario) {
        final String mensaje = textField.getText();
        if (!mensaje.isEmpty()) {

            Separator separator = new Separator(Orientation.HORIZONTAL);
            separator.setStyle("-fx-background-color: black;");
            modelo.addMessage(new Text(System.lineSeparator()));

            Text nombre = new Text(usuario.getNombre() + ": ");
            nombre.setFill(Color.BLUE);

            modelo.addMessage(nombre);
            Text text = new Text(mensaje + "\n"
                    + DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(LocalDateTime.now()) + "\n");
            text.setStroke(Color.BLACK);
            // textFlow.getChildren(new Text(System.lineSeparator()));
            modelo.addMessage(text);
            modelo.addMessage(new Text(System.lineSeparator()));

            separator.prefWidthProperty().bind(textFlow.widthProperty());
            modelo.addMessage(separator);
            textField.clear();

            if (usuario.getPuertoAmigo() != 0) {

                enviarMensaje(usuario, mensaje);

            }
        }
    }

    private static void enviarMensaje(Usuario usuario, String message) {
        Runnable runnable = () -> {
            String otherUserLang = modelo.getUsuario(usuario.getIdAmigo()).getLang();

            if (otherUserLang != usuario.getLang()) {

                try {
                    final String translated = translateMessage(message, usuario.getLang(), otherUserLang);
                    usuario.enviarMensaje(usuario.getNombre() + ": " + translated);
                } catch (Exception e) {
                    System.err.println("Couldn't translate message, error: " + e);
                }
            }

        };

        Thread t = new Thread(runnable);
        t.start();
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

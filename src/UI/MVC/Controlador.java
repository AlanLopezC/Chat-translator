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
     * 
     * @param stage - Stage
     */
    public Controlador(Stage stage) {
        modelo = new Modelo();
        vista = new Vista(this, stage, modelo.getMessagesLog());
        vista.CrearComponentesyVista();
    }

    /**
     * Método para iniciar sesion.
     * 
     * @param userName nombre de usuario.
     * @return Usuario usuario ingresado.
     */
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

    /**
     * Regresa usuario según id.
     * 
     * @param id id de usuario.
     * @return Usuario usuario encontrado.
     */
    public Usuario getUsuario(String id) {
        return modelo.getUsuario(id);
    }

    /**
     * Método para cerrar programa
     * 
     */
    public void salir() {
        System.exit(0);
    }

    /**
     * Método para entrar al chat.
     * 
     */
    public void entrarChat() {
        vista.crearElementos();
        vista.iniciarVistaChat();
    }

    /**
     * Método para agregar usuario.
     * 
     * @param languageI lenguaje de interfaz.
     * @param usuario   usuario a agregar.
     * @return Usuario usuario agregado.
     */
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

    /**
     * Método para agregar contacto a menu.
     * 
     * @param usuario usuario a agregar.
     */
    public void agregarContactoMenu(Usuario usuario) {
        if (usuario != null) {
            vista.agregarContacto(usuario);
        }
    }

    /**
     * Método para hacer efecto toggle.
     * 
     * @param rmi     Menu item.
     * @param li      Lenguaje de interfaz.
     * @param Usuario Usuario de quien ve.
     * @param label   Descripcion.
     */
    public void toggle(RadioMenuItem rmi, LanguageInterface li, Usuario usuario, Label label) {
        String s = rmi.getText();

        Usuario userToTalk = this.getUsuario(s);
        label.setText(li.senderDescription() + userToTalk.getNombre());

        usuario.setPuertoAmigo(userToTalk.getMiPuerto());
        usuario.setIdAmigo(userToTalk.getIdUsuario());
        usuario.iniciarServer();

    }

    /**
     * Mpetodo para recibir mensaje.
     * 
     * @param message Mensaje a recibir.
     */
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

    /**
     * Traduce mensaje.
     * 
     * @param message  Mensaje a traducir
     * @param fromLang Lenguaje inicial
     * @param toLang   Lenguaje final
     * @return String mensaje traducido
     * @throws Exception
     */
    private static String translateMessage(String message, String fromLang, String toLang) throws Exception {
        return Traductor.translate(fromLang, toLang, message);
    }

    /**
     * Metodo para chatear o enviar mensaje.
     * 
     * @param textField De donde tomar texto
     * @param textFlow  Donde escribir texto
     * @param usuario   Usuario que desea enviar mensaje.
     */
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

    /**
     * Metodo que determina el cambio de idioma.
     * * En otro thread para smooth efect.
     * 
     * @param usuario Usuario quien envía.
     * @param message Mensaje que envía.
     */
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
            } else {
                usuario.enviarMensaje(usuario.getNombre() + ": " + message);
            }

        };

        Thread t = new Thread(runnable);
        t.start();
    }

    /**
     * Metodo para regresar al login.
     */
    public void regresarLogin() {
        vista.regresarLogin();
    }

    /**
     * Metodo para cargar contactos.
     * 
     * @param contactos Contactos a cargar
     * @param usuario   usuario de quien se cargan.
     */
    public void cargarContactos(Menu contactos, Usuario usuario) {
        for (String contacto : usuario.getContactos().keySet()) {
            contactos.getItems().add(new RadioMenuItem(contacto));
        }
    }

}

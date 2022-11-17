package UI;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.NoSuchElementException;
import java.util.Optional;

import Chat.Usuario;
import Idioma.Traductor;
import UI.Languages.*;

public class Controller {

    private MenuBar menuBar;
    private Menu configuration;
    private Menu contactos;
    private Label label;
    private ScrollPane scrollPane;
    private static TextFlow textFlow;
    private HBox hBox;
    private TextField textField;
    private Button button;
    private BorderPane borderPane;
    private VBox root;

    private ToggleGroup toggleGroup;

    private InformationBank informationBank = new InformationBank();

    private LanguageInterface languageI;

    private static Usuario usuario;

    private VBox getConfigS() {
        switch (usuario.getLang()) {
            case "es":
                languageI = new SpanishUI();
                break;
            case "en":
                languageI = new EnglishUI();
                break;
            case "it":
                languageI = new ItalianUI();
                break;
            default:
                languageI = new SpanishUI();
        }

        // CONFIGURANDO EL MENU BAR
        menuBar = View.getMenuBar();
        menuBar.setMinHeight(25);


        for (String menuLabel : languageI.menuLabels()) {
            menuBar.getMenus().add(View.getMenu(menuLabel));
        }


        contactos = menuBar.getMenus().get(0);
        configuration = menuBar.getMenus().get(1);


        menuBar.getMenus().get(0).getItems().add(View.getRadioMenuItem(languageI.setContact()));
        menuBar.getMenus().get(1).getItems().add(View.getRadioMenuItem(languageI.setPassWord()));

        toggleGroup = View.getToggleGroup();



        // CONFIGURANDO LABEL
        // ! Hardcodeado para 2 usuarios
       // String otherUsername = usuario.getNombre().compareTo("Pedro") == 0 ? "Carlos" : "Pedro";
        // label = View.getLabel(languageI.senderDescription() + otherUsername + " ...");
        label = View.getLabel(languageI.setActive());

        // CONFIGURANDO TEXT FLOW
        textFlow = View.getTextFlow();

        // CONFIGURANDO SCROLL PANE
        scrollPane = View.getScrollPane();
        scrollPane.setContent(textFlow);

        // CONFIGURANDO BUTTON
        button = View.getSendButton(languageI.sendButtonLabel());

        // CONFIGURANDO TEXT FIELD
        textField = View.getTextField();

        // CONFIGURANDO HBOX
        hBox = View.getBottonMenu(10, textField, button);

        // CONFIGURANDO LOS MARGINS
        View.setMargin(View.getMargin(10), label, scrollPane, hBox);

        // CONFIGURANDO BORDER PANE
        borderPane = View.getLayout(label, scrollPane, hBox);

        return View.getRoot(menuBar, borderPane);
    }

    public void iniciar(String arg) {

        // ! Hardcodeado para 2 usuarios
        /* if (arg.compareTo("1") == 0) {
            usuario = new Usuario("Charlie", 5000, 5001, "en");
        } else {
            usuario = new Usuario("Pedro", 5001, 5000, "es");
        }*/

        // PARA INICIAR SE NECESITA EL NOMBRE DE USUARIO.
        usuario = informationBank.getUsuario("alan123");


        // usuario.iniciarServer(); SE MODIFICARÁ.

        // AQUÍ SE DECIDE QUE INTERFAZ SE USARA Y SE CARGAN LOS USUARIOS.
        root = getConfigS();

        button.setOnAction(e -> {
            final String message = textField.getText();
            if (!message.isEmpty()) {

                Separator separator = new Separator(Orientation.HORIZONTAL);
                separator.setStyle("-fx-background-color: black;");
                textFlow.getChildren().add(new Text(System.lineSeparator()));

                Text nombre = new Text(usuario.getNombre() + ": ");
                nombre.setFill(Color.BLUE);

                textFlow.getChildren().add(nombre);
                Text text = new Text(message + "\n"
                        + DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(LocalDateTime.now()) + "\n");
                text.setStroke(Color.BLACK);
                // textFlow.getChildren(new Text(System.lineSeparator()));
                textFlow.getChildren().add(text);
                textFlow.getChildren().add(new Text(System.lineSeparator()));

                separator.prefWidthProperty().bind(textFlow.widthProperty());
                textFlow.getChildren().add(separator);
                textField.clear();

                if (usuario.getPuertoAmigo() != 0){
                    usuario.enviarMensaje(usuario.getNombre() + ": " + message);
                }
            }
        });


        contactos.getItems().get(0).setOnAction(e -> {

            Usuario usuario1 = getUsuario();
            if (usuario1 != null){
                RadioMenuItem radioMenuItem = View.getRadioMenuItem(usuario1.getIdUsuario());
                radioMenuItem.setToggleGroup(toggleGroup);
                contactos.getItems().add(radioMenuItem);
                usuario.agregarContacto(usuario1);
            }
        });

        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {

                if (toggleGroup.getSelectedToggle() != null){

                    textFlow.getChildren().clear();

                    RadioMenuItem radioMenuItem = (RadioMenuItem) toggleGroup.getSelectedToggle();
                    String s = radioMenuItem.getText();

                    Usuario userToTalk = informationBank.getUsuario(s);
                    label.setText(languageI.senderDescription() + userToTalk.getNombre());

                    usuario.setPuertoAmigo(userToTalk.getMiPuerto());
                    usuario.iniciarServer();

                }
            }
        });

    }

    public VBox getRoot() {
        return root;
    }

    public static void receiveMessage(String message) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                Separator separator = new Separator(Orientation.HORIZONTAL);
                separator.setStyle("-fx-background-color: black;");
                textFlow.getChildren().add(new Text(System.lineSeparator()));

                // We decode it because comes in {languageCode}-{message} format, and we
                // translate it also.
                String translatedMessage;
                try {
                    translatedMessage = translateMessage(message);
                } catch (Exception e) {
                    translatedMessage = message + " (Couldn't translate)";
                }
                Text text = new Text(translatedMessage + "\n"
                        + DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(LocalDateTime.now()) + "\n");
                text.setStroke(Color.BLACK);
                // textFlow.getChildren(new Text(System.lineSeparator()));
                textFlow.getChildren().add(text);
                textFlow.getChildren().add(new Text(System.lineSeparator()));

                separator.prefWidthProperty().bind(textFlow.widthProperty());
                textFlow.getChildren().add(separator);

            }
        });
    }

    private static String translateMessage(String encodedString) throws Exception {
        // es-Pedro: Hola a todos
        String[] decoded = encodedString.split("-", 2);
        String langCode = decoded[0];
        String[] rest = decoded[1].split(":", 2);
        String name = rest[0] + ": ";
        String message = rest[1];

        return name + Traductor.translate(langCode, usuario.getLang(), message);

    }

    private Usuario getUsuario(){

        try {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText(languageI.enterUserName());
            dialog.setTitle(languageI.addContact());

            Optional<String> response = dialog.showAndWait();
            String userName = response.get();

            if (userName.isEmpty()){

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(languageI.informationNotice());
                alert.setContentText(languageI.fieldEmpty());
                alert.showAndWait();

                return null;
            }


            Usuario usuario1 = informationBank.getUsuario(userName); // OBTENIENDO DEL BANCO DE INFORMACIÓN.

            if (usuario1 == null){

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(languageI.informationNotice());
                alert.setContentText(languageI.userNameDontExit());
                alert.showAndWait();

                return null;
            }

            if (usuario.usuarioIn(usuario1.getIdUsuario())){

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(languageI.informationNotice());
                alert.setContentText(languageI.userHasAdded());
                alert.showAndWait();

                return null;

            }

            if (usuario1.getIdUsuario().equals(usuario.getIdUsuario())){

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(languageI.informationNotice());
                alert.setContentText(languageI.cannotAddYourself());
                alert.showAndWait();

                return null;
            }

            return usuario1;

        } catch (NoSuchElementException ignored){

        }
        return null;
    }
}

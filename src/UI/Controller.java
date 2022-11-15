package UI;

import javafx.application.Platform;
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

import Chat.Usuario;

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

    private Usuario usuario;

    public VBox getConfigS() {

        // CONFIGURANDO EL MENU BAR
        menuBar = View.getMenuBar();
        menuBar.setMinHeight(25);

        menuBar.getMenus().addAll(View.getMenu("Contactos"), View.getMenu("Configuración"));

        menuBar.getMenus().get(0).getItems().addAll(View.getMenu("Roberto"), View.getMenu("Carlos"));
        menuBar.getMenus().get(1).getItems().addAll(View.getMenu("Contraseña"));

        // CONFIGURANDO LABEL
        label = View.getLabel("Estas hablando con Pedro ....");

        // CONFIGURANDO TEXT FLOW
        textFlow = View.getTextFlow();

        // CONFIGURANDO SCROLL PANE
        scrollPane = View.getScrollPane();
        scrollPane.setContent(textFlow);

        // CONFIGURANDO BUTTON
        button = View.getSendButton("Enviar");

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

        if (arg.compareTo("1") == 0) {
            usuario = new Usuario("Carlos", 5000, 5001);
        } else {
            usuario = new Usuario("Pedro", 5001, 5000);
        }

        usuario.iniciarServer();

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

                usuario.enviarMensaje(usuario.getNombre() + ": " + message);
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

                Text text = new Text(message + "\n"
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

}

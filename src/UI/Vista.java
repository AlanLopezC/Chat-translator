package UI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.scene.Cursor;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.NoSuchElementException;
import java.util.Optional;

import UI.Languages.*;
import Chat.*;

public class Vista {
    Label labelT, labelU;
    TextField textU;
    Button btnEntrar, btnSalir;
    String nombreUsuario;
    Controlador controlador;
    VBox root;
    Scene scene1;
    Scene scene2;
    private MenuBar menuBar;
    private Menu configuration;
    private Menu contactos;
    private Label label;
    private ScrollPane scrollPane;
    private static TextFlow textFlow;
    private HBox hBox;
    private TextField textField;
    private Button sendButton;
    private BorderPane borderPane;
    private VBox root2;
    private ToggleGroup toggleGroup;
    // private InformationBank informationBank = new InformationBank();
    private LanguageInterface languageI;
    private static Usuario usuario;
    private Stage stage;
    final static double WIDTH = 700;
    final static double HEIGHT = 500;

    public Vista(Controlador controlador, Stage stage, TextFlow messageLog) {
        this.controlador = controlador;
        this.stage = stage;
        textFlow = messageLog;
    }

    public void CrearComponentesyVista() {
        labelT = new Label("Login");
        labelT.setFont(new Font(25));
        labelU = new Label("Usuario");
        labelU.setFont(new Font(15));
        textU = new TextField();
        textU.setMaxWidth(150);
        btnEntrar = new Button("Iniciar sesión");
        btnEntrar.setMaxWidth(150);
        btnEntrar.setCursor(Cursor.HAND);
        btnEntrar.setOnAction(e -> {

            usuario = controlador.getUsuario(textU.getText());
            controlador.entrarChat();

            sendButton.setOnAction(ev -> controlador.enviarMensaje(textFlow, textField));
        });
        btnSalir = new Button("Salir");
        btnSalir.setMaxWidth(150);
        btnSalir.setCursor(Cursor.HAND);
        btnSalir.setOnAction(event -> controlador.salir());

        root = new VBox();

        root.getChildren().addAll(labelT, labelU, textU, btnEntrar, btnSalir);
        root.setAlignment(Pos.CENTER);

        VBox.setMargin(labelU, new Insets(10, 0, 0, 0));
        VBox.setMargin(btnEntrar, new Insets(10, 0, 0, 0));
        VBox.setMargin(btnSalir, new Insets(12, 0, 0, 0));

        scene1 = new Scene(root, 300, 300);
        stage.setTitle("Translator Conversation");
        stage.setScene(scene1);
        // stage.resizableProperty().set(false);

        // MOSTRANDO STAGE
        stage.show();

    }

    public void crearElementos() {
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
        menuBar = new MenuBar();
        menuBar.setMinHeight(25);

        for (String menuLabel : languageI.menuLabels()) {
            menuBar.getMenus().add(new Menu(menuLabel));
        }

        contactos = menuBar.getMenus().get(0);
        configuration = menuBar.getMenus().get(1);

        menuBar.getMenus().get(0).getItems().add(new RadioMenuItem(languageI.setContact()));
        menuBar.getMenus().get(1).getItems().add(new RadioMenuItem(languageI.logOut()));

        toggleGroup = new ToggleGroup();

        contactos.getItems().get(0).setOnAction(e -> {

            Usuario usuario1 = this.getUsuario();
            // Usuario usuario1 = new Usuario("Alan", "alan123", 5000, "en");
            if (usuario1 != null) {
                RadioMenuItem radioMenuItem = new RadioMenuItem(usuario1.getIdUsuario());
                radioMenuItem.setToggleGroup(toggleGroup);
                contactos.getItems().add(radioMenuItem);
                usuario.agregarContacto(usuario1);
            }
        });

        configuration.getItems().get(0).setOnAction(e -> {
            stage.setScene(scene1);
        });

        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                if (toggleGroup.getSelectedToggle() != null) {

                    textFlow.getChildren().clear();

                    RadioMenuItem radioMenuItem = (RadioMenuItem) toggleGroup.getSelectedToggle();
                    String s = radioMenuItem.getText();

                    // Usuario userToTalk = informationBank.getUsuario(s);
                    Usuario userToTalk = controlador.getUsuario(s);
                    label.setText(languageI.senderDescription() + userToTalk.getNombre());

                    usuario.setPuertoAmigo(userToTalk.getMiPuerto());
                    usuario.setNombreAmigo(userToTalk.getIdUsuario());
                    usuario.iniciarServer();

                }
            }
        });

        label = new Label(languageI.setActive());
        label.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        label.setUnderline(true);

        // CONFIGURANDO SCROLL PANE
        scrollPane = new ScrollPane();
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(20, 20, 20, 20));
        scrollPane.setStyle("-fx-color: gray;");
        scrollPane.setContent(textFlow);

        // CONFIGURANDO BUTTON
        sendButton = new Button(languageI.sendButtonLabel());

        // CONFIGURANDO TEXT FIELD
        textField = new TextField();

        // CONFIGURANDO HBOX
        hBox = new HBox(10, textField, sendButton);

        // CONFIGURANDO LOS MARGINS
        BorderPane.setMargin(label, new Insets(10));
        BorderPane.setMargin(scrollPane, new Insets(10));
        BorderPane.setMargin(hBox, new Insets(10));

        // CONFIGURANDO BORDER PANE
        borderPane = new BorderPane();

        borderPane.setStyle("-fx-background-color: white;");
        borderPane.setPadding(new Insets(10));

        borderPane.setTop(label);
        borderPane.setCenter(scrollPane);
        borderPane.setBottom(hBox);

        borderPane.setPrefWidth(WIDTH);
        borderPane.setPrefHeight(HEIGHT);

        root2 = new VBox();
        root2.setAlignment(Pos.TOP_LEFT);
        root2.setBackground(Background.EMPTY);
        root2.getChildren().addAll(menuBar, borderPane);
        scene2 = new Scene(root2, 300, 300);

    }

    public void iniciarVistaChat() {
        stage.setTitle("Translator Conversation");
        stage.setScene(scene2);
        // stage.resizableProperty().set(false);

        // MOSTRANDO STAGE
        stage.show();
    }

    public Scene getScene1() {
        return scene1;
    }

    public Scene getScene2() {
        return scene2;
    }

    public Button getBotonSalir() {
        return btnSalir;
    }

    private Usuario getUsuario() {

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

            Usuario usuario1 = controlador.getUsuario(userName); // OBTENIENDO DEL BANCO DE INFORMACIÓN.

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

    public Usuario getU() {
        return usuario;
    }

}
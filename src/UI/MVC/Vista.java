package UI.MVC;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.scene.Cursor;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import UI.Languages.*;
import Chat.*;

public class Vista {
    Label labelT, labelU;
    TextField textU;
    Button btnEntrar, btnSalir;
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
    private Button button;
    private BorderPane borderPane;
    private VBox root2;
    private ToggleGroup toggleGroup;
    private LanguageInterface languageI;
    private static Usuario usuario;
    private Stage stage;
    final static double WIDTH = 700;
    final static double HEIGHT = 500;

    /**
     * Método para crear un Objeto Vista
     * @param controlador - El controlodor de la vista.
     * @param stage - El stage de la vista.
     * @param textFlow - TextFlow.
     */
    public Vista(Controlador controlador, Stage stage, TextFlow textFlow) {
        this.controlador = controlador;
        this.stage = stage;
        Vista.textFlow = textFlow;
    }

    /**
     * Crea los componentes gráficos de la interfaz.
     */
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

            usuario = controlador.logIn(textU.getText());
            if (usuario != null) {
                controlador.entrarChat();
                button.setOnAction(ev -> controlador.Chatear(textField, textFlow, usuario));
            }
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

    /**
     * Crea y configura los elementos gráficos de la interfaz.
     */
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
        controlador.cargarContactos(contactos, usuario);

        menuBar.getMenus().get(1).getItems().add(new RadioMenuItem(languageI.logOut()));

        toggleGroup = new ToggleGroup();

        contactos.getItems().get(0).setOnAction(e -> {

            Usuario usuario1 = controlador.agregarUsuario(languageI, usuario);
            controlador.agregarContactoMenu(usuario1);
        });

        configuration.getItems().get(0).setOnAction(e -> {
            controlador.regresarLogin();
        });

        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {

                if (toggleGroup.getSelectedToggle() != null) {

                    textFlow.getChildren().clear();

                    RadioMenuItem radioMenuItem = (RadioMenuItem) toggleGroup.getSelectedToggle();
                    controlador.toggle(radioMenuItem, languageI, usuario, label);

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
        button = new Button(languageI.sendButtonLabel());

        // CONFIGURANDO TEXT FIELD
        textField = new TextField();

        // CONFIGURANDO HBOX
        hBox = new HBox(10, textField, button);

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

    /**
     * Método para iniciar la vista del Chat en la Scene 2.
     */
    public void iniciarVistaChat() {
        stage.setTitle("Translator Conversation");
        stage.setScene(scene2);

        // MOSTRANDO STAGE
        stage.show();
    }

    /**
     * Método para obtener la Scene 1.
     * @return - Scene 1.
     */
    public Scene getScene1() {
        return scene1;
    }

    /**
     * Método para obtener la Scene 2.
     * @return - Scene 2.
     */
    public Scene getScene2() {
        return scene2;
    }

    /**
     * Método para obtener el botón de salir.
     * @return - Button.
     */
    public Button getBotonSalir() {
        return btnSalir;
    }

    /**
     * Método para obtener el usuario.
     * @return Usuario - usuario.
     */
    public Usuario getU() {
        return usuario;
    }

    /**
     * Método para agregar contactos al Menu de la interfaz.
     * @param usuario - Usuario a agregar.
     */
    public void agregarContacto(Usuario usuario) {
        RadioMenuItem radioMenuItem = new RadioMenuItem(usuario.getIdUsuario());
        radioMenuItem.setToggleGroup(toggleGroup);
        contactos.getItems().add(radioMenuItem);
        usuario.agregarContacto(usuario);
    }

    /**
     * Método para volver a estar en el LogIn.
     */
    public void regresarLogin() {
        stage.setScene(scene1);
    }

}
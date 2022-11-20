package UI;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.scene.Cursor;
import javafx.geometry.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.NoSuchElementException;
import java.util.Optional;

import UI.Languages.*;
import Chat.*;
import Idioma.*;


public class Vista{
    Label labelT, labelU;
    TextField textU;
    Button btnEntrar,btnSalir;
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
    private Button button;
    private BorderPane borderPane;
    private VBox root2;
    private ToggleGroup toggleGroup;
    private LanguageInterface languageI;
    private  static Usuario usuario;
    private Stage stage;
    final static double WIDTH = 700;
    final static double HEIGHT = 500;

    public Vista(Controlador controlador,Stage stage){
        this.controlador = controlador;
        this.stage = stage;
    }

    public void CrearComponentesyVista(){
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

            if (usuario != null){
                controlador.entrarChat();
                button.setOnAction(ev -> controlador.Chatear(textField,textFlow,usuario));
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Aviso de información");
                alert.setContentText("Usuario Incorrecto");
                alert.showAndWait();
            }
                
            // controlador.entrarChat();
            // button.setOnAction(ev -> controlador.Chatear(textField,textFlow,usuario));
        });



        btnSalir = new Button("Salir");
        btnSalir.setMaxWidth(150);
        btnSalir.setCursor(Cursor.HAND);
        btnSalir.setOnAction(event -> controlador.salir());
        

        root = new VBox();
  
        root.getChildren().addAll(labelT,labelU,textU, btnEntrar,btnSalir);
        root.setAlignment(Pos.CENTER);

        VBox.setMargin(labelU,new Insets(10,0,0,0));
        VBox.setMargin(btnEntrar,new Insets(10,0,0,0));
        VBox.setMargin(btnSalir,new Insets(12,0,0,0));

        scene1 = new Scene(root,300,300);
        stage.setTitle("Translator Conversation");
            stage.setScene(scene1);
            //stage.resizableProperty().set(false);

            // MOSTRANDO STAGE
            stage.show();



    }

    public void crearElementos(){
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
        controlador.cargarContactos(contactos,usuario);

        menuBar.getMenus().get(1).getItems().add(new RadioMenuItem(languageI.logOut()));

        toggleGroup = new ToggleGroup();

       contactos.getItems().get(0).setOnAction(e -> {

            Usuario usuario1 = controlador.agregarUsuario(languageI, usuario);
            //Usuario usuario1 = new Usuario("Alan", "alan123", 5000, "en");
            controlador.agregarContactoMenu(usuario1);
        });

        configuration.getItems().get(0).setOnAction(e -> {
           controlador.regresarLogin();
       });



        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {

                if (toggleGroup.getSelectedToggle() != null){

                    textFlow.getChildren().clear();

                    RadioMenuItem radioMenuItem = (RadioMenuItem) toggleGroup.getSelectedToggle();
                    controlador.toggle(radioMenuItem, languageI, usuario, label);

                }
            }
        });



        // CONFIGURANDO LABEL
        // ! Hardcodeado para 2 usuarios
       // String otherUsername = usuario.getNombre().compareTo("Pedro") == 0 ? "Carlos" : "Pedro";
        // label = View.getLabel(languageI.senderDescription() + otherUsername + " ...");
        label = new Label(languageI.setActive());
        label.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        label.setUnderline(true);

        // CONFIGURANDO TEXT FLOW
        textFlow = new TextFlow();
        

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
        scene2 = new Scene(root2,300,300);

        
    }

    public void iniciarVistaChat(){
        stage.setTitle("Translator Conversation");
        stage.setScene(scene2);
            //stage.resizableProperty().set(false);

            // MOSTRANDO STAGE
        stage.show();
    }
    
    public Scene getScene1(){
        return scene1;
    }

    public Scene getScene2(){
        return scene2;
    }

    public Button getBotonSalir(){
        return btnSalir;
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

    public void chatear(){
        //final String message = textField.getText();
            /*if (!message.isEmpty()) {

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
            }*/
    }

    public Usuario getU(){
        return usuario;
    }

    public void agregarContacto(Usuario usuario){
        RadioMenuItem radioMenuItem = new RadioMenuItem(usuario.getIdUsuario());
        radioMenuItem.setToggleGroup(toggleGroup);
        contactos.getItems().add(radioMenuItem);
        usuario.agregarContacto(usuario);
    }

    public void regresarLogin(){
        stage.setScene(scene1);
    }




    

}
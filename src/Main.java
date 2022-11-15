import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class Main extends Application {

   @Override
    public void start(Stage stage) throws Exception {

        // Parent root = FXMLLoader.load(getClass().getResource("Chat.fxml"));

        Controller controller = new Controller();
        controller.iniciar();
        VBox root = controller.getRoot();

        // CONFIGURANDO EL STAGE
        stage.setTitle("Translator Conversation");
        stage.setScene(new Scene(root, 700, 500));
        stage.resizableProperty().set(false);

        // MOSTRANDO STAGE
        stage.show();
    }
    

    public static void main(String[] args) {
        launch(args);
    }


}
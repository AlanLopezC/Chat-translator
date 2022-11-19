import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.scene.Cursor;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import UI.*;
import Chat.*;
 

public class Prueba extends Application {
    

    @Override
    public void start(Stage stage) throws Exception {

        // Parent root = FXMLLoader.load(getClass().getResource("Chat.fxml"));

        //Controller controller = new Controller();
        //controller.iniciar(arg);
        //VBox root = controller.getRoot();

        // CONFIGURANDO EL STAGE
       // Usuario usuario = new Usuario("Alan", "alan123", 5000, "en");
        Controlador c = new Controlador(stage);
       

    }

    public static void main(String[] args) {
       //  arg = args[0];
        launch(args);
    }

}

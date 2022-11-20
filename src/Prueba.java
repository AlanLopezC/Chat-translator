import javafx.application.Application;
import javafx.stage.Stage;
import UI.*;

public class Prueba extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // Parent root = FXMLLoader.load(getClass().getResource("Chat.fxml"));

        // Controller controller = new Controller();
        // controller.iniciar(arg);
        // VBox root = controller.getRoot();

        // CONFIGURANDO EL STAGE
        // Usuario usuario = new Usuario("Alan", "alan123", 5000, "en");
        Controlador c = new Controlador(stage);

    }

    public static void main(String[] args) {
        // arg = args[0];
        launch(args);
    }

}

import UI.MVC.Controlador;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Controlador c = new Controlador(stage);
    }

    public static void main(String[] args) {
        // arg = args[0];
        launch(args);
    }
}

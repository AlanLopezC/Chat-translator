import UI.MVC.Controlador;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * Método para iniciar aplicación.
     */
    @Override
    public void start(Stage stage) throws Exception {
        Controlador c = new Controlador(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

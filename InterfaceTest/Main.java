import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("MainInterface.fxml"));
        stage.setTitle("Translator Conversation");
        stage.setScene(new Scene(root, 478, 420));
        stage.resizableProperty().set(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch(Main.class, args);
    }
}
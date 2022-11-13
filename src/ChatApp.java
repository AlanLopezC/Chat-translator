import java.lang.Thread.State;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class ChatApp extends Application {
    final int WIDTH = 640, HEIGHT = 480;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Translated Chat");

        // Top
        Label messagesLanguage = new Label();
        messagesLanguage.setText("Spanish Messaging");

        // Center
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(20, 20, 20, 20));
        scrollPane.setStyle("-fx-color: gray;");
        // "-fx-background-color: rgb(256, 245, 23);"

        TextFlow textFlow = new TextFlow();
        scrollPane.setContent(textFlow);

        // Bottom
        HBox bottomMenu = new HBox(10);
        TextField textField = new TextField();
        Button sendButton = new Button("Send");
        sendButton.setOnAction(e -> {
            final String message = textField.getText();
            if (!message.isEmpty()) {
                Text text = new Text(message + "\n");
                textFlow.getChildren().add(text);
                textField.clear();
            }
        });
        bottomMenu.getChildren().addAll(textField, sendButton);

        // Margins
        Insets margin = new Insets(10);
        BorderPane.setMargin(messagesLanguage, margin);
        BorderPane.setMargin(scrollPane, margin);
        BorderPane.setMargin(bottomMenu, margin);

        // Layout
        BorderPane layout = new BorderPane();
        layout.setStyle("-fx-background-color: beige;");
        layout.setPadding(new Insets(10));
        layout.setTop(messagesLanguage);
        layout.setCenter(scrollPane);
        layout.setBottom(bottomMenu);

        // Scene
        Scene scene = new Scene(layout, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
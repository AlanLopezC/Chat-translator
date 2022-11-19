package UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;

public class View {

    final static double WIDTH = 700;
    final static double HEIGHT = 500;



    public static BorderPane getLayout(Label label, ScrollPane scrollPane, HBox hBox) {

        BorderPane layout = new BorderPane();

        layout.setStyle("-fx-background-color: white;");
        layout.setPadding(new Insets(10));

        layout.setTop(label);
        layout.setCenter(scrollPane);
        layout.setBottom(hBox);

        layout.setPrefWidth(WIDTH);
        layout.setPrefHeight(HEIGHT);

        return layout;
    }

    public static VBox getRoot(MenuBar menuBar, BorderPane borderPane) {

        VBox root = new VBox();
        root.setAlignment(Pos.TOP_LEFT);
        root.setBackground(Background.EMPTY);
        root.getChildren().addAll(menuBar, borderPane);
        return root;

    }

}

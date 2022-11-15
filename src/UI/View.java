package UI;

import javafx.beans.WeakInvalidationListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextFlow;

import java.util.WeakHashMap;

public class View {

    final static double WIDTH = 700;
    final static double HEIGHT = 500;

    public static MenuBar getMenuBar() {

        // CREANDO LOS MENUS DESPEGABLES
        Menu preSet = new Menu();
        Menu preContacts = new Menu();

        return new MenuBar(preContacts, preSet);
    }

    public static Menu getMenu(String name) {
        return new Menu(name);
    }

    public static Label getLabel(String text) {

        Label label = new Label(text);
        label.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        label.setUnderline(true);
        return label;
    }

    public static ScrollPane getScrollPane() {

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(20, 20, 20, 20));
        scrollPane.setStyle("-fx-color: gray;");

        return scrollPane;
    }

    public static TextFlow getTextFlow() {
        return new TextFlow();
    }

    public static HBox getBottonMenu(double v, TextField textField, Button button) {
        return new HBox(v, textField, button);
    }

    public static TextField getTextField() {
        return new TextField();
    }

    public static Button getSendButton(String name) {
        return new Button(name);
    }

    public static Insets getMargin(double v) {
        return new Insets(v);
    }

    public static void setMargin(Insets margin, Label label, ScrollPane scrollPane, HBox hBox) {

        BorderPane.setMargin(label, margin);
        BorderPane.setMargin(scrollPane, margin);
        BorderPane.setMargin(hBox, margin);

    }

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

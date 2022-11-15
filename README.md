
comands:

cd src

javac --module-path ../lib/javafx-sdk-17.0.1/lib/ --add-modules javafx.base,javafx.graphics,javafx.controls,javafx.fxml Main.java

Terminal 1:
java --module-path ../lib/javafx-sdk-17.0.1/lib/ --add-modules javafx.base,javafx.graphics,javafx.controls,javafx.fxml Main 1

Terminal 2:
java --module-path ../lib/javafx-sdk-17.0.1/lib/ --add-modules javafx.base,javafx.graphics,javafx.controls,javafx.fxml Main 2


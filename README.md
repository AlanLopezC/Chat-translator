This was the final project for the system design course.

We designed a Chat Translator App with java, where you receive all messages translated into your language and the messages you send are translated to the others language in their app. We used sockets and the Whatsmate API for the translation.

If you want to try it, you need to create an account and use your credentials.

![Preview](https://github.com/AlanLopezC/Chat-translator/assets/63161554/2c9277af-92b1-4e9f-a76c-dd0b3e40f246)


In case you don't have the folder lib/: 
You would need to add lib/javafx-17.0.1

***
Comands:

cd src

javac --module-path ../lib/javafx-sdk-17.0.1/lib/ --add-modules javafx.base,javafx.graphics,javafx.controls,javafx.fxml Main.java

Terminal 1:
java --module-path ../lib/javafx-sdk-17.0.1/lib/ --add-modules javafx.base,javafx.graphics,javafx.controls,javafx.fxml Main 1

Terminal 2:
java --module-path ../lib/javafx-sdk-17.0.1/lib/ --add-modules javafx.base,javafx.graphics,javafx.controls,javafx.fxml Main 2


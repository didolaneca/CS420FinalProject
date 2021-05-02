package me.diyan.testFX;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloFX extends Application {
    Stage window;
    Button button;

    @Override
    public void start(Stage stage) {
        window = stage;
        window.setTitle("#10 Extract and Validate Input");

        //Form
        TextField nameInput = new TextField();
        button = new Button("Click me");
        button.setOnAction(e -> System.out.println(nameInput.getText()));

        //Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(nameInput, button);

        Scene scene = new Scene(layout, 300,  250);
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

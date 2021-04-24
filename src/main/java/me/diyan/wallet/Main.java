package me.diyan.wallet;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    Button button;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Diyans Wallet");
        button = new Button("Add a transaction");

        //creating the layout
        StackPane layout = new StackPane();
        //adding a button to the layout
        layout.getChildren().add(button);

        //create a Scene
        Scene scene = new Scene(layout, 300, 250);
        //add the scene to the primary stage
        primaryStage.setScene(scene);
        //display to the user
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

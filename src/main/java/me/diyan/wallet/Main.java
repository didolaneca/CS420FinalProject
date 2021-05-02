package me.diyan.wallet;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import me.diyan.wallet.handlers.AddTransactionHandler;
import me.diyan.wallet.models.Transaction;
import me.diyan.wallet.models.TransactionDAO;
import me.diyan.wallet.models.TransactionDAOImpl;
import me.diyan.wallet.validators.WalletValidator;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main extends Application {
    Button transactionBtn;
    TransactionDAO transactionDAO;
    WalletValidator walletValidator;

    @Override
    public void init() throws Exception {
        super.init();
        this.transactionDAO = new TransactionDAOImpl();
        this.walletValidator = new WalletValidator();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Diyans Wallet");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        Label date = new Label("Date:");
        GridPane.setConstraints(date, 0, 0);

        TextField dateInput = new TextField();
        dateInput.setPromptText("MM/DD/YYYY");
        GridPane.setConstraints(dateInput, 1, 0);

        Label amount = new Label("Amount:");
        GridPane.setConstraints(amount, 0, 1);

        TextField amountInput = new TextField();
        amountInput.setPromptText("00.00");
        GridPane.setConstraints(amountInput, 1, 1);

        Label note = new Label("Note:");
        GridPane.setConstraints(note, 0, 2);
        TextField noteInput = new TextField();
        noteInput.setPromptText("Transaction Comments:");
        GridPane.setConstraints(noteInput, 1, 2);

        transactionBtn = new Button("Add a transaction");
        //adding event handler to the transactionBtn
        transactionBtn.setOnAction(event -> {
            //validate amount input
            walletValidator.validateAmount(amountInput);
            walletValidator.validateDate(dateInput);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            List<Transaction> transactionList = new ArrayList<>();
            transactionList.add(new Transaction(new Date(), 99.99, "Dido Test Note"));
            transactionList.add(new Transaction(new Date(), 109.99, "Dido Test2 Note"));
            transactionDAO.saveMultipleTransactions(transactionList);
            System.out.println("Added Transaction");
        });
        GridPane.setConstraints(transactionBtn, 1, 3);

        gridPane.getChildren().addAll(date, dateInput, amount, amountInput, note, noteInput, transactionBtn);

        //create a Scene
        Scene scene = new Scene(gridPane, 500, 450);
        //add the scene to the primary stage
        primaryStage.setScene(scene);
        //display to the user
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

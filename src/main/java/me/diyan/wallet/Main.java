package me.diyan.wallet;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import me.diyan.wallet.models.Transaction;
import me.diyan.wallet.models.TransactionDAO;
import me.diyan.wallet.models.TransactionDAOImpl;
import me.diyan.wallet.models.TransactionTable;
import me.diyan.wallet.validators.WalletValidator;

public class Main extends Application {
    Button transactionBtn, searchBtn, showBtn, searchBtnByDate, searchBtnByKeyWord;
    TransactionDAO transactionDAO;
    WalletValidator walletValidator;
    TableView<Transaction> transactionTableView;
    TransactionTable transactionTable;

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
        noteInput.setPromptText("Transaction Comments");
        GridPane.setConstraints(noteInput, 1, 2);

        searchBtnByDate = new Button("Search transactions by date");
        GridPane.setConstraints(searchBtnByDate, 2, 0);
        searchBtnByDate.setOnAction(event -> {
            if(walletValidator.validateDate(dateInput)){
                transactionDAO.filterTransactionsByDate(dateInput.getText());
                transactionTableView.refresh();
            } else {
                System.out.println("Error. Invalid format");
            }
        });

        searchBtnByKeyWord = new Button("Search transactions by keyword");
        GridPane.setConstraints(searchBtnByKeyWord, 2, 2);
        searchBtnByKeyWord.setOnAction(event -> {
            transactionDAO.filterTransactionsByKeyWord(noteInput.getText());
        });


        transactionBtn = new Button("Add a transaction");
        //adding event handler to the transactionBtn
        transactionBtn.setOnAction(event -> {
//            validate amount input
            if(walletValidator.validateAmount(amountInput) && walletValidator.validateDate(dateInput)){
                //add transaction
                transactionDAO.addTransaction(new Transaction(dateInput.getText(), Double.parseDouble(amountInput.getText()), noteInput.getText()));
                dateInput.clear();
                amountInput.clear();
                noteInput.clear();
            } else {
                System.out.println("Error! Can not add transaction");
            }
            System.out.println("Added Transaction");
        });
        transactionBtn.setAlignment(Pos.CENTER);
        GridPane.setConstraints(transactionBtn, 1, 3);

        this.transactionTableView = TransactionTable.createTransactionTable(this.transactionDAO.loadTransactions());
        VBox transactionVBox = new VBox();
        transactionVBox.getChildren().addAll(transactionTableView);
        GridPane.setConstraints(transactionVBox, 0,4,3,  3);
        gridPane.getChildren().addAll(date, dateInput, amount, amountInput, note, noteInput, transactionBtn, searchBtnByDate, searchBtnByKeyWord, transactionVBox);
        gridPane.setAlignment(Pos.CENTER);

        primaryStage.setOnCloseRequest(e -> transactionDAO.saveMultipleTransactions(transactionDAO.getTransactions()));
        //create a Scene
        Scene topScene = new Scene(gridPane, 1000, 1000);
        //add the scene to the primary stage
        primaryStage.setScene(topScene);
        //display to the user
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

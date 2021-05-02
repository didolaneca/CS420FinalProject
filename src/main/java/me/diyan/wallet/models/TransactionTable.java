package me.diyan.wallet.models;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;

public class TransactionTable {
    public static TableView<Transaction> createTransactionTable(ObservableList<Transaction> transactions){
        TableView<Transaction> transactionTableView = new TableView<>();
//        if (transactions.isEmpty()) {
//            transactionTableView.setPlaceholder(new Label("No rows to display!"));
//            return transactionTableView;
//        }
//        TableColumn<Transaction, Date> dateColumn = new TableColumn<>("Date");
//        dateColumn.setMinWidth(30);
//        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
//
//        TableColumn<Transaction, Double> amountColumn = new TableColumn<>("Amount");
//        dateColumn.setMinWidth(30);
//        dateColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
//
//        TableColumn<Transaction, String> noteColumn = new TableColumn<>("Note");
//        dateColumn.setMinWidth(100);
//        dateColumn.setCellValueFactory(new PropertyValueFactory<>("note"));
//
//        transactionTableView.setItems(transactions);
//        transactionTableView.getColumns().addAll(dateColumn, amountColumn, noteColumn);

        transactionTableView.setPlaceholder(new Label("No rows to display!"));
        return transactionTableView;
    }
}

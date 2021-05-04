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
        TableColumn<Transaction, Date> dateColumn = new TableColumn<>("Date");
        dateColumn.setMinWidth(100);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Transaction, Double> amountColumn = new TableColumn<>("Amount");
        amountColumn.setMinWidth(100);
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn<Transaction, String> noteColumn = new TableColumn<>("Note");
        noteColumn.setMinWidth(250);
        noteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));

        if (transactions.isEmpty()) {
            transactionTableView.setPlaceholder(new Label("No rows to display!"));
            return transactionTableView;
        }

        transactionTableView.setItems(transactions);
        transactionTableView.getColumns().addAll(dateColumn, amountColumn, noteColumn);
        return transactionTableView;
    }
}

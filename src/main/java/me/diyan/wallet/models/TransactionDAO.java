package me.diyan.wallet.models;

import javafx.collections.ObservableList;

import java.util.Date;
import java.util.List;

public interface TransactionDAO {
    //Returns the last transaction
    Transaction getTransaction();
    //Saves a transaction and returns true if successful or false if it fails
    boolean saveTransaction(Transaction transaction);
    boolean saveMultipleTransactions(List<Transaction> transactionList);
    //Returns a list of transaction based on range of dates
    List<Transaction> getTransactionsByDate(Date fromDate, Date untilDate);
    //retunrs a transactions matching a keyword
    List<Transaction> getTransactionsByKeyWord(String keyWord);

    ObservableList<Transaction> loadTransactions();
}

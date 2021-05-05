package me.diyan.wallet.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is used to save communicate with a date source, JSON in our case.
 */
public class TransactionDAOImpl implements TransactionDAO {

    private GsonBuilder gsonBuilder;
    List<Transaction> previousTransactions, filteredTransactions;
    private static String fileName = "./jsons/transactions.json";
    ObservableList<Transaction> transactions, tableFilteredTransactions, allTransactions;

    @Override
    public ObservableList<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public ObservableList<Transaction> filterTransactionsByDate(String date) {
        // we get the date, time to filter the transaction list
        allTransactions.setAll( transactions);
        tableFilteredTransactions = FXCollections.observableArrayList();
        filteredTransactions = transactions.stream().filter(
                transaction -> transaction.getDate().equals(date))
                .collect(Collectors.toList());
        tableFilteredTransactions.addAll(filteredTransactions);
        transactions.clear();
        transactions.addAll(tableFilteredTransactions);
        return transactions;
    }

    @Override
    public ObservableList<Transaction> filterTransactionsByKeyWord(String keyWord) {
        return null;
    }

    public TransactionDAOImpl(){
        //try to read file if not found create a new one and write to it
        try{
//            this.file = new File(classLoader.getResource(fileName).getFile());
            this.previousTransactions = readFile(fileName);
//            this.previousTransactions = new ArrayList<>();// readFile(fileName);
            //if found append to it
        } catch (Exception ex) {
            //Create a new file with the first transaction`
            System.out.println("Can't read file/missing file : " + ex.getMessage());
        }
//        catch (NullPointerException ex) {
//            System.out.println("Can't get filename : " + ex.getMessage());
//        }

    }

    @Override
    public Transaction getTransaction() {
        return null;
    }

    @Override
    public boolean saveTransaction(Transaction transaction) {
        this.gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String transactionJson = gson.toJson(transaction);
        try (FileWriter fileOutput = new FileWriter(fileName)) {
            fileOutput.write(transactionJson);
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean saveMultipleTransactions(List<Transaction> transactionList) {
        this.gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String transactionJson = gson.toJson(transactionList);
        try (FileWriter fileOutput = new FileWriter(fileName)) {
            fileOutput.write(transactionJson);
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Transaction> getTransactionsByDate(Date fromDate, Date untilDate) {
        return null;
    }

    @Override
    public List<Transaction> getTransactionsByKeyWord(String keyWord) {
        return null;
    }

    private static List<Transaction> readFile(String filename) throws FileNotFoundException {
        Gson gson = new Gson();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {


            return Arrays.asList(gson.fromJson(bufferedReader, Transaction[].class));
        } catch (IOException e) {
            System.out.println("Cant read file");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public ObservableList<Transaction> loadTransactions(){
        transactions = FXCollections.observableArrayList();
        allTransactions = FXCollections.observableArrayList();
        //add the products
        allTransactions.addAll(previousTransactions);
        transactions.addAll(previousTransactions);
        return transactions;
    }

    @Override
    public ObservableList<Transaction> addTransaction(Transaction transaction) {
        transactions.add(transaction);
        return transactions;
    }

    @Override
    public ObservableList<Transaction> showAllTransactions(){
        transactions.clear();
        transactions.addAll(allTransactions);
        return transactions;
    }
}

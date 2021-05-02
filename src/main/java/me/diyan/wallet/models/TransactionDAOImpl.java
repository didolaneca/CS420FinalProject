package me.diyan.wallet.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * This class is used to save communicate with a date source, JSON in our case.
 */
public class TransactionDAOImpl implements TransactionDAO {

    private GsonBuilder gsonBuilder;
    List<Transaction> previousTransactions;
    private static String fileName = "./jsons/transactions.json";
    File file;

    public TransactionDAOImpl(){
        //try to read file if not found create a new one and write to it
        try{
//            this.file = new File(classLoader.getResource(fileName).getFile());
            this.previousTransactions = readFile(fileName);
            //if found append to it
        } catch (FileNotFoundException ex) {
            //Create a new file with the first transaction`
            System.out.println("Can't read file/missing file : " + ex.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("Can't get filename : " + ex.getMessage());
        }

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
        try (FileWriter fileOutput = new FileWriter("transactions.json")) {
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
        try (FileWriter fileOutput = new FileWriter("./jsons/transactions.json")) {
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
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public ObservableList<Transaction> loadTransactions(){
        ObservableList<Transaction> transactions = FXCollections.observableArrayList();
        //add the products
        transactions.add(new Transaction(new Date(), 899.99, "Dido Test Note - Laptop"));
        transactions.add(new Transaction(new Date(), 1109.99, "Dido Test2 Note - iPhone"));
        transactions.add(new Transaction(new Date(), 2999.99, "Dido Test2 Note - New Mac book Pro"));
        return transactions;
    }

}

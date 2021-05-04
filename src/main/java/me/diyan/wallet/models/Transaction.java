package me.diyan.wallet.models;

import java.util.Date;
import java.util.Objects;

public class Transaction {
    private String date;
    private double amount;
    private String note;

    public Transaction() {
        this.date = "";
        this.amount = 0.0;
        this.note = "";
    }

    public Transaction(String date, double amount, String note) {
        this.date = date;
        this.amount = amount;
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.amount, amount) == 0 && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, amount, note);
    }

    @Override
    public String toString() {
        return "Transaction {" +
                "date = " + date +
                ", amount = " + amount +
                ", note = '" + note + '\'' +
                '}';
    }
}

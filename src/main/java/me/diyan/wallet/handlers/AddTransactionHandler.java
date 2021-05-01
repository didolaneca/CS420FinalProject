package me.diyan.wallet.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AddTransactionHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
        System.out.println("Added Transaction");
    }
}

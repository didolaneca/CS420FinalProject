package me.diyan.wallet.validators;

import javafx.scene.control.TextField;

public class WalletValidator implements AmountValidator, DateValidator{
    @Override
    public boolean validateAmount(TextField textField) {
        String amount = textField.getText();
        try {
             Double.parseDouble(amount);
            return true;
        } catch (NumberFormatException ex) {
            System.out.println("Error: " + amount + " is not a proper number format -> " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean validateDate(TextField textField) {
        String amount = textField.getText();
        return false;
    }
}

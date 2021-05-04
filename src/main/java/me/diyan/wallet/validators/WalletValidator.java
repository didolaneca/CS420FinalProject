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
        String date = textField.getText();
        int month, day, year;
        if(date.matches("\\d{2}/\\d{2}/\\d{4}")){
            try{
                month = Integer.parseInt(date.substring(0, 2));
                day = Integer.parseInt(date.substring(3, 5));
                year = Integer.parseInt(date.substring(6));
                if(month < 13 && month > 0){
                    if(month == 2 && day < 29){
                        return true;
                    } else if((month == 1 || month == 3 || month == 5 || month == 7
                                || month == 8 || month == 10 || month == 12) && day < 32){
                        return true;
                    } else if((month == 4 || month == 6 || month == 9 || month == 11)
                            && day < 31){
                        return true;
                    } else {
                        return false;
                    }
                }
            } catch (NumberFormatException ex ){
                System.out.println("Error! Date not in proper format -> " + ex.getMessage());
                return false;

            }
//            return true;
        }
        return false;
    }
}

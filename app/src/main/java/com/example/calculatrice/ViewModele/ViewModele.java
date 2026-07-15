package com.example.calculatrice.ViewModele;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.calculatrice.model.Addition;
import com.example.calculatrice.model.Division;
import com.example.calculatrice.model.Multiplication;
import com.example.calculatrice.model.Operation;
import com.example.calculatrice.model.Soustraction;

import java.util.Objects;

public class ViewModele  extends ViewModel {

    double firstNumber;
    double secondNumber;
    String operator;
    double result;
    String display;

    private final MutableLiveData<String> displayValue = new MutableLiveData<>();

    public MutableLiveData<String> getDisplayValue() {
        return displayValue;
    }

    public ViewModele () {
        displayValue.setValue("");
    }

    private void clearError() {
        if(displayValue.getValue().equals("Erreur div by zero")) {
            displayValue.setValue("");
            display = null;
        }
    }

    // calculer operation
    private void performOperation(){
        display = null;
        switch(operator) {
            case "+":
                Operation addition = new Addition();
                result = addition.operation(firstNumber,secondNumber);
                break;
            case "-":
                Operation soustraction = new Soustraction();
                result = soustraction.operation(firstNumber,secondNumber);
                break;
            case "*":
                Operation multiplication = new Multiplication();
                result = multiplication.operation(firstNumber,secondNumber);
                break;
            case "/":
                Operation division = new Division();
                if(secondNumber == 0.0) {
                    result = 0.0;
                    display = "Erreur div by zero";
                }
                else {
                    result = division.operation(firstNumber, secondNumber);
                }
                break;
            default:
                // code block
        }
        if(display == null) {
            display = String.valueOf(result);
        }

    }

    // ajouter chiffre
    public void onNumberClick(String s) {
        clearError();

        if(displayValue.getValue() == null || display == null) {
            displayValue.setValue(s);
            display = s;
        }else {
            displayValue.setValue(displayValue.getValue() + s);
            display = display + s;
        }
    }

    // ajouter point
    public void onDotClick() {
        clearError();
        if(!Objects.requireNonNull(displayValue.getValue()).contains(".") || !displayValue.getValue().isEmpty()) {
            displayValue.setValue(displayValue.getValue() + ".");
        }
    }

    // ajouter operation
    public void onOperatorClick(String s) {
        clearError();
        if(Objects.requireNonNull(displayValue.getValue()).isEmpty()){
            onNumberClick(s);
        }else if (displayValue.getValue().endsWith("-") || displayValue.getValue().endsWith("+") || displayValue.getValue().endsWith("*") || displayValue.getValue().endsWith("/")){
            return;
        }
        else {
            if(operator == null) {
                firstNumber = Double.parseDouble(Objects.requireNonNull(displayValue.getValue()));
            } else {
                secondNumber = Double.parseDouble(Objects.requireNonNull(displayValue.getValue()));
                performOperation();
                firstNumber = result;
            }
            operator = s;
            displayValue.setValue("");
        }
    }

    //calculer operation
    public void onEqualClick() {
        clearError();
        secondNumber = Double.parseDouble(Objects.requireNonNull(displayValue.getValue()));
        performOperation();
        displayValue.setValue(display);
        firstNumber = 0;
        secondNumber = 0;
        operator = null;
        result = 0;
        display = null;
    }

    //effacer le dernier caractère
    public void onDeleteClick() {
        clearError();
        displayValue.setValue(Objects.requireNonNull(displayValue.getValue()).substring(0,displayValue.getValue().length()-1));
        display = displayValue.getValue();
        if(displayValue.getValue().isEmpty()) {
            displayValue.setValue("");
            display = null;
        }
    }
    //effacer tout
    public void onClearClick() {
        displayValue.setValue("");
        firstNumber = 0;
        secondNumber = 0;
        operator = null;
        result = 0;
        display = null;

    }
}

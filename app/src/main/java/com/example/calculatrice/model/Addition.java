package com.example.calculatrice.model;

public class Addition implements Operation {
    double firstOperande;
    double secondOperande;

    public double operation(double firstOperande, double secondOperande){
        return firstOperande + secondOperande;
    }
}

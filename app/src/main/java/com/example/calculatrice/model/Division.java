package com.example.calculatrice.model;

public class Division implements Operation {
    double firstOperande;
    double secondOperande;

    public double operation(double firstOperande, double secondOperande){
        return (double) firstOperande /secondOperande;
    }
}

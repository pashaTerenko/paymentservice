package com.terenko.paymentservice.exeption;

public class InsufficientFundsExeption extends IllegalArgumentException{

    public InsufficientFundsExeption() {
        super("insufficient funds on the balance");
    }
}

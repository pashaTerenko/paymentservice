package com.terenko.paymentservice.models;

public enum TransactionResult {
    TRANSACTION_SUCCESS("ok"),TRANSACTION_FAIL("error");
    private String string;

    TransactionResult(String name){string = name;}

    @Override
    public String toString() {
        return string;
    }
}

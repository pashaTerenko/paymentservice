package com.terenko.paymentservice.Service;

import com.terenko.paymentservice.DTO.TransactionDTO;
import com.terenko.paymentservice.models.Account;
import com.terenko.paymentservice.models.Transaction;

import java.util.List;

public class TransactionServiceImpl implements TransactionService{
    @Override
    public Transaction executeTransaction(TransactionDTO transactionDTO) {
        return null;
    }

    @Override
    public Transaction getById(long id) {
        return null;
    }

    @Override
    public List<Transaction> getBySourceAccount(Account account) {
        return null;
    }

    @Override
    public List<Transaction> getByDestinationAccount(Account account) {
        return null;
    }
}

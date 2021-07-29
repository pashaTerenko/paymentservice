package com.terenko.paymentservice.Service;

import com.terenko.paymentservice.DTO.TransactionDTO;
import com.terenko.paymentservice.models.Account;
import com.terenko.paymentservice.models.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction executeTransaction(TransactionDTO transactionDTO);
    Transaction getById(long id);
    List<Transaction> getBySourceAccount(Account account);
    List<Transaction> getByDestinationAccount(Account account);
}

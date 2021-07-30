package com.terenko.paymentservice.Service;

import com.terenko.paymentservice.DTO.TransactionDTO;
import com.terenko.paymentservice.models.Account;
import com.terenko.paymentservice.models.Client;
import com.terenko.paymentservice.models.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction saveTransaction(Transaction transaction);
    Transaction getById(long id);
    List<Transaction> getBySourceAccount(Account account);
    List<Transaction> getByDestinationAccount(Account account);
    List<Transaction> getByDestinationClient(Client account);
    List<Transaction> getBySourceClient(Client account);

}

package com.terenko.paymentservice.Service;

import com.terenko.paymentservice.DTO.TransactionDTO;
import com.terenko.paymentservice.models.Account;
import com.terenko.paymentservice.models.Client;
import com.terenko.paymentservice.models.Transaction;
import com.terenko.paymentservice.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class TransactionServiceImpl implements TransactionService{
    final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
        return transaction;

    }

    @Override
    public Transaction getById(long id) {
        return null;
    }

    @Override
    public List<Transaction> getBySourceAccount(Account account) {
        return transactionRepository.findBySourceAcc(account);
    }

    @Override
    public List<Transaction> getByDestinationAccount(Account account) {
        return transactionRepository.findByDestAcc(account);
    }

    @Override
    public List<Transaction> getByDestinationClient(Client client) {
        return transactionRepository.findByDestAcc_Client(client);
    }

    @Override
    public List<Transaction> getBySourceClient(Client client) {
        return transactionRepository.findBySourceAcc_Client(client);
    }
}

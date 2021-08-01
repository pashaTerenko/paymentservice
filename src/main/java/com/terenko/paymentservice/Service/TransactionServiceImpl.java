package com.terenko.paymentservice.Service;

import com.terenko.paymentservice.DTO.TransactionDTO;
import com.terenko.paymentservice.models.Account;
import com.terenko.paymentservice.models.Client;
import com.terenko.paymentservice.models.Transaction;
import com.terenko.paymentservice.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service

public class TransactionServiceImpl implements TransactionService{
    private static Logger log = Logger.getLogger(TransactionServiceImpl.class.getName());

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
    public Transaction getById(long id) throws IllegalArgumentException {
        Transaction transaction = transactionRepository.findByPaymentId(id);
        if(transaction==null)throw new IllegalArgumentException("transaction not found");

        return transaction;
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

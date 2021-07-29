package com.terenko.paymentservice.Service;

import com.terenko.paymentservice.DTO.AccountDTO;
import com.terenko.paymentservice.models.Account;
import com.terenko.paymentservice.models.Client;
import com.terenko.paymentservice.models.Transaction;

import java.util.List;

public class AccountServiceImpl implements AccountService{
    @Override
    public Account addAccount(AccountDTO accountDTO, Client owner) {
        return null;
    }

    @Override
    public Account remAccount(AccountDTO accountDTO) {
        return null;
    }

    @Override
    public Account getById(long id) {
        return null;
    }

    @Override
    public List<Account> getByClient(Client client) {
        return null;
    }

    @Override
    public List<Account> getByTransaction(Transaction transaction) {
        return null;
    }
}

package com.terenko.paymentservice.Service;

import com.terenko.paymentservice.DTO.AccountDTO;
import com.terenko.paymentservice.models.Account;
import com.terenko.paymentservice.models.Client;
import com.terenko.paymentservice.models.Transaction;
import com.terenko.paymentservice.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service

public class AccountServiceImpl implements AccountService{
    private static Logger log = Logger.getLogger(AccountServiceImpl.class.getName());

    final AccountRepository accountRepository;
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account addAccount(AccountDTO accountDTO, Client owner) {

        Account account =AccountDTO.from(accountDTO);
        account.setClient(owner);
        accountRepository.save(account);
        log.info("account with id "+account.getAccountId()+ " created");
        return account;
    }

    @Override
    public void updateAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Account remAccount(AccountDTO accountDTO) {
        return null;
    }

    @Override
    public Account getById(long id) {
        return accountRepository.findByAccountId(id);
    }

    @Override
    public List<Account> getByClient(Client client) {
        return accountRepository.findAccountsByClient(client);
    }

    @Override
    public List<Account> getByTransaction(Transaction transaction) {
        Account recipient = getByIncomingTransaction(transaction);
       Account payer = getByDepartingTransaction(transaction);
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(recipient);
        accounts.add(payer);
        return accounts;
    }

    @Override
    public Account getByIncomingTransaction(Transaction transaction) {
        return accountRepository.findByIncomingTransactionSetContaining(transaction);
    }

    @Override
    public Account getByDepartingTransaction(Transaction transaction) {
        return accountRepository.findByDepartingTransactionSetContaining(transaction);
    }

    @Override
    public boolean isExist(long id) {
        return accountRepository.existsByAccountId(id);
    }
}

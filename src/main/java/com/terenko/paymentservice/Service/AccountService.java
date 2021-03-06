package com.terenko.paymentservice.Service;

import com.terenko.paymentservice.DTO.AccountDTO;
import com.terenko.paymentservice.models.Account;
import com.terenko.paymentservice.models.Client;
import com.terenko.paymentservice.models.Transaction;

import java.util.List;

public interface AccountService {
    Account addAccount(AccountDTO accountDTO, Client owner);
    void updateAccount(Account account);
    Account remAccount(AccountDTO accountDTO);
    Account getById(long id);
    List<Account> getByClient(Client client);
    List<Account> getByTransaction(Transaction transaction);
    Account getByIncomingTransaction(Transaction transaction);
    Account getByDepartingTransaction(Transaction transaction);
    boolean isExist(long id);

}

package com.terenko.paymentservice.repositories;


import com.terenko.paymentservice.models.Account;
import com.terenko.paymentservice.models.Client;
import com.terenko.paymentservice.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByAccountId(long id);

    Account findByAccountNumber(long number);

    List<Account> findAccountsByClient(Client client);
    boolean existsByAccountId(long id);
    Account findByAccountType(String type);
    Account findByIncomingTransactionSetContaining(Transaction transaction);
    Account findByDepartingTransactionSetContaining(Transaction transaction);
}
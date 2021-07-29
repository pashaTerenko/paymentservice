package com.terenko.paymentservice.repositories;


import com.terenko.paymentservice.models.Account;
import com.terenko.paymentservice.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByAccountId(long id);

    Account findByAccountNumber(long number);

    List<Account> findAccountsByClient(Client client);

    Account findByAccountType(String type);
}
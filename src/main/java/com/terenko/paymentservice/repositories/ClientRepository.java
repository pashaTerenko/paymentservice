package com.terenko.paymentservice.repositories;


import com.terenko.paymentservice.models.Account;
import com.terenko.paymentservice.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {
    Client findByClientId(long id);
    Client findByAccountsContaining(Account account);
    Client findByLogin(String login);
    List<Client> findByFirstNameAndLastName(String firstname,String lastName);

}

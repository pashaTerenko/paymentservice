package com.terenko.paymentservice;

import com.terenko.paymentservice.DTO.AccountDTO;
import com.terenko.paymentservice.DTO.ClientDTO;
import com.terenko.paymentservice.DTO.TransactionDTO;
import com.terenko.paymentservice.Service.AccountService;
import com.terenko.paymentservice.Service.ClientService;
import com.terenko.paymentservice.models.Client;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;

@Component
public class Runner implements CommandLineRunner {

    final ClientService clientService;
    final AccountService accountService;

    public Runner(ClientService clientService, AccountService accountService) {
        this.clientService = clientService;
        this.accountService = accountService;
        clientDTO1 = new ClientDTO();
        clientDTO1.setFirstName("fn1");
        clientDTO1.setLastName("ln1");
        clientDTO1.setLogin("l1");
        clientDTO1.setPassword("psw1");
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountNumber("223456789");
        accountDTO.setAccountType("card/simple");
        accountDTO.setBalance(BigDecimal.valueOf(100.5));
        HashSet<AccountDTO> hashSet = new HashSet();
        hashSet.add(accountDTO);
        clientDTO1.setAccounts(hashSet);
        clientDTO2 = new ClientDTO();
        clientDTO2.setFirstName("fn1");
        clientDTO2.setLastName("ln1");
        clientDTO1.setLogin("l1");
        clientDTO2.setPassword("psw1");
        AccountDTO accountDTO2 = new AccountDTO();
        accountDTO2.setAccountNumber("123456789");
        accountDTO2.setAccountType("card/simple");
        accountDTO2.setBalance(BigDecimal.valueOf(100.5));
        HashSet<AccountDTO> hashSet2 = new HashSet();
        hashSet2.add(accountDTO2);
        clientDTO2.setAccounts(hashSet2);
    }
    ClientDTO clientDTO1;
    ClientDTO clientDTO2;

    @Override
    public void run(String... args) throws Exception {
      PaymentTest();

    }
    void PaymentTest() throws Exception {
        Client cl1 = clientService.addClient(clientDTO1);
        Client cl2 = clientService.addClient(clientDTO2);
    }
}
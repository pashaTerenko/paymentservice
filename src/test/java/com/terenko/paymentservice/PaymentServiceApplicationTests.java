package com.terenko.paymentservice;

import com.terenko.paymentservice.Controller.PaymentController;
import com.terenko.paymentservice.DTO.AccountDTO;
import com.terenko.paymentservice.DTO.ClientDTO;
import com.terenko.paymentservice.DTO.TransactionDTO;
import com.terenko.paymentservice.Service.AccountService;
import com.terenko.paymentservice.Service.ClientService;
import com.terenko.paymentservice.models.Account;
import com.terenko.paymentservice.models.Client;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import java.util.HashSet;


@AutoConfigureMockMvc

@SpringBootTest
class PaymentServiceApplicationTests {
    final AccountService accountService;
    final ClientService clientService;
    final PaymentController paymentController;
    @LocalServerPort
    private int port;

    @Mock
    private RestTemplate restTemplate;
    @Autowired
    private MockMvc mockMvc;

    PaymentServiceApplicationTests(AccountService accountService, ClientService clientService, PaymentController paymentController) {
        this.accountService = accountService;
        this.clientService = clientService;
        this.paymentController = paymentController;
    }

    ClientDTO clientDTO1;
    ClientDTO clientDTO2;

    @BeforeAll
    void before() {
        clientDTO1 = new ClientDTO();
        clientDTO1.setFirstName("fn1");
        clientDTO1.setFirstName("ln1");
        clientDTO1.setLogin("l1");
        clientDTO1.setPassword("psw1");
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountNumber("242424");
        accountDTO.setAccountType("card/simple");
        accountDTO.setBalance(BigDecimal.valueOf(100.5));
        HashSet<AccountDTO> hashSet = new HashSet();
        hashSet.add(accountDTO);
        clientDTO1.setAccounts(hashSet);
        clientDTO2 = new ClientDTO();
        clientDTO2.setFirstName("fn1");
        clientDTO2.setFirstName("ln1");
        clientDTO1.setLogin("l1");
        clientDTO2.setPassword("psw1");
        AccountDTO accountDTO2 = new AccountDTO();
        accountDTO2.setAccountNumber("242424");
        accountDTO2.setAccountType("card/simple");
        accountDTO2.setBalance(BigDecimal.valueOf(100.5));
        HashSet<AccountDTO> hashSet2 = new HashSet();
        hashSet.add(accountDTO2);
        clientDTO1.setAccounts(hashSet2);
    }

    @Test
    void PaymentTest() throws Exception {
        Client cl1 = clientService.addClient(clientDTO1);
        Client cl2 = clientService.addClient(clientDTO2);
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setSourceAccId(cl1.getAccounts().iterator().next().getAccountId());
        transactionDTO.setDestAccId(cl2.getAccounts().iterator().next().getAccountId());
        transactionDTO.setAmount(BigDecimal.valueOf(20));
        transactionDTO.setReason("reason");
        Mockito.when(restTemplate.postForEntity("/api/v1/payment/transaction", transactionDTO, TransactionDTO.class))
                .thenReturn(new ResponseEntity(transactionDTO, HttpStatus.OK));
        System.out.println(transactionDTO.toString());

    }
}

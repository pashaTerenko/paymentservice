package com.terenko.paymentservice.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.terenko.paymentservice.DTO.AccountDTO;
import com.terenko.paymentservice.DTO.ClientDTO;
import com.terenko.paymentservice.Service.AccountService;
import com.terenko.paymentservice.Service.ClientService;
import com.terenko.paymentservice.models.Client;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/client/")
public class ClientController {
    final ClientService clientService;
    final AccountService accountService;
    public ClientController(ClientService clientService, AccountService accountService) {
        this.clientService = clientService;
        this.accountService = accountService;
    }

    @GetMapping(value = "get", consumes = { "application/json", "application/xml" }, produces = { "application/json", "application/xml" })
    @ResponseStatus(HttpStatus.OK)
    @JsonView(AccountDTO.Detail.class)
    public List<AccountDTO> getAccounts(@RequestParam("client_id") long clientId  ) throws IllegalArgumentException {
        Client client =clientService.getById(clientId);
        return client.getAccounts().stream().map(AccountDTO::of).collect(Collectors.toList());
    }
    @PostMapping(value = "create", consumes = { "application/json", "application/xml" }, produces = { "application/json", "application/xml" })
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(ClientDTO.Status.class)
    public ClientDTO create(@Validated(ClientDTO.New.class)  @RequestBody  ClientDTO clientDTO) {
        return ClientDTO.of(clientService.addClient(clientDTO));
    }
    @PostMapping(value = "createacc", consumes = { "application/json", "application/xml" }, produces = { "application/json", "application/xml" })
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(AccountDTO.Detail.class)
    public AccountDTO create(@Validated(AccountDTO.New.class)  @RequestBody  AccountDTO accountDTO,@RequestParam("client_id") long clientId) {
        Client client =clientService.getById(clientId);
        return AccountDTO.of(accountService.addAccount(accountDTO,client));
    }

}

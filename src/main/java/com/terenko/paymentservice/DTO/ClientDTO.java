package com.terenko.paymentservice.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.terenko.paymentservice.models.Client;
import com.terenko.paymentservice.models.TransactionResult;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class ClientDTO {
    interface New {

    }

    private long clientId;
    @NotNull(groups = {New.class})
    @JsonProperty("first_name")
    private String firstName;
    @NotNull(groups = {New.class})
    @JsonProperty("last_name")

    private String lastName;
    @NotNull(groups = {New.class})
    private String login;
    @NotNull(groups = {New.class})
    private String password;

    private Set<AccountDTO> accounts;

    public static ClientDTO of(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setClientId(client.getClientId());
        clientDTO.setAccounts(client.getAccounts().stream().map(AccountDTO::of).collect(Collectors.toSet()));
        clientDTO.setFirstName(client.getFirstName());
        clientDTO.setLastName(client.getLastName());
        return clientDTO;
    }

    public static Client from(ClientDTO clientDTO) {
        Client client = new Client();

        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setLogin(clientDTO.getLogin());
        client.setLogin(clientDTO.getPassword());
        return client;
    }
}

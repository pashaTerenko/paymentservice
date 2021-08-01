package com.terenko.paymentservice.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.terenko.paymentservice.models.Client;
import com.terenko.paymentservice.models.TransactionResult;
import lombok.Data;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@XmlRootElement
@JacksonXmlRootElement(localName = "client")
public class ClientDTO {
    public interface New {

    }
    public interface Status {
    }
    public interface Detail {
    }

    @JsonProperty("client_id")
    @JacksonXmlProperty(localName = "client_id")
    @JsonView({Status.class,Detail.class})

    private long clientId;
    @NotNull(groups = {New.class})
    @JsonProperty("first_name")
    @JacksonXmlProperty(localName = "account_type")
    @JsonView({TransactionDTO.Detail.class})

    private String firstName;
    @NotNull(groups = {New.class})
    @JacksonXmlProperty(localName = "account_type")
    @JsonProperty("last_name")
    @JsonView({TransactionDTO.Detail.class})

    private String lastName;

  /*  @NotNull(groups = {New.class})
    private String login;
    @NotNull(groups = {New.class})
    private String password;*/
  @Valid
    private Set< AccountDTO> accounts;

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
      //  client.setLogin(clientDTO.getLogin());
       // client.setLogin(clientDTO.getPassword());
        return client;
    }
}

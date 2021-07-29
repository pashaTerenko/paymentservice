package com.terenko.paymentservice.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

public class ClientDTO {
    interface New{

    }

    @Null(groups = {New.class})
    @JsonProperty("id")
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

    private List<AccountDTO> accounts;
}

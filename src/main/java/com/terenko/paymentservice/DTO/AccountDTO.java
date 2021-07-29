package com.terenko.paymentservice.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class AccountDTO {
    @JsonProperty("account_num")
    private String accountNumber;
    @JsonProperty("account_type")

    private String accountType;
    private BigDecimal balance;
}

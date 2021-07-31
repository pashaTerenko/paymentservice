package com.terenko.paymentservice.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.terenko.paymentservice.models.Account;
import com.terenko.paymentservice.models.Client;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
public class AccountDTO {
    interface Details {
    }

    @JsonView({Details.class})
    private long accountId;

    @JsonProperty("account_num")
    @Pattern(regexp = "^\\\\d{9}$")
    private String accountNumber;
    @JsonProperty("account_type")
    private String accountType;
    private BigDecimal balance;

    public static AccountDTO of(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountId(account.getAccountId());

        accountDTO.setBalance(account.getBalance());
        accountDTO.setAccountType(account.getAccountNumber());
        accountDTO.setAccountNumber(account.getAccountNumber());
        return accountDTO;
    }

    public static Account from(AccountDTO accountDTO) {
        Account account = new Account();
        account.setBalance(accountDTO.getBalance());
        account.setAccountType(accountDTO.getAccountNumber());
        account.setAccountNumber(accountDTO.getAccountNumber());
        return account;
    }
}

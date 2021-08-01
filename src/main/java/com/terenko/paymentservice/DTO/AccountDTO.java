package com.terenko.paymentservice.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.terenko.paymentservice.models.Account;
import com.terenko.paymentservice.models.Client;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@XmlRootElement
@JacksonXmlRootElement(localName = "account")
public class AccountDTO {
   public interface Detail {
    }
    public interface New {
    }
    @JsonView({Detail.class})
    @JsonProperty("account_id")

    private long accountId;

    @JsonProperty("account_num")
    @JacksonXmlProperty(localName = "account_num")
    @Pattern(regexp = "^\\\\d{9}$")
    @JsonView({Detail.class})
    private String accountNumber;
    @JacksonXmlProperty(localName = "account_type")
    @JsonProperty("account_type")
    @JsonView({Detail.class})
    private String accountType;
    @NotNull(groups = {New.class})
    @JsonView({Detail.class})
    @Min(0)
    private BigDecimal balance;

    public static AccountDTO of(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountId(account.getAccountId());

        accountDTO.setBalance(account.getBalance());
        accountDTO.setAccountType(account.getAccountType());
        accountDTO.setAccountNumber(account.getAccountNumber());
        return accountDTO;
    }

    public static Account from(AccountDTO accountDTO) {
        Account account = new Account();
        account.setBalance(accountDTO.getBalance());
        account.setAccountType(accountDTO.getAccountType());

        account.setAccountNumber(accountDTO.getAccountNumber());

        return account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDTO that = (AccountDTO) o;
        return accountId == that.accountId && Objects.equals(accountNumber, that.accountNumber) && Objects.equals(accountType, that.accountType) && Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, accountNumber, accountType, balance);
    }
}

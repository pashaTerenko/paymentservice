package com.terenko.paymentservice.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.sun.xml.txw2.annotation.XmlElement;
import com.terenko.paymentservice.Service.AccountService;
import com.terenko.paymentservice.models.Account;
import com.terenko.paymentservice.models.Client;
import com.terenko.paymentservice.models.Transaction;
import com.terenko.paymentservice.models.TransactionResult;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Date;
import java.util.stream.Collectors;

@Data
@XmlRootElement
@JacksonXmlRootElement(localName = "transaction")
public class TransactionDTO {
    public interface New {
    }

    public interface Status {
    }

    public interface Detail {
    }
    @JsonView({Status.class})
    @Null(groups = {New.class})
    private String status;
    @JsonView({Status.class})

    @JsonProperty("payment_id")
    @JacksonXmlProperty(localName = "transaction")
    private long paymentId;

    @JsonView({Detail.class})
    @Null(groups = {New.class})
    private String timestamp;
    @JsonProperty("source_acc_id")
    @JacksonXmlProperty(localName = "source_acc_id")

    @JsonView({Detail.class})
    @NotNull(groups = {New.class})
    private long sourceAccId;
    @JsonProperty("dest_acc_id")
    @JacksonXmlProperty(localName = "dest_acc_id")
    @JsonView({Detail.class})

    @NotNull(groups = {New.class})
    private long destAccId;
    @JsonView({Detail.class})
    @NotNull(groups = {New.class})
    @Min(value = 0)
    private BigDecimal amount;
    @NotNull(groups = {New.class})
    @JsonView({Detail.class})
    private String reason;
    @JsonView({Detail.class})
    @JsonProperty("src_acc_num")
    private String sourceAccNum;
    @JsonView({Detail.class})
    @JsonProperty("dest_acc_num")
    private String destAccNum;
    @JsonView({Detail.class})
    private ClientDTO payer;
    @JsonView({Detail.class})
    private ClientDTO recipient;

    public static TransactionDTO of(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setPaymentId(transaction.getPaymentId());
        transactionDTO.setTimestamp(transaction.getCreated().toString());
        transactionDTO.setStatus(transaction.getTransactionResult().toString());
        transactionDTO.setSourceAccId(transaction.getSourceAcc().getAccountId());
        transactionDTO.setDestAccId(transaction.getDestAcc().getAccountId());
        transactionDTO.setReason(transaction.getReason());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setSourceAccNum(transaction.getSourceAcc().getAccountNumber());
        transactionDTO.setDestAccNum(transaction.getDestAcc().getAccountNumber());
        transactionDTO.setPayer(ClientDTO.of(transaction.getSourceAcc().getClient()));
        transactionDTO.setRecipient(ClientDTO.of(transaction.getDestAcc().getClient()));
        return transactionDTO;
    }

    public static Transaction from(TransactionDTO transactionDTO, AccountService accountService) throws IllegalArgumentException {
        Transaction transaction = new Transaction();
        transaction.setSourceAcc(accountService.getById(transactionDTO.getSourceAccId()));
        transaction.setDestAcc(accountService.getById(transactionDTO.getDestAccId()));
        if (transaction.getSourceAcc() == null)
            throw new IllegalArgumentException("source account undefined");

        if (transaction.getDestAcc() == null)
            throw new IllegalArgumentException("destination account undefined");

        transaction.setReason(transactionDTO.getReason());
        transaction.setAmount(transactionDTO.getAmount());
        return transaction;
    }
}

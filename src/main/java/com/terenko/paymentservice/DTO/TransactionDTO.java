package com.terenko.paymentservice.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.terenko.paymentservice.Service.AccountService;
import com.terenko.paymentservice.models.Account;
import com.terenko.paymentservice.models.Client;
import com.terenko.paymentservice.models.Transaction;
import com.terenko.paymentservice.models.TransactionResult;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.Date;
import java.util.stream.Collectors;

@Data
public class TransactionDTO {
    public interface New {
    }

    public interface Status {
    }

    public interface Detail {
    }

    @JsonView({Status.class})
    @Null(groups = {New.class})
    @JsonProperty("payment_id")
    private long paymentId;
    @JsonView({Status.class})
    @Null(groups = {New.class})
    private TransactionResult status;
    @JsonView({Detail.class})
    @Null(groups = {New.class})
    private Date timestamp;
    @JsonProperty("source_acc_id")
    @JsonView({Detail.class})
    @NotNull(groups = {New.class})
    private long sourceAccId;
    @JsonView({Detail.class})
    @JsonProperty("dest_acc_id")
    @NotNull(groups = {New.class})
    private long destAccId;
    @JsonView({Detail.class})
    @NotNull(groups = {New.class})
    private BigDecimal amount;
    @NotNull(groups = {New.class})
    private String reason;


    public static TransactionDTO of(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setPaymentId(transaction.getPaymentId());
        transactionDTO.setTimestamp(transaction.getCreated());
        transactionDTO.setStatus(transaction.getTransactionResult());
        transactionDTO.setSourceAccId(transaction.getSourceAcc().getAccountId());
        transactionDTO.setDestAccId(transaction.getDestAcc().getAccountId());
        transactionDTO.setReason(transaction.getReason());
        transactionDTO.setAmount(transaction.getAmount());

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

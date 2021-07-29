package com.terenko.paymentservice.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.terenko.paymentservice.models.Account;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

public class TransactionDTO {
    @JsonProperty("payment_id")
    private long paymentId;

    @JsonProperty("source_acc_id")
    private long sourceAccId;
    @JsonProperty("dest_acc_id")
    private long destAccId;
    private BigDecimal amount;
    private String reason;
}

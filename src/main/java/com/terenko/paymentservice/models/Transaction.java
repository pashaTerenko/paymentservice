package com.terenko.paymentservice.models;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Transaction extends  BaseEntity{

    @Id
    @SequenceGenerator(name = "mySeqGen", sequenceName = "mySeq", initialValue = 98723450, allocationSize = 100)
    @GeneratedValue(generator = "mySeqGen")
    private long paymentId;
    @ManyToOne
    @JoinColumn(name="account_s_id", nullable=false)
    private Account sourceAcc;
    @ManyToOne
    @JoinColumn(name="account_d_id", nullable=false)
    private Account destAcc;
    private BigDecimal amount;
    private String reason;
    @NotNull
    @Enumerated
    @Column(nullable = false)

    private TransactionResult transactionResult;

}


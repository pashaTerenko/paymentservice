package com.terenko.paymentservice.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Account extends BaseEntity {
    @Id
    @SequenceGenerator(name = "mySeqGenAcc", sequenceName = "mySeq1", initialValue = 111, allocationSize = 100)
    @GeneratedValue(generator = "mySeqGenAcc")
    private long accountId;
    @Column(unique = true)
    private String accountNumber;
    private String accountType;
    private BigDecimal balance;
    @OneToMany(mappedBy="sourceAcc")

    Set<Transaction> incomingTransactionSet = new HashSet<>();

    @OneToMany(mappedBy="destAcc")

    Set<Transaction> departingTransactionSet = new HashSet<>();
    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    private Client client;

}

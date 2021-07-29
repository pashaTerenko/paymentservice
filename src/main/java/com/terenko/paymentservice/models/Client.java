package com.terenko.paymentservice.models;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Client extends BaseEntity{
@Id
@SequenceGenerator(name = "mySeqGen", sequenceName = "mySeq", initialValue = 98723450, allocationSize = 100)
@GeneratedValue(generator = "mySeqGen")
private long clientId;
private String firstName;
private String lastName;
private String login;
private String password;/*
@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@Fetch(value= FetchMode.SELECT)*/
@OneToMany(mappedBy="client")
private Set<Account> accounts;

}

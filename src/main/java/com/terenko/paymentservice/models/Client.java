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
public class Client extends BaseEntity{
@Id
@SequenceGenerator(name = "mySeqGenCl", sequenceName = "mySeqCl", initialValue = 111, allocationSize = 100)
@GeneratedValue(generator = "mySeqGenCl")
private long clientId;
@NotNull
@Column(nullable = false)
private String firstName;
@NotNull
@Column(nullable = false)
private String lastName;
//private String login;
/*
@Fetch(value = FetchMode.SELECT)
private String password;
*/
/*
@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@Fetch(value= FetchMode.SELECT)*/
@OneToMany(mappedBy="client")
private Set<Account> accounts =new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return clientId == client.clientId && Objects.equals(firstName, client.firstName) && Objects.equals(lastName, client.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), clientId, firstName, lastName);
    }
}

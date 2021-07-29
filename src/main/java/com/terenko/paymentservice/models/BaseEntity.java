package com.terenko.paymentservice.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Base class with property 'id'.
 * Used as a base class for all objects that requires this property.
        */

@MappedSuperclass
@Data
public class BaseEntity {



    @CreatedDate
    @Column(name = "created")
    protected Date created;

    @LastModifiedDate
    @Column(name = "updated")
    protected Date updated;

    public BaseEntity() {
        setCreated(new Date());
        setUpdated(new Date());
    }
}

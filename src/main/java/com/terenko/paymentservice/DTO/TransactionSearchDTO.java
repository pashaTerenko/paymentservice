package com.terenko.paymentservice.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement
@JacksonXmlRootElement(localName = "transaction")
public class TransactionSearchDTO {
    @JsonProperty("source_acc_id")
    @JacksonXmlProperty(localName = "source_acc_id")
    private long sourceAccId;
    @JsonProperty("dest_acc_id")
    @JacksonXmlProperty(localName = "dest_acc_id")
    private long destAccId;
    @JsonProperty("payer_id")
    @JacksonXmlProperty(localName = "payer_id")
    private long payerId;
    @JsonProperty("recipient_id")
    @JacksonXmlProperty(localName = "recipient_id")
    private long recipientId;
}

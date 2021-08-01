package com.terenko.paymentservice.DTO;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement
@JacksonXmlRootElement(localName = "Error")
public class ValidationErrorDTO {
    int code;
    String text;
}

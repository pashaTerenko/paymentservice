package com.terenko.paymentservice.DTO;

import lombok.Data;

@Data
public class ValidationErrorDTO {
    int code;
    String text;
}

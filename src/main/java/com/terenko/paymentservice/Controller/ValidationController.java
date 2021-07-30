package com.terenko.paymentservice.Controller;

import com.terenko.paymentservice.DTO.ValidationErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;

@RestController
@Validated
class ValidationController {

    // request mapping method omitted

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ValidationErrorDTO> handleConstraintViolationException(ConstraintViolationException e) {
        ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
        validationErrorDTO.setCode(400);
        validationErrorDTO.setText(e.getMessage());

        return  new ResponseEntity<>(validationErrorDTO,HttpStatus.valueOf(400));
    }

}
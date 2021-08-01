package com.terenko.paymentservice.Controller;

import com.terenko.paymentservice.DTO.ValidationErrorDTO;
import com.terenko.paymentservice.exeption.InsufficientFundsExeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

@ControllerAdvice

class ValidationController {

    // request mapping method omitted

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ValidationErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
        validationErrorDTO.setCode(400);


        validationErrorDTO.setText(e.getLocalizedMessage());

        return  new ResponseEntity<>(validationErrorDTO,HttpStatus.valueOf(400));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ValidationErrorDTO> handleConstraintViolationException(ConstraintViolationException e) {
        ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
        validationErrorDTO.setCode(400);
        StringBuilder sb = new StringBuilder();
        e.getConstraintViolations().forEach(x->{
            sb.append(x.getPropertyPath()+"="+x.getInvalidValue()+" :"+x.getMessage());
            sb.append("\n");
                }
        );
        validationErrorDTO.setText(sb.toString());

        return  new ResponseEntity<>(validationErrorDTO,HttpStatus.valueOf(400));
    }
    @ExceptionHandler(InsufficientFundsExeption.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ValidationErrorDTO> InsufficientFundsExeption(InsufficientFundsExeption e) {
        ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
        validationErrorDTO.setCode(400);
        validationErrorDTO.setText(e.getLocalizedMessage());

        return  new ResponseEntity<>(validationErrorDTO,HttpStatus.valueOf(400));
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ValidationErrorDTO> IllegalArgumentException(IllegalArgumentException e) {
        ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
        validationErrorDTO.setCode(400);
        validationErrorDTO.setText(e.getLocalizedMessage());

        return  new ResponseEntity<>(validationErrorDTO,HttpStatus.valueOf(400));
    }
}
package com.terenko.paymentservice.Controller;

import com.terenko.paymentservice.DTO.TransactionDTO;
import com.terenko.paymentservice.Service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/payment/")
public class PaymentController {

   final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces =
        MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransactionDTO>> transaction(@Validated({TransactionDTO.New.class}) @RequestBody List<TransactionDTO> transactionDTOList){
    List<TransactionDTO> response = paymentService.ExecuteTransactions(transactionDTOList);
    return new ResponseEntity<>(response, HttpStatus.OK);
}
}

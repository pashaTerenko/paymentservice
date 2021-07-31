package com.terenko.paymentservice.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.terenko.paymentservice.DTO.TransactionDTO;
import com.terenko.paymentservice.Service.PaymentService;
import com.terenko.paymentservice.models.TransactionResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/payment/")
@Validated
@Valid
public class PaymentController {

    final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    //Controller accept xml and json formats

    @PostMapping(value = "transactions", consumes = { "application/json", "application/xml" }, produces = { "application/json", "application/xml" })
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionDTO> transactions(@Validated(TransactionDTO.New.class)  @RequestBody List<@Valid TransactionDTO> transactionDTOList) {
        return paymentService.executeTransactions(transactionDTOList);
    }

    @PostMapping(value = "transaction", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE  })
    @ResponseStatus(HttpStatus.OK)
    @JsonView(TransactionDTO.Status.class)

    public TransactionDTO transaction(@Validated(TransactionDTO.New.class) @RequestBody final TransactionDTO transactionDTO)throws Exception {
        return  paymentService.executeTransaction(transactionDTO);
    }
}

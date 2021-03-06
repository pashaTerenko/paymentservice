package com.terenko.paymentservice.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.terenko.paymentservice.DTO.AccountDTO;
import com.terenko.paymentservice.DTO.ClientDTO;
import com.terenko.paymentservice.DTO.TransactionDTO;
import com.terenko.paymentservice.DTO.TransactionSearchDTO;
import com.terenko.paymentservice.Service.PaymentService;
import com.terenko.paymentservice.models.Client;
import com.terenko.paymentservice.models.TransactionResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/payment/")
public class PaymentController {

    final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    //Controller accept xml and json formats

    @PostMapping(value = "transactions", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE  })
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(TransactionDTO.Status.class)
    public List<TransactionDTO> transactions(@Validated(TransactionDTO.New.class)  @RequestBody List<@Valid TransactionDTO> transactionDTOList) {
        return paymentService.executeTransactions(transactionDTOList);
    }

    @PostMapping(value = "transaction", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE  })
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(TransactionDTO.Status.class)
    public TransactionDTO transaction(@Validated(TransactionDTO.New.class) @RequestBody  TransactionDTO transactionDTO)throws Exception {
        return  paymentService.executeTransaction(transactionDTO);
    }
    @GetMapping(value = "search", consumes = { "application/json", "application/xml" }, produces = { "application/json", "application/xml" })
    @ResponseStatus(HttpStatus.OK)
    @JsonView(TransactionDTO.Detail.class)
    public List<TransactionDTO> search(@Validated @RequestBody TransactionSearchDTO transactionSearchDTO) throws IllegalArgumentException {
        return paymentService.search(transactionSearchDTO);
    }
}

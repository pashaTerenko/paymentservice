package com.terenko.paymentservice.Service;

import com.terenko.paymentservice.DTO.TransactionDTO;
import com.terenko.paymentservice.DTO.TransactionSearchDTO;
import com.terenko.paymentservice.models.Transaction;

import java.util.List;

public interface PaymentService {
        List<TransactionDTO> executeTransactions(List<TransactionDTO> transactionDTOList);
         TransactionDTO executeTransaction(TransactionDTO transactionDTO);
        List<TransactionDTO> search(TransactionSearchDTO transactionSearchDTO);


}

package com.terenko.paymentservice.Service;

import com.terenko.paymentservice.DTO.TransactionDTO;
import com.terenko.paymentservice.models.Transaction;

import java.util.List;

public interface PaymentService {
        List<TransactionDTO> ExecuteTransactions(List<TransactionDTO> transactionDTOList);
}

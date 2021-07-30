package com.terenko.paymentservice.Service;

import com.terenko.paymentservice.DTO.TransactionDTO;
import com.terenko.paymentservice.models.Account;
import com.terenko.paymentservice.models.Transaction;
import com.terenko.paymentservice.models.TransactionResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class PaymentServiceImpl implements PaymentService{
    AccountService accountService;
    TransactionService transactionService;
    @Transactional
    @Override
    public List<TransactionDTO> ExecuteTransactions(List<TransactionDTO> transactionDTOList) {
        List<Transaction> transactions =transactionDTOList.stream().map(x->TransactionDTO.from(x,accountService)).collect(Collectors.toList());
        //
        transactions.forEach(x->{

            if(x.getSourceAcc().getBalance().divide(x.getAmount()).compareTo(BigDecimal.ZERO)==1||x.getSourceAcc().getBalance().divide(x.getAmount()).compareTo(BigDecimal.ZERO)==0){
                Account source = x.getSourceAcc();
                Account dest = x.getDestAcc();
                source.getBalance().divide(x.getAmount());
                dest.getBalance().add(x.getAmount());
                accountService.updateAccount(source);
                accountService.updateAccount(dest);
                transactionService.saveTransaction(x);
                x.setTransactionResult(TransactionResult.TRANSACTION_SUCCESS);

            }else{
                x.setTransactionResult(TransactionResult.TRANSACTION_FAIL);
                transactionService.saveTransaction(x);
            }

        });
        //


        return transactions.stream().map(TransactionDTO::of).collect(Collectors.toList());
    }
}

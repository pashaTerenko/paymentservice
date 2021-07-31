package com.terenko.paymentservice.Service;

import com.terenko.paymentservice.DTO.TransactionDTO;
import com.terenko.paymentservice.exeption.InsufficientFundsExeption;
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
   final AccountService accountService;
   final TransactionService transactionService;

    public PaymentServiceImpl(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @Transactional
    @Override
    public List<TransactionDTO> executeTransactions(List<TransactionDTO> transactionDTOList) {
        return transactionDTOList.stream().map(this::executeTransaction).collect(Collectors.toList());
    }
    @Transactional
    @Override
    public TransactionDTO executeTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = null;
        try {
            transaction = TransactionDTO.from(transactionDTO,accountService);
            Account source = transaction.getSourceAcc();
            Account dest = transaction.getDestAcc();
            BigDecimal amount=transaction.getAmount();
            BigDecimal remaind=source.getBalance().subtract(amount);
            //check balance
            if((remaind.compareTo(BigDecimal.ZERO)==1)||remaind.compareTo(BigDecimal.ZERO)==0){
                //Transfer
                source.setBalance(source.getBalance().subtract(amount)) ;
                dest.setBalance(dest.getBalance().add(amount));
                //update models
                accountService.updateAccount(source);
                accountService.updateAccount(dest);
                //save transaction
                transaction.setTransactionResult(TransactionResult.TRANSACTION_SUCCESS);

                transactionService.saveTransaction(transaction);

            }else
            throw new InsufficientFundsExeption();
        } catch (InsufficientFundsExeption e) {
            transaction.setTransactionResult(TransactionResult.TRANSACTION_FAIL);
            transactionService.saveTransaction(transaction);
        }


        return TransactionDTO.of(transaction);
    }
}

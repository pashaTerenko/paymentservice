package com.terenko.paymentservice.Service;

import com.terenko.paymentservice.DTO.TransactionDTO;
import com.terenko.paymentservice.DTO.TransactionSearchDTO;
import com.terenko.paymentservice.exeption.InsufficientFundsExeption;
import com.terenko.paymentservice.models.Account;
import com.terenko.paymentservice.models.Client;
import com.terenko.paymentservice.models.Transaction;
import com.terenko.paymentservice.models.TransactionResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {
    private static Logger log = Logger.getLogger(PaymentServiceImpl.class.getName());
    final AccountService accountService;
    final TransactionService transactionService;
    final ClientService clientService;

    public PaymentServiceImpl(AccountService accountService, TransactionService transactionService, ClientService clientService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.clientService = clientService;
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
            transaction = TransactionDTO.from(transactionDTO, accountService);
            Account source = transaction.getSourceAcc();
            if (source == null) throw new IllegalArgumentException("source account  not found");
            Account dest = transaction.getDestAcc();
            if (dest == null) throw new IllegalArgumentException("destination account not found");

            BigDecimal amount = transaction.getAmount();
            BigDecimal remaind = source.getBalance().subtract(amount);
            //check balance
            if ((remaind.compareTo(BigDecimal.ZERO) == 1) || remaind.compareTo(BigDecimal.ZERO) == 0) {
                //Transfer
                source.setBalance(source.getBalance().subtract(amount));
                dest.setBalance(dest.getBalance().add(amount));
                //update models
                accountService.updateAccount(source);
                accountService.updateAccount(dest);
                //save transaction
                transaction.setTransactionResult(TransactionResult.TRANSACTION_SUCCESS);

                transactionService.saveTransaction(transaction);
                log.info("transaction with id "+ transaction.getPaymentId()+" success");
            } else
                throw new InsufficientFundsExeption();
        } catch (InsufficientFundsExeption e) {
            transaction.setTransactionResult(TransactionResult.TRANSACTION_FAIL);
            transactionService.saveTransaction(transaction);
            log.info("transaction with id "+ transaction.getPaymentId()+" failed");

        }


        return TransactionDTO.of(transaction);
    }

    @Override
    public List<TransactionDTO> search(TransactionSearchDTO dto) {


        List<Transaction> bySearch;
        List<Transaction> transactions = new ArrayList<>();
        if(dto.getSourceAccId()!=0)
        if (accountService.isExist(dto.getSourceAccId())) {
            Account account = accountService.getById(dto.getSourceAccId());
         bySearch=transactionService.getBySourceAccount(account);
         transactions=   transactions.stream().filter(x->x.getSourceAcc().equals(account)).collect(Collectors.toList());
         transactions.addAll(bySearch);
        }else throw new IllegalArgumentException("source account  not found");
        if(dto.getDestAccId()!=0)
            if (accountService.isExist(dto.getDestAccId())) {
                Account account = accountService.getById(dto.getDestAccId());
            if(!transactions.isEmpty())
                transactions=    transactions.stream().filter(x->x.getDestAcc().equals(account)).collect(Collectors.toList());
            else{
                bySearch=transactionService.getByDestinationAccount(account);
                transactions.addAll(bySearch);
            }
        }else  throw new IllegalArgumentException("destination account  not found");
        if(dto.getPayerId()!=0)
            if (clientService.isExist(dto.getPayerId())) {
                Client client = clientService.getById(dto.getPayerId());
            if(!transactions.isEmpty())
            transactions=    transactions.stream().filter(x->x.getSourceAcc().getClient().equals(client)).collect(Collectors.toList());
            else{
                bySearch=transactionService.getBySourceClient(client);
                transactions.addAll(bySearch);
            }
        }else  throw new IllegalArgumentException("source client  not found");
        if(dto.getRecipientId()!=0)
            if (clientService.isExist(dto.getRecipientId())) {
                Client  client = clientService.getById(dto.getRecipientId());
            if(!transactions.isEmpty())
                transactions=transactions.stream().filter(x->x.getDestAcc().getClient().equals(client)).collect(Collectors.toList());
            else{
                bySearch=transactionService.getByDestinationClient(client);
                transactions.addAll(bySearch);
            }
        }else  throw new IllegalArgumentException("destination client  not found");
        return transactions.stream().map(TransactionDTO::of).collect(Collectors.toList());

    }
}

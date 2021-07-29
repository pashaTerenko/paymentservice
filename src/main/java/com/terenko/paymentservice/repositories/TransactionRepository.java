package com.terenko.paymentservice.repositories;


import com.terenko.paymentservice.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    Transaction findByPaymentId(long id);
    List<Transaction> findBySourceAcc_AccountId(long id);
    List<Transaction> findByDestAcc_AccountId(long id);
    List<Transaction> findBySourceAcc_Client_ClientId(long id);
    List<Transaction> findByDestAcc_Client_ClientId(long id);


}

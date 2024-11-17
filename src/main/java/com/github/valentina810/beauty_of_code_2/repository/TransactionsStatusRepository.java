package com.github.valentina810.beauty_of_code_2.repository;

import com.github.valentina810.beauty_of_code_2.model.StatusTransaction;
import com.github.valentina810.beauty_of_code_2.model.Transaction;
import com.github.valentina810.beauty_of_code_2.model.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionsStatusRepository extends JpaRepository<TransactionStatus, Long> {
    TransactionStatus findByStatusName(String statusName);
}

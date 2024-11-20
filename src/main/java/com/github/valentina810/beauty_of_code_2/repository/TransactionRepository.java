
package com.github.valentina810.beauty_of_code_2.repository;

import com.github.valentina810.beauty_of_code_2.dto.TransactionsWithStatusDto;
import com.github.valentina810.beauty_of_code_2.model.Transaction;
import com.github.valentina810.beauty_of_code_2.model.TransactionStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    @Query(value="select new com.github.valentina810.beauty_of_code_2.dto.TransactionsWithStatusDto(t.id, st) " +
            "from Transaction t join TransactionStatus st on t.status.id = st.statusId "+
            "where t.id in :transactionIds")
    List<TransactionsWithStatusDto> findTransactionStatusesByIds(@Param("transactionIds") List<UUID> transactionIds);

    @Modifying
    @Query("update Transaction t set t.status = :status where t.id in :ids")
    void updateTransactionStatusForIds(@Param("ids") List<UUID> ids, @Param("status") TransactionStatus status);

    @Transactional
    @Query(value = "SELECT unnest(generate_transactions(:n, :statusName));", nativeQuery = true)
    List<UUID> generateTransactions(@Param("n") int n, @Param("statusName") String statusName);


//    private final List<Transaction> transactions = new ArrayList<>();
//
//    public void updateTransaction(Transaction transaction) {
//        transactions.add(transaction);
//    }
//
//    public List<Transaction> getTransactions() {
//        return transactions;
//    }
}

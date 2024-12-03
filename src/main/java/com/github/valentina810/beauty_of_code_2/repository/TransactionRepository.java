
package com.github.valentina810.beauty_of_code_2.repository;

import com.github.valentina810.beauty_of_code_2.dto.TransactionsWithStatusDto;
import com.github.valentina810.beauty_of_code_2.model.Transaction;
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
    @Transactional
    @Query(value = "update transaction set id_status = :idStatus where id in :ids;", nativeQuery = true)
    void updateTransactionStatusForIds(@Param("ids") List<UUID> ids, @Param("idStatus") Long idStatus);

    @Transactional
    @Query(value = "select unnest(generate_transactions(:n, :statusName));", nativeQuery = true)
    List<UUID> generateTransactions(@Param("n") int n, @Param("statusName") String statusName);

    @Modifying
    @Transactional
    @Query(value = "delete from transaction where id in :ids;", nativeQuery = true)
    void deleteAllByIds(@Param("ids") List<UUID> ids);
}

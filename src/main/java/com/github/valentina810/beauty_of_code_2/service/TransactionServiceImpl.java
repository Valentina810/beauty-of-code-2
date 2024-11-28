
package com.github.valentina810.beauty_of_code_2.service;

import com.github.valentina810.beauty_of_code_2.dto.ErrorTransactionDto;
import com.github.valentina810.beauty_of_code_2.dto.TransactionsResultDto;
import com.github.valentina810.beauty_of_code_2.dto.TransactionsWithStatusDto;
import com.github.valentina810.beauty_of_code_2.model.StatusTransaction;
import com.github.valentina810.beauty_of_code_2.model.TransactionStatus;
import com.github.valentina810.beauty_of_code_2.repository.TransactionRepository;
import com.github.valentina810.beauty_of_code_2.repository.TransactionsStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.github.valentina810.beauty_of_code_2.model.StatusTransaction.PENDING;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private static final int BATCH_SIZE = 1000;

    private final TransactionRepository transactionRepository;
    private final TransactionsStatusRepository transactionStatusRepository;

    @Async("transactionTaskExecutor")
    public CompletableFuture<TransactionsResultDto> processBatch(List<UUID> batchIds, StatusTransaction status) {
        TransactionsResultDto result = new TransactionsResultDto();
        List<UUID> successfullyProcessedTransactions = new ArrayList<>();
        List<ErrorTransactionDto> erroneousTransactions = new ArrayList<>();

        try {
            List<TransactionsWithStatusDto> transactionStatuses = transactionRepository.findTransactionStatusesByIds(batchIds);

            List<UUID> transactionsToProcess = new ArrayList<>();
            for (TransactionsWithStatusDto transactionData : transactionStatuses) {
                UUID transactionId = transactionData.getId();
                String currentStatus = transactionData.getStatus().getStatusName();

                if (PENDING.name().equalsIgnoreCase(currentStatus)) {
                    transactionsToProcess.add(transactionId);
                } else {
                    erroneousTransactions.add(
                            ErrorTransactionDto.builder()
                                    .transactionId(transactionId)
                                    .errorMessage("Cannot process transaction with status: " + currentStatus).build()
                    );
                }
            }

            if (!transactionsToProcess.isEmpty()) {
                TransactionStatus byStatusName = transactionStatusRepository.findByStatusName(status.name());
                transactionRepository.updateTransactionStatusForIds(transactionsToProcess, byStatusName.getStatusId());
                successfullyProcessedTransactions.addAll(transactionsToProcess);
            }

            List<UUID> missingTransactionIds = batchIds.stream()
                    .filter(id -> transactionStatuses.stream().noneMatch(data -> id.equals(data.getId())))
                    .toList();

            missingTransactionIds.forEach(id -> erroneousTransactions.add(
                    ErrorTransactionDto.builder()
                            .transactionId(id)
                            .errorMessage("Transaction not found.")
                            .build()
            ));

        } catch (Exception e) {
            batchIds.forEach(id -> erroneousTransactions.add(
                    ErrorTransactionDto.builder()
                            .transactionId(id)
                            .errorMessage(e.getMessage()).build()
            ));
        }

        result.setCountSuccessfullyProcessedTransactions((long) successfullyProcessedTransactions.size());
        result.setSuccessfullyProcessedTransactions(successfullyProcessedTransactions);
        result.setCountErroneousTransactions((long) erroneousTransactions.size());
        result.setErroneousTransactions(erroneousTransactions);

        return CompletableFuture.completedFuture(result);
    }

    @Override
    public TransactionsResultDto updateTransactionStatuses(List<UUID> transactionIds, StatusTransaction status) {
        TransactionsResultDto finalResult = new TransactionsResultDto();
        List<CompletableFuture<TransactionsResultDto>> futures = new ArrayList<>();

        for (int i = 0; i < transactionIds.size(); i += BATCH_SIZE) {
            List<UUID> batchIds = transactionIds.subList(i, Math.min(i + BATCH_SIZE, transactionIds.size()));
            futures.add(processBatch(batchIds, status));
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        List<UUID> allSuccessfullyProcessedTransactions = new ArrayList<>();
        List<ErrorTransactionDto> allErroneousTransactions = new ArrayList<>();
        long successfulCount = 0;
        long errorCount = 0;

        for (CompletableFuture<TransactionsResultDto> future : futures) {
            try {
                TransactionsResultDto batchResult = future.get();
                successfulCount += batchResult.getCountSuccessfullyProcessedTransactions();
                allSuccessfullyProcessedTransactions.addAll(batchResult.getSuccessfullyProcessedTransactions());
                errorCount += batchResult.getCountErroneousTransactions();
                allErroneousTransactions.addAll(batchResult.getErroneousTransactions());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        finalResult.setCountSuccessfullyProcessedTransactions(successfulCount);
        finalResult.setSuccessfullyProcessedTransactions(allSuccessfullyProcessedTransactions);
        finalResult.setCountErroneousTransactions(errorCount);
        finalResult.setErroneousTransactions(allErroneousTransactions);

        return finalResult;
    }
}


//
//    public void processTransactions(List<Transaction> transactions) {
//        for (Transaction transaction : transactions) {
//            if (transaction.getAmount().compareTo(new BigDecimal(10000))>0) {
//                logger.log("Processing large transaction: " + transaction.getId());
//            }
//            processTransaction(transaction);
//        }
//    }
//
//    private void processTransaction(Transaction transaction) {
//        try {
//            if (transaction.getStatus().getStatusName().equals("PENDING")) {
////                transaction.setStatus("PROCESSED");
//                repository.save(transaction);
//            }
//        } catch (Exception e) {
//            logger.log("Error processing transaction: " + e.getMessage());
//        }
//    }

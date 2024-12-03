
package com.github.valentina810.beauty_of_code_2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "transaction", schema = "public")
public class Transaction {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
    @Column(name = "date", nullable = false)
    private String date;
    @ManyToOne(fetch = FetchType.EAGER)//#todo подумать
    @JoinColumn(name = "id_status", nullable = false)
    private TransactionStatus status;
}

package com.github.valentina810.beauty_of_code_2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "transaction_status", schema = "public")
public class TransactionStatus {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "status_id", nullable = false, unique = true)
    private Long statusId;
    @Column(name = "status_name", nullable = false)
    private String statusName;
}
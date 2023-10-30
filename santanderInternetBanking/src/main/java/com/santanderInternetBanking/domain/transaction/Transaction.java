package com.santanderInternetBanking.domain.transaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.santanderInternetBanking.domain.user.Client;
import com.santanderInternetBanking.dto.TransactionDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name="transactions")
@Table(name="transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name="sender_id")
    private Client sender;
    private String senderAccount;
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    public Transaction(TransactionDTO transactionDTO) {
        this.amount = transactionDTO.value();
        this.senderAccount = transactionDTO.senderAccount();
        this.timestamp = transactionDTO.timestamp();
    }
}

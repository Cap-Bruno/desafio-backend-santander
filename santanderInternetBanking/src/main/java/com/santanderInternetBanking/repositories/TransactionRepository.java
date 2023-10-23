package com.santanderInternetBanking.repositories;

import com.santanderInternetBanking.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}

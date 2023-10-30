package com.santanderInternetBanking.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public record TransactionDTO(BigDecimal value, String senderAccount, LocalDateTime timestamp) {

}

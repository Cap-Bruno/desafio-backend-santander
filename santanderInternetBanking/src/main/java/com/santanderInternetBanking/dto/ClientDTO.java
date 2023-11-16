package com.santanderInternetBanking.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public record ClientDTO(@NotEmpty(message = "Campo Nome obrigatório")
                        String name,
                        boolean exclusive,
                        @NotNull(message = "O Saldo é obrigatório")
                        BigDecimal balance,
                        @NotEmpty(message = "O Numero da Conta é obrigatório")
                        String numAccount,
                        @NotNull(message = "Data de aniversario obrigatória")
                        Date birthdate) {

}

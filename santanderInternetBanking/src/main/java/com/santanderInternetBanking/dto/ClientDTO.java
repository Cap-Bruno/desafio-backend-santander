package com.santanderInternetBanking.dto;

import java.math.BigDecimal;
import java.util.Date;

public record ClientDTO(String name, boolean exclusive, BigDecimal balance, String numAccount, Date birthdate) {

}

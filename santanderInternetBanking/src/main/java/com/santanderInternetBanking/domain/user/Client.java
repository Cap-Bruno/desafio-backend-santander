package com.santanderInternetBanking.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity(name="clients")
@Table(name="clients")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean exclusive;
    private BigDecimal balance;
    private String numAccount;
    private Date birthdate;
}

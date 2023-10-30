package com.santanderInternetBanking.domain.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.santanderInternetBanking.dto.ClientDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity(name="clients")
@Table(name="clients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean exclusive;
    private BigDecimal balance;
    @Column(unique = true)
    private String numAccount;
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date birthdate;

    public Client(ClientDTO client){
        this.name = client.name();
        this.exclusive = client.exclusive();
        this.balance = client.balance();
        this.numAccount = client.numAccount();
        this.birthdate = client.birthdate();
    }
}

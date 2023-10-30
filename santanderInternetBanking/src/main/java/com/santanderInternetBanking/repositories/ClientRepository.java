package com.santanderInternetBanking.repositories;

import com.santanderInternetBanking.domain.user.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByNumAccount(String numAccount);

    @Override
    public String toString();
}

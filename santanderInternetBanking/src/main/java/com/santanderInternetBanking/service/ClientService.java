package com.santanderInternetBanking.service;

import com.santanderInternetBanking.domain.user.Client;
import com.santanderInternetBanking.dto.ClientDTO;
import com.santanderInternetBanking.dto.TransactionDTO;
import com.santanderInternetBanking.repositories.ClientRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public void validateWithdraw(@NotNull Client client, BigDecimal amount) throws Exception{
        if(client.getBalance().compareTo(amount) < 0){
            throw new Exception("Saldo insuficiente!");
        }
    }

    public void validateNegativeValue(@NotNull Client client, BigDecimal amount) throws Exception{
        Double amountDouble = amount.doubleValue();
        if(amountDouble < 0){
            throw new Exception("Valor negativo!");
        }
    }
    public void withdraw(Client client, BigDecimal amount){
        Double amountDouble = amount.doubleValue();
        Double amountDoubleClient = client.getBalance().doubleValue();

        amountDouble = calculateTax(client, amount, amountDoubleClient, amountDouble);
        if (amountDouble == null) return;
        BigDecimal amountBD = new BigDecimal(amountDouble);
        client.setBalance(amountBD);
        //Chamar a createWithdrawTransaction

    }

    private static Double calculateTax(Client client, BigDecimal amount, Double amountDoubleClient, Double amountDouble) {
        BigDecimal zero = new BigDecimal(0);
        BigDecimal oneHundred = new BigDecimal(100);
        BigDecimal threeHundred = new BigDecimal(300);

        if ((client.isExclusive()) || (zero.compareTo(amount) < 0 && oneHundred.compareTo(amount) > -1)){
            Double remainingBalance = amountDoubleClient - amountDouble;
            BigDecimal remainingBalanceBigDecimal = new BigDecimal(remainingBalance);
            client.setBalance(remainingBalanceBigDecimal);
            return null;
        }
        if (oneHundred.compareTo(amount) < 0 && threeHundred.compareTo(amount) > -1)
            amountDouble *= 0.96;
        if(threeHundred.compareTo(amount) < 0)
            amountDouble *= 0.9;
        return amountDouble;
    }

    public void deposit(Client client, BigDecimal value, TransactionDTO transactionDTO) throws Exception {
        BigDecimal balance = client.getBalance();
        Double newBalance = balance.add(value).doubleValue();

        client.setBalance(new BigDecimal(newBalance));
        //Chamar a createDepositTransaction
        //transactionService.createDepositTransaction(transactionDTO);
    }
    public Client findClientByNumAccount(String numAccount) throws Exception{
        return this.clientRepository.findByNumAccount(numAccount).orElseThrow(() -> new Exception("Cliente não encontrado!"));
    }

    public Client createClient(ClientDTO client){
        Client newClient = new Client(client);
        this.saveClient(newClient);
        return newClient;
    }

    public void saveClient(Client client){
        this.clientRepository.save(client);
    }

    public List<Client> getAllClients(){
        return this.clientRepository.findAll();
    }

    public Client updateClient(ClientDTO client, Long id){
        Client toUpdateClient = this.clientRepository.findById(id).get();

        //Only this variables can be updated
        toUpdateClient.setName(client.name());
        toUpdateClient.setExclusive(client.exclusive());
        toUpdateClient.setBirthdate(client.birthdate());

        this.saveClient(toUpdateClient);
        return toUpdateClient;
    }

    public Client deleteClientById(Long id){
        Client clientDeleted = this.clientRepository.findById(id).get();
        this.clientRepository.deleteById(id);
        return clientDeleted;
    }
}

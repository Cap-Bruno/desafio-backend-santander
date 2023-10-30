package com.santanderInternetBanking.service;

import com.santanderInternetBanking.domain.transaction.Transaction;
import com.santanderInternetBanking.domain.user.Client;
import com.santanderInternetBanking.dto.TransactionDTO;
import com.santanderInternetBanking.repositories.TransactionRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private ClientService clientService;
    @Autowired
    private TransactionRepository transactionRepository;

    //createWithdrawTransaction
    public Transaction createWithdrawTransaction(TransactionDTO transaction) throws Exception{
        Client sender = this.clientService.findClientByNumAccount(transaction.senderAccount());
        //Transaction findTransaction = this.findByTimestamp(transaction.timestamp());

        clientService.validateWithdraw(sender, transaction.value());
        clientService.validateNegativeValue(sender, transaction.value());
        BigDecimal clientSubtractValue = sender.getBalance().subtract(transaction.value());
        Transaction newTransaction = getTransactionWithdraw(transaction, sender, clientSubtractValue);

        return newTransaction;
    }
    public Transaction createDepositTransaction(TransactionDTO transaction) throws Exception{
        Client sender = this.clientService.findClientByNumAccount(transaction.senderAccount());
        clientService.validateNegativeValue(sender, transaction.value());
        BigDecimal clientAddValue = sender.getBalance().add(transaction.value());
        Transaction newTransaction = getTransactionWithdraw(transaction, sender, clientAddValue);

        return newTransaction;
    }


    @NotNull
    private Transaction getTransactionWithdraw(TransactionDTO transaction, Client sender, BigDecimal senderValue) {
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setSenderAccount(transaction.senderAccount());
        newTransaction.setTimestamp(LocalDateTime.now());
        sender.setBalance(senderValue);
        this.saveTransaction(newTransaction);
        this.clientService.saveClient(sender);
        return newTransaction;
    }

    //createDepositTransaction
    public Transaction findTransactionByTimestamp(LocalDateTime timestamp) throws Exception{
        return this.transactionRepository.findByTimestamp(timestamp).orElseThrow(() -> new Exception("Transação não encontrada!"));
    }

    public void saveTransaction(Transaction transaction){
        this.transactionRepository.save(transaction);
    }
    public List<Transaction> getAllTransaction(){
        return this.transactionRepository.findAll();
    }

    public List<Transaction> getAllTransactionByDate(Transaction transaction){
        return this.transactionRepository.findByTimestamp(transaction.getTimestamp()).stream().toList();
    }

}

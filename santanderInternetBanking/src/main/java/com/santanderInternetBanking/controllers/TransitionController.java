package com.santanderInternetBanking.controllers;

import com.santanderInternetBanking.domain.transaction.Transaction;
import com.santanderInternetBanking.dto.TransactionDTO;
import com.santanderInternetBanking.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@Tag(name = "transactions")
public class TransitionController {
    @Autowired
    private TransactionService transactionService;

    @Operation(summary = "Realiza a o saque", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaferencia criada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao criar a transferencia")
    })
    @PostMapping("/withdraw")
    private ResponseEntity<Transaction> createWithdrawTransaction(@RequestBody TransactionDTO transaction) throws Exception{
        Transaction newTransaction = this.transactionService.createWithdrawTransaction(transaction);
        return new ResponseEntity<>(newTransaction, HttpStatus.OK);
    }

    @Operation(summary = "Realiza o deposito da transferencia", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deposito realizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao depositar")
    })
    @PostMapping("/deposit")
    private ResponseEntity<Transaction> createDepositTransaction(@RequestBody TransactionDTO transaction) throws Exception{
        Transaction newTransaction = this.transactionService.createDepositTransaction(transaction);
        return new ResponseEntity<>(newTransaction, HttpStatus.OK);
    }

    @Operation(summary = "Busca todos os depositos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transações encontradas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao encontrar as transações")
    })
    @GetMapping
    private ResponseEntity<List<Transaction>> getAllTransaction(){
        List<Transaction> transactions = this.transactionService.getAllTransaction();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}

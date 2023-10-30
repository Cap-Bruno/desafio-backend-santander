package com.santanderInternetBanking.controllers;

import com.santanderInternetBanking.domain.user.Client;
import com.santanderInternetBanking.dto.ClientDTO;
import com.santanderInternetBanking.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/clients")
@Tag(name = "clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Operation(summary = "Realiza a criação do cliente", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente criado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao criar o cliente")
    })
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody ClientDTO client){
        Client newClient = clientService.createClient(client);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }

    @Operation(summary = "Busca de todos os cliente", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca feita com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar os cliente")
    })
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients(){
        List<Client> clients = this.clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }


    @Operation(summary = "Realiza a atualização do cliente", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar o cliente")
    })
    @PutMapping("/{id}")
    public Client attClient(@PathVariable("id") Long id,
                            @RequestBody ClientDTO client){
        return this.clientService.updateClient(client, id);
    }

    @Operation(summary = "Deleta o cliente", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente deletado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar o cliente")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable("id") Long id){
        Client newClient = clientService.deleteClientById(id);
        return new ResponseEntity<>(newClient, HttpStatus.OK);
    }
}

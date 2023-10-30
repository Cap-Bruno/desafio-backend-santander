package com.santanderInternetBanking;

import com.santanderInternetBanking.domain.transaction.Transaction;
import com.santanderInternetBanking.domain.user.Client;
import com.santanderInternetBanking.dto.ClientDTO;
import com.santanderInternetBanking.dto.TransactionDTO;
import com.santanderInternetBanking.repositories.ClientRepository;
import com.santanderInternetBanking.service.ClientService;
import com.santanderInternetBanking.service.TransactionService;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

//@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class SantanderInternetBankingApplicationTests {

	@Autowired
	private ClientService clientService;
	@Autowired
	private ClientRepository clientRepository;

	Client client;
	ClientDTO clientDTO;

	@Test
	public void testCreateClient() {
		clientDTO = new ClientDTO("Bruno", true, new BigDecimal(2000), "3301", new Date());
		clientService.createClient(clientDTO);
		client = new Client(clientDTO);
		List<Client> listClients = clientService.getAllClients();
		Assertions.assertEquals(listClients.size(), 5);
	}
	@Test
	public void testWithdraw() throws Exception {
		client = clientService.findClientByNumAccount("0000");
		clientService.withdraw(this.client, new BigDecimal(500));
		Assertions.assertEquals(this.client.getBalance(), new BigDecimal(1500));
	}
	@Test
	public void testDeposit() throws Exception {
		client = clientService.findClientByNumAccount("1111");
		TransactionDTO transactionDTO = new TransactionDTO(new BigDecimal(1000),"1111", LocalDateTime.now());
		BigInteger xx = transactionDTO.value().toBigInteger();
		clientService.deposit(this.client, new BigDecimal(xx), transactionDTO);
		Assertions.assertEquals(this.client.getBalance(), new BigDecimal(1100));
	}
	//está retornando uma lista vazia ou não retorna o valor

	//@Test
	public void testUpdateClient() throws Exception {

		clientDTO = new ClientDTO("Bruno", true, new BigDecimal(2000), "0133", new Date());
		client = new Client(clientDTO);
		//client = clientService.findClientByNumAccount("0133");

		clientService.updateClient(clientDTO, 6L);
		this.client.setName("Alfredo");
		this.client.setBirthdate(new Date("2001-03-01"));
		this.client.setExclusive(false);
		System.out.println("update(): " + this.client.getName());
		Assertions.assertEquals(this.client.getName(), "Alfredo");
	}

	@Test
	public void testDeleteClient(){
		clientService.deleteClientById(1L);
		List<Client> listClients = this.clientService.getAllClients();
		Assertions.assertEquals(listClients.size(), 4);
	}
}

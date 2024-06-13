package com.bankingapp.controller;

import java.util.List;
import java.util.Map;

import org.apache.coyote.http11.Http11InputBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.dto.AccountDto;
import com.bankingapp.repository.AccountRepository;
import com.bankingapp.service.AccountService;
import com.bankingapp.service.impl.AccountServiceImpl;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	private static final int ResponseEntity = 0;
//	@Autowired - use constructor injection
	private AccountService accountService;

	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<AccountDto>> findAll(){
		System.out.println("Inside Controller findAll");
		return new ResponseEntity<>(accountService.getAll(),HttpStatus.OK);
		
	}
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {

		return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {

		return new ResponseEntity<>(accountService.getAccountById(id), HttpStatus.FOUND);

	}

	@PutMapping("/deposit/{myId}")
	public ResponseEntity<AccountDto> deposit(@PathVariable Long myId, @RequestBody Map <String, Double> request) {
		return new ResponseEntity<>(accountService.modifyBalance(myId, request.get("amount")), HttpStatus.OK);

	}
	
	@PutMapping("/withdraw/{myId}")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long myId,@RequestBody Map<String,Double> request){
		return new ResponseEntity<>(accountService.withdraw(myId, request.get("amount")),HttpStatus.OK);
	}
	@DeleteMapping("/delete/{myId}")
	public ResponseEntity<?> delete(@PathVariable Long myId){
		return new ResponseEntity<>(accountService.delete(myId),HttpStatus.OK);
	}
	

}

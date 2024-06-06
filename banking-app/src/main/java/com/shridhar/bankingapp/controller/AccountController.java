package com.shridhar.bankingapp.controller;

import com.shridhar.bankingapp.dto.AccountDto;
import com.shridhar.bankingapp.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Add Account REST API
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto accountDto = accountService.getAccountById(id);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }
    @PutMapping("{Id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long Id,
                                              @RequestBody Map<String, Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(Id,amount);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }
    @PutMapping("{Id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long Id, @RequestBody Map<String, Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(Id,amount);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accountDtos= accountService.getAllAccounts();
        return  new ResponseEntity<>(accountDtos, HttpStatus.OK);
    }

    @DeleteMapping("{Id}")
    public ResponseEntity<String> delete(@PathVariable Long Id){
        accountService.deleteAccount(Id);
        return new ResponseEntity<>("Successfully deleted account.", HttpStatus.OK);
    }
}

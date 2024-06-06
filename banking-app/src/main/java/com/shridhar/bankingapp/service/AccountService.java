package com.shridhar.bankingapp.service;

import com.shridhar.bankingapp.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long Id);

    AccountDto deposit(Long Id, Double amount);

    AccountDto withdraw(Long Id, Double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(Long Id);
}

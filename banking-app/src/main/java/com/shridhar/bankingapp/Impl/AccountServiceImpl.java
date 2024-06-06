package com.shridhar.bankingapp.Impl;

import com.shridhar.bankingapp.dto.AccountDto;
import com.shridhar.bankingapp.entity.Account;
import com.shridhar.bankingapp.mapper.AccountMapper;
import com.shridhar.bankingapp.repository.AccountRepository;
import com.shridhar.bankingapp.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

//    public AccountServiceImpl() {
//    }

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.maptoAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long Id) {
        Account account = accountRepository
                .findById(Id)
                .orElseThrow(() -> new RuntimeException("Invalid Account Id."));
        return AccountMapper.maptoAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long Id, Double amount) {
        Account account = accountRepository
                .findById(Id)
                .orElseThrow(() -> new RuntimeException("Invalid Account Id."));
        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.maptoAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long Id, Double amount) {
        Account account = accountRepository
                .findById(Id)
                .orElseThrow(() -> new RuntimeException("Invalid Account Id."));
        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficient Funds.");
        }
        Double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.maptoAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(AccountMapper::maptoAccountDto)
                .collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long Id) {
        Account account = accountRepository
                .findById(Id)
                .orElseThrow(() -> new RuntimeException("Invalid Account Id."));
        accountRepository.deleteById(Id);
    }


}

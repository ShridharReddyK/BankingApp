package com.shridhar.bankingapp.dto;

public class AccountDto {
    private Long Id;
    private String accountHolderName;
    private double balance;

    public AccountDto() {
    }

    public AccountDto(Long id, String accountHolderName, double balance) {
        Id = id;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

package org.lld.machine_coding.banking_system;

import java.util.HashMap;

class AccountManager {
    static AccountManager accountManagerInstance;
    HashMap<String, Account> accounts = new HashMap<>();

    private AccountManager() {
    }

    static synchronized AccountManager getInstance() {
        if (accountManagerInstance == null) {
            accountManagerInstance = new AccountManager();
        }
        return accountManagerInstance;
    }

    public void createAccount(String accountNumber, double balance, String name, AccountType type) {
        Account account = Account.builder()
                .accountNumber(accountNumber)
                .balance(balance)
                .name(name)
                .type(type)
                .build();
        accounts.putIfAbsent(accountNumber, account);
    }

    public double deposit(String accountNumber, double amount) throws AccountNotFoundException {
        if (!accounts.containsKey(accountNumber))
            throw new AccountNotFoundException("Account with number " + accountNumber + " not found");
        Account account = accounts.get(accountNumber);

        //locked at object level
        synchronized (account) {
            account.setBalance(account.getBalance() + amount);
            return account.getBalance();
        }
    }

    public double withdraw(String accountNumber, double amount) throws AccountNotFoundException, InsufficientBalanceException {
        if (!accounts.containsKey(accountNumber))
            throw new AccountNotFoundException("Account with number " + accountNumber + " not found");

        Account account = accounts.get(accountNumber);
        //locked at object level
        synchronized (account) {
            double balance = account.getBalance();
            if (balance < amount)
                throw new InsufficientBalanceException(String.format("Insufficient balance, current balance %.2f", balance));
            account.setBalance(balance - amount);
            return account.getBalance();
        }
    }
}

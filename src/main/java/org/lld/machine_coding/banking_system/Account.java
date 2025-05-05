package org.lld.machine_coding.banking_system;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Account {
    private String accountNumber;
    private double balance;
    private String name;
    private AccountType type;
}


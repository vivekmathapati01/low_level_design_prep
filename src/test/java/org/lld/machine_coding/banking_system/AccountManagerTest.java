package org.lld.machine_coding.banking_system;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountManagerTest {

    @Test
    void getInstance() {
        AccountManager instance = AccountManager.getInstance();
        assertNotNull(instance);
    }

    @Test
    void deposit() {

    }

    @Test
    void withdraw() {
    }
}
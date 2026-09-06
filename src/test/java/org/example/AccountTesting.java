package org.example;
// import org.example.Account;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTesting {

    @Test
    void testParameterizedConstructorAmount() {
        Account account = new Account("John", 101, "1234", 500);
        assertEquals(1500, account.getAmount());
    }

    @Test
    void testDefaultConstructor() {
        Account acc = new Account();
        assertAll(
                () -> assertNull(acc.getName()),
                () -> assertEquals(0, acc.getAccountNumber()),
                () -> assertNull(acc.getPIN()),
                () -> assertEquals(0.0, acc.getAmount())
        );
    }

    @Test
    void SetAccountDetails() {
        Account account = new Account();
        account.setAccountNumber(145);
        account.setName("Chris");
        account.setAmount(400.50);
        account.setPIN("4567");
        assertEquals(145, account.getAccountNumber());
        assertEquals("Chris",account.getName());
        assertEquals("4567",account.getPIN());
        assertEquals(400.50, account.getAmount());
    }

}


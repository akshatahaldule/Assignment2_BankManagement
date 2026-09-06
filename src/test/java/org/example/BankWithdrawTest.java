package org.example;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;

public class BankWithdrawTest {

    private Bank createBank() {
        Bank bnk = new Bank();
        bnk.AL.add(new Account("David", 100, "1000", 100));
        bnk.AL.add(new Account("John", 200, "2000", 20));
        return bnk;
    }

    @Test
    void testSuccessfullWithdraw() {
        Bank bnk = createBank();
        InputStream originalIn = System.in;
        try {String input = "100\n" + "1000\n" + "30\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            bnk.withdraw();
            // 1100 - 30
            assertEquals(1070.0,bnk.AL.get(0).getAmount()
            );
        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    void testAccountNumberMatchesButPINDoesNot() {

        Bank bnk = createBank();

        InputStream originalIn = System.in;

        try {
            String input = "100\n" + "500\n";

            System.setIn(new ByteArrayInputStream(input.getBytes()));

            bnk.withdraw();

            assertEquals(1100.0,bnk.AL.get(0).getAmount()
            );

        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    void testInsufficientBalance() {

        Bank bnk = createBank();

        InputStream originalIn = System.in;

        try {
            String input = "100\n" + "1000\n" + "2000\n";

            System.setIn(
                    new ByteArrayInputStream(input.getBytes())
            );

            bnk.withdraw();

            // Amount should remain unchanged
            assertEquals(
                    1100.0,
                    bnk.AL.get(0).getAmount()
            );

        } finally {
            System.setIn(originalIn);
        }
    }
}


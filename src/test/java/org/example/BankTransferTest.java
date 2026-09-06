package org.example;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;

public class BankTransferTest {

    private Bank createBank() {

        Bank bnk = new Bank();

        bnk.AL.add(new Account("Smith", 100, "1111", 100));
        bnk.AL.add(new Account("Dennis", 200, "2222", 50));

        return bnk;
    }

    @Test
    void testSuccessfulTransfer() {
        Bank bnk = createBank();
        InputStream originalIn = System.in;
        try {
            String input = "100\n" + "1111\n" + "200\n" + "30\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            bnk.transfer();
            // Sender: 1100 - 30 = 1070
            assertEquals(1070.0, bnk.AL.get(0).getAmount());
            // Receiver: 1050 + 30 = 1080
            assertEquals(1080.0, bnk.AL.get(1).getAmount());
        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    void testSenderAccountNumberMatchesButPINDoesNot() {
        Bank bnk = createBank();
        InputStream originalIn = System.in;
        try {
            String input = "100\n" + "1000\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            bnk.transfer();
            assertEquals(1100.0, bnk.AL.get(0).getAmount());
        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    void testReceiverNotFound() {
        Bank bnk = createBank();
        InputStream originalIn = System.in;
        try {
            String input = "100\n" + "1111\n" + "999\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            bnk.transfer();
            assertEquals(1100.0, bnk.AL.get(0).getAmount());
            assertEquals(1050.0, bnk.AL.get(1).getAmount());
        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    void testInsufficientSenderBalance() {
        Bank bnk = createBank();
        InputStream originalIn = System.in;
        try {
            String input = "100\n" + "1111\n" + "200\n" + "2000\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            bnk.transfer();
            // No AMount should change
            assertEquals(1100.0, bnk.AL.get(0).getAmount());

            assertEquals(1050.0, bnk.AL.get(1).getAmount());

        } finally {
            System.setIn(originalIn);
        }
    }


}



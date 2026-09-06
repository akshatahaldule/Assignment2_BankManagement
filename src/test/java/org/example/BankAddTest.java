package org.example;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;

public class BankAddTest {

    @Test
    void testAddNewRecord() {
        Bank bnk = new Bank();
        InputStream originalIn = System.in;
        try {
            String input = "Steeve\n" + "234\n" + "7777\n" + "50\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));

            bnk.addNewRecord();

            assertEquals(1, bnk.AL.size());

            Account acc = bnk.AL.get(0);

            assertEquals("Steeve", acc.getName());
            assertEquals(234, acc.getAccountNumber());
            assertEquals("7777", acc.getPIN());

            // Constructor adds 1000
            assertEquals(1050.0, acc.getAmount());

        } finally {
            System.setIn(originalIn);
        }
    }
}




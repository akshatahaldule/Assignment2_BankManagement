package org.example;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

public class FileHandlingTest {

    private final File file = new File("BankRecord.txt");

    @AfterEach
    void cleanup() {

        if (file.isFile()) {
            file.delete();
        }

        if (file.isDirectory()) {
            file.delete();
        }
    }

    @Test
    void testSaveAndLoad() throws Exception {

        Bank bnk = new Bank();

        bnk.AL.add(
                new Account("Steeve", 100, "1000", 100)
        );

        bnk.AL.add(
                new Account("Diana", 200, "2000", 20)
        );

        bnk.save();

        assertTrue(file.exists());

        Bank loadBanks = new Bank();

        loadBanks.load();

        assertEquals(2, loadBanks.AL.size());

        assertEquals(
                "Steeve",
                loadBanks.AL.get(0).getName()
        );

        assertEquals(
                100,
                loadBanks.AL.get(0).getAccountNumber()
        );

        assertEquals(
                "1000",
                loadBanks.AL.get(0).getPIN()
        );

        assertEquals(
                1100.0,
                loadBanks.AL.get(0).getAmount()
        );

        assertEquals(
                "Diana",
                loadBanks.AL.get(1).getName()
        );
    }

    @Test
    void testLoadWithNullObject() throws Exception {

        ObjectOutputStream out =
                new ObjectOutputStream(
                        new FileOutputStream(file)
                );

        out.writeObject(
                new Account("Teressa", 300, "3000", 30)
        );

        // Explicit null allows:
        // if(temp == null)
        // to evaluate TRUE.
        out.writeObject(null);

        out.close();

        Bank bnk = new Bank();

        bnk.load();

        assertEquals(1, bnk.AL.size());

        assertEquals(
                "Teressa",
                bnk.AL.get(0).getName()
        );
    }

    @Test
    void testSaveException() throws Exception {

        if (file.exists()) {
            file.delete();
        }

        assertTrue(file.mkdir());

        Bank bnk = new Bank();

        assertDoesNotThrow(
                () -> bnk.save()
        );

        assertTrue(file.isDirectory());
    }



    @Test
    void testPrint() {

        Bank bnk = new Bank();

        bnk.AL.add(
                new Account("Mary", 400, "4000", 40)
        );

        bnk.AL.add(
                new Account("James", 600, "6000", 60)
        );

        assertDoesNotThrow(
                () -> bnk.print()
        );

        assertEquals(2, bnk.AL.size());
    }

}



package org.example;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;


@Suite
@SelectClasses({
        AccountTesting.class,
        BankAddTest.class,
        BankTransferTest.class,
        BankWithdrawTest.class,
        FileHandlingTest.class
})
public class AllBMTest {


}

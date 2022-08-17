package com.allstate.payments.unittests;

import com.allstate.payments.domain.CreditCardTransaction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DomainClassTests {


    @Test
    public void testEqualityForCreditCardTransaction() {
        //GIVEN we have 2 transactions
        CreditCardTransaction transaction1 = new CreditCardTransaction();
        CreditCardTransaction transaction2 = new CreditCardTransaction();

        //WHEN both transactions have the same ID
        transaction1.setId(123);
        transaction2.setId(123);

        //THEN the transactions should be equal
        assertEquals(transaction1, transaction2);
        //OR COULD DO
        // assertTrue(transaction1.equals(transaction2));
    }

}
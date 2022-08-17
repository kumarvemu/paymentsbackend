package com.allstate.payments.unittests;

import com.allstate.payments.data.CreditCardTransactionRepository;
import com.allstate.payments.data.UserRepository;
import com.allstate.payments.domain.CreditCardTransaction;
import com.allstate.payments.service.PaymentsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
public class ServiceTests {

    /*
    Create a test to make sure that the countTransactions method of the service class
    is counting the right number.
    - mock the repository and return for whichever method is called some valid data,
    so that you can check the number is correct
     */

    @Autowired
    PaymentsService paymentsService;

    @MockBean
    CreditCardTransactionRepository creditCardTransactionRepository;

    @MockBean
    UserRepository userRepository;

    @Test
    public void testNumberOfTransactions() {
        List<CreditCardTransaction> transactions = new ArrayList<>();
        transactions.add(new CreditCardTransaction(1,10.2,"USA","USD", LocalDate.now(), "123",1,0.2,"VISA"));
        transactions.add(new CreditCardTransaction(2,10.2,"USA","USD", LocalDate.now(), "123",1,0.2,"VISA"));
        transactions.add(new CreditCardTransaction(3,10.2,"USA","USD", LocalDate.now(), "123",1,0.2,"VISA"));

        Mockito.when(creditCardTransactionRepository.findAll()).thenReturn(transactions);

        int result = paymentsService.countTransactions();
        assertEquals(3, result);
    }
}

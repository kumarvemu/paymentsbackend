package com.allstate.payments.unittests;

import com.allstate.payments.control.PaymentsController;
import com.allstate.payments.data.UserRepository;
import com.allstate.payments.domain.CreditCardTransaction;
import com.allstate.payments.service.PaymentsService;
import org.junit.jupiter.api.BeforeEach;
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

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
public class ControllerTests {

    @Autowired
    PaymentsController paymentsController;

    @MockBean
    PaymentsService paymentsService;

    @MockBean
    UserRepository userRepository;

    @BeforeEach
    public void runFirst() {
        Mockito.when(paymentsService.countTransactions())
                .thenReturn(116);
        Mockito.when(paymentsService.getTransactionById(1))
                .thenReturn(new CreditCardTransaction());
    }

    @Test
    public void checkThatNumberOfTransactionsIsAMapWithAKeyOfVolume() {
        Map<String,String> result = paymentsController.getNumberOfPayments();
        assertEquals("116", result.get("volume"));
    }

    @Test
    public void checkThatNumberOfTransactionsIsAMapWithAKeyOfVolume2() {
        Map<String,String> result = paymentsController.getNumberOfPayments();
        assertEquals("116", result.get("volume"));
    }

}

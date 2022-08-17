package com.allstate.payments.integrationtests;

import com.allstate.payments.data.CreditCardTransactionRepository;
import com.allstate.payments.data.UserRepository;
import com.allstate.payments.domain.CreditCardTransaction;
import com.allstate.payments.dtos.CreditCardTransactionDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JsonContentAssert;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
@AutoConfigureMockMvc
public class PaymentsTesting {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private CreditCardTransactionRepository creditCardTransactionRepository;

    @Autowired
    Jackson2ObjectMapperBuilder mapperBuilder;

    @MockBean
    UserRepository userRepository;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    @WithMockUser(username="testuser", password="testpassword", roles={"MANAGER"})
    public void checkNewTransactionsAreAddedToDatabase() throws Exception {
        CreditCardTransactionDTO newTransaction = new CreditCardTransactionDTO();
        newTransaction.setAmount(123.45);
        newTransaction.setCountry("AUS");
        newTransaction.setCurrency("EUR");
        newTransaction.setType("Mastercard");
        newTransaction.setOrderId("224466");

        ObjectMapper objectMapper = mapperBuilder.build();
        String json = objectMapper.writeValueAsString(newTransaction);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/payment")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        CreditCardTransaction databaseResponse = newTransaction.toCreditCardTransaction();
        databaseResponse.setId(1);

        Mockito.when(creditCardTransactionRepository.save(any())).thenReturn(databaseResponse);

        ResultActions result = mockMvc.perform(request)
                .andExpect(status().isOk());

        String responseJson = result.andReturn().getResponse().getContentAsString();
        String expectedJson = objectMapper.writeValueAsString(databaseResponse);
        System.out.println(responseJson);
        System.out.println(expectedJson);
        JsonContentAssert jsonAssert = new JsonContentAssert(CreditCardTransaction.class, expectedJson);
        jsonAssert.isEqualToJson(responseJson);

//        Mockito.verify(creditCardTransactionRepository)
//                .save(newTransaction.toCreditCardTransaction());

        ArgumentCaptor<CreditCardTransaction> capturedTransaction
                = ArgumentCaptor.forClass(CreditCardTransaction.class);

        Mockito.verify(creditCardTransactionRepository)
                .save(capturedTransaction.capture());

        CreditCardTransaction cct = capturedTransaction.getValue();
        assertEquals("EUR", cct.getCurrency());

    }


}
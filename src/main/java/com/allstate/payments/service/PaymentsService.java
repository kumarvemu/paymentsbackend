
package com.allstate.payments.service;

import com.allstate.payments.domain.CreditCardTransaction;
import com.allstate.payments.dtos.CreditCardTransactionDTO;

import java.util.List;
import java.util.Map;

public interface PaymentsService {

    List<CreditCardTransaction> getAllTransactions();
    int countTransactions();
    List<CreditCardTransaction> getAllTransactionsForCountry(String country);
    List<CreditCardTransaction> getAllTransactionsForOrderId(String orderId);
    CreditCardTransaction getTransactionById(Integer id);
    CreditCardTransaction add(CreditCardTransactionDTO transaction);
    CreditCardTransaction updateTransaction(Integer id, Map<String,String> data);

}
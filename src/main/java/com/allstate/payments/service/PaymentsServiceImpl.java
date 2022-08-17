package com.allstate.payments.service;

import com.allstate.payments.data.CreditCardTransactionRepository;
import com.allstate.payments.domain.CreditCardTransaction;
import com.allstate.payments.dtos.CreditCardTransactionDTO;
import com.allstate.payments.exceptions.InvalidNewTransactionException;
import com.allstate.payments.exceptions.TransactionNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentsServiceImpl implements PaymentsService{

    @Autowired
    private CreditCardTransactionRepository creditCardTransactionRepository;

    Logger logger = LoggerFactory.getLogger(PaymentsService.class);


    @Override
    public List<CreditCardTransaction> getAllTransactions() {
        return creditCardTransactionRepository.findAll();
    }

    @Override
    public int countTransactions() {
        return creditCardTransactionRepository.findAll().size();
    }

    @Override
    public List<CreditCardTransaction> getAllTransactionsForCountry(String country) {

//        return creditCardTransactionRepository.findAll()
//                .stream().filter( trans -> trans.getCountry().equals(country))
//                .collect(Collectors.toList());

        return creditCardTransactionRepository.findAllByCountry(country);
    }

    @Override
    public List<CreditCardTransaction> getAllTransactionsForOrderId(String orderId) {
        return creditCardTransactionRepository.findAllByOrderId(orderId);
    }

    @Override
    public CreditCardTransaction getTransactionById(Integer id) {
        Optional<CreditCardTransaction> optionalCCT =  creditCardTransactionRepository.findById(id);
        if (optionalCCT.isPresent()) {
            return optionalCCT.get();
        }
        logger.info("There is no transaction with an ID of " + id);
        throw new TransactionNotFoundException("There is no transaction with an ID of " + id);
    }


    @Override
    public CreditCardTransaction add(CreditCardTransactionDTO transactionDTO) {
        CreditCardTransaction transaction = transactionDTO.toCreditCardTransaction();

        if(transaction.getOrderId() == null || transaction.getTaxCode() ==null) {
            throw new InvalidNewTransactionException("Order Id and Tax Code must be provided");
        }
        try {
            return creditCardTransactionRepository.save(transaction);
        }
        catch (Exception e) {
            throw new InvalidNewTransactionException("We were unable to save your transaction");
        }
    }

    @Override
    public CreditCardTransaction updateTransaction(Integer id, Map<String, String> data) {
        CreditCardTransaction transaction = getTransactionById(id);
        if (data.containsKey("amount")) transaction.setAmount(Double.parseDouble(data.get("amount")));
        if (data.containsKey("country")) transaction.setCountry(data.get("country"));
        if (data.containsKey("currency")) transaction.setCurrency(data.get("currency"));
        if (data.containsKey("date")) transaction.setDate(LocalDate.parse(data.get("date")));
        if (data.containsKey("orderId")) transaction.setOrderId(data.get("orderId"));
        if (data.containsKey("taxCode")) transaction.setTaxCode(Integer.parseInt(data.get("taxCode")));
        if (data.containsKey("taxRate")) transaction.setTaxRate(Double.parseDouble(data.get("taxRate")));
        if (data.containsKey("type")) transaction.setType(data.get("type"));
        return creditCardTransactionRepository.save(transaction);
    }
}
package com.allstate.payments.dtos;

import com.allstate.payments.domain.CreditCardTransaction;

import java.time.LocalDate;

public class CreditCardTransactionDTO {

    private Double amount;
    private String country;
    private String currency;
    private String orderId;
    private String type;

    public CreditCardTransactionDTO() {
    }

    public CreditCardTransactionDTO(CreditCardTransaction cct) {
        this.amount = cct.getAmount();
        this.country = cct.getCountry();
        this.currency = cct.getCurrency();
        this.orderId = cct.getOrderId();
        this.type = cct.getType();
    }

    public CreditCardTransaction toCreditCardTransaction() {

        CreditCardTransaction cct = new CreditCardTransaction(null,amount,country,currency,
                LocalDate.now(), orderId, 30, 0.15, type);
        if (country.toLowerCase().equals("uk")) {
            cct.setTaxRate(0.2);
            cct.setTaxCode(1);
        }
        if (country.toLowerCase().equals("usa")) {
            cct.setTaxRate(0.0);
            cct.setTaxCode(0);
        }
        if (country.toLowerCase().equals("irl")) {
            cct.setTaxRate(0.23);
            cct.setTaxCode(52);
        }
        return cct;
    }


    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
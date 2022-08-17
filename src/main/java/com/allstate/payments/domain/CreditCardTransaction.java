package com.allstate.payments.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="cctransactions")
public class CreditCardTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double amount;
    private String country;
    private String currency;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd")
    private LocalDate date;

    @Column(name="order_id")
    private String orderId;

    @Column(name="tax_code")
    private Integer taxCode;

    @Column(name="tax_rate")
    private Double taxRate;
    private String type;

    public CreditCardTransaction() {
    }

    public CreditCardTransaction(Integer id, Double amount, String country, String currency, LocalDate date, String orderId, Integer taxCode, Double taxRate, String type) {
        this.id = id;
        this.amount = amount;
        this.country = country;
        this.currency = currency;
        this.date = date;
        this.orderId = orderId;
        this.taxCode = taxCode;
        this.taxRate = taxRate;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(Integer taxCode) {
        this.taxCode = taxCode;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "CreditCardTransaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", country='" + country + '\'' +
                ", currency='" + currency + '\'' +
                ", date=" + date +
                ", orderId='" + orderId + '\'' +
                ", taxCode=" + taxCode +
                ", taxRate=" + taxRate +
                ", type='" + type + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCardTransaction that = (CreditCardTransaction) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
package com.sallu.currencyexchangeservice;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "currency_rate")
public class CurrencyRate {
    @Id
    private Long id;
    private String currencyName;
    private BigDecimal currencyValue;

    public CurrencyRate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public BigDecimal getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(BigDecimal currencyValue) {
        this.currencyValue = currencyValue;
    }
}

package com.sallu.currencyexchangeservice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;

public class ExchangeResponse {
    private String from;
    private String to;
    private BigDecimal conversionMultiple;
    private String instancePort;

    public String getInstancePort() {
        return instancePort;
    }

    public void setInstancePort(String instancePort) {
        this.instancePort = instancePort;
    }

    public ExchangeResponse() {
    }

    public ExchangeResponse(String from, String to, BigDecimal conversionMultiple) {
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getConversionMultiple() {
        return conversionMultiple;
    }

    public void setConversionMultiple(BigDecimal conversionMultiple) {
        this.conversionMultiple = conversionMultiple;
    }
}

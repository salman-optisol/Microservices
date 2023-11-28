package com.sallu.currencyconversionservice;

import java.math.BigDecimal;

public class ConversionResponse {
    private String from;
    private String to;
    private BigDecimal conversionMultiple;
    private int quantity;
    private BigDecimal convertedValue;
    private String instancePort;

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getConvertedValue() {
        return convertedValue;
    }

    public void setConvertedValue(BigDecimal convertedValue) {
        this.convertedValue = convertedValue;
    }

    public String getInstancePort() {
        return instancePort;
    }

    public void setInstancePort(String instancePort) {
        this.instancePort = instancePort;
    }
}

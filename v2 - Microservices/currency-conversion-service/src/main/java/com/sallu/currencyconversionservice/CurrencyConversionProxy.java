package com.sallu.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange", url = "localhost:8000")
@FeignClient(name = "currency-exchange")
public interface CurrencyConversionProxy {
    @GetMapping("currency-exchange/from/{from}/to/{to}")
    String currencyConversion(
            @PathVariable("from") String from,
            @PathVariable("to") String to
    );
}

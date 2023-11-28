package com.sallu.currencyconversionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/exchange-converter")
public class ConversionController {

    @Autowired
    Environment environment;

    @Autowired
    CurrencyExchangeProxy proxy;

    @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
    public ConversionResponse retrieveConvertedValue(@PathVariable String from, @PathVariable String to, @PathVariable int quantity) {
        Map<String, String> uriMap = new HashMap<>();
        uriMap.put("from", from);
        uriMap.put("to", to);

        String url = "http://localhost:8000/currency-exchange/from/{from}/to/{to}";
        ResponseEntity<ConversionResponse> apiResponse = new RestTemplate().getForEntity(url, ConversionResponse.class, uriMap);

        ConversionResponse response = new ConversionResponse();
        response.setFrom(from);
        response.setTo(to);
        response.setConversionMultiple(apiResponse.getBody().getConversionMultiple());
        response.setQuantity(quantity);
        response.setConvertedValue(response.getConversionMultiple().multiply(BigDecimal.valueOf(quantity)));
        response.setInstancePort(environment.getProperty("local.server.port"));

        return response;
    }

    @GetMapping("feign/from/{from}/to/{to}/quantity/{quantity}")
    public ConversionResponse retrieveConvertedValueFeign(@PathVariable String from, @PathVariable String to, @PathVariable int quantity) {

        ConversionResponse response = proxy.retrieveCurrencyExchange(from, to);
        response.setQuantity(quantity);
        response.setConvertedValue(response.getConversionMultiple().multiply(BigDecimal.valueOf(quantity)));
//        response.setInstancePort(environment.getProperty("local.server.port"));

        return response;
    }
}

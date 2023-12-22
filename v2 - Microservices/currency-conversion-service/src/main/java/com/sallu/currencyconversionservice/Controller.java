package com.sallu.currencyconversionservice;

import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("currency-conversion")
public class Controller {

    @Autowired
    CurrencyConversionProxy proxy;

    private Logger logger = LoggerFactory.getLogger(Controller.class);

    @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
    public String currencyConversion(
            @PathVariable("from") String from,
            @PathVariable("to") String to,
            @PathVariable("quantity") int quantity
    ) {
        StringBuilder builder = new StringBuilder();

        Map<String, String> map = new HashMap<>();
        map.put("from", from);
        map.put("to", to);

        ResponseEntity<String> response = new RestTemplate().getForEntity("http://currency-exchange:8000/currency-exchange/from/{from}/to/{to}", String.class, map);

        builder.append(response.getBody());
        builder.append("\n");
        builder.append("Quantity : " + quantity);

        return builder.toString();
    }

    @GetMapping("feign/from/{from}/to/{to}/quantity/{quantity}")
    public String currencyConversionFeign(
            @PathVariable("from") String from,
            @PathVariable("to") String to,
            @PathVariable("quantity") int quantity
    ) {
        StringBuilder builder = new StringBuilder();

        builder.append(proxy.currencyConversion(from, to));
        builder.append("\n");
        builder.append("Quantity : " + quantity);

        return builder.toString();
    }


    @GetMapping("circut-breaker-demo")
    @Retry(name = "circut-breaker-demo", fallbackMethod = "defaultResponseMethod")
    public String circutBreakerDemo() {
        logger.info("INFORMATION LOG : TRYING TO FETCH....");
        new RestTemplate().getForEntity("http://www.dummy-sallu-url.com", String.class);
        return "This is the end of method";
    }

    public String defaultResponseMethod(Exception ex) {
        return "DEFAULT MESSAGE : Sorry the requested resource not available at the moment";
    }
}

package com.sallu.currencyexchangeservice;

import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;
    @Autowired
    private CurrencyRateRepository repo;

    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
    @GetMapping("from/{from}/to/{to}")
    public ExchangeResponse retrieveExchangeData(@PathVariable String from, @PathVariable String to) {
        ExchangeResponse response = new ExchangeResponse();
        response.setFrom(from);
        response.setTo(to);
        response.setInstancePort(environment.getProperty("local.server.port"));
        response.setConversionMultiple(repo.findByCurrencyName(from).getCurrencyValue().divide(repo.findByCurrencyName(to).getCurrencyValue(), 2, RoundingMode.HALF_UP));
        return response;
    }

    // we can have circut breaker - which breaks the circut and return default response even without hitting actual one bcoz already actual one is failing a lot
    // we can have ratelimiter - allowing only n no. of api calls per minute configuration
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

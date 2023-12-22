package com.sallu.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("currency-exchange")
public class Controller {

    @Autowired
    private Environment environment;
    private Map<String, Integer> map;

    {
        map = new HashMap<>();
        map.put("INR", 1);
        map.put("USD", 70);
        map.put("SR", 20);
        map.put("EURO", 80);
    }

    @GetMapping("/from/{from}/to/{to}")
    public String currencyExchange(@PathVariable("from") String from, @PathVariable("to") String to) {
        StringBuilder builder = new StringBuilder();
        builder.append("FROM : " + from);
        builder.append("\n");
        builder.append("TO   : " + to);
        builder.append("\n");
        builder.append("Exchange Value : " + ((double)map.get(from) / map.get(to)));
        builder.append("\n");
        builder.append("Port Number : " + environment.getProperty("local.server.port"));

        return builder.toString();
    }
}

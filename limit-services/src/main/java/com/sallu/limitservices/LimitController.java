package com.sallu.limitservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/limits")
public class LimitController {

    LimitsConfiguration configuration;

    public LimitController(LimitsConfiguration configuration) {
        this.configuration = configuration;
    }

    @GetMapping
    public String getLimits() {
        return "minimum : " + configuration.getMinimum() + " maximum : " + configuration.getMaximum();
    }
}

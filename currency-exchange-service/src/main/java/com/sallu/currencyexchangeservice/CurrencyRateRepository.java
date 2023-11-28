package com.sallu.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long> {
    CurrencyRate findByCurrencyName(String name);
}

package com.mathew.bank.Mathewbank.config;

import com.mathew.bank.Mathewbank.cachCountryState.CountryCache;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class CacheConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public CountryCache countryCacheSingletonBean() {
        return new CountryCache();
    }

}

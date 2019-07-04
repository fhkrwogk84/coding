package com.test.coding.core.config;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import java.util.concurrent.TimeUnit;


@Configuration
@EnableCaching
public class EhCacheConfig {

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cacheManager -> cacheManager.createCache("hotSearchKeywordList", initConfiguration(new Duration(TimeUnit.SECONDS, 20)));
    }

    private MutableConfiguration<Object, Object> initConfiguration(Duration duration) {
        return new MutableConfiguration<>()
                .setStatisticsEnabled(true)
                .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(duration));
    }
}
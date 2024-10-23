package ru.stepup.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(ExecutorProperties.class)
public class IntegrationConfig {

    @Bean("product-client")
    public RestTemplate paymentRestTemplate(ExecutorProperties executorProperties, RestTemplateResponseErrorHandler restTemplateResponseErrorHandler) {
        RestTemplateProperties restTemplateProperties = executorProperties.productClient();
        return new RestTemplateBuilder()
                .rootUri(restTemplateProperties.url())
                .setConnectTimeout(restTemplateProperties.connectTimeout())
                .setReadTimeout(restTemplateProperties.readTimeout())
                .errorHandler(restTemplateResponseErrorHandler)
                .build();
    }
}
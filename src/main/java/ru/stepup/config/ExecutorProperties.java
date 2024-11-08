package ru.stepup.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@Configuration
@ConfigurationProperties(prefix = "executor")
public class ExecutorProperties {
    private BigDecimal defaultLimit;
    private String resetCron;

    public BigDecimal getDefaultLimit() {
        return defaultLimit;
    }

    public void setDefaultLimit(BigDecimal defaultLimit) {
        this.defaultLimit = defaultLimit;
    }

    public String getResetCron() {
        return resetCron;
    }

    public void setResetCron(String resetCron) {
        this.resetCron = resetCron;
    }
}
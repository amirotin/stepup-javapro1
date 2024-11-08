package ru.stepup.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import ru.stepup.repository.LimitRepository;
import org.springframework.stereotype.Service;

import ru.stepup.dto.Limit;
import ru.stepup.config.ExecutorProperties;

import java.math.BigDecimal;

@Service
public class LimitService {
    private final LimitRepository limitRepository;
    private final ExecutorProperties executorProperties;

    public LimitService(LimitRepository limitRepository, ExecutorProperties executorProperties) {
        this.limitRepository = limitRepository;
        this.executorProperties = executorProperties;
    }

    public Limit getLimitByUserId(Long userId) {
        return limitRepository.findByUserId(userId).orElseGet(() -> {
            Limit limit = new Limit(userId, executorProperties.getDefaultLimit());
            return limitRepository.save(limit);
        });
    }

    public Limit reduceLimitByUserId(Long userId, BigDecimal value) {
        Limit limit = getLimitByUserId(userId);
        if (limit.getAvailableLimit().compareTo(value) < 0) {
            throw new IllegalArgumentException("Not enough limit");
        }
        limit.setAmount(limit.getAmount().add(value));
        return limitRepository.save(limit);
    }

    public Limit restoreLimitByUserId(Long userId, BigDecimal value) {
        Limit limit = getLimitByUserId(userId);
        if (limit.getLimit().compareTo(limit.getAmount().add(value)) < 0) {
            throw new IllegalArgumentException("Can't restore more than limit");
        }
        limit.setAmount(limit.getAmount().add(value));
        return limitRepository.save(limit);
    }

    public Limit setLimitByUserId(Long userId, BigDecimal value) {
        Limit limit = getLimitByUserId(userId);
        limit.setLimit(value);
        return limitRepository.save(limit);
    }


    @Scheduled(cron = "#{executorProperties.resetCron}")
    @Transactional
    public void restoreLimits() {
        limitRepository.resetAllLimits();
    }
}

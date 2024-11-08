package ru.stepup.controller;

import org.springframework.web.bind.annotation.GetMapping;
import ru.stepup.dto.Limit;
import ru.stepup.service.LimitService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/limits")
public class LimitController {
    private final LimitService limitService;

    public LimitController(LimitService limitService) {
        this.limitService = limitService;
    }

    @GetMapping("/limit/{userId}")
    public Limit getLimit(@PathVariable Long userId) {
        return limitService.getLimitByUserId(userId);
    }

    @PostMapping("/limit/{userId}/reduce")
    public Limit reduceLimit(@PathVariable Long userId, @RequestParam("value") String value) {
        return limitService.reduceLimitByUserId(userId, new BigDecimal(value));
    }

    @PostMapping("/limit/{userId}/restore")
    public Limit restoreLimit(@PathVariable Long userId, @RequestParam("value") String value) {
        return limitService.restoreLimitByUserId(userId, new BigDecimal(value));
    }

    @PostMapping("/limit/{userId}/set")
    public Limit setLimit(@PathVariable Long userId, @RequestParam("value") String value) {
        return limitService.setLimitByUserId(userId, new BigDecimal(value));
    }
}

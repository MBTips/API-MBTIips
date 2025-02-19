package com.mbtips.common.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/health-check")
@Tag(name = "L7 health-check", description = "L7 health-check API")
public class HealthCheckController {

    @GetMapping
    @Operation(summary = "L7 Health Check", description = "L7 Health Check API")
    public String healthCheck() {
        return "ok";
    }
}

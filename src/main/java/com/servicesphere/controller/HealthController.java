package com.servicesphere.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/api/health")
    public Map<String, String> health() {
        return Map.of(
                "application", "ServiceSphere",
                "status", "UP",
                "message", "ServiceSphere backend is running successfully"
        );
    }
}
package com.servicesphere.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicesphere.dto.ServiceProviderResponse;
import com.servicesphere.mapper.ServiceProviderMapper;
import com.servicesphere.service.ServiceProviderService;

@RestController
@RequestMapping("/api/providers")
public class ServiceProviderController {

    private final ServiceProviderService providerService;
    private final ServiceProviderMapper providerMapper;

    public ServiceProviderController(
            ServiceProviderService providerService,
            ServiceProviderMapper providerMapper) {
        this.providerService = providerService;
        this.providerMapper = providerMapper;
    }

    @GetMapping
    public ResponseEntity<List<ServiceProviderResponse>> getVerifiedProviders() {

        List<ServiceProviderResponse> providers = providerService
                .getVerifiedProviders()
                .stream()
                .map(providerMapper::toResponse)
                .toList();

        return ResponseEntity.ok(providers);
    }

    @GetMapping("/available")
    public ResponseEntity<List<ServiceProviderResponse>> getAvailableProviders() {

        List<ServiceProviderResponse> providers = providerService
                .getAvailableProviders()
                .stream()
                .map(providerMapper::toResponse)
                .toList();

        return ResponseEntity.ok(providers);
    }
}


package com.servicesphere.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.servicesphere.dto.ServiceProviderRequest;
import com.servicesphere.dto.ServiceProviderResponse;
import com.servicesphere.entity.ServiceProvider;
import com.servicesphere.entity.User;
import com.servicesphere.mapper.ServiceProviderMapper;
import com.servicesphere.repository.UserRepository;
import com.servicesphere.service.ServiceProviderService;

@RestController
@RequestMapping("/api/providers")
public class ServiceProviderController {

    private final ServiceProviderService providerService;
    private final ServiceProviderMapper providerMapper;
    private final UserRepository userRepository;

    public ServiceProviderController(
            ServiceProviderService providerService,
            ServiceProviderMapper providerMapper,
            UserRepository userRepository) {

        this.providerService = providerService;
        this.providerMapper = providerMapper;
        this.userRepository = userRepository;
    }

    // Get all verified service providers
    @GetMapping
    public ResponseEntity<List<ServiceProviderResponse>> getVerifiedProviders() {

        List<ServiceProviderResponse> providers = providerService
                .getVerifiedProviders()
                .stream()
                .map(providerMapper::toResponse)
                .toList();

        return ResponseEntity.ok(providers);
    }

    // Get all available service providers
    @GetMapping("/available")
    public ResponseEntity<List<ServiceProviderResponse>> getAvailableProviders() {

        List<ServiceProviderResponse> providers = providerService
                .getAvailableProviders()
                .stream()
                .map(providerMapper::toResponse)
                .toList();

        return ResponseEntity.ok(providers);
    }

    // Get the logged-in provider's own profile
    @GetMapping("/me")
    public ResponseEntity<ServiceProviderResponse> getMyProviderProfile() {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found."));

        if (!"PROVIDER".equals(user.getRole())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Only service providers can access provider profiles.");
        }

        ServiceProvider provider =
                providerService.getProviderByUserId(user.getId());

        ServiceProviderResponse response =
                providerMapper.toResponse(provider);

        return ResponseEntity.ok(response);
    }

    // Get a service provider by ID
    @GetMapping("/{id}")
    public ResponseEntity<ServiceProviderResponse> getProviderById(
            @PathVariable Long id) {

        ServiceProvider provider = providerService.getProviderById(id);

        ServiceProviderResponse response =
                providerMapper.toResponse(provider);

        return ResponseEntity.ok(response);
    }

    // Create a provider profile
    @PostMapping("/profile")
    public ResponseEntity<ServiceProviderResponse> createProviderProfile(
            @RequestBody ServiceProviderRequest request) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found."));

        if (!"PROVIDER".equals(user.getRole())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Only service providers can create provider profiles.");
        }

        ServiceProvider provider =
                providerService.createProviderProfile(user, request);

        ServiceProviderResponse response =
                providerMapper.toResponse(provider);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}

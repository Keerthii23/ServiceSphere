
package com.servicesphere.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicesphere.entity.ServiceProvider;
import com.servicesphere.repository.ServiceProviderRepository;

@Service
@Transactional(readOnly = true)
public class ServiceProviderService {

    private final ServiceProviderRepository providerRepository;

    public ServiceProviderService(
            ServiceProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    public List<ServiceProvider> getAllProviders() {
        return providerRepository.findAll();
    }

    public ServiceProvider getProviderById(Long id) {
        return providerRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Service provider not found with ID: " + id));
    }

    public ServiceProvider getProviderByUserId(Long userId) {
        return providerRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Service provider not found for user ID: " + userId));
    }

    public List<ServiceProvider> getVerifiedProviders() {
        return providerRepository.findByVerificationStatus("VERIFIED");
    }

    public List<ServiceProvider> getAvailableProviders() {
        return providerRepository
                .findByAvailableTrueAndVerificationStatus("VERIFIED");
    }

    @Transactional
    public ServiceProvider saveProvider(ServiceProvider provider) {
        return providerRepository.save(provider);
    }

    public boolean providerExistsForUser(Long userId) {
        return providerRepository.existsByUserId(userId);
    }
}

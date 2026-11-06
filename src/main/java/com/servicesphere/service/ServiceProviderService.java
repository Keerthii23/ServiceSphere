
package com.servicesphere.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicesphere.dto.ServiceProviderRequest;
import com.servicesphere.entity.ServiceCategory;
import com.servicesphere.entity.ServiceProvider;
import com.servicesphere.entity.User;
import com.servicesphere.repository.ServiceCategoryRepository;
import com.servicesphere.repository.ServiceProviderRepository;

@Service
@Transactional(readOnly = true)
public class ServiceProviderService {

    private final ServiceProviderRepository providerRepository;
    private final ServiceCategoryRepository categoryRepository;

    public ServiceProviderService(
            ServiceProviderRepository providerRepository,
            ServiceCategoryRepository categoryRepository) {
        this.providerRepository = providerRepository;
        this.categoryRepository = categoryRepository;
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
    public ServiceProvider createProviderProfile(
            User user,
            ServiceProviderRequest request) {

        if (providerRepository.existsByUserId(user.getId())) {
            throw new IllegalStateException(
                    "A provider profile already exists for this user.");
        }

        if (request.getExperienceYears() == null
                || request.getExperienceYears() < 0) {
            throw new IllegalArgumentException(
                    "Experience years must be zero or greater.");
        }

        if (request.getCategoryIds() == null
                || request.getCategoryIds().isEmpty()) {
            throw new IllegalArgumentException(
                    "At least one service category must be selected.");
        }

        Set<ServiceCategory> categories = new HashSet<>();

        for (Long categoryId : request.getCategoryIds()) {
            ServiceCategory category = categoryRepository.findById(categoryId)
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "Service category not found: " + categoryId));

            categories.add(category);
        }

        ServiceProvider provider = new ServiceProvider();

        provider.setUser(user);
        provider.setBio(request.getBio());
        provider.setExperienceYears(request.getExperienceYears());
        provider.setServiceArea(request.getServiceArea());
        provider.setHourlyRate(request.getHourlyRate());
        provider.setCategories(categories);

        provider.setAvailable(false);
        provider.setVerificationStatus("PENDING");

        return providerRepository.save(provider);
    }

    @Transactional
    public ServiceProvider saveProvider(ServiceProvider provider) {
        return providerRepository.save(provider);
    }

    public boolean providerExistsForUser(Long userId) {
        return providerRepository.existsByUserId(userId);
    }
}

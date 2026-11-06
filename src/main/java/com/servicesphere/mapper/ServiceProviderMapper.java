package com.servicesphere.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.servicesphere.dto.ServiceProviderResponse;
import com.servicesphere.entity.ServiceProvider;

@Component
public class ServiceProviderMapper {

    public ServiceProviderResponse toResponse(ServiceProvider provider) {

        return new ServiceProviderResponse(
                provider.getId(),
                provider.getUser().getId(),
                provider.getUser().getFullName(),
                provider.getBio(),
                provider.getExperienceYears(),
                provider.getServiceArea(),
                provider.getHourlyRate(),
                provider.getAvailable(),
                provider.getVerificationStatus(),
                provider.getCategories()
                        .stream()
                        .map(category -> category.getName())
                        .collect(Collectors.toSet()),
                provider.getCreatedAt()
        );
    }
}
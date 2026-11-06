package com.servicesphere.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class ServiceProviderResponse {

    private Long id;
    private Long userId;
    private String fullName;
    private String bio;
    private Integer experienceYears;
    private String serviceArea;
    private BigDecimal hourlyRate;
    private boolean available;
    private String verificationStatus;
    private Set<String> categories;
    private LocalDateTime createdAt;

    public ServiceProviderResponse() {
    }

    public ServiceProviderResponse(
            Long id,
            Long userId,
            String fullName,
            String bio,
            Integer experienceYears,
            String serviceArea,
            BigDecimal hourlyRate,
            boolean available,
            String verificationStatus,
            Set<String> categories,
            LocalDateTime createdAt) {

        this.id = id;
        this.userId = userId;
        this.fullName = fullName;
        this.bio = bio;
        this.experienceYears = experienceYears;
        this.serviceArea = serviceArea;
        this.hourlyRate = hourlyRate;
        this.available = available;
        this.verificationStatus = verificationStatus;
        this.categories = categories;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getBio() {
        return bio;
    }

    public Integer getExperienceYears() {
        return experienceYears;
    }

    public String getServiceArea() {
        return serviceArea;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
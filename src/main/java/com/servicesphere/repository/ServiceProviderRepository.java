
package com.servicesphere.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicesphere.entity.ServiceProvider;

public interface ServiceProviderRepository
        extends JpaRepository<ServiceProvider, Long> {

    Optional<ServiceProvider> findByUserId(Long userId);

    List<ServiceProvider> findByVerificationStatus(String verificationStatus);

    List<ServiceProvider> findByAvailableTrueAndVerificationStatus(
            String verificationStatus);

    boolean existsByUserId(Long userId);
}

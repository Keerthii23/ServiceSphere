
package com.servicesphere.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicesphere.entity.ServiceCategory;

public interface ServiceCategoryRepository
        extends JpaRepository<ServiceCategory, Long> {

    List<ServiceCategory> findByActiveTrue();

    Optional<ServiceCategory> findByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCase(String name);
}

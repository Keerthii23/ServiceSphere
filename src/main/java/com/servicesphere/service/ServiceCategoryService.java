
package com.servicesphere.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicesphere.entity.ServiceCategory;
import com.servicesphere.repository.ServiceCategoryRepository;

@Service
@Transactional(readOnly = true)
public class ServiceCategoryService {

    private final ServiceCategoryRepository categoryRepository;

    public ServiceCategoryService(
            ServiceCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<ServiceCategory> getActiveCategories() {
        return categoryRepository.findByActiveTrue();
    }
}

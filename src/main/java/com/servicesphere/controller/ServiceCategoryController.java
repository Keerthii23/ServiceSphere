
package com.servicesphere.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicesphere.entity.ServiceCategory;
import com.servicesphere.service.ServiceCategoryService;

@RestController
@RequestMapping("/api/categories")
public class ServiceCategoryController {

    private final ServiceCategoryService categoryService;

    public ServiceCategoryController(
            ServiceCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<ServiceCategory>> getActiveCategories() {
        return ResponseEntity.ok(categoryService.getActiveCategories());
    }
}

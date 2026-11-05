
package com.servicesphere.dto;

import java.time.LocalDateTime;

public record RegisterResponse(
        Long id,
        String fullName,
        String email,
        String phone,
        String role,
        LocalDateTime createdAt
) {
}

package com.jcaa.usersmanagement.application.service.dto.query;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GetMunicipioByIdQuery(
        @NotBlank(message = "id must not be blank")
        @Size(max = 10, message = "id must have at most 10 characters")
        String id) {
}
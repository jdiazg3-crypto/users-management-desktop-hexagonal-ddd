package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateMunicipioCommand(
        @NotBlank(message = "id must not be blank")
        @Size(max = 10, message = "id must have at most 10 characters")
        String id,
        @NotBlank(message = "nombre must not be blank")
        @Size(max = 100, message = "nombre must have at most 100 characters")
        String nombre,
        @NotNull(message = "idProvincia must not be null")
        @Positive(message = "idProvincia must be a positive integer")
        Integer idProvincia) {
}
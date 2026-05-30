package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record MunicipioResponse(
        String id,
        String nombre,
        Integer idProvincia) {}
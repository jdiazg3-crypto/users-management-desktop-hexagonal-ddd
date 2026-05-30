package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

public record MunicipioPersistenceDto(
        String id,
        String nombre,
        Integer idProvincia) {}
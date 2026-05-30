package com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity;

public record MunicipioEntity(
        String id,
        String nombre,
        Integer idProvincia) {}
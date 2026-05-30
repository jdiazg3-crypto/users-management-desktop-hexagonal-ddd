package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record CreateMunicipioRequest(
        String id,
        String nombre,
        Integer idProvincia) {}
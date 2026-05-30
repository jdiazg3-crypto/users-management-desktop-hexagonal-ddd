package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record UpdateMunicipioRequest(
        String id,
        String nombre,
        Integer idProvincia) {}
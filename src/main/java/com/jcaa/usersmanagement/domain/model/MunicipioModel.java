package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.valueobject.MunicipioId;
import lombok.Value;

@Value
public class MunicipioModel {

    MunicipioId id;
    String nombre;
    Integer idProvincia;

    public static MunicipioModel create(
            final MunicipioId id,
            final String nombre,
            final Integer idProvincia) {
        return new MunicipioModel(id, nombre, idProvincia);
    }

    public MunicipioModel update(
            final String nombre,
            final Integer idProvincia) {
        return new MunicipioModel(id, nombre, idProvincia);
    }
}
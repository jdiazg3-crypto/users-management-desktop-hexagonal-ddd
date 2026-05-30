package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.MunicipioModel;
import com.jcaa.usersmanagement.domain.valueobject.MunicipioId;

import java.util.List;
import java.util.Optional;

public interface MunicipioRepositoryPort {

    MunicipioModel save(MunicipioModel municipio);

    MunicipioModel update(MunicipioModel municipio);

    void delete(MunicipioId id);

    Optional<MunicipioModel> findById(MunicipioId id);

    List<MunicipioModel> findAll();
}
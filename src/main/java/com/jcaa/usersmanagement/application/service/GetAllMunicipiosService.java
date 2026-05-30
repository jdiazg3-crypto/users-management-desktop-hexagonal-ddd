package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAllMunicipiosUseCase;
import com.jcaa.usersmanagement.application.port.out.MunicipioRepositoryPort;
import com.jcaa.usersmanagement.domain.model.MunicipioModel;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public final class GetAllMunicipiosService implements GetAllMunicipiosUseCase {

    private final MunicipioRepositoryPort municipioRepositoryPort;

    @Override
    public List<MunicipioModel> execute() {
        return municipioRepositoryPort.findAll();
    }
}
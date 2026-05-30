package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.query.GetMunicipioByIdQuery;
import com.jcaa.usersmanagement.domain.model.MunicipioModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface GetMunicipioByIdUseCase {
    MunicipioModel execute(@NotNull @Valid GetMunicipioByIdQuery query);
}
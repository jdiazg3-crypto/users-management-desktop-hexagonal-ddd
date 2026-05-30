package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.CreateMunicipioCommand;
import com.jcaa.usersmanagement.domain.model.MunicipioModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CreateMunicipioUseCase {
    MunicipioModel execute(@NotNull @Valid CreateMunicipioCommand command);
}
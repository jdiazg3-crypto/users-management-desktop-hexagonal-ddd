package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.UpdateMunicipioCommand;
import com.jcaa.usersmanagement.domain.model.MunicipioModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface UpdateMunicipioUseCase {
    MunicipioModel execute(@NotNull @Valid UpdateMunicipioCommand command);
}
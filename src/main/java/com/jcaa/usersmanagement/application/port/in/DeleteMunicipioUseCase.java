package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.DeleteMunicipioCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface DeleteMunicipioUseCase {
    void execute(@NotNull @Valid DeleteMunicipioCommand command);
}
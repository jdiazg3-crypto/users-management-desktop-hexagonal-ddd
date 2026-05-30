package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdateMunicipioUseCase;
import com.jcaa.usersmanagement.application.port.out.MunicipioRepositoryPort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateMunicipioCommand;
import com.jcaa.usersmanagement.application.service.mapper.MunicipioApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.MunicipioNotFoundException;
import com.jcaa.usersmanagement.domain.model.MunicipioModel;
import com.jcaa.usersmanagement.domain.valueobject.MunicipioId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import java.util.Set;

@RequiredArgsConstructor
public final class UpdateMunicipioService implements UpdateMunicipioUseCase {

    private final MunicipioRepositoryPort municipioRepositoryPort;
    private final Validator validator;

    @Override
    public MunicipioModel execute(final UpdateMunicipioCommand command) {
        validateCommand(command);
        final MunicipioId id = new MunicipioId(command.id());
        municipioRepositoryPort.findById(id)
                .orElseThrow(() -> MunicipioNotFoundException.becauseIdWasNotFound(command.id()));
        final MunicipioModel model = MunicipioApplicationMapper.fromUpdateCommandToModel(command);
        return municipioRepositoryPort.update(model);
    }

    private void validateCommand(final UpdateMunicipioCommand command) {
        final Set<ConstraintViolation<UpdateMunicipioCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
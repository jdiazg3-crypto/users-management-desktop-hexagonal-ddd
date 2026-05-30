package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteMunicipioUseCase;
import com.jcaa.usersmanagement.application.port.out.MunicipioRepositoryPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteMunicipioCommand;
import com.jcaa.usersmanagement.application.service.mapper.MunicipioApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.MunicipioNotFoundException;
import com.jcaa.usersmanagement.domain.valueobject.MunicipioId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import java.util.Set;

@RequiredArgsConstructor
public final class DeleteMunicipioService implements DeleteMunicipioUseCase {

    private final MunicipioRepositoryPort municipioRepositoryPort;
    private final Validator validator;

    @Override
    public void execute(final DeleteMunicipioCommand command) {
        validateCommand(command);
        final MunicipioId id = MunicipioApplicationMapper.fromDeleteCommandToMunicipioId(command);
        municipioRepositoryPort.findById(id)
                .orElseThrow(() -> MunicipioNotFoundException.becauseIdWasNotFound(command.id()));
        municipioRepositoryPort.delete(id);
    }

    private void validateCommand(final DeleteMunicipioCommand command) {
        final Set<ConstraintViolation<DeleteMunicipioCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
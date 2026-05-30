package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateMunicipioUseCase;
import com.jcaa.usersmanagement.application.port.out.MunicipioRepositoryPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateMunicipioCommand;
import com.jcaa.usersmanagement.application.service.mapper.MunicipioApplicationMapper;
import com.jcaa.usersmanagement.domain.model.MunicipioModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import java.util.Set;

@RequiredArgsConstructor
public final class CreateMunicipioService implements CreateMunicipioUseCase {

    private final MunicipioRepositoryPort municipioRepositoryPort;
    private final Validator validator;

    @Override
    public MunicipioModel execute(final CreateMunicipioCommand command) {
        validateCommand(command);
        final MunicipioModel model = MunicipioApplicationMapper.fromCreateCommandToModel(command);
        return municipioRepositoryPort.save(model);
    }

    private void validateCommand(final CreateMunicipioCommand command) {
        final Set<ConstraintViolation<CreateMunicipioCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
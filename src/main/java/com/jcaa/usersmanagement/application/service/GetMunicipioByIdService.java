package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetMunicipioByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.MunicipioRepositoryPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetMunicipioByIdQuery;
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
public final class GetMunicipioByIdService implements GetMunicipioByIdUseCase {

    private final MunicipioRepositoryPort municipioRepositoryPort;
    private final Validator validator;

    @Override
    public MunicipioModel execute(final GetMunicipioByIdQuery query) {
        validateQuery(query);
        final MunicipioId id = MunicipioApplicationMapper.fromGetByIdQueryToMunicipioId(query);
        return municipioRepositoryPort.findById(id)
                .orElseThrow(() -> MunicipioNotFoundException.becauseIdWasNotFound(query.id()));
    }

    private void validateQuery(final GetMunicipioByIdQuery query) {
        final Set<ConstraintViolation<GetMunicipioByIdQuery>> violations = validator.validate(query);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
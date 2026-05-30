package com.jcaa.usersmanagement.domain.exception;

public final class MunicipioNotFoundException extends DomainException {

    private static final String MESSAGE_BY_ID = "The municipio with id '%s' was not found.";

    private MunicipioNotFoundException(final String message) {
        super(message);
    }

    public static MunicipioNotFoundException becauseIdWasNotFound(final String municipioId) {
        return new MunicipioNotFoundException(String.format(MESSAGE_BY_ID, municipioId));
    }
}
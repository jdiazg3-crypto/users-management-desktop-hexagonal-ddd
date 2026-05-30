package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateMunicipioCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteMunicipioCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateMunicipioCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetMunicipioByIdQuery;
import com.jcaa.usersmanagement.domain.model.MunicipioModel;
import com.jcaa.usersmanagement.domain.valueobject.MunicipioId;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MunicipioApplicationMapper {

    public MunicipioModel fromCreateCommandToModel(final CreateMunicipioCommand command) {
        return MunicipioModel.create(
                new MunicipioId(command.id()),
                command.nombre(),
                command.idProvincia());
    }

    public MunicipioModel fromUpdateCommandToModel(final UpdateMunicipioCommand command) {
        return new MunicipioModel(
                new MunicipioId(command.id()),
                command.nombre(),
                command.idProvincia());
    }

    public MunicipioId fromGetByIdQueryToMunicipioId(final GetMunicipioByIdQuery query) {
        return new MunicipioId(query.id());
    }

    public MunicipioId fromDeleteCommandToMunicipioId(final DeleteMunicipioCommand command) {
        return new MunicipioId(command.id());
    }
}
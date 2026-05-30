package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateMunicipioCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteMunicipioCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateMunicipioCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetMunicipioByIdQuery;
import com.jcaa.usersmanagement.domain.model.MunicipioModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateMunicipioRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.MunicipioResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateMunicipioRequest;
import java.util.List;

public final class MunicipioDesktopMapper {

    private MunicipioDesktopMapper() {}

    public static CreateMunicipioCommand toCreateCommand(final CreateMunicipioRequest request) {
        return new CreateMunicipioCommand(
                request.id(),
                request.nombre(),
                request.idProvincia());
    }

    public static UpdateMunicipioCommand toUpdateCommand(final UpdateMunicipioRequest request) {
        return new UpdateMunicipioCommand(
                request.id(),
                request.nombre(),
                request.idProvincia());
    }

    public static DeleteMunicipioCommand toDeleteCommand(final String id) {
        return new DeleteMunicipioCommand(id);
    }

    public static GetMunicipioByIdQuery toGetByIdQuery(final String id) {
        return new GetMunicipioByIdQuery(id);
    }

    public static MunicipioResponse toResponse(final MunicipioModel model) {
        return new MunicipioResponse(
                model.getId().value(),
                model.getNombre(),
                model.getIdProvincia());
    }

    public static List<MunicipioResponse> toResponseList(final List<MunicipioModel> models) {
        return models.stream().map(MunicipioDesktopMapper::toResponse).toList();
    }
}
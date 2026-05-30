package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.CreateMunicipioUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteMunicipioUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllMunicipiosUseCase;
import com.jcaa.usersmanagement.application.port.in.GetMunicipioByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateMunicipioUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateMunicipioRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.MunicipioResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateMunicipioRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.MunicipioDesktopMapper;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public final class MunicipioController {

    private final CreateMunicipioUseCase createMunicipioUseCase;
    private final UpdateMunicipioUseCase updateMunicipioUseCase;
    private final DeleteMunicipioUseCase deleteMunicipioUseCase;
    private final GetMunicipioByIdUseCase getMunicipioByIdUseCase;
    private final GetAllMunicipiosUseCase getAllMunicipiosUseCase;

    public MunicipioResponse createMunicipio(final CreateMunicipioRequest request) {
        final var command = MunicipioDesktopMapper.toCreateCommand(request);
        final var model = createMunicipioUseCase.execute(command);
        return MunicipioDesktopMapper.toResponse(model);
    }

    public MunicipioResponse updateMunicipio(final UpdateMunicipioRequest request) {
        final var command = MunicipioDesktopMapper.toUpdateCommand(request);
        final var model = updateMunicipioUseCase.execute(command);
        return MunicipioDesktopMapper.toResponse(model);
    }

    public void deleteMunicipio(final String id) {
        final var command = MunicipioDesktopMapper.toDeleteCommand(id);
        deleteMunicipioUseCase.execute(command);
    }

    public MunicipioResponse findMunicipioById(final String id) {
        final var query = MunicipioDesktopMapper.toGetByIdQuery(id);
        final var model = getMunicipioByIdUseCase.execute(query);
        return MunicipioDesktopMapper.toResponse(model);
    }

    public List<MunicipioResponse> listAllMunicipios() {
        final var models = getAllMunicipiosUseCase.execute();
        return MunicipioDesktopMapper.toResponseList(models);
    }
}
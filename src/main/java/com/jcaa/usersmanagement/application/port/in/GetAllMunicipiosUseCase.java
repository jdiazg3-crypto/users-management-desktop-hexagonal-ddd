package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.MunicipioModel;
import java.util.List;

public interface GetAllMunicipiosUseCase {
    List<MunicipioModel> execute();
}
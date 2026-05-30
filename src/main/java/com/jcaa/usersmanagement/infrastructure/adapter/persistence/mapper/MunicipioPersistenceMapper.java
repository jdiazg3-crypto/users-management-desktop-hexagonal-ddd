package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.model.MunicipioModel;
import com.jcaa.usersmanagement.domain.valueobject.MunicipioId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.MunicipioPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.MunicipioEntity;
import lombok.experimental.UtilityClass;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class MunicipioPersistenceMapper {

    public MunicipioPersistenceDto fromModelToDto(final MunicipioModel model) {
        return new MunicipioPersistenceDto(
                model.getId().value(),
                model.getNombre(),
                model.getIdProvincia());
    }

    public MunicipioEntity fromResultSetToEntity(final ResultSet rs) throws SQLException {
        return new MunicipioEntity(
                rs.getString("id_municipio"),
                rs.getString("nombre"),
                rs.getInt("id_provincia"));
    }

    public MunicipioModel fromEntityToModel(final MunicipioEntity entity) {
        return new MunicipioModel(
                new MunicipioId(entity.id()),
                entity.nombre(),
                entity.idProvincia());
    }

    public MunicipioModel fromResultSetToModel(final ResultSet rs) throws SQLException {
        return fromEntityToModel(fromResultSetToEntity(rs));
    }

    public List<MunicipioModel> fromResultSetToModelList(final ResultSet rs) throws SQLException {
        final List<MunicipioModel> list = new ArrayList<>();
        while (rs.next()) {
            list.add(fromResultSetToModel(rs));
        }
        return list;
    }
}
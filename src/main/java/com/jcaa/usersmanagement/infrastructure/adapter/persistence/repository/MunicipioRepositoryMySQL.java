package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.MunicipioRepositoryPort;
import com.jcaa.usersmanagement.domain.model.MunicipioModel;
import com.jcaa.usersmanagement.domain.valueobject.MunicipioId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.MunicipioPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.MunicipioPersistenceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Log
@RequiredArgsConstructor
public final class MunicipioRepositoryMySQL implements MunicipioRepositoryPort {

    private static final String SQL_INSERT =
            "INSERT INTO municipio (id_municipio, nombre, id_provincia) VALUES (?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE municipio SET nombre = ?, id_provincia = ? WHERE id_municipio = ?";

    private static final String SQL_SELECT_BY_ID =
            "SELECT id_municipio, nombre, id_provincia FROM municipio WHERE id_municipio = ? LIMIT 1";

    private static final String SQL_SELECT_ALL =
            "SELECT id_municipio, nombre, id_provincia FROM municipio ORDER BY id_municipio ASC";

    private static final String SQL_DELETE =
            "DELETE FROM municipio WHERE id_municipio = ?";

    private final Connection connection;

    @Override
    public MunicipioModel save(final MunicipioModel municipio) {
        final MunicipioPersistenceDto dto = MunicipioPersistenceMapper.fromModelToDto(municipio);
        try (final PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            statement.setString(1, dto.id());
            statement.setString(2, dto.nombre());
            statement.setInt(3, dto.idProvincia());
            statement.executeUpdate();
            return findById(municipio.getId()).orElseThrow();
        } catch (final SQLException exception) {
            throw PersistenceException.becauseSaveFailed(dto.id(), exception);
        }
    }

    @Override
    public MunicipioModel update(final MunicipioModel municipio) {
        final MunicipioPersistenceDto dto = MunicipioPersistenceMapper.fromModelToDto(municipio);
        try (final PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            statement.setString(1, dto.nombre());
            statement.setInt(2, dto.idProvincia());
            statement.setString(3, dto.id());
            statement.executeUpdate();
            return findById(municipio.getId()).orElseThrow();
        } catch (final SQLException exception) {
            throw PersistenceException.becauseUpdateFailed(dto.id(), exception);
        }
    }

    @Override
    public void delete(final MunicipioId id) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
            statement.setString(1, id.value());
            statement.executeUpdate();
        } catch (final SQLException exception) {
            throw PersistenceException.becauseDeleteFailed(id.toString(), exception);
        }
    }

    @Override
    public Optional<MunicipioModel> findById(final MunicipioId id) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            statement.setString(1, id.value());
            final ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                return Optional.empty();
            }
            return Optional.of(MunicipioPersistenceMapper.fromResultSetToModel(rs));
        } catch (final SQLException exception) {
            throw PersistenceException.becauseFindByIdFailed(id.toString(), exception);
        }
    }

    @Override
    public List<MunicipioModel> findAll() {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
            final ResultSet rs = statement.executeQuery();
            return MunicipioPersistenceMapper.fromResultSetToModelList(rs);
        } catch (final SQLException exception) {
            throw PersistenceException.becauseFindAllFailed(exception);
        }
    }
}
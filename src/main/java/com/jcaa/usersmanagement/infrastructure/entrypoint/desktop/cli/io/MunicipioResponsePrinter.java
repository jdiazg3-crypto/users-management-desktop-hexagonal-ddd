package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.MunicipioResponse;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public final class MunicipioResponsePrinter {

    private static final String SEPARATOR = "-".repeat(44);
    private static final String ROW_FORMAT = "  %-16s : %s%n";

    private final ConsoleIO console;

    public void print(final MunicipioResponse response) {
        console.println(SEPARATOR);
        console.printf(ROW_FORMAT, "ID",           response.id());
        console.printf(ROW_FORMAT, "Nombre",       response.nombre());
        console.printf(ROW_FORMAT, "ID Provincia", String.valueOf(response.idProvincia()));
        console.println(SEPARATOR);
    }

    public void printList(final List<MunicipioResponse> municipios) {
        if (municipios.isEmpty()) {
            console.println("  No se encontraron municipios.");
            return;
        }
        console.printf("%n  Total: %d municipio(s)%n", municipios.size());
        municipios.forEach(this::print);
    }
}
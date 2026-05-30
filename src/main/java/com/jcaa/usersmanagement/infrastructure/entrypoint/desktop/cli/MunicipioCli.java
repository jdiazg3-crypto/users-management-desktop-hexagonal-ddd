package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli;

import com.jcaa.usersmanagement.domain.exception.MunicipioNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.MunicipioResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.MunicipioController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateMunicipioRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.MunicipioResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateMunicipioRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public final class MunicipioCli {

    private static final String MENU_BORDER = "  ==========================================";

    private final MunicipioController municipioController;
    private final ConsoleIO console;

    public void start() {
        final MunicipioResponsePrinter printer = new MunicipioResponsePrinter(console);
        boolean running = true;
        while (running) {
            printMenu();
            final int choice = console.readInt("\n  Opcion: ");
            try {
                switch (choice) {
                    case 1 -> listAll(printer);
                    case 2 -> findById(printer);
                    case 3 -> create(printer);
                    case 4 -> update(printer);
                    case 5 -> delete();
                    case 0 -> running = false;
                    default -> console.println("  Opcion invalida.");
                }
            } catch (final ConstraintViolationException ex) {
                console.println("  Errores de validacion:");
                ex.getConstraintViolations()
                        .forEach(v -> console.println("    - " + v.getMessage()));
            } catch (final MunicipioNotFoundException ex) {
                console.println("  No encontrado: " + ex.getMessage());
            } catch (final RuntimeException ex) {
                console.println("  Error inesperado: " + ex.getMessage());
            }
        }
    }

    private void printMenu() {
        console.println();
        console.println(MENU_BORDER);
        console.println("    Gestion de Municipios");
        console.println(MENU_BORDER);
        console.println("    [1] Listar todos");
        console.println("    [2] Buscar por ID");
        console.println("    [3] Crear municipio");
        console.println("    [4] Actualizar municipio");
        console.println("    [5] Eliminar municipio");
        console.println("    [0] Volver al menu principal");
        console.println(MENU_BORDER);
    }

    private void listAll(final MunicipioResponsePrinter printer) {
        final List<MunicipioResponse> list = municipioController.listAllMunicipios();
        printer.printList(list);
    }

    private void findById(final MunicipioResponsePrinter printer) {
        final String id = console.readRequired("ID de municipio: ");
        final MunicipioResponse response = municipioController.findMunicipioById(id);
        printer.print(response);
    }

    private void create(final MunicipioResponsePrinter printer) {
        final String id           = console.readRequired("ID municipio (max 10)  : ");
        final String nombre       = console.readRequired("Nombre                 : ");
        final Integer idProvincia = console.readInt(     "ID provincia           : ");
        final MunicipioResponse created = municipioController.createMunicipio(
                new CreateMunicipioRequest(id, nombre, idProvincia));
        console.println("\n  Municipio creado exitosamente.");
        printer.print(created);
    }

    private void update(final MunicipioResponsePrinter printer) {
        final String id           = console.readRequired("ID municipio           : ");
        final String nombre       = console.readRequired("Nombre                 : ");
        final Integer idProvincia = console.readInt(     "ID provincia           : ");
        final MunicipioResponse updated = municipioController.updateMunicipio(
                new UpdateMunicipioRequest(id, nombre, idProvincia));
        console.println("\n  Municipio actualizado exitosamente.");
        printer.print(updated);
    }

    private void delete() {
        final String id = console.readRequired("ID de municipio a eliminar: ");
        municipioController.deleteMunicipio(id);
        console.println("  Municipio eliminado exitosamente.");
    }
}
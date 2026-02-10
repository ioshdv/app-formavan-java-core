package com.formavan.exception;

import java.util.Map;

/**
 * Recurso: Clase de Excepción.
 * Propósito: Mapear errores de campos específicos (Nombre, Precio, Imagen).
 * Relación: Es lanzada por el Validador cuando los datos fallan.
 */
public class ValidationException extends Exception {
    private final Map<String, String> errors;

    public ValidationException(Map<String, String> errors) {
        super("Errores de validación detectados.");
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
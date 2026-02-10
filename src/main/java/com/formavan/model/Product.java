package com.formavan.model;

import java.io.File;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Representa el producto a crear en el sistema.
 * @param id Identificador único.
 * @param name Nombre del producto.
 * @param price Precio (validación numérica).
 * @param image Archivo de imagen asociado.
 */
public record Product(
        UUID id,
        String name,
        BigDecimal price,
        File image
) {}
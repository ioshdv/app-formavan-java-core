package com.formavan.validation;

import com.formavan.model.Product;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ProductValidator {
    // Carga los mensajes traducibles del archivo .properties
    private final ResourceBundle messages = ResourceBundle.getBundle("messages_es");

    public Map<String, String> validate(Product product) {
        Map<String, String> errors = new HashMap<>();

        // Validación de Nombre (Obligatorio)
        if (product.name() == null || product.name().isBlank()) {
            errors.put("name", messages.getString("form.product.name.required"));
        }

        // Validación de Precio (Positivo)
        if (product.price() == null || product.price().compareTo(BigDecimal.ZERO) <= 0) {
            errors.put("price", messages.getString("form.product.price.invalid"));
        }

        // Validación de Imagen (Existencia)
        if (product.image() == null || !product.image().exists()) {
            errors.put("image", messages.getString("form.product.image.error"));
        }

        return errors;
    }
}
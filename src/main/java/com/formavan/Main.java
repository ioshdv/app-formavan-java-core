package com.formavan;

import com.formavan.model.Product;
import com.formavan.service.ImageProcessor;
import com.formavan.validation.ProductValidator;
import com.formavan.exception.ValidationException;

import java.io.File;
import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        // Inicialización de Recursos
        ProductValidator validator = new ProductValidator();
        ImageProcessor imageService = new ImageProcessor();

        // 1. ESTADO: IDLE (Preparación de datos)
        // Nota: Asegúrate de tener "producto_original.jpg" en la raíz del proyecto.
        File imagenOriginal = new File("producto_original.jpg");
        File imagenDestino = new File("producto_optimizado.jpg");

        Product miProducto = new Product(
                UUID.randomUUID(),
                "Smartphone Java 21",
                new BigDecimal("799.99"),
                imagenOriginal
        );

        try {
            // 2. ESTADO: LOADING (Validación de Esquema)
            System.out.println("[ESTADO: LOADING] Validando formulario...");
            Map<String, String> errores = validator.validate(miProducto);

            if (!errores.isEmpty()) {
                throw new ValidationException(errores);
            }

            // 3. ESTADO: LOADING (Procesamiento de Imagen)
            System.out.println("[ESTADO: LOADING] Optimizando imagen (Compresión automática)...");
            imageService.resizeAndCompress(imagenOriginal, imagenDestino, 800);

            // Simulación de barra de progreso de subida
            for (int i = 0; i <= 100; i += 25) {
                Thread.sleep(200);
                System.out.print("\rSubiendo archivo: " + i + "%");
            }
            System.out.println();

            // 4. ESTADO: SUCCESS
            System.out.println("[ESTADO: SUCCESS] Producto registrado exitosamente.");
            System.out.println("Imagen guardada en: " + imagenDestino.getAbsolutePath());

        } catch (ValidationException e) {
            // 5. ESTADO: ERROR (Mapeo de errores a campos)
            System.err.println("[ESTADO: ERROR] Error de validación en el servidor:");
            e.getErrors().forEach((campo, mensaje) ->
                    System.err.println("Campo '" + campo + "': " + mensaje));

        } catch (Exception e) {
            // Manejo de errores temporales/sistema
            System.err.println("[ESTADO: ERROR] Error inesperado: " + e.getMessage());
        }
    }
}
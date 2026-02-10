package com.formavan.service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessor {

    /**
     * Recurso: Método de procesamiento.
     * Propósito: Redimensionar y comprimir la imagen para cumplir el requerimiento.
     */
    public void resizeAndCompress(File input, File output, int targetWidth) throws IOException {
        // Validación de existencia del insumo
        if (!input.exists()) {
            throw new IOException("El archivo 'producto_original.jpg' no existe en la raíz del proyecto.");
        }

        // Leer imagen original
        BufferedImage originalImage = ImageIO.read(input);

        // Calcular alto proporcional
        int targetHeight = (int) (originalImage.getHeight() * ((double) targetWidth / originalImage.getWidth()));

        // Crear nueva imagen escalada
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = resizedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g2d.dispose();

        // Guardar archivo comprimido
        ImageIO.write(resizedImage, "jpg", output);
    }
}
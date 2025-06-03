package com.example.demo.service;

import com.example.demo.model.ArchivoTransformado;
import com.example.demo.model.TransformacionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransformacionService {

    @Value("${app.url.base:https://conversor-partituras.onrender.com}")
    private String baseUrl;

    private final String carpetaDestino = "/tmp/archivos";

    public TransformacionService() {
        File carpeta = new File(carpetaDestino);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
    }

    public TransformacionResponse transformarPorInstrumento(MultipartFile archivo, String instrumento) {
        String claveDestino = obtenerClavePorInstrumento(instrumento);
        int transposicion = obtenerTransposicionPorInstrumento(instrumento);

        String nombreArchivo = "partitura_" + instrumento + "_" + System.currentTimeMillis() + ".musicxml";
        ArchivoTransformado archivoTransformado = guardarYCrearArchivoTransformado(archivo, nombreArchivo);

        TransformacionResponse respuesta = new TransformacionResponse();
        respuesta.setMensaje("Transformación por instrumento realizada (simulada)");
        respuesta.setInstrumento(instrumento);
        respuesta.setClaveDestino(claveDestino);
        respuesta.setTransposicion(transposicion);
        respuesta.setArchivos(List.of(archivoTransformado));

        return respuesta;
    }

    public TransformacionResponse transformarPersonalizado(MultipartFile archivo, String clave, int transposicion) {
        String timestamp = LocalDateTime.now().toString().replace(":", "-");
        String nombreArchivo = "partitura_personalizada_" + timestamp + ".musicxml";
        ArchivoTransformado archivoTransformado = guardarYCrearArchivoTransformado(archivo, nombreArchivo);

        TransformacionResponse respuesta = new TransformacionResponse();
        respuesta.setMensaje("Transformación personalizada realizada (simulada)");
        respuesta.setClaveOrigen(clave);
        respuesta.setTransposicion(transposicion);
        respuesta.setArchivos(List.of(archivoTransformado));

        return respuesta;
    }

    private ArchivoTransformado guardarYCrearArchivoTransformado(MultipartFile archivo, String nombreArchivo) {
        try {
            File destino = new File(carpetaDestino + "/" + nombreArchivo);
            try (FileOutputStream fos = new FileOutputStream(destino)) {
                fos.write(simularTransformacion(archivo)); // Simulación
            }

            String urlDescarga = baseUrl + "/api/descargar/" + nombreArchivo;

            return new ArchivoTransformado(
                    nombreArchivo,
                    "application/vnd.recordare.musicxml+xml",
                    urlDescarga
            );

        } catch (IOException e) {
            throw new RuntimeException("Error guardando archivo transformado: " + e.getMessage(), e);
        }
    }

    private byte[] simularTransformacion(MultipartFile archivo) throws IOException {
        // Por ahora, se retorna el mismo contenido simulado
        return archivo.getBytes();
    }

    private String obtenerClavePorInstrumento(String instrumento) {
        return switch (instrumento.toLowerCase()) {
            case "clarinete" -> "G";
            case "tuba" -> "F";
            case "saxo_alto" -> "G";
            default -> "G";
        };
    }

    private int obtenerTransposicionPorInstrumento(String instrumento) {
        return switch (instrumento.toLowerCase()) {
            case "clarinete" -> 2;
            case "saxo_alto" -> 9;
            case "tuba" -> -5;
            default -> 0;
        };
    }
}

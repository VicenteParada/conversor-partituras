package com.example.demo.service;

import com.example.demo.model.ArchivoTransformado;
import com.example.demo.model.TransformacionResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransformacionService {

    public TransformacionResponse transformarPorInstrumento(MultipartFile archivo, String instrumento) {
        String claveDestino = obtenerClavePorInstrumento(instrumento);
        int transposicion = obtenerTransposicionPorInstrumento(instrumento);

        // Simulamos un archivo transformado (en producción, aquí se generaría el nuevo archivo)
        ArchivoTransformado archivoTransformado = new ArchivoTransformado(
                "partitura_" + instrumento + ".xml",
                "application/vnd.recordare.musicxml+xml",
                simularContenidoTransformado(archivo)
        );

        List<ArchivoTransformado> archivos = new ArrayList<>();
        archivos.add(archivoTransformado);

        TransformacionResponse respuesta = new TransformacionResponse();
        respuesta.setMensaje("Transformación por instrumento realizada (simulada)");
        respuesta.setInstrumento(instrumento);
        respuesta.setClaveDestino(claveDestino);
        respuesta.setTransposicion(transposicion);
        respuesta.setArchivos(archivos);
        return respuesta;
    }

    public TransformacionResponse transformarPersonalizado(MultipartFile archivo, String clave, int transposicion) {
        // Simulamos un archivo transformado (real: lógica real de cambio de clave y transposición)
        ArchivoTransformado archivoTransformado = new ArchivoTransformado(
                "partitura_personalizada.xml",
                "application/vnd.recordare.musicxml+xml",
                simularContenidoTransformado(archivo)
        );

        List<ArchivoTransformado> archivos = new ArrayList<>();
        archivos.add(archivoTransformado);

        TransformacionResponse respuesta = new TransformacionResponse();
        respuesta.setMensaje("Transformación personalizada realizada (simulada)");
        respuesta.setClaveOrigen(clave);
        respuesta.setTransposicion(transposicion);
        respuesta.setArchivos(archivos);
        return respuesta;
    }

    private byte[] simularContenidoTransformado(MultipartFile archivo) {
        try {
            return archivo.getBytes(); // En real, aquí se transforma la partitura
        } catch (Exception e) {
            return new byte[0];
        }
    }

    private String obtenerClavePorInstrumento(String instrumento) {
        return switch (instrumento.toLowerCase()) {
            case "clarinete" -> "G";   // Clave de Sol
            case "tuba" -> "F";        // Clave de Fa
            case "saxo_alto" -> "G";
            default -> "G";
        };
    }

    private int obtenerTransposicionPorInstrumento(String instrumento) {
        return switch (instrumento.toLowerCase()) {
            case "clarinete" -> 2;   // Bb: +2 semitonos
            case "saxo_alto" -> 9;   // Eb: +9 semitonos
            case "tuba" -> -5;       // Fa: -5 semitonos
            default -> 0;
        };
    }
}

package com.example.demo.controller;

import com.example.demo.model.TransformacionResponse;
import com.example.demo.service.TransformacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TransformacionController {

    private final TransformacionService transformacionService;

    @Autowired
    public TransformacionController(TransformacionService transformacionService) {
        this.transformacionService = transformacionService;
    }

    @PostMapping("/transformar")
    public ResponseEntity<TransformacionResponse> transformarPartitura(
            @RequestParam("archivo") MultipartFile archivo,
            @RequestParam("modo") String modo,
            @RequestParam(value = "instrumento", required = false) String instrumento,
            @RequestParam(value = "clave", required = false) String clave,
            @RequestParam(value = "transposicion", required = false) Integer transposicion) {

        try {
            TransformacionResponse respuesta;

            if ("predeterminado".equalsIgnoreCase(modo)) {
                // Basado en instrumento (ej: clarinete, tuba, etc.)
                respuesta = transformacionService.transformarPorInstrumento(archivo, instrumento);
            } else {
                // Configuración personalizada
                respuesta = transformacionService.transformarPersonalizado(archivo, clave, transposicion);
            }

            return ResponseEntity.ok(respuesta);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new TransformacionResponse("Error: " + e.getMessage()));
        }
    }
}

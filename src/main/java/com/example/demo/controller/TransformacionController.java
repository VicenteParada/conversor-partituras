package com.example.demo.controller;

import com.example.demo.model.TransformacionResponse;
import com.example.demo.service.TransformacionService;
import org.springframework.beans.factory.annotation.Autowired;
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
            @RequestParam("claveOrigen") String claveOrigen,
            @RequestParam("claveDestino") String claveDestino,
            @RequestParam("instrumento") String instrumento) {

        TransformacionResponse respuesta = transformacionService.transformar(archivo, claveOrigen, claveDestino, instrumento);
        return ResponseEntity.ok(respuesta);
    }
}

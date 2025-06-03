package com.example.demo.controller;

import com.example.demo.model.TransformacionResponse;
import com.example.demo.service.TransformacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TransformacionController {

    private final TransformacionService transformacionService;

    private final String carpetaArchivos = "/tmp/archivos"; // misma ruta usada en el service

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

        if (archivo == null || archivo.isEmpty()) {
            return ResponseEntity.badRequest().body(new TransformacionResponse("Archivo no proporcionado."));
        }

        if (modo == null || modo.isBlank()) {
            return ResponseEntity.badRequest().body(new TransformacionResponse("Modo de transformación no especificado."));
        }

        try {
            TransformacionResponse respuesta;

            if ("predeterminado".equalsIgnoreCase(modo)) {
                if (instrumento == null || instrumento.isBlank()) {
                    return ResponseEntity.badRequest().body(new TransformacionResponse("Debe especificar un instrumento."));
                }
                respuesta = transformacionService.transformarPorInstrumento(archivo, instrumento);
            } else if ("personalizado".equalsIgnoreCase(modo)) {
                if (clave == null || clave.isBlank() || transposicion == null) {
                    return ResponseEntity.badRequest().body(new TransformacionResponse("Debe especificar clave y transposición."));
                }
                respuesta = transformacionService.transformarPersonalizado(archivo, clave, transposicion);
            } else {
                return ResponseEntity.badRequest().body(new TransformacionResponse("Modo inválido. Use 'predeterminado' o 'personalizado'."));
            }

            return ResponseEntity.ok(respuesta);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new TransformacionResponse("Error en la transformación: " + e.getMessage()));
        }
    }

    // ✅ NUEVO ENDPOINT para descargar archivos
    @GetMapping("/descargar/{nombre}")
    public ResponseEntity<FileSystemResource> descargarArchivo(@PathVariable String nombre) {
        File archivo = new File(carpetaArchivos + "/" + nombre);

        if (!archivo.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); // o ajustar a XML si se desea
        headers.setContentDisposition(ContentDisposition.attachment().filename(nombre).build());

        return new ResponseEntity<>(new FileSystemResource(archivo), headers, HttpStatus.OK);
    }
}

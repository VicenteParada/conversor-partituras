package com.example.demo.model;

import java.util.List;

// Descomenta esta línea si ya tienes definida la clase ArchivoTransformado
// import com.example.demo.model.ArchivoTransformado;

public class TransformacionResponse {

    private String mensaje;
    private String claveOrigen;
    private String claveDestino;
    private String instrumento;
    private Integer transposicion;

    // Si todavía no definiste la clase ArchivoTransformado, comenta esta línea
    private List<ArchivoTransformado> archivos;

    // Constructor por defecto
    public TransformacionResponse() {}

    // Constructor con mensaje (útil para errores)
    public TransformacionResponse(String mensaje) {
        this.mensaje = mensaje;
    }

    // Getters y setters
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getClaveOrigen() {
        return claveOrigen;
    }

    public void setClaveOrigen(String claveOrigen) {
        this.claveOrigen = claveOrigen;
    }

    public String getClaveDestino() {
        return claveDestino;
    }

    public void setClaveDestino(String claveDestino) {
        this.claveDestino = claveDestino;
    }

    public String getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(String instrumento) {
        this.instrumento = instrumento;
    }

    public Integer getTransposicion() {
        return transposicion;
    }

    public void setTransposicion(Integer transposicion) {
        this.transposicion = transposicion;
    }

    public List<ArchivoTransformado> getArchivos() {
        return archivos;
    }

    public void setArchivos(List<ArchivoTransformado> archivos) {
        this.archivos = archivos;
    }
}

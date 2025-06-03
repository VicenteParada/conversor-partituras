package com.example.demo.model;

public class TransformacionResponse {
    private String mensaje;
    private String claveOrigen;
    private String claveDestino;
    private String instrumento;

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
}

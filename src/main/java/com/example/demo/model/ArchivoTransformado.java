package com.example.demo.model;

public class ArchivoTransformado {

    private String nombre;            // Nombre del archivo generado (por ejemplo, "partitura_transpuesta.xml")
    private String tipoContenido;    // Tipo MIME (por ejemplo, "application/vnd.recordare.musicxml+xml")
    private byte[] contenido;        // Contenido binario del archivo (opcional, puede omitirse en la respuesta)
    private String url;              // URL para descargar el archivo

    public ArchivoTransformado() {}

    public ArchivoTransformado(String nombre, String tipoContenido, byte[] contenido) {
        this.nombre = nombre;
        this.tipoContenido = tipoContenido;
        this.contenido = contenido;
    }

    // Constructor alternativo con URL (sin contenido si no es necesario enviarlo)
    public ArchivoTransformado(String nombre, String tipoContenido, String url) {
        this.nombre = nombre;
        this.tipoContenido = tipoContenido;
        this.url = url;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoContenido() {
        return tipoContenido;
    }

    public void setTipoContenido(String tipoContenido) {
        this.tipoContenido = tipoContenido;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

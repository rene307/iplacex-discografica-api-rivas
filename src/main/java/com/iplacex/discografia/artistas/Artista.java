package com.iplacex.discografia.artistas;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "artistas")
public class Artista {
    @Id
    private String id;
    private String nombre;

    public Artista() {}                    // <- ctor vacío p/ Jackson

    public Artista(String nombre) {        // <- crear desde JSON { "nombre": "..." }
        this.nombre = nombre;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}


package com.iplacex.discografia.artistas;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "artistas")  // 👈 plural en Mongo
public class Artista {
    @Id private String id;
    private String nombre;
    private String pais;

    public Artista() {}
    public Artista(String nombre, String pais) { this.nombre = nombre; this.pais = pais; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
}

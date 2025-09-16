package com.iplacex.discografia.artistas;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ArtistaController {

    private final IArtistaRepository repo;

    public ArtistaController(IArtistaRepository repo) {
        this.repo = repo;
    }

    @GetMapping(value = "/artistas", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Artista> getAll() {
        return repo.findAll();            // GET http://localhost:8080/api/artistas
    }

    @PostMapping(
        value = "/artista",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Artista> create(@RequestBody Artista body) {
        Artista saved = repo.save(body);  // POST http://localhost:8080/api/artista  { "nombre": "Soda Stereo" }
        return ResponseEntity.created(URI.create("/api/artista/" + saved.getId()))
                             .body(saved);
    }
}

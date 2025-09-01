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

    public ArtistaController(IArtistaRepository repo) { this.repo = repo; }

    @PostMapping(value="/artista", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Artista> HandleInsertArtistaRequest(@RequestBody Artista body) {
        Artista created = repo.save(body);
        return ResponseEntity.created(URI.create("/api/artista/" + created._id)).body(created);
    }

    @GetMapping(value="/artistas", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Artista>> HandleGetAristasRequest() {
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping(value="/artista/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Artista> HandleGetArtistaRequest(@PathVariable("id") String id) {
        return repo.findById(id).map(ResponseEntity::ok)
                           .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(value="/artista/{id}", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Artista> HandleUpdateArtistaRequest(@PathVariable("id") String id, @RequestBody Artista body) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        body._id = id;
        return ResponseEntity.ok(repo.save(body));
    }

    @DeleteMapping(value="/artista/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> HandleDeleteArtistaRequest(@PathVariable("id") String id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

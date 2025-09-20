package com.iplacex.discografia.artistas;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/artistas")           // siempre plural en la ruta
@CrossOrigin(origins = "*")
public class ArtistaController {

    private final IArtistaRepository repo;

    public ArtistaController(IArtistaRepository repo) {
        this.repo = repo;
    }

    // Ping para comprobar mapeo
    @GetMapping("/ping")
    public Map<String,String> ping() {
        return Map.of("ok", "artistas");
    }

    @GetMapping
    public List<Artista> all() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Artista byId(@PathVariable String id) {
        return repo.findById(id)
                   .orElseThrow(() -> new IllegalArgumentException("id no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Artista create(@RequestBody Artista a) {
        if (a.getNombre() == null || a.getNombre().isBlank()) {
            throw new IllegalArgumentException("nombre es requerido");
        }
        return repo.save(a);
    }

    @PutMapping("/{id}")
    public Artista update(@PathVariable String id, @RequestBody Artista a) {
        a.setId(id);
        return repo.save(a);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        repo.deleteById(id);
    }
}

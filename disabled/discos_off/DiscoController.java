package com.iplacex.discografia.discos;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DiscoController {
    private final IDiscoRepository repo;

    public DiscoController(IDiscoRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/discos")
    public List<Disco> all() {
        return repo.findAll();
    }

    @PostMapping("/disco")
    public ResponseEntity<Disco> create(@RequestBody Disco d) {
        Disco saved = repo.save(d);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}

package com.iplacex.discografia.discos;

import com.iplacex.discografia.artistas.IArtistaRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class DiscoController {

    private final IDiscoRepository discos;
    private final IArtistaRepository artistas;

    public DiscoController(IDiscoRepository discos, IArtistaRepository artistas) {
        this.discos = discos; this.artistas = artistas;
    }

    @PostMapping(value="/disco", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> HandlePostDiscoRequest(@RequestBody Disco body) {
        if (body.idArtista == null || !artistas.existsById(body.idArtista)) {
            return ResponseEntity.badRequest().body("El artista no existe o idArtista es nulo");
        }
        Disco created = discos.save(body);
        return ResponseEntity.created(URI.create("/api/disco/" + created._id)).body(created);
    }

    @GetMapping(value="/discos", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Disco>> HandleGetDiscosRequest() {
        return ResponseEntity.ok(discos.findAll());
    }

    @GetMapping(value="/disco/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Disco> HandleGetDiscoRequest(@PathVariable("id") String id) {
        return discos.findById(id).map(ResponseEntity::ok)
                             .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value="/artista/{id}/discos", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Disco>> HandleGetDiscosByArtistaRequest(@PathVariable("id") String idArtista) {
        return ResponseEntity.ok(discos.findDiscosByIdArtista(idArtista));
    }
}

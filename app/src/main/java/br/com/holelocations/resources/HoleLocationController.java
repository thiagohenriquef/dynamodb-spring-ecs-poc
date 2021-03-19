package br.com.holelocations.resources;

import br.com.holelocations.dto.HoleLocationDTO;
import br.com.holelocations.entities.HoleLocation;
import br.com.holelocations.service.HoleLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/hole-locations")
public class HoleLocationController {
    @Autowired
    HoleLocationService service;

    @GetMapping
    public ResponseEntity<List<HoleLocation>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody HoleLocationDTO dto) {
        return ResponseEntity.ok(service.save(dto.dtoToEntity()));
    }

    @PutMapping("/{id}")
    public String updateEmployee(@PathVariable("id") String id, @RequestBody HoleLocationDTO hole) {
        hole.setId(id);
        return service.update(id, hole.dtoToEntity());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<HoleLocation> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}

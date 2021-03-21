package br.com.holelocations.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIStatusController {

    @GetMapping(path = "/status")
    public ResponseEntity<String> APIStatus() {
        return ResponseEntity.ok("OK");
    }
}

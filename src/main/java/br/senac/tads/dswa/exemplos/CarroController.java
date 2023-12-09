package br.senac.tads.dswa.exemplos;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    private CarroService service;

    private final Map<String, String> response = new HashMap<>();

    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(service.listar());
        } catch (Exception e) {
            response.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> procurar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.procurar(id));
        } catch (ResponseStatusException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).body(response);
        } catch (Exception e) {
            response.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@Valid @RequestBody CarroDto dto) {
        try {
            service.adicionar(dto);
            return ResponseEntity.noContent().build();
        } catch (ResponseStatusException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).body(response);
        } catch (Exception e) {
            response.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody CarroDto dto) {
        try {
            service.atualizar(id, dto);
            return ResponseEntity.noContent().build();
        } catch (ResponseStatusException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).body(response);
        } catch (Exception e) {
            response.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        try {
            service.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (ResponseStatusException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).body(response);
        } catch (Exception e) {
            response.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

}

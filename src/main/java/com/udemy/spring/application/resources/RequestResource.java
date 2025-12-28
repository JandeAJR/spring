package com.udemy.spring.application.resources;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.udemy.spring.application.dtos.RequestDTO;
import com.udemy.spring.application.services.RequestService;
import com.udemy.spring.infrastructure.models.Request;

@RestController // indicates that this class is a REST controller
@RequestMapping("/requests") // base URL for this resource
public class RequestResource {
	@Autowired // Dependency Injection
    private RequestService requestService;

    @GetMapping //endpoint
    public ResponseEntity<List<Request>> findAll() {
        List<Request> requests = requestService.findAll();
        return ResponseEntity.ok().body(requests);
    }

    @GetMapping(value = "/{id}")//endpoint
    public ResponseEntity<Request> findById(@PathVariable Long id) {
        Request request = requestService.findById(id);
        return ResponseEntity.ok().body(request);
    }

    @PostMapping//endpoint
    public ResponseEntity<Request> insert(@RequestBody RequestDTO requestDTO) {
        Request request = requestService.requestRegistration(requestDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(request.getId())
                .toUri();
        return ResponseEntity.created(uri).body(request);
    }

    @DeleteMapping(value = "/{id}")//endpoint
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        requestService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")//endpoint
    public ResponseEntity<Request> update(@PathVariable Long id, @RequestBody RequestDTO requestDTO) {
        Request request = requestService.update(id, requestDTO);
        return ResponseEntity.ok().body(request);
    }
}

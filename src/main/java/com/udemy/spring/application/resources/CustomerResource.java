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

import com.udemy.spring.application.services.CustomerService;
import com.udemy.spring.infrastructure.models.Customer;

@RestController // indicates that this class is a REST controller
@RequestMapping("/customers") // base URL for this resource
public class CustomerResource {
	@Autowired // Dependency Injection
    private CustomerService customerService;

    @GetMapping //endpoint
    public ResponseEntity<List<Customer>> findAll() {
       List<Customer> customers = customerService.findAll();
       return ResponseEntity.ok().body(customers);
    }

    @GetMapping(value = "/{id}") //endpoint
    public ResponseEntity<Customer> findById(@PathVariable String id) {
        Customer customer = customerService.findById(id);
        return ResponseEntity.ok().body(customer);
    }

    @PostMapping //endpoint
    public ResponseEntity<Customer> insert(@RequestBody Customer customer) {
        customer = customerService.customerRegistration(customer);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(customer.getTelephone())
                .toUri();
        return ResponseEntity.created(uri).body(customer);
    }

    @DeleteMapping(value = "/{id}") //endpoint
    public ResponseEntity<Void> delete(@PathVariable String id) {
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}") //endpoint
    public ResponseEntity<Customer> update(@PathVariable String id, @RequestBody Customer customer) {
        customer = customerService.update(id, customer);
        return ResponseEntity.ok().body(customer);
    }
}

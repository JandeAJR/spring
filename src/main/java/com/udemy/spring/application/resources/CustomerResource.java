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
import com.udemy.spring.infrastructure.models.pks.CustomerPk;

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

    @GetMapping(value = "/{telephone}/{cpf}") //endpoint
    public ResponseEntity<Customer> findById(@PathVariable String telephone, @PathVariable String cpf) {
        Customer customer = customerService.findById(new CustomerPk(telephone, cpf));
        return ResponseEntity.ok().body(customer);
    }

    @PostMapping //endpoint
    public ResponseEntity<Customer> insert(@RequestBody Customer customer) {
        customer = customerService.customerRegistration(customer);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{telephone}/{cpf}")
                .buildAndExpand(customer.getId().getTelephone(), customer.getId().getCpf())
                .toUri();
        return ResponseEntity.created(uri).body(customer);
    }

    @DeleteMapping(value = "/{telephone}/{cpf}") //endpoint
    public ResponseEntity<Void> delete(@PathVariable String telephone, @PathVariable String cpf) {
        customerService.deleteById(new CustomerPk(telephone, cpf));
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{telephone}/{cpf}") //endpoint
    public ResponseEntity<Customer> update(@PathVariable String telephone, @PathVariable String cpf, @RequestBody Customer customer) {
		customer = customerService.update(new CustomerPk(telephone, cpf), customer);
		return ResponseEntity.ok().body(customer);
	}
}

package com.udemy.spring.application.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.spring.application.client.HttpClient;
import com.udemy.spring.application.dtos.CepDTO;

@RestController
@RequestMapping("/cep")
public class CepResource {
	@Autowired // Dependency Injection
	private HttpClient httpClient;
	
	@GetMapping(value = "/{cep}")
	public CepDTO buscarCep(@PathVariable String cep) {
		return httpClient.buscarCep(cep);
	}	
}

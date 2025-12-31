package com.udemy.spring.application.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.spring.application.dtos.CepDTO;
import com.udemy.spring.application.services.CepService;

@RestController
@RequestMapping("/cep")
public class CepResource {
	private final CepService cepService;
	
	// Constructor Injection
	public CepResource(CepService cepService) {
		this.cepService = cepService;
	}
	
	@GetMapping(value = "/{cep}")
	public CepDTO buscarCep(@PathVariable String cep) {
		return cepService.buscarCep(cep);
	}	
}

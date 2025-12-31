package com.udemy.spring.application.services;

import org.springframework.stereotype.Service;

import com.udemy.spring.application.client.ViaCepClient;
import com.udemy.spring.application.dtos.CepDTO;

@Service
public class CepService {
	private final ViaCepClient viaCepClient;
	
	// Constructor Injection
	public CepService(ViaCepClient viaCepClient) {
		this.viaCepClient = viaCepClient;
	}

	public CepDTO buscarCep(String cep) {
        return viaCepClient.buscarCep(cep);
    }
}

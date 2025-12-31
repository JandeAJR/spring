package com.udemy.spring.application.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.udemy.spring.application.dtos.CepDTO;

@FeignClient(url = "https://viacep.com.br/ws/", name = "cep")
public interface HttpClient {
	@GetMapping("{cep}/json")
	CepDTO buscarCep(@PathVariable String cep);
}

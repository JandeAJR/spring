package com.udemy.spring.application.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.udemy.spring.application.dtos.CepDTO;

@FeignClient(name = "via-cep", url = "${viacep.url}")
public interface ViaCepClient {
	@GetMapping(value = "/{cep}/json", produces = "application/json")
	CepDTO buscarCep(@PathVariable("cep") String cep);
}

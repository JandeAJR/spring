package com.udemy.spring.application.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.spring.application.dtos.CepDTO;
import com.udemy.spring.application.services.CepService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Pattern;

@Validated
@RestController
@RequestMapping("/cep")
public class CepResource {
	private final CepService cepService; // Service Injection
	
	// Constructor Injection
	public CepResource(CepService cepService) {
		this.cepService = cepService;
	}
	
	@GetMapping(value = "/{cep}", produces = "application/json")
	@Operation( // Swagger Documentation
	        summary = "Consulta endereço por CEP",
	        description = "Retorna os dados de endereço a partir de um CEP válido"
	    )
    @ApiResponses({ // Swagger Documentation
        @ApiResponse(responseCode = "200", description = "CEP encontrado"),
        @ApiResponse(responseCode = "400", description = "CEP inválido"),
        @ApiResponse(responseCode = "404", description = "CEP não encontrado")
    })
	public ResponseEntity<CepDTO> buscarCep(
	        @PathVariable 
	        @Pattern(regexp = "\\d{8}", message = "CEP deve conter 8 dígitos")
	        String cep
	) {
        return ResponseEntity.ok(cepService.buscarCep(cep));
    }
}

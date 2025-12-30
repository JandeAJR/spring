package com.udemy.spring.application.dtos;

import lombok.Data;

@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
public class CepDTO {
	String cep;
	String Logradouro;
	String complemento;
	String bairro;
	String localidade;
	String uf;
	String ibge;
	String gia;
	String ddd;
	String siafi;
}

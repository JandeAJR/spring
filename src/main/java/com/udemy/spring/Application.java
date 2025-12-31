package com.udemy.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@EnableFeignClients // Habilita o uso do OpenFeign para comunicação entre serviços (apis externas)
@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Spring Pizza API 🍕",
        version = "v1",
        description = """
            API responsável pelo gerenciamento de pedidos, clientes e pizzas.
            Desenvolvida seguindo boas práticas REST, princípios de domínio
            e foco em clareza, previsibilidade e escalabilidade.
            """,
        contact = @Contact(
            name = "Time de Arquitetura",
            email = "arquitetura@empresa.com",
            url = "https://empresa.com"
        ),
        license = @License(
            name = "Apache 2.0",
            url = "https://www.apache.org/licenses/LICENSE-2.0"
        )
    ),
    servers = {
        @Server(
            url = "http://localhost:8080/api/spring",
            description = "Ambiente Local"
        ),
        @Server(
            url = "https://api.empresa.com/api/spring",
            description = "Produção"
        )
    }
)
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

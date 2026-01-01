package com.udemy.spring.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * Configuração do Swagger/OpenAPI para documentação da API REST.
 * Define informações como título, versão, descrição, contato e servidores.
 * Essas informações ajudam desenvolvedores a entenderem e utilizarem a API de forma eficaz.
 * A documentação gerada pode ser acessada via Swagger UI.
 * Swagger/OpenAPI url: http://localhost:8080/api/spring/swagger
 */

@Configuration
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
public class SwaggerConfig {}

package com.udemy.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.http.HttpServletResponse;

/**
 * Configuração de segurança da aplicação.
 * Define regras de autenticação e autorização para os endpoints da API.
 * Configura CORS, desabilita CSRF e define quais endpoints são públicos ou protegidos.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
	        // 🔑 ATIVA CORS PELO SECURITY
	        .cors(Customizer.withDefaults())

	        // ❌ API REST → sem CSRF
	        .csrf(csrf -> csrf.disable())

	        // 🔐 Tratamento correto de erro de autenticação
	        .exceptionHandling(ex -> ex
	            .authenticationEntryPoint((request, response, authException) ->
	                response.sendError(
	                    HttpServletResponse.SC_UNAUTHORIZED,
	                    "Unauthorized"
	                )
	            )
	        )

	        // 🛡️ Regras de autorização
	        .authorizeHttpRequests(auth -> auth
	            // Swagger liberado
	            .requestMatchers(
	                "/swagger-ui.html",
	                "/swagger-ui/**",
	                "/v3/api-docs/**",
	                "/api/spring/v3/api-docs/**"
	            ).permitAll()

	            // Endpoints públicos
	            .requestMatchers(HttpMethod.GET, "/info/**").permitAll()
	            .requestMatchers(HttpMethod.GET, "/cep/**").permitAll()
	            
	            // Apenas teste (apagar depois)
	            .requestMatchers(HttpMethod.GET, "/customers/**").permitAll()
	            .requestMatchers(HttpMethod.GET, "/pizzas/**").permitAll()
	            .requestMatchers(HttpMethod.GET, "/requests/**").permitAll()
	            .requestMatchers(HttpMethod.POST, "/customers/**").permitAll()
	            .requestMatchers(HttpMethod.POST, "/pizzas/**").permitAll()
	            .requestMatchers(HttpMethod.POST, "/requests/**").permitAll()

	            // Endpoints protegidos
	            .anyRequest().authenticated()
	        )

	        // ❌ SEM POPUP (muito importante)
	        .httpBasic(basic -> basic.disable())
	        .formLogin(form -> form.disable());

	    return http.build();
	}
}

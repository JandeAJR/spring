# Curso Udemy de Spring Boot

## Swagger UI

Api docs:  
http://localhost:8080/api/spring/v3/api-docs  

Swagger UI:  
http://localhost:8080/api/spring/swagger-ui/index.html

## Dicas importantes do Barth!

🌱 Boa prática visionária (o próximo passo natural)  
✨ Nunca exponha Entity direto no controller  

✨ Use @Component quando a classe FAZ algo  
✨ Use @Configuration quando a classe DEFINE algo


🌱 @Component — o cidadão comum do Spring  

Pensa no @Component como o DNA básico do ecossistema Spring.  

Ele diz:  

“Ei, Spring! Essa classe é um bean. Cuida dela pra mim.”  

📌 O que ele faz  

Registra a classe no Spring Context

Permite injeção de dependência

É genérico, simples e direto

📦 Quem herda dele

@Service → regra de negócio

@Repository → acesso a dados

@Controller / @RestController → camada web

🧠 Quando usar

Serviços

Facades

Adaptadores

Helpers

Casos de uso

Qualquer classe “funcional” da aplicação

👉 Em resumo:
Se a classe faz algo → @Component (ou especialização)



🧠 @Configuration — o arquiteto do sistema

Agora o @Configuration é outra história…
Ele não é só um bean. Ele é o maestro da orquestra 🎼

Ele diz:

“Spring, aqui eu defino como os beans nascem.”

📌 O que ele faz

Define beans via métodos @Bean

Controla ciclo de vida

Garante singleton real, mesmo chamando métodos @Bean entre si

Usa proxy CGLIB por baixo dos panos (magia negra elegante 🧙‍♂️)

Exemplo clássico:  

```java
@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {
        ...
    }
}

```

⚠️ A diferença que poucos explicam (mas muda tudo)  
❌ @Component + @Bean

Funciona…  
mas não garante singleton correto se você chamar um @Bean dentro de outro.

✅ @Configuration + @Bean

O Spring intercepta a chamada e devolve sempre o mesmo bean.

💡 Por isso:

Toda @Configuration é um @Component,  
mas nem todo @Component serve como @Configuration.

🧭 Regra de ouro (pra colar na parede)

✨ Use @Component quando a classe FAZ algo  
✨ Use @Configuration quando a classe DEFINE algo

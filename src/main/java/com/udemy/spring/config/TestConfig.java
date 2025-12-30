package com.udemy.spring.config;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.udemy.spring.infrastructure.models.Customer;
import com.udemy.spring.infrastructure.models.Pizza;
import com.udemy.spring.infrastructure.models.Request;
import com.udemy.spring.infrastructure.models.pks.CustomerPk;
import com.udemy.spring.infrastructure.repositories.CustomerRepository;
import com.udemy.spring.infrastructure.repositories.PizzaRepository;
import com.udemy.spring.infrastructure.repositories.RequestRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	@Autowired // Dependency Injection
    private CustomerRepository customerRepository;
    @Autowired // Dependency Injection
    private PizzaRepository pizzaRepository;
    @Autowired // Dependency Injection
    private RequestRepository requestRepository;

    public void run(String... args) throws Exception {
    	Pizza pizza = new Pizza("Muzzarela", 30.50);
        Pizza pizza2 = new Pizza("Calabreza", 25.89);
        Pizza pizza3 = new Pizza("Frango com Catupiry", 50.00);
        pizzaRepository.saveAll(Arrays.asList(pizza, pizza2, pizza3));

        CustomerPk customerPk = new CustomerPk("11999999999", "123456789");
        Customer customer = new Customer(customerPk, "Marcos", "Av. Paulista, 1578");
        customerRepository.save(customer);

        Request request = new Request(LocalDateTime.now(ZoneId.of("UTC")), customer, Arrays.asList(pizza, pizza2, pizza3), 125.00);
        requestRepository.save(request);
    }
}

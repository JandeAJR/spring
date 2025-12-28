package com.udemy.spring.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.udemy.spring.application.services.exceptions.DatabaseException;
import com.udemy.spring.application.services.exceptions.ResourceNotFoundException;
import com.udemy.spring.infrastructure.models.Pizza;
import com.udemy.spring.infrastructure.repositories.PizzaRepository;

import jakarta.persistence.EntityNotFoundException;

/*
    Esta classe representa os serviços disponíveis para o cadastro da pizza.
*/

@Service // Indica que esta classe é um serviço do Spring.
public class PizzaService {
	@Autowired // Injeção de dependência do repositório de pizzas.
    private PizzaRepository pizzaRepository;

    public Pizza pizzaRegistration(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public Pizza findById(Long id) {
        Optional<Pizza> optional = pizzaRepository.findById(id);
        return optional.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    public Pizza update(Long id, Pizza pizza) {
        try {
            Pizza entity = pizzaRepository.getReferenceById(id);
            updateEntity(entity, pizza);
            return pizzaRepository.save(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
        catch (RuntimeException e) { // Captura outras exceções de tempo de execução.
			e.printStackTrace();
		}
        
		return null;
    }

    private void updateEntity(Pizza entity, Pizza pizza) {
        entity.setPrice(pizza.getPrice());
        entity.setName(pizza.getName());
    }

    public void deleteById(Long id) {
        try {
            pizzaRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
        catch (RuntimeException e) { // Captura outras exceções de tempo de execução.
			e.printStackTrace();
		}
    }
}

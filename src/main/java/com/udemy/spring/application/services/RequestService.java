package com.udemy.spring.application.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.udemy.spring.application.dtos.RequestDTO;
import com.udemy.spring.application.services.exceptions.DatabaseException;
import com.udemy.spring.application.services.exceptions.ResourceNotFoundException;
import com.udemy.spring.infrastructure.models.Customer;
import com.udemy.spring.infrastructure.models.Pizza;
import com.udemy.spring.infrastructure.models.Request;
import com.udemy.spring.infrastructure.models.pks.CustomerPk;
import com.udemy.spring.infrastructure.repositories.RequestRepository;

import jakarta.persistence.EntityNotFoundException;

/*
    Esta classe representa os serviços disponíveis para o pedido.
*/

@Service // Indica que esta classe é um serviço do Spring.
public class RequestService {
	@Autowired // Injeção de dependência do repositório de pedidos.
    private RequestRepository requestRepository;

    @Autowired // Injeção de dependência do serviço de pizzas.
    private PizzaService pizzaService;

    @Autowired // Injeção de dependência do serviço de clientes.
    private CustomerService customerService;

    public Request requestRegistration(RequestDTO requestDTO) {
        Pizza pizza = pizzaService.findById(requestDTO.getPizzaId());
        Customer customer = customerService.findById(new CustomerPk(requestDTO.getTelephone(), requestDTO.getCpf()));
        Request request = new Request(LocalDateTime.now(ZoneId.of("UTC")), requestDTO.getAmount(), requestDTO.getPrice(),
                                      pizza, customer);
        
        return requestRepository.save(request);
    }
    public Request findById(Long id) {
        Optional<Request> optional = requestRepository.findById(id);
        return optional.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    public Request update(Long id, RequestDTO requestDTO) {
        try {
            Request entity = requestRepository.getReferenceById(id);
            updateEntity(entity, requestDTO);
            return requestRepository.save(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
        catch (RuntimeException e) { // Captura outras exceções de tempo de execução.
			e.printStackTrace();
		}
        
		return null;
    }

    private void updateEntity(Request entity, RequestDTO request) {
        entity.setAmount(request.getAmount());
    }

    public void deleteById(Long id) {
        try {
            requestRepository.deleteById(id);
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

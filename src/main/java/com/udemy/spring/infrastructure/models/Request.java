package com.udemy.spring.infrastructure.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/*
    Esta classe representa o modelo da entidade

    O modelo de entidade deve:

        - Implementar a interface Serializable;
        - Ter os atributos da entidade que representa;
        - Ter construtores;
        - Ter métodos Gettters e Setters;
        - Ter métodos hascode e equals;
        - Ter o método toString;
        - Colocar as anotações para definir a entidade, as colunas e os relacionamentos se tiver;
*/

@Entity
@Table(name = "tb_request")
public class Request implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_request")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT") //ISO 8601
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)                                            // FetchType.LAZY  -> Lazy loading (default) -> Carregamento tardio 
    @JoinColumns({@JoinColumn(name = "telephone"), @JoinColumn(name = "cpf")})   //  FetchType.EAGER -> Eager loading -> Carregamento imediato
    private Customer customer;                                                  // Para usar Lazy Loading, configurar spring.jpa.open-in-view=true
                                                                               //  Para usar Eager Loading, configurar spring.jpa.open-in-view=false
    
    @ManyToMany // Relação muitos para muitos entre Request e Pizza
    @JoinTable(name = "tb_item_request", // Tabela intermediária
            joinColumns = @JoinColumn(name = "request_id"), // Chave estrangeira para Request
            inverseJoinColumns = @JoinColumn(name = "pizza_id")) // Chave estrangeira para Pizza
    private List<Pizza> pizzas = new ArrayList<>();

    @Column(name = "price", columnDefinition = "NUMBER(10,2)") // Definindo explicitamente o tipo da coluna no banco de dados
    private Double price;

    public Request() {}

    public Request(LocalDateTime date, Customer customer, List<Pizza> pizzas, Double price) {
        this.date = date;
        this.customer = customer;
        this.pizzas = pizzas;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public Customer getCustomer() {
       return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(id, request.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", date=" + date +
                ", price=" + price +
                ", customer=" + customer +
                '}';
    }
}

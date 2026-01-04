package com.udemy.spring.infrastructure.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
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
@Table(name = "tb_pizza")
public class Pizza implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) // Usando identity MySQL ou SQL Server
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pizza") // Usando sequence para Oracle
    @SequenceGenerator(
        name = "seq_pizza",
        sequenceName = "seq_pizza",
        allocationSize = 1
    )
    private Long id;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "price", columnDefinition = "NUMBER(10,2)") // Definindo explicitamente o tipo da coluna no banco de dados
    private Double price;

    @JsonIgnore
    @ManyToMany(mappedBy = "pizzas")
    private List<Request>  requests = new ArrayList<>();

    public Pizza() {}

    public Pizza(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Request> getRequests() {
        return requests;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Pizza)) return false;
        Pizza pizza = (Pizza) obj;
        return Objects.equals(id, pizza.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

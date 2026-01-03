-- =========================
-- CUSTOMER
-- =========================
CREATE TABLE tb_customer (
    telephone VARCHAR(11) NOT NULL,
    cpf       VARCHAR(11) NOT NULL,
    name      VARCHAR(255),
    address   VARCHAR(255),
    CONSTRAINT pk_customer PRIMARY KEY (telephone, cpf)
);

-- =========================
-- PIZZA
-- =========================
CREATE TABLE tb_pizza (
    id    BIGINT NOT NULL,
    name  VARCHAR(255),
    price DECIMAL(10,2),
    CONSTRAINT pk_pizza PRIMARY KEY (id)
);

-- =========================
-- REQUEST
-- =========================
CREATE TABLE tb_request (
    id        BIGINT NOT NULL,
    date      TIMESTAMP,
    telephone VARCHAR(11) NOT NULL,
    cpf       VARCHAR(11) NOT NULL,
    price     DECIMAL(10,2),
    CONSTRAINT pk_request PRIMARY KEY (id),
    CONSTRAINT fk_request_customer
        FOREIGN KEY (telephone, cpf)
        REFERENCES tb_customer (telephone, cpf)
);

-- =========================
-- ITEM REQUEST (Many-to-Many)
-- =========================
CREATE TABLE tb_item_request (
    request_id BIGINT NOT NULL,
    pizza_id   BIGINT NOT NULL,
    CONSTRAINT pk_item_request PRIMARY KEY (request_id, pizza_id),
    CONSTRAINT fk_item_request_request
        FOREIGN KEY (request_id)
        REFERENCES tb_request (id),
    CONSTRAINT fk_item_request_pizza
        FOREIGN KEY (pizza_id)
        REFERENCES tb_pizza (id)
);

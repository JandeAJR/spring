-- =========================
-- Tabela Customer
-- =========================
CREATE TABLE tb_customer (
    telephone VARCHAR2(11 CHAR),
    cpf       VARCHAR2(11 CHAR),
    name      VARCHAR2(255 CHAR),
    address   VARCHAR2(255 CHAR),
    CONSTRAINT pk_customer PRIMARY KEY (telephone, cpf)
);

-- =========================
-- Tabela Pizza
-- =========================
CREATE TABLE tb_pizza (
    id    NUMBER PRIMARY KEY,
    name  VARCHAR2(255 CHAR),
    price NUMBER(10,2)
);

-- =========================
-- Tabela Request
-- =========================
CREATE TABLE tb_request (
    id           NUMBER PRIMARY KEY,
    date_request TIMESTAMP,
    telephone    VARCHAR2(11 CHAR),
    cpf          VARCHAR2(11 CHAR),
    price        NUMBER(10,2),
    CONSTRAINT fk_request_customer FOREIGN KEY (telephone, cpf)
        REFERENCES tb_customer (telephone, cpf)
);

-- =================================================================
-- Tabela de associação tb_item_request (ManyToMany Request x Pizza)
-- =================================================================
CREATE TABLE tb_item_request (
    request_id NUMBER,
    pizza_id   NUMBER,
    CONSTRAINT fk_item_request_request FOREIGN KEY (request_id)
        REFERENCES tb_request (id),
    CONSTRAINT fk_item_request_pizza FOREIGN KEY (pizza_id)
        REFERENCES tb_pizza (id),
    CONSTRAINT pk_item_request PRIMARY KEY (request_id, pizza_id)
);


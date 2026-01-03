-- =========================
-- PIZZAS
-- =========================
INSERT INTO tb_pizza (id, name, price)
VALUES (NEXT VALUE FOR seq_pizza, 'Muzzarela', 30.50);

INSERT INTO tb_pizza (id, name, price)
VALUES (NEXT VALUE FOR seq_pizza, 'Calabreza', 25.89);

INSERT INTO tb_pizza (id, name, price)
VALUES (NEXT VALUE FOR seq_pizza, 'Frango com Catupiry', 50.00);

-- =========================
-- CUSTOMER
-- =========================
INSERT INTO tb_customer (telephone, cpf, name, address)
VALUES ('11999999999', '12345678900', 'Marcos', 'Av. Paulista, 1578');

-- =========================
-- REQUEST
-- =========================
INSERT INTO tb_request (id, date, telephone, cpf, price)
VALUES (
    NEXT VALUE FOR seq_request,
    CURRENT_TIMESTAMP,
    '11999999999',
    '12345678900',
    125.00
);

-- =========================
-- RELATION REQUEST x PIZZA
-- =========================
INSERT INTO tb_item_request (request_id, pizza_id)
VALUES (1, 1);

INSERT INTO tb_item_request (request_id, pizza_id)
VALUES (1, 2);

INSERT INTO tb_item_request (request_id, pizza_id)
VALUES (1, 3);

-- =========================
-- Inserts iniciais
-- =========================

-- Clientes
INSERT INTO tb_customer (telephone, cpf, name, address)
VALUES ('11999999999','123456789','Marcos','Av. Paulista, 1578');

-- Pizzas
INSERT INTO tb_pizza (id, name, price) VALUES (seq_pizza.NEXTVAL,'Muzzarela',30.50);
INSERT INTO tb_pizza (id, name, price) VALUES (seq_pizza.NEXTVAL,'Calabreza',25.89);
INSERT INTO tb_pizza (id, name, price) VALUES (seq_pizza.NEXTVAL,'Frango com Catupiry',50.00);

-- Requests
-- Pegando os IDs das pizzas inseridas
INSERT INTO tb_request (id, date_request, telephone, cpf, price)
VALUES (seq_request.NEXTVAL, SYSTIMESTAMP, '11999999999','123456789', 125.00);

-- Assumindo que o request gerado tem ID = 1 e pizzas têm IDs 1,2,3
INSERT INTO tb_item_request (request_id, pizza_id) VALUES (1, 1);
INSERT INTO tb_item_request (request_id, pizza_id) VALUES (1, 2);
INSERT INTO tb_item_request (request_id, pizza_id) VALUES (1, 3);

COMMIT;


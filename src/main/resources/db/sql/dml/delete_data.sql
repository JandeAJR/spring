-- ========================================================================================
-- Exclui dados de exemplo das tabelas TB_ITEM_REQUEST, TB_REQUEST, TB_PIZZA e TB_CUSTOMER
-- ========================================================================================

DELETE FROM TB_ITEM_REQUEST
WHERE REQUEST_ID = 2;

DELETE FROM TB_REQUEST
WHERE ID = 2;

DELETE FROM TB_PIZZA
WHERE ID = 4;

DELETE FROM TB_CUSTOMER
WHERE TELEPHONE = '61992170097' AND CPF = '66508690159';

COMMIT;


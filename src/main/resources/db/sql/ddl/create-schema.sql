-- =========================
-- Schema HOMOLOG
-- =========================

-- Cria usuário/schema
CREATE USER HOMOLOG IDENTIFIED BY root;

-- Dá privilégios mínimos para desenvolvimento/homolog
GRANT CONNECT, RESOURCE TO HOMOLOG;

-- Dá quota ilimitada no tablespace USERS
ALTER USER HOMOLOG QUOTA UNLIMITED ON USERS;

-- Verifica a quota disponível no tablespace USERS para o usuário HOMOLOG
SELECT username, tablespace_name, bytes, max_bytes 
FROM dba_ts_quotas 
WHERE username = 'HOMOLOG';

-- Opcional: se precisar criar sequences e tables
GRANT CREATE TABLE, CREATE SEQUENCE, CREATE VIEW TO HOMOLOG;


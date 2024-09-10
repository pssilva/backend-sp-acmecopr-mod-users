/**
-- Database: desafio-tecnico

DROP DATABASE IF EXISTS "desafio-tecnico";

CREATE DATABASE "desafio-tecnico"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'pt_BR.UTF-8'
    LC_CTYPE = 'pt_BR.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

COMMENT ON DATABASE "desafio-tecnico"
    IS 'Bando de dados padrão para qualquer desafio ténico {{TITULO_DESAFIO}} do processo seletivo da emprea {{NOME_EMPRESA}}!';
*/

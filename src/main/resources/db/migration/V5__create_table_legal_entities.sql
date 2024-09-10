-- Table: public.tb_legal_entities

DROP TABLE IF EXISTS public.tb_legal_entities;

-- #############################################
-- Tabela Pessoa Jurídica - PJ
-- #############################################
CREATE TABLE IF NOT EXISTS public.tb_legal_entities
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    nome_fantasia character varying(180) COLLATE pg_catalog."default" NOT NULL,
    nome_comercial character varying(180) COLLATE pg_catalog."default" NOT NULL,
    cnpj character varying(11) COLLATE pg_catalog."default" NOT NULL,
    email character varying(120) COLLATE pg_catalog."default" NOT NULL,
    pessoa_id uuid NOT NULL,
    user_id uuid,
    modified timestamp without time zone DEFAULT clock_timestamp(),
    created timestamp without time zone DEFAULT clock_timestamp(),
    CONSTRAINT tb_legal_entities_pkey PRIMARY KEY (id),
    CONSTRAINT fk_tb_legal_entities FOREIGN KEY (pessoa_id)
        REFERENCES public.tb_persons (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_tb_legal_entities_user_create FOREIGN KEY (user_id)
        REFERENCES public.tb_persons (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tb_legal_entities
    OWNER to postgres;
-- #############################################
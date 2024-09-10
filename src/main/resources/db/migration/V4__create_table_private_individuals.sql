-- Table: public.tb_private_individuals

DROP TABLE IF EXISTS public.tb_private_individuals;
-- #############################################
-- Tabela Pessoa Física - PF
-- #############################################
CREATE TABLE IF NOT EXISTS public.tb_private_individuals
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    nome character varying(180) COLLATE pg_catalog."default" NOT NULL,
    sobrenome character varying(180) COLLATE pg_catalog."default" NOT NULL,
    cpf character varying(11) COLLATE pg_catalog."default" NOT NULL,
    email character varying(120) COLLATE pg_catalog."default" NOT NULL,
    pessoa_id uuid NOT NULL,
    user_id uuid,
    modified timestamp without time zone DEFAULT clock_timestamp(),
    created timestamp without time zone DEFAULT clock_timestamp(),
    CONSTRAINT tb_private_individuals_pkey PRIMARY KEY (id),
    CONSTRAINT fk_tb_private_individuals FOREIGN KEY (pessoa_id)
        REFERENCES public.tb_persons (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_tb_private_individuals_user_create FOREIGN KEY (user_id)
        REFERENCES public.tb_persons (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tb_private_individuals
    OWNER to postgres;
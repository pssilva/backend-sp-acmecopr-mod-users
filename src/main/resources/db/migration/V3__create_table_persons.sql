-- Table: public.tb_persons

-- DROP TABLE IF EXISTS public.tb_persons;

CREATE TABLE IF NOT EXISTS public.tb_persons
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    nome character varying(180) COLLATE pg_catalog."default" NOT NULL,
    sobrenome character varying(180) COLLATE pg_catalog."default" NOT NULL,
    user_id uuid,
    modified timestamp without time zone DEFAULT clock_timestamp(),
    created timestamp without time zone DEFAULT clock_timestamp(),
    CONSTRAINT tb_persons_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tb_persons
    OWNER to postgres;
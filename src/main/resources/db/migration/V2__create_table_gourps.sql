-- Table: public.tb_groups

-- DROP TABLE IF EXISTS public.tb_groups;

CREATE TABLE IF NOT EXISTS public.tb_groups
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    nome_grupo character varying(180) COLLATE pg_catalog."default" NOT NULL,
    descricao character varying(180) COLLATE pg_catalog."default" NOT NULL,
    modified timestamp without time zone DEFAULT clock_timestamp(),
    created timestamp without time zone DEFAULT clock_timestamp(),
    CONSTRAINT tb_groups_pkey PRIMARY KEY (id),
    CONSTRAINT uni_nome_grupo UNIQUE (nome_grupo)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tb_groups
    OWNER to postgres;
-- Index: idx_nome_grupo

-- DROP INDEX IF EXISTS public.idx_nome_grupo;

CREATE INDEX IF NOT EXISTS idx_nome_grupo
    ON public.tb_groups USING btree
    (nome_grupo COLLATE pg_catalog."default" ASC NULLS LAST)
    WITH (deduplicate_items=True)
    TABLESPACE pg_default;
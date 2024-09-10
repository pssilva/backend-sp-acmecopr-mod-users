
--
-- TOC entry 2 (class 3079 OID 16396)
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


--
-- TOC entry 4467 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


-- Table: public.tb_users

-- DROP TABLE IF EXISTS public.tb_users;

CREATE TABLE IF NOT EXISTS public.tb_users
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    email character varying(120) COLLATE pg_catalog."default" NOT NULL,
    password character varying(120) COLLATE pg_catalog."default" NOT NULL,
    modified timestamp without time zone DEFAULT clock_timestamp(),
    created timestamp without time zone DEFAULT clock_timestamp(),
    CONSTRAINT tb_users_pkey PRIMARY KEY (id),
    CONSTRAINT uniq_email UNIQUE (email)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tb_users
    OWNER to postgres;

COMMENT ON COLUMN public.tb_users.email
    IS 'email do login e autenticação';
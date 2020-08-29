
CREATE TABLE public.empresa
(
    id           serial,
    cnpj         character varying(14)  NOT NULL,
    razao_social  character varying(100) NOT NULL,
    nome_fantasia character varying(100),
    data_abertura date,
    cep          character varying(8),
    logradouro   character varying(255) NOT NULL,
    numero       character varying(20)  NOT NULL,
    complemento  character varying(255),
    bairro       character varying(120) NOT NULL,
    municipio_id bigint,
    email        character varying(120),
    telefone     character varying(11),
    tipo_empresa  character varying(20)  NOT NULL,
    CONSTRAINT empresa_pkey PRIMARY KEY (id),
    CONSTRAINT fk_empresa_municipio_id FOREIGN KEY (municipio_id)
        REFERENCES public.municipio (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE UNIQUE INDEX idx_empresa_cnpj
    ON public.empresa USING btree
        (cnpj COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;
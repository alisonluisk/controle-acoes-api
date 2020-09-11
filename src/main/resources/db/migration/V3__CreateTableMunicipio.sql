
CREATE TABLE public.municipio
(
    id integer NOT NULL,
    nome character varying(50) NOT NULL,
    estado_id integer NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    CONSTRAINT municipio_pkey PRIMARY KEY (id),
    CONSTRAINT fk_municipio_estado_id FOREIGN KEY (estado_id)
        REFERENCES public.estado (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
COMMENT ON TABLE public.municipio IS 'Tabela de Manutenção de Cidade.';
COMMENT ON COLUMN public.municipio.id IS 'Identificação da Cidade, Código do IBGE.';
COMMENT ON COLUMN public.municipio.nome IS 'Nome da Cidade.';
COMMENT ON COLUMN public.municipio.estado_id IS 'Identificação do Estado, Código do IBGE.';
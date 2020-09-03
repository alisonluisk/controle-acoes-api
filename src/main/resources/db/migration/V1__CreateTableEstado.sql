
CREATE TABLE public.estado
(
    id integer NOT NULL,
    nome character varying(50) NOT NULL,
    sigla character varying(3) NOT NULL,
    CONSTRAINT estado_pkey PRIMARY KEY (id)
);
COMMENT ON TABLE public.estado IS 'Tabela de Manutenção de Estado.';
COMMENT ON COLUMN public.estado.id IS 'Identificação do Estado, Código do IBGE.';
COMMENT ON COLUMN public.estado.nome IS 'Nome do Estado.';
COMMENT ON COLUMN public.estado.sigla IS 'Sigla do Estado, Abreviação.';
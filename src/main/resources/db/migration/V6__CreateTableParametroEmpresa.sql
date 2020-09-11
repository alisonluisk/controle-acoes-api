CREATE TABLE public.parametro_empresa
(
    id           serial,
    empresa_id    bigint,
    cotas_on bigint,
    cotas_pn bigint,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    CONSTRAINT parametro_empresa_pkey PRIMARY KEY (id),
    CONSTRAINT fk_parametro_empresa_empresa_id FOREIGN KEY (empresa_id)
        REFERENCES public.empresa(id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

COMMENT ON TABLE public.parametro_empresa IS 'Tabela de Parâmentros das açoes.';
COMMENT ON COLUMN public.parametro_empresa.id IS 'Identificação do Parâmetro.';
COMMENT ON COLUMN public.parametro_empresa.empresa_id IS 'Identificação da Empresa.';
COMMENT ON COLUMN public.parametro_empresa.cotas_on IS 'Percentual das Cotas ON das empresas criadas que ficarão vinculadas a essa empresa na criação das ações.';
COMMENT ON COLUMN public.parametro_empresa.cotas_pn IS 'Percentual das Cotas PN das empresas criadas que ficarão vinculadas a essa empresa na criação das ações.';

ALTER TABLE public.empresa DROP COLUMN cotas_on;
ALTER TABLE public.empresa DROP COLUMN cotas_pn;
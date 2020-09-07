CREATE TABLE public.parametro_empresa
(
    id           serial,
    empresa_id    bigint,
    cotas_on bigint,
    cotas_pn bigint,
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

CREATE TABLE public.parametro_empresa_log (
        id INTEGER NOT NULL,
        rev INTEGER NOT NULL,
        revtype INTEGER,
        empresa_id INTEGER,
        cotas_on INTEGER,
        cotas_pn INTEGER,
        CONSTRAINT id PRIMARY KEY (id, rev)
);
COMMENT ON TABLE public.parametro_empresa_log IS 'Tabela de Log de Parâmentros das ações.';
COMMENT ON COLUMN public.parametro_empresa_log.id IS 'Identificação do Parâmetro.';
COMMENT ON COLUMN public.parametro_empresa_log.rev IS 'Identificação do Log do Parâmetro no Sistema.';
COMMENT ON COLUMN public.parametro_empresa_log.revtype IS 'Identificação do tipo de alteração:
0 - INSERT
1 - UPDATE
2 - DELETE';
COMMENT ON COLUMN public.parametro_empresa_log.empresa_id IS 'Identificação da Empresa.';
COMMENT ON COLUMN public.parametro_empresa_log.cotas_on IS 'Percentual das Cotas ON das empresas criadas que ficarão vinculadas a essa empresa na criação das ações.';
COMMENT ON COLUMN public.parametro_empresa_log.cotas_pn IS 'Percentual das Cotas PN das empresas criadas que ficarão vinculadas a essa empresa na criação das ações.';

ALTER TABLE public.empresa DROP COLUMN cotas_on;
ALTER TABLE public.empresa DROP COLUMN cotas_pn;
ALTER TABLE public.empresa_log DROP COLUMN cotas_on;
ALTER TABLE public.empresa_log DROP COLUMN cotas_pn;
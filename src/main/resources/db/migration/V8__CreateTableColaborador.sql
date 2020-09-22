
CREATE TABLE public.colaborador
(
    id          serial,
    cpf         character varying(11)  NOT NULL,
    nome        character varying(255)  NOT NULL,
    email       character varying(120),
    rg          character varying(30),
    data_nascimento date,
    telefone_fixo     character varying(11),
    telefone_celular     character varying(11),
    estado_civil  character varying(30),
    cep          character varying(8),
    logradouro   character varying(255) NOT NULL,
    numero       bigint  NOT NULL,
    complemento  character varying(255),
    bairro       character varying(120) NOT NULL,
    municipio_id bigint NOT NULL,
    ativo boolean NOT NULL DEFAULT True,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    CONSTRAINT colaborador_pkey PRIMARY KEY (id),
    CONSTRAINT fk_colaborador_municipio_id FOREIGN KEY (municipio_id)
        REFERENCES public.municipio(id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE UNIQUE INDEX idx_colaborador_cpf
    ON public.colaborador USING btree
        (cpf COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;

COMMENT ON TABLE public.colaborador IS 'Tabela de Manutenção de Colaboradores.';
COMMENT ON COLUMN public.colaborador.id IS 'Identificação do colaborador no sistema.';
COMMENT ON COLUMN public.colaborador.cpf IS 'CPF do Colaborador.';
COMMENT ON COLUMN public.colaborador.nome IS 'Nome do colaborador';
COMMENT ON COLUMN public.colaborador.email IS 'Email da empresa.';
COMMENT ON COLUMN public.colaborador.rg IS 'RG do colaborador';
COMMENT ON COLUMN public.colaborador.data_nascimento IS 'Data de Nascimento.';
COMMENT ON COLUMN public.colaborador.telefone_fixo IS 'telefone Fixo.';
COMMENT ON COLUMN public.colaborador.telefone_celular IS 'Telefone Celular.';
COMMENT ON COLUMN public.colaborador.estado_civil IS 'Estado Civil, apenas PF.';
COMMENT ON COLUMN public.colaborador.cep IS 'Cep de indetificação do Logradouro. Informação informada no sistema e processada por webservice de cep para validação.';
COMMENT ON COLUMN public.colaborador.logradouro IS 'Nome da Rua, avenida, etc. da empresa. Informação coletada pela Busca de CEP.';
COMMENT ON COLUMN public.colaborador.numero IS 'Numero do endereço da empresa, caso seja sem número(S/N) deixar em branco ou zero.';
COMMENT ON COLUMN public.colaborador.bairro IS 'Bairro do endereço de localização.';
COMMENT ON COLUMN public.colaborador.municipio_id IS 'Identificação da Cidade, Código do IBGE.';
COMMENT ON COLUMN public.colaborador.complemento IS 'Informação complementar do endereço, apto, sala, ponto de referência.';
COMMENT ON COLUMN public.colaborador.ativo IS 'Status do Colaborador - T ou TRUE para ativo e F ou FALSE para inativo.';
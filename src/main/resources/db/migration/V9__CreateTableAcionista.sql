CREATE TABLE public.acionista (
       id serial,
       conta INTEGER NOT NULL,
       nome VARCHAR(128) NOT NULL,
       cpf_cnpj VARCHAR(14) NOT NULL,
       dt_nascimento DATE,
       rg_inscricao VARCHAR(16),
       estado_civil VARCHAR(16),
       cep VARCHAR(8),
       logradouro VARCHAR(128) NOT NULL,
       numero INTEGER,
       bairro VARCHAR(64) NOT NULL,
       municipio_id INTEGER,
       complemento VARCHAR(128),
       telefone_fixo VARCHAR(11),
       telefone_celular VARCHAR(11),
       email VARCHAR(64),
       representante VARCHAR(128),
       cpf_representante VARCHAR(11),
       banco INTEGER,
       agencia VARCHAR(8),
       numero_conta VARCHAR(16),
       cpf_conta_banco VARCHAR(11),
       nome_conta_banco VARCHAR(128),
       ativo BOOLEAN NOT NULL,
       created_at timestamp without time zone NOT NULL,
       updated_at timestamp without time zone NOT NULL,
       CONSTRAINT acionista_pkey PRIMARY KEY (id),
       CONSTRAINT fk_acionista_municipio_id FOREIGN KEY (municipio_id)
           REFERENCES public.municipio(id) MATCH SIMPLE
           ON UPDATE NO ACTION
           ON DELETE NO ACTION
);


CREATE UNIQUE INDEX idx_acionista_cpf_cnpj
    ON public.acionista USING btree
        (cpf_cnpj COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;

-- CREATE UNIQUE INDEX idx_acionista_conta
--     ON public.acionista USING btree
--         (conta COLLATE pg_catalog."default" ASC NULLS LAST)
--     TABLESPACE pg_default;

COMMENT ON TABLE public.acionista IS 'Tabela de Manutenção de Acionista';
COMMENT ON COLUMN public.acionista.id IS 'Identificação do acionista.';
COMMENT ON COLUMN public.acionista.conta IS 'Conta do Acionista.';
COMMENT ON COLUMN public.acionista.nome IS 'Nome do acionista PF ou PJ.';
COMMENT ON COLUMN public.acionista.cpf_cnpj IS 'CPF do acionista ou CNPJ da Empresa.';
COMMENT ON COLUMN public.acionista.dt_nascimento IS 'Data de Nascimento.';
COMMENT ON COLUMN public.acionista.rg_inscricao IS 'RG para PF ou inscrição municipal ou estadual para PJ.';
COMMENT ON COLUMN public.acionista.estado_civil IS 'Estado Civil, apenas PF.';
COMMENT ON COLUMN public.acionista.cep IS 'Cep de indetificação do Logradouro. Informação informada no sistema e processada por webservice de cep para validação.';
COMMENT ON COLUMN public.acionista.logradouro IS 'Nome da Rua, avenida, etc. da empresa. Informação coletada pela Busca de CEP.';
COMMENT ON COLUMN public.acionista.numero IS 'Numero do endereço da empresa, caso seja sem número(S/N) deixar em branco ou zero.';
COMMENT ON COLUMN public.acionista.bairro IS 'Bairro do endereço de localização.';
COMMENT ON COLUMN public.acionista.municipio_id IS 'Identificação da Cidade, Código do IBGE.';
COMMENT ON COLUMN public.acionista.complemento IS 'Informação complementar do endereço, apto, sala, ponto de referência.';
COMMENT ON COLUMN public.acionista.telefone_fixo IS 'telefone Fixo.';
COMMENT ON COLUMN public.acionista.telefone_celular IS 'Telefone Celular.';
COMMENT ON COLUMN public.acionista.email IS 'Email da empresa.';
COMMENT ON COLUMN public.acionista.representante IS 'Representante Legal em caso de PJ.';
COMMENT ON COLUMN public.acionista.cpf_representante IS 'CPF do Representante Legal, apenas PJ.';
COMMENT ON COLUMN public.acionista.banco IS 'Código do Banco do acionista.';
COMMENT ON COLUMN public.acionista.agencia IS 'número da agência bancária.';
COMMENT ON COLUMN public.acionista.numero_conta IS 'Número da conta bancária do acionista.';
COMMENT ON COLUMN public.acionista.cpf_conta_banco IS 'Informação do CPF em caso e a conta não ser do Titular / Acionista.';
COMMENT ON COLUMN public.acionista.nome_conta_banco IS 'Nome do Titular da conta bancária caso a conta não seja do acionista.';
COMMENT ON COLUMN public.acionista.ativo IS 'Status do Acionista - T ou TRUE para ativo e F ou FALSE para inativo.';
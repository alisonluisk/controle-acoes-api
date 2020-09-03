
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
    qtd_acoes bigint,
    cotas_on bigint,
    cotas_pn bigint,
    matriz_id bigint,
    ativo boolean NOT NULL DEFAULT True,
    CONSTRAINT empresa_pkey PRIMARY KEY (id),
    CONSTRAINT fk_empresa_municipio_id FOREIGN KEY (municipio_id)
        REFERENCES public.municipio(id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_empresa_empresa_id FOREIGN KEY (matriz_id)
        REFERENCES public.empresa(id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE UNIQUE INDEX idx_empresa_cnpj
    ON public.empresa USING btree
        (cnpj COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;

COMMENT ON TABLE public.empresa IS 'Tabela de manutenção de Empresas, holdings, Matrizes e filiais.';
COMMENT ON COLUMN public.empresa.id IS 'Identificação da Empresa.';
COMMENT ON COLUMN public.empresa.cnpj IS 'Número de CNPJ da Empresa cadastrada.';
COMMENT ON COLUMN public.empresa.razao_social IS 'Nome Empresarial da Empresa - Razão Social.';
COMMENT ON COLUMN public.empresa.nome_fantasia IS 'Nome Fantasia da Empresa.';
COMMENT ON COLUMN public.empresa.data_abertura IS 'Data de Abertura da Empresa, informação do Cartão CNPJ.';
COMMENT ON COLUMN public.empresa.cep IS 'Cep de indetificação do Logradouro. Informação informada no sistema e processada por webservice de cep para validação.';
COMMENT ON COLUMN public.empresa.logradouro IS 'Nome da Rua, avenida, etc. da empresa. Informação coletada pela Busca de CEP.';
COMMENT ON COLUMN public.empresa.numero IS 'Numero do endereço da empresa, caso seja sem número(S/N) deixar em branco ou zero.';
COMMENT ON COLUMN public.empresa.complemento IS 'Informação complementar do endereço, apto, sala, ponto de referência.';
COMMENT ON COLUMN public.empresa.bairro IS 'Bairro do endereço de localização.';
COMMENT ON COLUMN public.empresa.municipio_id IS 'Identificação da Cidade, Código do IBGE.';
COMMENT ON COLUMN public.empresa.email IS 'Email da empresa.';
COMMENT ON COLUMN public.empresa.telefone IS 'Número do Telefone da Empresa, podendo ser celular. Padrão 45 999999999.';
COMMENT ON COLUMN public.empresa.tipo_empresa IS 'Definição do tipo de empresa:
Holding
Matriz
Filial';
COMMENT ON COLUMN public.empresa.matriz_id IS 'Identificação da Matriz desta Empresa. Se houver.';
COMMENT ON COLUMN public.empresa.qtd_acoes IS 'Quantidade de Ações que esta empresa terá para divisão. Padrão para Matriz e Filiais 5.050.000';
COMMENT ON COLUMN public.empresa.cotas_on IS 'Percentual de cotas ON que ficarão com a empresa.';
COMMENT ON COLUMN public.empresa.cotas_pn IS 'Percentual de Cotas PN que ficarão com a Empresa.';
COMMENT ON COLUMN public.empresa.ativo IS 'Status da Empresa - T ou TRUE para ativo e F ou FALSE para inativo.';
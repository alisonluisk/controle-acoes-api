CREATE TABLE public.conta_investimento (
                id serial,
                conta INTEGER NOT NULL,
                empresa_id INTEGER,
                acionista_id INTEGER,
                tipo_contrato VARCHAR(32) NOT NULL,
                integralizacao VARCHAR(32) NOT NULL,
                qtd_lotes integer NOT NULL,
                qtd_acoes integer NOT NULL,
                valor_acao NUMERIC(10,2) NOT NULL,
                aporte_total NUMERIC(10,2) NOT NULL,
                parcelas INTEGER,
                aporte_mensal NUMERIC(10,2),
                valor_adesao NUMERIC(10,2),
                parcela_adesao INTEGER,
                valor_parcela_adesao NUMERIC(10,2),
                possui_linha_credito BOOLEAN,
                observacoes varchar,
                created_at timestamp without time zone NOT NULL,
                updated_at timestamp without time zone NOT NULL,
                CONSTRAINT conta_investimento_pkey PRIMARY KEY (id)
);
COMMENT ON TABLE public.conta_investimento IS 'Tabela de Manutenção da Conta de Investimento.';
COMMENT ON COLUMN public.conta_investimento.id IS 'Identificação do Conta de Investimento';
COMMENT ON COLUMN public.conta_investimento.conta IS 'Número da conta de investimento.';
COMMENT ON COLUMN public.conta_investimento.acionista_id IS 'Identificação do acionista';
COMMENT ON COLUMN public.conta_investimento.empresa_id IS 'Identificação da Empresa.';
COMMENT ON COLUMN public.conta_investimento.tipo_contrato IS 'Tipo de Contrato: Flex, Prime, Lis Money.';
COMMENT ON COLUMN public.conta_investimento.integralizacao IS 'Forma de integralização do valor:
AVISTA - A Vista
AINTEGRALIZAR - A Integralizar';
COMMENT ON COLUMN public.conta_investimento.qtd_lotes IS 'Qtd. de lotes da conta de investimento.';
COMMENT ON COLUMN public.conta_investimento.qtd_acoes IS 'Qtd. de ações da conta de investimento.';
COMMENT ON COLUMN public.conta_investimento.valor_acao IS 'Valor da ação da conta de investimento.';
COMMENT ON COLUMN public.conta_investimento.aporte_total IS 'Valor de aplicação total.';
COMMENT ON COLUMN public.conta_investimento.parcelas IS 'Total de Parcelas.';
COMMENT ON COLUMN public.conta_investimento.aporte_mensal IS 'valor de aplicação mensal em caso de mais de 1 parcela.';
COMMENT ON COLUMN public.conta_investimento.valor_adesao IS 'Valor da adesão.';
COMMENT ON COLUMN public.conta_investimento.parcela_adesao IS 'Quantidade de Parcelas da Adesão.';
COMMENT ON COLUMN public.conta_investimento.possui_linha_credito IS 'Identificação se conta possui linha de credito.';
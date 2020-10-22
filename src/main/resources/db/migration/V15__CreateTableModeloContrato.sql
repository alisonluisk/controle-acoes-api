CREATE TABLE public.modelo_contrato (
                id serial,
                codigo_modelo INTEGER NOT NULL,
                versao NUMERIC(3,1) NOT NULL,
                tipo_contrato VARCHAR(16) NOT NULL,
                forma_pagamento VARCHAR(32) NOT NULL,
                nome_modelo VARCHAR(128) NOT NULL,
                modelo TEXT NOT NULL,
                ativo BOOLEAN,
                created_at timestamp without time zone NOT NULL,
                updated_at timestamp without time zone NOT NULL,
                CONSTRAINT modelo_contrato_pkey PRIMARY KEY (id)
);
COMMENT ON COLUMN public.modelo_contrato.id IS 'Identificação do contrato para o sistema.';
COMMENT ON COLUMN public.modelo_contrato.versao IS 'Controle da versão do modelo de contrato.';
COMMENT ON COLUMN public.modelo_contrato.tipo_contrato IS 'Tipo de Contrato: Flex, Prime, Lis Money.';
COMMENT ON COLUMN public.modelo_contrato.forma_pagamento IS 'Forma de Pagamento: a Vista, a Integralizar.';
COMMENT ON COLUMN public.modelo_contrato.nome_modelo IS 'Nome do Modelo de Contrato.';
COMMENT ON COLUMN public.modelo_contrato.modelo IS 'Descricão do contrato, cláusulas, variáveis e identificações. Formato JSON.';
COMMENT ON COLUMN public.modelo_contrato.ativo IS 'Status do Modelo - T ou TRUE para ativo e F ou FALSE para inativo.';
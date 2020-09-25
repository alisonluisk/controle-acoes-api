CREATE TABLE public.usuarios (
         id serial,
         usuario VARCHAR(24) NOT NULL,
         acionista_id INTEGER,
         colaborador_id INTEGER,
         perfil_usuario_id INTEGER NOT NULL,
         ativo BOOLEAN NOT NULL,
         created_at timestamp without time zone NOT NULL,
         updated_at timestamp without time zone NOT NULL,
         CONSTRAINT usuario_pkey PRIMARY KEY (id)
);
COMMENT ON TABLE public.usuarios IS 'Tabela para manutenção de controle de usuários do sistema (administrativos, vendedores, gerentes, CEO)';
COMMENT ON COLUMN public.usuarios.id IS 'Identificação do usuário no sistema.';
COMMENT ON COLUMN public.usuarios.usuario IS 'Usuário do sistema.';
COMMENT ON COLUMN public.usuarios.acionista_id IS 'Identificação do acionista.';
COMMENT ON COLUMN public.usuarios.colaborador_id IS 'Identificação do colaborador no sistema.';
COMMENT ON COLUMN public.usuarios.perfil_usuario_id IS 'Identificação do Perfil de Usuário.';
COMMENT ON COLUMN public.usuarios.ativo IS 'Status do usuário - T ou TRUE para ativo e F ou FALSE para inativo.';
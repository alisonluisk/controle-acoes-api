CREATE TABLE public.perfil_usuario
(
    id        serial,
    nome      VARCHAR(64) NOT NULL,
    descricao VARCHAR(500),
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    CONSTRAINT perfil_usuario_pkey PRIMARY KEY (id)
);
COMMENT ON TABLE public.perfil_usuario IS 'Tabela de Manutenção de Perfis de Usuários.';
COMMENT ON COLUMN public.perfil_usuario.id IS 'Identificação do Perfil de Usuário.';
COMMENT ON COLUMN public.perfil_usuario.nome IS 'Nome do Perfil';
COMMENT ON COLUMN public.perfil_usuario.descricao IS 'Descrição do Perfil - Apontar o que pode ser acessado. Resumo.';

insert into perfil_usuario (nome, descricao, created_at, updated_at) values('ADMINISTRADOR', 'Administrador', current_timestamp, current_timestamp);
insert into perfil_usuario (nome, descricao, created_at, updated_at) values('ACIONISTA', 'Acionista', current_timestamp, current_timestamp);


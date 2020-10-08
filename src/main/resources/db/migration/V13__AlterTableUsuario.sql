ALTER TABLE public.usuario
    ADD COLUMN senha character varying;
ALTER TABLE public.usuario
    ADD COLUMN senha_configurada boolean default false;
COMMENT ON COLUMN public.usuario.senha IS 'Senha de acesso criptografada.';
COMMENT ON COLUMN public.usuario.senha_configurada IS 'Senha de acesso foi configurada pelo usuário.';

INSERT INTO public.colaborador (cpf, nome, email, estado_civil, cep, logradouro, numero, bairro, municipio_id, ativo,
                                created_at, updated_at) VALUES ('97556615065', 'Administrador', 'admin@ibolsa.com.br',
                                'SOLTEIRO', '85900005', 'Rua Barão do Rio Branco', 100, 'Centro', 4127700, true,
                                current_date , current_date);


INSERT INTO public.usuario (usuario, colaborador_id, perfil_usuario_id, ativo, created_at, updated_at,
                            senha, senha_configurada) VALUES ('administrador', (select id from colaborador where cpf = '97556615065'),
                            (select id from perfil_usuario where nome = 'Administrador'), true, current_date , current_date ,
                            '$2a$10$WWvbXZLaQa9ylvJxefQkFuH0A5yqJ9lka0U9mJcqyPmOm4fvKI1rK', true);
-- INSERT INTO public.usuario (id, usuario, acionista_id, colaborador_id, perfil_usuario_id, ativo, created_at, updated_at, senha, senha_configurada) VALUES (4, 'fabiana', NULL, 2, NULL, false, '2020-10-06 21:11:13.293', '2020-10-06 21:11:54.45', '$2a$10$ymWYMv8izv6qCnOnHIQ54O1lrPmOABD.N7/jZJ0w1moGB/SnqiJRi', true);

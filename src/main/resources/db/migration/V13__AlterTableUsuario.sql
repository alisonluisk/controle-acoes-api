ALTER TABLE public.usuario
    ADD COLUMN senha character varying;
ALTER TABLE public.usuario
    ADD COLUMN senha_configurada boolean default false;
COMMENT ON COLUMN public.usuario.senha IS 'Senha de acesso criptografada.';
COMMENT ON COLUMN public.usuario.senha_configurada IS 'Senha de acesso foi configurada pelo usuário.';

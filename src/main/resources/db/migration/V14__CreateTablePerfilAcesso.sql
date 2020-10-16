CREATE TABLE public.perfil_usuario_acessos
(
    perfil_usuario_id bigint NOT NULL,
    acessos character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT fk_perfil_acessos_perfil_id FOREIGN KEY (perfil_usuario_id)
        REFERENCES public.perfil_usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
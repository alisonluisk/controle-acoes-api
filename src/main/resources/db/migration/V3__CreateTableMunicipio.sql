
CREATE TABLE public.municipio
(
    id integer NOT NULL,
    nome character varying(50) NOT NULL,
    estado_id integer NOT NULL,
    CONSTRAINT municipio_pkey PRIMARY KEY (id),
    CONSTRAINT fk_municipio_estado_id FOREIGN KEY (estado_id)
        REFERENCES public.estado (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
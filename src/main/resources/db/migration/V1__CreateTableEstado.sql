
CREATE TABLE public.estado
(
    id integer NOT NULL,
    nome character varying(50) NOT NULL,
    sigla character varying(3) NOT NULL,
    CONSTRAINT estado_pkey PRIMARY KEY (id)
)
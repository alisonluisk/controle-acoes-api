ALTER TABLE public.empresa ADD COLUMN codigo_farmacia bigint;

COMMENT ON COLUMN public.empresa.codigo_farmacia IS 'Código do cadastro da empresa no sistema da farmacia.';
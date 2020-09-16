ALTER TABLE public.empresa ADD COLUMN status_acoes varchar(60);

UPDATE public.empresa set status_acoes = 'AGUARDANDO' where tipo_empresa <> 'HOLDING';
UPDATE public.empresa set status_acoes = 'SEM_ACOES' where tipo_empresa = 'HOLDING';
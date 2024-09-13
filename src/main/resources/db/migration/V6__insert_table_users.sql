
-- #############################################
-- Inserir dados na tabela Users
-- #############################################
-- TRUNCATE TABLE public.users CONTINUE IDENTITY RESTRICT;

DELETE FROM public.users
WHERE id IN ('ccd5c63e-2c75-41c2-9c21-50a2ef84d5ce','ead18951-37ee-4779-8f1c-b916b510633d')
OR email IN ('user_admim@gmail.com','user_comun@gmail.com');

INSERT INTO public.users
(id, email, last_name, "name", "password", rules, status, created, modified)
values
('ccd5c63e-2c75-41c2-9c21-50a2ef84d5ce', 'user_admim@gmail.com',  'Ultimo Acesso Admin', 'Fulano Admin', '$2a$10$xRvf58hqAL2G4xXD2sZsWOw7zwHA.yPY2HUbcVj7/etBnQml3Ofum', '{ADMIN,USER}', 'ACTIVE', now(), now()),
('ead18951-37ee-4779-8f1c-b916b510633d', 'user_comun@gmail.com',  'Último Nome User Comum', 'Ciclano User', '$2a$10$xRvf58hqAL2G4xXD2sZsWOw7zwHA.yPY2HUbcVj7/etBnQml3Ofum', '{USER}', 'ACTIVE', now(), now());
-- #############################################
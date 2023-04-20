--liquibase formatted sql
--changeset <olehkvasha>:<create-telegram-user-sequence-id>
CREATE SEQUENCE IF NOT EXISTS public.telegram_user_id_seq INCREMENT 1 START 1 MINVALUE 1;

--rollback DROP SEQUENCE public.telegram_user_id_seq;

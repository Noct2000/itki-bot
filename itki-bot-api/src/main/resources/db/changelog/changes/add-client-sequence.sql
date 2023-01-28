--liquibase formatted sql
--changeset <olehkvasha>:<create-client-sequence-id>
CREATE SEQUENCE IF NOT EXISTS public.client_id_seq INCREMENT 1 START 2 MINVALUE 1;

--rollback DROP SEQUENCE public.chat_status_id_seq;

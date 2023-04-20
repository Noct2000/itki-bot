--liquibase formatted sql
--changeset <olehkvasha>:<create-group-sequence-id>
CREATE SEQUENCE IF NOT EXISTS public.group_id_seq INCREMENT 1 START 1 MINVALUE 1;

--rollback DROP SEQUENCE public.group_id_seq;

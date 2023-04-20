--liquibase formatted sql
--changeset <olehkvasha>:<create-curator-sequence-id>
CREATE SEQUENCE IF NOT EXISTS public.curator_id_seq INCREMENT 1 START 1 MINVALUE 1;

--rollback DROP SEQUENCE public.curator_id_seq;

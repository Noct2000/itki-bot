--liquibase formatted sql
--changeset <olehkvasha>:<create-answer-sequence-id>
CREATE SEQUENCE IF NOT EXISTS public.answer_id_seq INCREMENT 1 START 1 MINVALUE 1;

--rollback DROP SEQUENCE public.answer_id_seq;

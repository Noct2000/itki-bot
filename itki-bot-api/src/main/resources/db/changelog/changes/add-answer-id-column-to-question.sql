--liquibase formatted sql
--changeset <olehkvasha>:<add-answer-id-column-to-question>
ALTER TABLE question
    ADD COLUMN IF NOT EXISTS answer_id bigint;

--rollback ALTER TABLE question DROP COLUMN IF EXISTS answer_id;

--liquibase formatted sql
--changeset <olehkvasha>:<create-answer-table>
CREATE TABLE IF NOT EXISTS public.answer
(
    id bigint NOT NULL,
    text varchar(500),
    CONSTRAINT answer_pk PRIMARY KEY (id)
);

--rollback DROP TABLE IF EXISTS answer;

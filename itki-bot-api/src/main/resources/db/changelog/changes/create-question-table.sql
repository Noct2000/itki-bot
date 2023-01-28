--liquibase formatted sql
--changeset <olehkvasha>:<create-question-table>
CREATE TABLE IF NOT EXISTS public.question
(
    id bigint NOT NULL,
    text varchar(500),
    CONSTRAINT question_pk PRIMARY KEY (id)
);

--rollback DROP TABLE IF EXISTS question;

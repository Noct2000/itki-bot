--liquibase formatted sql
--changeset <olehkvasha>:<create-curator-table>
CREATE TABLE IF NOT EXISTS public.curator
(
    id bigint NOT NULL,
    name varchar(30),
    last_name varchar(30),
    additional_name varchar(30),
    CONSTRAINT curator_pk PRIMARY KEY (id)
);

--rollback DROP TABLE IF EXISTS curator;

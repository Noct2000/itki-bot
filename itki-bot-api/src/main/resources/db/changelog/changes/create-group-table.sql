--liquibase formatted sql
--changeset <olehkvasha>:<create-group-table>
CREATE TABLE IF NOT EXISTS public.group
(
    id bigint NOT NULL,
    name varchar(30),
    CONSTRAINT group_pk PRIMARY KEY (id)
);

--rollback DROP TABLE IF EXISTS group;

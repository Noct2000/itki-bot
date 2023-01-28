--liquibase formatted sql
--changeset <olehkvasha>:<create-role-table>
CREATE TABLE IF NOT EXISTS public.role
(
    id bigint NOT NULL,
    "name" character varying(256) UNIQUE NOT NULL,
    CONSTRAINT role_pk PRIMARY KEY (id)
    );

INSERT INTO role (id, "name") VALUES (1, 'ADMIN');
INSERT INTO role (id, "name") VALUES (2, 'USER');

--rollback DROP TABLE role;

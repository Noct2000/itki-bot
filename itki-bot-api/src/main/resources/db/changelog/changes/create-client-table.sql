--liquibase formatted sql
--changeset <olehkvasha>:<create-client-table>
CREATE TABLE IF NOT EXISTS public.client
(
    id bigint NOT NULL,
    login character varying(256) NOT NULL,
    password character varying(256) NOT NULL,
    CONSTRAINT operator_pk PRIMARY KEY (id)
    );

--rollback DROP TABLE client;

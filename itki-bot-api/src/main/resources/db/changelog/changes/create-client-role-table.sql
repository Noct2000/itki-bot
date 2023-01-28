--liquibase formatted sql
--changeset <olehkvasha>:<create-client-role-table>
CREATE TABLE IF NOT EXISTS public.client_role
(
    client_id bigint NOT NULL,
    role_id bigint NOT NULL,
    CONSTRAINT client_id_fk FOREIGN KEY (client_id)
    REFERENCES public.client (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT role_id_fk FOREIGN KEY (role_id)
    REFERENCES public.role (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    );

--rollback DROP TABLE client_role;

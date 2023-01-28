--liquibase formatted sql
--changeset <olehkvasha>:<add-first-client-admin-row-to-client-table>
INSERT INTO client (id, login, password)
VALUES (1, 'admin', '$2a$10$ko34ilHo2WcRR/QCZ1z4l.rwfezPU8nZOc7HgSv2VU7TEZfpPznSC');

INSERT INTO client_role (client_id, role_id)
VALUES (1, 1);

--rollback DELETE FROM client_role WHERE client_id = 1;
--rollback DELETE FROM client WHERE client_id = 1;


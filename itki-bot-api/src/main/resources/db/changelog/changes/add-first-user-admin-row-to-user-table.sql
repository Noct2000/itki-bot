--liquibase formatted sql
--changeset <olehkvasha>:<add-first-user-admin-row-to-user-table>
INSERT INTO public.user (id, login, password)
VALUES (1, 'admin', '$2a$10$ko34ilHo2WcRR/QCZ1z4l.rwfezPU8nZOc7HgSv2VU7TEZfpPznSC');

INSERT INTO user_role (user_id, role_id)
VALUES (1, 1);

--rollback DELETE FROM user_role WHERE user_id = 1;
--rollback DELETE FROM user WHERE user_id = 1;


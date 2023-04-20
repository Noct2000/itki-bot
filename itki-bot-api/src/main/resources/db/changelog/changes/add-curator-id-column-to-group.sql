--liquibase formatted sql
--changeset <olehkvasha>:<add-curator-id-column-to-group>
ALTER TABLE public.group
    ADD COLUMN IF NOT EXISTS curator_id bigint;

--rollback ALTER TABLE group DROP COLUMN IF EXISTS curator_id;

--liquibase formatted sql
--changeset <olehkvasha>:<add-group-curator-constraint>
ALTER TABLE public.group
    ADD CONSTRAINT fk_group_curator FOREIGN KEY (curator_id) REFERENCES public.curator (id);

--rollback ALTER TABLE group DROP CONSTRAINT IF EXISTS fk_group_curator;

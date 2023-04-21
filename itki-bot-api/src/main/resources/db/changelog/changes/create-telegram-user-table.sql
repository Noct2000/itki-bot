--liquibase formatted sql
--changeset <olehkvasha>:<create-telegram-user-table>
CREATE TABLE IF NOT EXISTS public.telegram_user
(
    id bigint NOT NULL,
    username varchar(30),
    first_name varchar(100),
    last_name varchar(100),
    external_chat_id varchar(255) NOT NULL UNIQUE,
    CONSTRAINT telegram_user_pk PRIMARY KEY (id)
);

--rollback DROP TABLE IF EXISTS telegram_user;

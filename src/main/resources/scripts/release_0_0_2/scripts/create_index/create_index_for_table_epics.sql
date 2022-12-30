--liquibase formatted sql

--changeset ovchinnikov-va:create_index_for_table_epics rollbackSplitStatements:true
--comment: Создание индекса для таблицы эпиков
CREATE INDEX epics_name_idx ON epics(name);


--rollback DROP INDEX epics_name_idx;
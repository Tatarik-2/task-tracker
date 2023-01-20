--liquibase formatted sql

--changeset zolotov-vu:create_index_for_table_bugs rollbackSplitStatements:true
--comment: Создание индекса для таблицы багов
CREATE INDEX bugs_name_idx ON bugs (name);


--rollback DROP INDEX bugs_name_idx;
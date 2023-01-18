--liquibase formatted sql

--changeset zolotov-vu:create_index_for_table_bugs rollbackSplitStatements:true
--comment: Создание индексов для таблицы багов
CREATE INDEX bugs_name_idx ON bugs (name);
CREATE INDEX bugs_assigneeId_idx ON bugs (assignee_id);


--rollback DROP INDEX bugs_name_idx;
--liquibase formatted sql

--changeset zolotov-vu:create_index_assigneeId_for_table_bugs rollbackSplitStatements:true
--comment: Создание индекса для таблицы багов
CREATE INDEX bugs_assigneeId_idx ON bugs (assignee_id);


--rollback DROP INDEX bugs_assigneeId_idx;
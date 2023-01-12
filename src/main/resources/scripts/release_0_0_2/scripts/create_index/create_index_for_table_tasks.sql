--liquibase formatted sql

--changeset makarov_ra:create_index_for_table_tasks rollbackSplitStatements:true
--comment: Создание индекса для таблицы задач
CREATE INDEX tasks_name_idx
    ON tasks(name);


--rollback DROP INDEX tasks_name_idx;
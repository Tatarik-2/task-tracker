--liquibase formatted sql

--changeset makarov-ra:create_table_tasks rollbackSplitStatements:true
--comment: Создание таблицы задач
CREATE TABLE TASKS
(
    ID   VARCHAR(36) PRIMARY KEY,
    NAME VARCHAR(128),
    "desc" VARCHAR(256),
    status VARCHAR(18),
    priority VARCHAR(18),
    history_id VARCHAR(36),
    author_id VARCHAR(36),
    assignee_id VARCHAR(36),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

COMMENT ON TABLE TASKS IS 'Задачи';
COMMENT ON COLUMN TASKS.ID IS 'Идентификатор';
COMMENT ON COLUMN TASKS.NAME IS 'Наименование';
COMMENT ON COLUMN TASKS.DESC IS 'Описание';
COMMENT ON COLUMN TASKS.STATUS IS 'Статус';
COMMENT ON COLUMN TASKS.PRIORITY IS 'Приоритет';
COMMENT ON COLUMN TASKS.HISTORY_ID IS 'Идентификатор истории';
COMMENT ON COLUMN TASKS.AUTHOR_ID IS 'Идентификатор автора';
COMMENT ON COLUMN TASKS.ASSIGNEE_ID IS 'Идентификатор исполнителя';
COMMENT ON COLUMN TASKS.CREATED_AT IS 'Дата создания записи';
COMMENT ON COLUMN TASKS.UPDATED_AT IS 'Дата обновления записи';

--rollback DROP TABLE TASKS;
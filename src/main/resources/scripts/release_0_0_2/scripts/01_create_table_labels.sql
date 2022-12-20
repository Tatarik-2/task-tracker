--liquibase formatted sql

--changeset zalyalov-na:create_table_workloads rollbackSplitStatements:true
--comment: Создание таблицы рабочих пространств
CREATE TABLE LABELS
(
    ID         VARCHAR(36) PRIMARY KEY,
    TEXT   VARCHAR(64) NOT NULL,
    AUTHOR_ID  VARCHAR(36) NOT NULL,
    TASK_ID VARCHAR(36) NOT NULL,
    CREATED_AT TIMESTAMP NOT NULL,
    UPDATED_AT TIMESTAMP NOT NULL
);

COMMENT
ON TABLE LABELS IS 'Метки задачи';
COMMENT
ON COLUMN LABELS.ID IS 'Идентификатор';
COMMENT
ON COLUMN LABELS.TEXT 'Текст метки';
COMMENT
ON COLUMN LABELS.TASK_ID IS 'Идентификатор задачи';
COMMENT
ON COLUMN WORKLOADS.AUTHOR_ID IS 'Идентификатор автора';
COMMENT
ON COLUMN WORKLOADS.CREATED_AT IS 'Дата создания записи';
COMMENT
ON COLUMN WORKLOADS.UPDATED_AT IS 'Дата обновления записи';


-- rollback DROP TABLE WORKLOADS;
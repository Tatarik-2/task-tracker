--liquibase formatted sql

--changeset zalyalov-na:create_table_scheduler_info rollbackSplitStatements:true
--comment: Создание таблицы scheduler_info
CREATE TABLE SCHEDULER_INFO
(
    ID                VARCHAR(36) PRIMARY KEY,
    LAST_TRIGGER_TIME TIMESTAMP NOT NULL
);

COMMENT
ON TABLE SCHEDULER_INFO IS 'schedulerinfo';
COMMENT
ON COLUMN SCHEDULER_INFO.ID IS 'Идентификатор';
COMMENT
ON COLUMN SCHEDULER_INFO.LAST_TRIGGER_TIME IS 'Дата обновления записи';


-- rollback DROP TABLE SCHEDULER_INFO;



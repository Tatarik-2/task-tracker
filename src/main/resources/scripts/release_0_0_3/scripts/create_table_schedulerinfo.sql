--liquibase formatted sql

--changeset zalyalov-na:create_table_schedulerinfo rollbackSplitStatements:true
--comment: Создание таблицы schedulerinfo
CREATE TABLE SCHEDULERINFO
(
    ID                VARCHAR(36) PRIMARY KEY,
    LAST_TRIGGER_TIME TIMESTAMP NOT NULL
);

COMMENT
ON TABLE SCHEDULERINFO IS 'schedulerinfo';
COMMENT
ON COLUMN SCHEDULERINFO.ID IS 'Идентификатор';
COMMENT
ON COLUMN SCHEDULERINFO.LAST_TRIGGER_TIME IS 'Дата обновления записи';


-- rollback DROP TABLE SCHEDULERINFO;



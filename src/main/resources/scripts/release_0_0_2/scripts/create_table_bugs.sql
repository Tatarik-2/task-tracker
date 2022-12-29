--liquibase formatted sql

--changeset zolotov-vu:create_table_bugs rollbackSplitStatements:true
--comment: Создание таблицы багов
CREATE TABLE BUGS
(
    ID          VARCHAR(36) PRIMARY KEY,
    NAME        VARCHAR(128) NOT NULL,
    DESCRIPTION VARCHAR(256) NOT NULL,
    STATUS      VARCHAR(18)  NOT NULL,
    PRIORITY    VARCHAR(18)  NOT NULL,
    HISTORY_ID  VARCHAR(36)  NOT NULL,
    AUTHOR_ID   VARCHAR(36)  NOT NULL,
    ASSIGNEE_ID VARCHAR(36)  NOT NULL,
    SPRINT_ID   VARCHAR(36),
    CREATED_AT  TIMESTAMP,
    UPDATED_AT  TIMESTAMP
);

COMMENT
ON TABLE BUGS IS 'Баги';
COMMENT
ON COLUMN BUGS.ID IS 'Идентификатор';
COMMENT
ON COLUMN BUGS.NAME IS 'Наименование';
COMMENT
ON COLUMN BUGS.DESCRIPTION IS 'Описание';
COMMENT
ON COLUMN BUGS.STATUS IS 'Статус';
COMMENT
ON COLUMN BUGS.PRIORITY IS 'Приоритет';
COMMENT
ON COLUMN BUGS.HISTORY_ID IS 'Идентификатор истории';
COMMENT
ON COLUMN BUGS.AUTHOR_ID IS 'Идентификатор автора';
COMMENT
ON COLUMN BUGS.ASSIGNEE_ID IS 'Идентификатор исполнителя';
COMMENT
ON COLUMN BUGS.SPRINT_ID IS 'Идентификатор спринта';
COMMENT
ON COLUMN BUGS.CREATED_AT IS 'Дата создания записа';
COMMENT
ON COLUMN BUGS.UPDATED_AT IS 'Дата обновления записи';

--rollback DROP TABLE BUGS;

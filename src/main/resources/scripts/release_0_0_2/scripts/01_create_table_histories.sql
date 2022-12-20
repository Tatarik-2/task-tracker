--liquibase formatted sql

--changeset kichenko-av:create_table_deliveries rollbackSplitStatements:true
--comment: Создание таблицы историй
CREATE TABLE HISTORIES
(
    ID   VARCHAR(36) PRIMARY KEY,
    NAME VARCHAR(128),
    DESCRIPTION VARCHAR(256), --в ТЗ просят "desc" - это имя зарезервировано в SQL изменил на "Description"
    STATUS VARCHAR(18),
    PRIORITY VARCHAR(18),
    EPIC_ID VARCHAR(36),
    AUTHOR_ID VARCHAR(36),
    SPRINT_ID VARCHAR(36),
    CREATED_AT TIMESTAMP,
    UPDATED_AT TIMESTAMP
    --FOREIGN KEY (EPIC_ID) REFERENCES EPICS (ID) --TODO уточнить название таблицы EPICS
);

COMMENT ON TABLE HISTORIES IS 'Истории';
COMMENT ON COLUMN HISTORIES.ID IS 'Идентификатор';
COMMENT ON COLUMN HISTORIES.NAME IS 'Наименование';
COMMENT ON COLUMN HISTORIES.DESCRIPTION IS 'Описание';
COMMENT ON COLUMN HISTORIES.STATUS IS 'Статус';
COMMENT ON COLUMN HISTORIES.PRIORITY IS 'Приоритет';
COMMENT ON COLUMN HISTORIES.EPIC_ID IS 'Идентификатор эпика';
COMMENT ON COLUMN HISTORIES.AUTHOR_ID IS 'Идентификатор автора';
COMMENT ON COLUMN HISTORIES.SPRINT_ID IS 'Идентификатор спринта';
COMMENT ON COLUMN HISTORIES.CREATED_AT IS 'Дата создания записи';
COMMENT ON COLUMN HISTORIES.UPDATED_AT IS 'Дата обновления записи';


--rollback DROP TABLE HISTORIES;
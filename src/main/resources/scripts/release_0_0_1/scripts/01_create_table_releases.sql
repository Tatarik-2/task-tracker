--liquibase formatted sql

--changeset kalyashov-ga:create_table_releases rollbackSplitStatements:true
--comment: Создание таблицы релизов
CREATE TABLE RELEASES
(
    ID   VARCHAR(36) PRIMARY KEY,
    NAME VARCHAR(128)
);

COMMENT ON TABLE RELEASES IS 'Релизы';
COMMENT ON COLUMN RELEASES.ID IS 'Идентификатор релиза';
COMMENT ON COLUMN RELEASES.NAME IS 'Наименование релиза';

--rollback DROP TABLE RELEASES;
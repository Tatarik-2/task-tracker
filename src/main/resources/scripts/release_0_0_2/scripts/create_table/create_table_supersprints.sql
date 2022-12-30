--liquibase formatted sql

--changeset zalyalov-na:create_table_supersprints rollbackSplitStatements:true
--comment: Создание таблицы суперспринтов
CREATE TABLE SUPERSPRINTS
(
    ID         VARCHAR(36) PRIMARY KEY,
    NAME       VARCHAR(128) NOT NULL,
    START_AT TIMESTAMP NOT NULL,
    END_AT TIMESTAMP NOT NULL,
    AUTHOR_ID  VARCHAR(36) NOT NULL,
    CREATED_AT TIMESTAMP NOT NULL,
    UPDATED_AT TIMESTAMP NOT NULL
);

COMMENT
ON TABLE WORKLOADS IS 'Суперспринты';
COMMENT
ON COLUMN SUPERSPRINTS.ID IS 'Идентификатор';
COMMENT
ON COLUMN SUPERSPRINTS.NAME IS 'Наименование';
COMMENT
ON COLUMN SUPERSPRINTS.START_AT IS 'Дата старта суперспринта';
COMMENT
ON COLUMN SUPERSPRINTS.END_AT IS 'Дата конца суперспринта';
COMMENT
ON COLUMN SUPERSPRINTS.AUTHOR_ID IS 'Идентификатор автора';
COMMENT
ON COLUMN SUPERSPRINTS.CREATED_AT IS 'Дата создания записи';
COMMENT
ON COLUMN SUPERSPRINTS.UPDATED_AT IS 'Дата обновления записи';


-- rollback DROP TABLE SUPERSPRINTS;



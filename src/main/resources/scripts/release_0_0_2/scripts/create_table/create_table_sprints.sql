--liquibase formatted sql

--changeset tokarev-as:create_table_sprints rollbackSplitStatements:true
--comment: Создание таблицы спринтов
CREATE TABLE SPRINTS
(
    ID         VARCHAR(36) PRIMARY KEY,
    NAME   VARCHAR(128) NOT NULL,
    START_AT TIMESTAMP,
    END_AT TIMESTAMP,
    AUTHOR_ID  VARCHAR(36) NOT NULL,
    SUPERSPRINT_ID VARCHAR(36) NOT NULL,
    CREATED_AT TIMESTAMP NOT NULL,
    UPDATED_AT TIMESTAMP NOT NULL
);

COMMENT
ON TABLE SPRINTS IS 'Спринты';
COMMENT
ON COLUMN SPRINTS.ID IS 'Идентификатор';
COMMENT
ON COLUMN SPRINTS.TEXT IS 'Текст спринта';
COMMENT
ON COLUMN SPRINTS.SUPERSPRINT_ID IS 'Идентификатор задачи';
COMMENT
ON COLUMN SPRINTS.AUTHOR_ID IS 'Идентификатор автора';
COMMENT
ON COLUMN SPRINTS.CREATED_AT IS 'Дата создания записи';
COMMENT
ON COLUMN SPRINTS.UPDATED_AT IS 'Дата обновления записи';
COMMENT
ON COLUMN SPRINTS.START_AT IS 'Дата обновления записи';
COMMENT
ON COLUMN SPRINTS.END_AT IS 'Дата обновления записи';


-- rollback DROP TABLE SPRINTS;
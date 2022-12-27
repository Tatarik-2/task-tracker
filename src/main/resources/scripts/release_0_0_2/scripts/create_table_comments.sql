--liquibase formatted sql

--changeset burravlev:create_table_comments rollbackSplitStatements:true
--comment: Создание таблицы комментариев

CREATE TABLE COMMENTS(
    ID VARCHAR(36) PRIMARY KEY,
    TEXT VARCHAR(128) NOT NULL,
    CREATED_AT TIMESTAMP,
    UPDATED_AT TIMESTAMP,
    TASK_ID VARCHAR(36)
);

COMMENT ON TABLE COMMENTS IS 'Комментарии';
COMMENT ON COLUMN COMMENTS.ID IS 'Идентификатор';
COMMENT ON COLUMN COMMENTS.TEXT IS 'Текст';
COMMENT ON COLUMN COMMENTS.CREATED_AT IS 'Дата создания';
COMMENT ON COLUMN COMMENTS.UPDATED_AT IS 'Дата обновления';
COMMENT ON COLUMN COMMENTS.TASK_ID IS 'Идентификатор таска';

--rollback DROP TABLE COMMENTS;
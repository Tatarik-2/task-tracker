CREATE TABLE COMMENTS(
    ID VARCHAR(36) PRIMARY KEY,
    TEXT VARCHAR(128) NOT NULL,
    CREATED_AT TIMESTAMP,
    UPDATED_AT TIMESTAMP,
    TASK_ID VARCHAR(36)
);

-- COMMENT
-- ON TABLE COMMENTS IS 'Комментарии';
-- COMMENT
-- ON TABLE COMMENTS.ID IS 'Идентификатор';
-- COMMENT
-- ON TABLE COMMENTS.TEXT IS 'Текст комментария';
-- COMMENT
-- ON TABLE COMMENTS.CREATED_AT IS 'Дата создания';
-- COMMENT
-- ON TABLE COMMENTS.UPDATED_AT IS 'Дата обновления';
-- COMMENT
-- ON TABLE COMMENTS.TASK_ID IS 'Идентификатор задачи';
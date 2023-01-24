--liquibase formatted sql

--changeset zolotov-vu:create_table_notification_subscriptions rollbackSplitStatements:true
--comment: Создание таблицы уведомлений подписок
CREATE TABLE NOTIFICATION_SUBSCRIPTIONS
(
    ID         VARCHAR(36) PRIMARY KEY,
    USER_ID    VARCHAR(36) NOT NULL,
    PROJECT_ID VARCHAR(36) NOT NULL,
    CREATED_AT TIMESTAMP,
    UNIQUE (USER_ID, PROJECT_ID)
);

COMMENT
ON TABLE NOTIFICATION_SUBSCRIPTIONS IS 'Уведомления подписки';
COMMENT
ON COLUMN NOTIFICATION_SUBSCRIPTIONS.ID IS 'Идентификатор';
COMMENT
ON COLUMN NOTIFICATION_SUBSCRIPTIONS.USER_ID IS 'Идентификатор пользователя';
COMMENT
ON COLUMN NOTIFICATION_SUBSCRIPTIONS.PROJECT_ID IS 'Идентификатор проекта';
COMMENT
ON COLUMN NOTIFICATION_SUBSCRIPTIONS.CREATED_AT IS 'Дата создания записи';


--rollback DROP TABLE NOTIFICATION_SUBSCRIPTIONS;

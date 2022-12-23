--liquibase formatted sql

--changeset zalyalov-na:create_table_workloads rollbackSplitStatements:true
--comment: Создание таблицы рабочих пространств
CREATE TABLE WORKLOADS
(
    ID         VARCHAR(36) PRIMARY KEY,
    NAME       VARCHAR(128) NOT NULL,
    STATUS     VARCHAR(18) NOT NULL,
    AUTHOR_ID  VARCHAR(36) NOT NULL,
    CREATED_AT TIMESTAMP NOT NULL,
    UPDATED_AT TIMESTAMP NOT NULL
);

COMMENT
ON TABLE WORKLOADS IS 'Рабочие пространства';
COMMENT
ON COLUMN WORKLOADS.ID IS 'Идентификатор';
COMMENT
ON COLUMN WORKLOADS.NAME IS 'Наименование';
COMMENT
ON COLUMN WORKLOADS.STATUS IS 'Статус';
COMMENT
ON COLUMN WORKLOADS.AUTHOR_ID IS 'Идентификатор автора';
COMMENT
ON COLUMN WORKLOADS.CREATED_AT IS 'Дата создания записи';
COMMENT
ON COLUMN WORKLOADS.UPDATED_AT IS 'Дата обновления записи';


-- rollback DROP TABLE WORKLOADS;



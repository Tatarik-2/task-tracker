--liquibase formatted sql

--changeset ovchinnikov-va:create_table_epics rollbackSplitStatements:true
--comment: Создание таблицы эпиков
CREATE TABLE epics
(
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    description VARCHAR(256) NOT NULL,
    status VARCHAR(18) NOT NULL,
    priority VARCHAR(18) NOT NULL,
    project_id VARCHAR(36) NOT NULL,
    author_id VARCHAR(36) NOT NULL,
    supersprint_id VARCHAR(36),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE INDEX epics_name_idx ON epics(name);

COMMENT ON TABLE epics IS 'Эпики';
COMMENT ON COLUMN epics.id IS 'Идентификатор';
COMMENT ON COLUMN epics.name IS 'Наименование';
COMMENT ON COLUMN epics.description IS 'Описание';
COMMENT ON COLUMN epics.status IS 'Статус';
COMMENT ON COLUMN epics.priority IS 'Приоритет';
COMMENT ON COLUMN epics.project_id IS 'Идентификатор проекта';
COMMENT ON COLUMN epics.author_id IS 'Идентификатор автора';
COMMENT ON COLUMN epics.supersprint_id IS 'Идентификатор суперспринта';
COMMENT ON COLUMN epics.created_at IS 'Дата создания записи';
COMMENT ON COLUMN epics.updated_at IS 'Дата обновления записи';

--rollback DROP TABLE epics;
--liquibase formatted sql

--changeset kichenko-av:create_index_for_table_histories rollbackSplitStatements:true
--comment: Создание индекса для таблицы историй
CREATE UNIQUE INDEX histories_name_idx
ON histories(name);


--rollback DROP INDEX histories_name_idx;
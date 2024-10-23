-- 创建 msg_record 表，如果表不存在
CREATE TABLE IF NOT EXISTS msg_record
(
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    config_id   INTEGER      NOT NULL DEFAULT 0,
    title       VARCHAR(255) NOT NULL DEFAULT '',
    content     TEXT         NOT NULL,
    uid         VARCHAR(255) NOT NULL DEFAULT '',
    nickname    VARCHAR(255) NOT NULL DEFAULT '',
    msg_str     VARCHAR(255) NOT NULL DEFAULT '',
    version     INTEGER      NOT NULL DEFAULT 0,
    create_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    creator     VARCHAR(255) NOT NULL DEFAULT '',
    update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updater     VARCHAR(255) NOT NULL DEFAULT ''
); $$$
CREATE INDEX IF NOT EXISTS idx_config_id ON msg_record (config_id); $$$
CREATE INDEX IF NOT EXISTS idx_uid ON msg_record (uid); $$$
CREATE INDEX IF NOT EXISTS idx_create_time ON msg_record (create_time); $$$

CREATE TRIGGER IF NOT EXISTS before_update_msg_record
    BEFORE UPDATE
    ON msg_record
    FOR EACH ROW
BEGIN
    UPDATE msg_record SET version = OLD.version + 1 WHERE id = OLD.id;
END; $$$


-- 创建 msg_config 表，如果表不存在

CREATE TABLE IF NOT EXISTS msg_config
(
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    content     VARCHAR(255) NOT NULL DEFAULT '',
    version     INTEGER      NOT NULL DEFAULT 0,
    create_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    creator     VARCHAR(255) NOT NULL DEFAULT '',
    update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updater     VARCHAR(255) NOT NULL DEFAULT ''
); $$$
CREATE INDEX IF NOT EXISTS idx_create_time ON msg_config (create_time); $$$

CREATE TRIGGER IF NOT EXISTS before_update_msg_config
    BEFORE UPDATE
    ON msg_config
    FOR EACH ROW
BEGIN
    UPDATE msg_config SET version = OLD.version + 1 WHERE id = OLD.id;
END; $$$

-- 创建 msg_record 表，如果表不存在
CREATE TABLE IF NOT EXISTS msg_record
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    config_id   INT          NOT NULL DEFAULT 0 COMMENT '配置ID',
    title       VARCHAR(255) NOT NULL DEFAULT '' COMMENT '标题',
    content     TEXT         NOT NULL DEFAULT '' COMMENT '内容',
    uid         VARCHAR(255) NOT NULL DEFAULT '' COMMENT '用户ID',
    nickname    VARCHAR(255) NOT NULL DEFAULT '' COMMENT '用户昵称',
    msg_str     VARCHAR(255) NOT NULL DEFAULT '' COMMENT '透传数据',
    version     INT          NOT NULL DEFAULT 0 COMMENT '版本号',
    create_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    creator     VARCHAR(255) NOT NULL DEFAULT '' COMMENT '创建者',
    update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    updater     VARCHAR(255) NOT NULL DEFAULT '' COMMENT '更新者',
    INDEX idx_config_id (config_id),
    INDEX idx_uid (uid),
    INDEX idx_create_time (create_time)
);

-- 检查触发器是否存在，如果不存在则创建
DELIMITER //

CREATE TRIGGER IF NOT EXISTS before_update_msg_record
    BEFORE UPDATE
    ON msg_record
    FOR EACH ROW
BEGIN
    SET NEW.version = OLD.version + 1;
END //

DELIMITER ;

-- 创建 msg_config 表，如果表不存在
CREATE TABLE IF NOT EXISTS msg_config
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    content     VARCHAR(255) NOT NULL DEFAULT '' COMMENT '配置信息',
    revision    INT          NOT NULL DEFAULT 0 COMMENT '版本号',
    create_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    creator     VARCHAR(255) NOT NULL DEFAULT '' COMMENT '创建者',
    update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    updater     VARCHAR(255) NOT NULL DEFAULT '' COMMENT '更新者',
    INDEX idx_create_time (create_time)
);

-- 检查触发器是否存在，如果不存在则创建
DELIMITER //

DROP TRIGGER IF EXISTS before_update_msg_config;

CREATE TRIGGER before_update_msg_config
    BEFORE UPDATE
    ON msg_config
    FOR EACH ROW
BEGIN
    SET NEW.revision = OLD.revision + 1;
END //

DELIMITER ;


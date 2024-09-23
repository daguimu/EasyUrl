CREATE DATABASE IF NOT EXISTS `shortdb`;


CREATE TABLE IF NOT EXISTS `shorter`
(
    `id`           bigint(20) unsigned NOT NULL COMMENT 'primary key',
    `shouter_str`  varchar(15) NOT NULL COMMENT 'short url',
    `original_url`  varchar(2000) NOT NULL COMMENT 'original url',
    `create_time`     datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time with record',
    `deleted`      tinyint(4) NOT NULL DEFAULT '0' COMMENT 'maybe delete',
    `update_time`     datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time',
    `expire_time` datetime    NOT NULL COMMENT 'expire time',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='short url records';
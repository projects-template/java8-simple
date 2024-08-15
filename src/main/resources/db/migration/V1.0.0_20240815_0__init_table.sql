SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE js_user
(
    id          bigint auto_increment                                              NOT NULL,
    name        varchar(100) DEFAULT ''                                            NOT NULL COMMENT '用户名',
    create_time datetime     DEFAULT CURRENT_TIMESTAMP                             NOT NULL COMMENT '创建时间',
    update_time datetime     DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
    is_del      tinyint      DEFAULT 0                                             NOT NULL COMMENT '是否删除 0 - 未删除、 1 - 已删除',
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_general_ci
    COMMENT ='用户表';

SET FOREIGN_KEY_CHECKS = 1;

create database if not exists `spring-boot-demo-db`;
use `spring-boot-demo-db`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for USER
-- ----------------------------
DROP TABLE IF EXISTS `USER`;
CREATE TABLE `USER`  (
                       `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                       `account_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '账号',
                       `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '姓名',
                       `token` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'token',
                       `gmt_create` bigint(11) NULL DEFAULT NULL COMMENT '创建时间',
                       `gmt_modified` bigint(11) NULL DEFAULT NULL COMMENT '修改时间',
                       `bio` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '个性签名',
                       `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '头像url',
                       PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for QUESTION
-- ----------------------------
DROP TABLE IF EXISTS `QUESTION`;
CREATE TABLE `QUESTION`  (
                           `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                           `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '标题',
                           `description` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '描述',
                           `gmt_create` bigint(11) NULL DEFAULT NULL COMMENT '创建时间',
                           `gmt_modified` bigint(11) NULL DEFAULT NULL COMMENT '修改时间',
                           `creator` bigint(11) NULL DEFAULT 0 COMMENT '创建人',
                           `comment_count` bigint(11) NULL DEFAULT 0 COMMENT '评论数',
                           `view_count` bigint(11) NULL DEFAULT 0 COMMENT '查看数',
                           `like_count` bigint(11) NULL DEFAULT 0 COMMENT '点赞数',
                           `tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '标签',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for COMMENT
-- ----------------------------
DROP TABLE IF EXISTS `COMMENT`;
CREATE TABLE `COMMENT`  (
                          `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
                          `parent_id` bigint(11) NULL DEFAULT NULL COMMENT '父id',
                          `type` int(11) NULL DEFAULT NULL COMMENT '类型',
                          `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '评论内容',
                          `commentator` bigint(11) NULL DEFAULT NULL COMMENT '评论者id',
                          `like_count` bigint(11) NULL DEFAULT 0 COMMENT '点赞数',
                          `comment_count` bigint(11) NULL DEFAULT 0 COMMENT '评论数',
                          `gmt_create` bigint(11) NULL DEFAULT NULL COMMENT '创建时间',
                          `gmt_modified` bigint(11) NULL DEFAULT NULL COMMENT '修改时间',
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for NOTIFICATION
-- ----------------------------
DROP TABLE IF EXISTS `NOTIFICATION`;
CREATE TABLE `NOTIFICATION`  (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT,
                               `notifer` bigint(20) NULL DEFAULT NULL,
                               `notifer_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
                               `receiver` bigint(20) NULL DEFAULT NULL,
                               `outer_id` bigint(20) NULL DEFAULT NULL,
                               `outer_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
                               `type` int(11) NULL DEFAULT NULL,
                               `gmt_create` bigint(20) NULL DEFAULT NULL,
                               `status` int(11) NULL DEFAULT 0,
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;


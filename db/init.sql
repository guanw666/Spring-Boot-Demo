create database if not exists `spring-boot-demo-db`;
use `spring-boot-demo-db`;

drop table if exists USER;
create table USER
(
  id           bigint(11) auto_increment comment '主键'
    primary key,
  account_id   varchar(50)  null comment '账号',
  name         varchar(100) null comment '姓名',
  token        varchar(36)  null comment 'token',
  gmt_create   bigint(11)   null comment '创建时间',
  gmt_modified bigint(11)   null comment '修改时间',
  bio          varchar(255) null comment '个性签名',
  avatar_url   varchar(255) null comment '头像url'
);

drop table if exists QUESTION;
create table QUESTION
(
  id            bigint(11) auto_increment comment '主键'
    primary key,
  title         varchar(50)          null comment '标题',
  description   varchar(4000)        null comment '描述',
  gmt_create    bigint(11)           null comment '创建时间',
  gmt_modified  bigint(11)           null comment '修改时间',
  creator       bigint(11) default 0 null comment '创建人',
  comment_count bigint(11) default 0 null comment '评论数',
  view_count    bigint(11) default 0 null comment '查看数',
  like_count    bigint(11) default 0 null comment '点赞数',
  tag           varchar(255)         null comment '标签'
);

drop table if exists COMMENT;
create table COMMENT
(
  id            bigint(11) auto_increment comment 'id'
    primary key,
  parent_id     bigint(11)           null comment '父id',
  type          int                  null comment '类型',
  comment       varchar(255)         null comment '评论内容',
  commentator   bigint(11)           null comment '评论者id',
  like_count    bigint(11) default 0 null comment '点赞数',
  comment_count bigint(11) default 0 null comment '评论数',
  gmt_create    bigint(11)           null comment '创建时间',
  gmt_modified  bigint(11)           null comment '修改时间'
);

drop table if exists NOTIFICATION;
create table NOTIFICATION
(
  id           bigint auto_increment
    primary key,
  notifer      bigint        null,
  notifer_name varchar(100)  null,
  receiver     bigint        null,
  outer_id     bigint        null,
  outer_title  varchar(255)  null,
  type         int           null,
  gmt_create   bigint        null,
  status       int default 0 null
);


/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost:3306
 Source Schema         : sta_user

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : 65001

 Date: 16/06/2020 16:34:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(55) DEFAULT NULL COMMENT '用户名',
  `real_name` varchar(55) DEFAULT NULL COMMENT '姓名',
  `pwd` varchar(128) DEFAULT NULL COMMENT '密码',
  `org_name` varchar(255) DEFAULT NULL COMMENT '机构名称',
  `research_area` varchar(255) DEFAULT NULL COMMENT '研究领域',
  `status` char(1) DEFAULT NULL COMMENT '状态 1、正常 2、封号',
  `icon` varchar(255) DEFAULT NULL COMMENT '头像',
  `email` varchar(255) DEFAULT NULL COMMENT '电子邮件',
  `createdate` datetime DEFAULT NULL COMMENT '创建时间',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `salt2` varchar(55) DEFAULT NULL COMMENT 'salt2',
  `lastupdacct` varchar(55) DEFAULT NULL COMMENT '最近修改人',
  `lastupdtime` datetime DEFAULT NULL COMMENT '最近修改时间',
  `note` varchar(200) DEFAULT NULL COMMENT '备注',
  `has_updpwd` char(1) DEFAULT NULL COMMENT '是否修改过密码 0、否 1、是',
  `onlinestatus` char(1) DEFAULT NULL COMMENT '在线状态  1-在线 0-离线',
  `last_role_id` int(11) DEFAULT NULL COMMENT '最近登录角色',
  `last_login_time` datetime DEFAULT NULL COMMENT '最近登录时间',
  `lastip` varchar(25) DEFAULT NULL COMMENT '最后登录ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

SET FOREIGN_KEY_CHECKS = 1;

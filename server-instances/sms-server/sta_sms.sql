/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost:3306
 Source Schema         : sta_sms

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : 65001

 Date: 16/06/2020 16:33:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sms_company
-- ----------------------------
DROP TABLE IF EXISTS `sms_company`;
CREATE TABLE `sms_company` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `appid` varchar(255) DEFAULT NULL COMMENT '用户id',
  `appsecret` varchar(255) DEFAULT NULL COMMENT '用户秘钥',
  `com_name` varchar(100) DEFAULT NULL COMMENT '公司名称',
  `sms_send_type` int(1) NOT NULL COMMENT '短信模板头配置位置  0-自己配置  1-第三方配置',
  `status` int(1) DEFAULT '1' COMMENT '状态  0-禁用   	1-启用   	2-删除',
  `url` varchar(100) DEFAULT NULL COMMENT '短信接口地址',
  `sdkTag` varchar(100) DEFAULT NULL COMMENT '使用sdk的标签',
  `createtime` datetime DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for sms_logs
-- ----------------------------
DROP TABLE IF EXISTS `sms_logs`;
CREATE TABLE `sms_logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `l_temp_id` int(11) NOT NULL COMMENT '模板id  -1 为未使用模板',
  `sdkTag` varchar(100) DEFAULT NULL COMMENT '标签',
  `l_phone_num` varchar(20) NOT NULL COMMENT '发送的手机号码',
  `l_content` varchar(255) DEFAULT NULL COMMENT '发送内容',
  `l_request_ip` varchar(45) DEFAULT NULL COMMENT '调用者ip地址',
  `status` int(1) DEFAULT NULL COMMENT '发送状态   0-发送失败   1-发送成功   2-发送中',
  `l_failure_reason` varchar(255) DEFAULT NULL COMMENT '失败原因',
  `l_send_time` datetime DEFAULT NULL COMMENT '发送时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for sms_res
-- ----------------------------
DROP TABLE IF EXISTS `sms_res`;
CREATE TABLE `sms_res` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) DEFAULT NULL,
  `name` varchar(111) DEFAULT NULL,
  `des` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `iconCls` varchar(255) DEFAULT 'am-icon-file',
  `seq` bigint(20) DEFAULT '1',
  `type` varchar(2) DEFAULT '2' COMMENT '1 菜单 2 功能',
  `status` varchar(2) DEFAULT '1' COMMENT '1-启用 0-未启用',
  `lastUpdAcct` varchar(20) DEFAULT NULL,
  `lastUpdTime` datetime DEFAULT NULL,
  `note` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sms_template
-- ----------------------------
DROP TABLE IF EXISTS `sms_template`;
CREATE TABLE `sms_template` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tpl_com_id` int(11) NOT NULL COMMENT '对应公司id',
  `sdkTag` varchar(100) NOT NULL COMMENT '使用平台标签',
  `tpl_no` varchar(20) NOT NULL COMMENT '模板编号',
  `tpl_name` varchar(255) NOT NULL COMMENT '模板名称',
  `tpl_sms_header` varchar(255) DEFAULT NULL COMMENT '短信签名',
  `tpl_content` varchar(255) NOT NULL COMMENT '模板内容',
  `tpl_remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` int(1) DEFAULT '1' COMMENT '0-禁用 1-启用  2-删除',
  `createtime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;

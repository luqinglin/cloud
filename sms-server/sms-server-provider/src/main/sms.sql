-- MySQL dump 10.13  Distrib 5.7.24, for macos10.14 (x86_64)
--
-- Host: localhost    Database: sms
-- ------------------------------------------------------
-- Server version	5.7.24-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `sms`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `sms` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `sms`;

--
-- Table structure for table `sms_company`
--

DROP TABLE IF EXISTS `sms_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sms_company`
--

LOCK TABLES `sms_company` WRITE;
/*!40000 ALTER TABLE `sms_company` DISABLE KEYS */;
INSERT INTO `sms_company` VALUES (18,'EUCP-EMY-SMS1-1AH8G','D8EDCA438C502217','北京亿美软通科技有限公司',0,1,'http://bjmtn.b2m.cn:80/simpleinter/sendSMS','亿美软通','2019-07-16 17:09:00','2019-07-16 17:09:00');
/*!40000 ALTER TABLE `sms_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sms_logs`
--

DROP TABLE IF EXISTS `sms_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sms_logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `l_temp_id` int(11) NOT NULL DEFAULT '-1' COMMENT '模板id  -1 为未使用模板',
  `sdkTag` varchar(100) DEFAULT NULL COMMENT '标签',
  `l_phone_num` varchar(20) NOT NULL COMMENT '发送的手机号码',
  `l_content` varchar(255) DEFAULT NULL COMMENT '发送内容',
  `l_request_ip` varchar(45) DEFAULT NULL COMMENT '调用者ip地址',
  `status` int(1) DEFAULT NULL COMMENT '发送状态   0-发送失败   1-发送成功   2-发送中',
  `l_failure_reason` varchar(255) DEFAULT NULL COMMENT '失败原因',
  `l_send_time` datetime DEFAULT NULL COMMENT '发送时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `sms_res`
--

DROP TABLE IF EXISTS `sms_res`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sms_res`
--

LOCK TABLES `sms_res` WRITE;
/*!40000 ALTER TABLE `sms_res` DISABLE KEYS */;
INSERT INTO `sms_res` VALUES (1,0,'系统管理','系统管理','#',1,'&#xe614;',1,'1','1','admin','2018-01-17 10:22:03','启用系统资源'),(3,1,'公司管理','公司管理','/sms/company',2,'&#xe614;',1,'1','1','admin','2016-10-18 16:24:08','添加资源'),(4,1,'模板管理','模板管理','/sms/templates',2,'&#xe614;',3,'1','1','','2017-10-13 11:26:19','修改系统资源'),(8,1,'记录管理','记录管理','/sms/recode',2,'&#xe614;',8,'1','1','admin','2016-10-20 21:29:24','修改资源');
/*!40000 ALTER TABLE `sms_res` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sms_template`
--

DROP TABLE IF EXISTS `sms_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-29 10:55:31

/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : operation_log

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2020-07-26 21:51:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for controller_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `controller_operation_log`;
CREATE TABLE `controller_operation_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `operation_id` varchar(20) NOT NULL,
  `operation_module` varchar(64) DEFAULT NULL,
  `operation_type` varchar(64) DEFAULT NULL,
  `operation_desc` varchar(500) DEFAULT NULL,
  `operation_request_param` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `operation_response_param` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `operation_content` varchar(1000) DEFAULT NULL,
  `operation_uri` varchar(255) DEFAULT NULL,
  `operation_method` varchar(255) DEFAULT NULL,
  `operation_ip` varchar(64) DEFAULT NULL,
  `operation_create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for exception_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `exception_operation_log`;
CREATE TABLE `exception_operation_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `exception_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `exception_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `exception_message` varchar(10000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `exception_request_param` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `operation_uri` varchar(255) DEFAULT NULL,
  `operation_method` varchar(255) DEFAULT NULL,
  `operation_ip` varchar(64) DEFAULT NULL,
  `operation_create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `user_age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

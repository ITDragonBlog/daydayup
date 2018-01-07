/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : lock

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-01-05 18:20:01
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `innodb_lock`
-- ----------------------------
DROP TABLE IF EXISTS `innodb_lock`;
CREATE TABLE `innodb_lock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `k` varchar(20) DEFAULT NULL,
  `v` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of innodb_lock
-- ----------------------------

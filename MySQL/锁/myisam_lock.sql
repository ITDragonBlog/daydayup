/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : lock

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-01-05 18:20:22
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `myisam_lock`
-- ----------------------------
DROP TABLE IF EXISTS `myisam_lock`;
CREATE TABLE `myisam_lock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `k` varchar(20) DEFAULT NULL,
  `v` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of myisam_lock
-- ----------------------------
INSERT INTO myisam_lock VALUES ('1', '1', '1000');
INSERT INTO myisam_lock VALUES ('2', '2', '2000');
INSERT INTO myisam_lock VALUES ('3', '3', '3000');
INSERT INTO myisam_lock VALUES ('4', '4', '4000');
INSERT INTO myisam_lock VALUES ('5', '5', '5000');
INSERT INTO myisam_lock VALUES ('6', '6', '6000');
INSERT INTO myisam_lock VALUES ('7', '7', '7000');
INSERT INTO myisam_lock VALUES ('8', '8', '8000');
INSERT INTO myisam_lock VALUES ('9', '9', '9000');

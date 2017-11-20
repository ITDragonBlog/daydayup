/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 60011
Source Host           : localhost:3306
Source Database       : jpa

Target Server Type    : MYSQL
Target Server Version : 60011
File Encoding         : 65001

Date: 2017-11-20 14:54:49
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '类目名',
  `is_leaf` int(11) DEFAULT NULL COMMENT '是否为叶节点，1表示是，0表示不是',
  `parent_id` int(11) DEFAULT NULL COMMENT '父节点id',
  `createdDate` datetime DEFAULT NULL COMMENT '创建时间',
  `updatedDate` datetime DEFAULT NULL COMMENT '更新时间',
  `status` int(11) DEFAULT NULL COMMENT '状态，1表示删除，0表示存在',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO category VALUES ('1', '一级类目Java', '0', '0', '2017-11-20 23:21:01', '2017-11-20 23:21:01', '0');
INSERT INTO category VALUES ('2', '一级类目JavaSE', '0', '0', '2017-11-20 23:21:01', '2017-11-20 23:21:01', '0');
INSERT INTO category VALUES ('3', '一级类目JavaEE', '0', '0', '2017-11-20 23:21:01', '2017-11-20 23:21:01', '0');
INSERT INTO category VALUES ('4', '二级类目Collection', '1', '2', '2017-11-20 23:21:01', '2017-11-20 23:21:01', '0');
INSERT INTO category VALUES ('5', '二级类目Map', '1', '2', '2017-11-20 23:21:01', '2017-11-20 23:21:01', '0');
INSERT INTO category VALUES ('6', '二级类目Spring', '0', '3', '2017-11-20 23:21:01', '2017-11-20 23:21:01', '0');
INSERT INTO category VALUES ('7', '二级类目SpringMVC', '1', '3', '2017-11-20 23:21:01', '2017-11-20 23:21:01', '0');
INSERT INTO category VALUES ('8', '二级类目Mybatis', '1', '3', '2017-11-20 23:21:01', '2017-11-20 23:21:01', '0');
INSERT INTO category VALUES ('9', '二级类目ITDragon', '1', '1', '2017-11-20 23:21:01', '2017-11-20 23:21:01', '0');
INSERT INTO category VALUES ('10', '三级类目SpringBoot', '1', '6', '2017-11-20 23:21:01', '2017-11-20 23:21:01', '0');
INSERT INTO category VALUES ('11', '三级类目SpringCloud', '1', '6', '2017-11-20 23:21:01', '2017-11-20 23:21:01', '0');

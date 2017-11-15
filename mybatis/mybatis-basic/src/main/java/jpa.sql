/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 60011
Source Host           : localhost:3306
Source Database       : jpa

Target Server Type    : MYSQL
Target Server Version : 60011
File Encoding         : 65001

Date: 2017-11-12 16:41:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for classroom
-- ----------------------------
DROP TABLE IF EXISTS `classroom`;
CREATE TABLE `classroom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room` varchar(255) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `c_t_id` (`teacher_id`),
  KEY `c_s_id` (`student_id`),
  CONSTRAINT `c_t_id` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classroom
-- ----------------------------
INSERT INTO `classroom` VALUES ('1', 'JavaEE', '1', '1');
INSERT INTO `classroom` VALUES ('2', 'Linux', '2', '2');

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('1', 'lxl@qq.com', 'lxl');
INSERT INTO `person` VALUES ('2', 'cyy@qq.com', 'cyy');
INSERT INTO `person` VALUES ('3', 'itdrgon@qq.com', 'itdragon');
INSERT INTO `person` VALUES ('4', 'java@qq.com', 'java');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `s_c_id` (`class_id`),
  CONSTRAINT `s_c_id` FOREIGN KEY (`class_id`) REFERENCES `classroom` (`student_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', 'ITDragon', '1');
INSERT INTO `student` VALUES ('2', 'Marry', '1');
INSERT INTO `student` VALUES ('3', 'XiaoMing', '2');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', 'Java');
INSERT INTO `teacher` VALUES ('2', 'Docker');

-- ----------------------------
-- Procedure structure for ges_person_count
-- ----------------------------
DROP PROCEDURE IF EXISTS `ges_person_count`;
DELIMITER ;;
CREATE DEFINER=``@`` PROCEDURE `ges_person_count`(IN person_id INT, OUT person_count INT)
BEGIN  
SELECT COUNT(*) FROM jpa.p_person WHERE p_person.id > person_id INTO person_count;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for get_person_count
-- ----------------------------
DROP PROCEDURE IF EXISTS `get_person_count`;
DELIMITER ;;
CREATE DEFINER=``@`` PROCEDURE `get_person_count`(IN person_id INT, OUT person_count INT)
BEGIN  
SELECT COUNT(*) FROM jpa.person WHERE person.id > person_id INTO person_count;
END
;;
DELIMITER ;

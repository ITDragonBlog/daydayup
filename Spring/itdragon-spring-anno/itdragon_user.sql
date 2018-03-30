/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : jpa

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-03-30 09:40:12
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `itdragon_user`
-- ----------------------------
DROP TABLE IF EXISTS `itdragon_user`;
CREATE TABLE `itdragon_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `iphone` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `platform` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `updated_date` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of itdragon_user
-- ----------------------------
INSERT INTO itdragon_user VALUES ('1', 'itdragon', '2017-12-15 17:11:28', 'itdragon@163.com', '12345677890', '05c59f092ef7037a0af2f6f1b5a81730', 'weixin', '22e563f8-1214-458d-a600-2605d35af420', '2017-12-15 17:11:28', 'ITDragonBlog');
INSERT INTO itdragon_user VALUES ('2', 'itdragon2', '2017-12-15 18:11:49', 'itdragon@qq.com', '12345677890', '7fd4103a7074d68aaff7739484c66232', 'qq', '8f503db6-75f8-4d3b-9e78-c590bc646167', '2017-12-15 18:11:49', 'ITDragonBlog');
INSERT INTO itdragon_user VALUES ('3', 'lxlcyy13', '2017-12-16 10:35:06', 'update@email.com', '12345678888', '5a0071f9fea1c83b98b0d22cac376562', 'qq', 'ddc852fd-c260-46b5-b23e-eccfedd248ab', '2017-12-16 10:35:06', 'ITDragonBlog');
INSERT INTO itdragon_user VALUES ('4', 'niubi', '2017-12-16 10:45:18', 'itdragon@qq.com', '12345679999', '911f9dacf7589d8480bd5c63244c8ce5', 'other', 'ea8376d8-18fb-4118-b9d8-63ee8e4f6702', '2017-12-16 10:45:18', 'ITDragonBlog');
INSERT INTO itdragon_user VALUES ('5', 'gitLiu', '2017-12-16 10:46:04', 'itdragon@git.com', '12349857999', '05430df010b0a0f04bb4cce3d23d7a75', 'github', 'de0c1c06-3c8d-41a8-b482-79a3a1f6915c', '2017-12-16 10:46:04', 'ITDragonGit-3');

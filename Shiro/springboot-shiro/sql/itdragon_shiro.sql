
SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `itdragon_syspermission`
-- ----------------------------
DROP TABLE IF EXISTS `itdragon_syspermission`;
CREATE TABLE `itdragon_syspermission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `available` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `resource_type` enum('menu','button') DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of itdragon_syspermission
-- ----------------------------
INSERT INTO itdragon_syspermission VALUES ('1', '', '删除用户信息权限', 'employees:delete', null, '/employees');
INSERT INTO itdragon_syspermission VALUES ('2', '', '配置用户资源权限', 'permission:create', null, '/permission');
INSERT INTO itdragon_syspermission VALUES ('3', '', '更新用户资源权限', 'permission:update', null, '/permission');
INSERT INTO itdragon_syspermission VALUES ('4', '', '更新用户信息权限', 'employees:update', null, '/employees');

-- ----------------------------
-- Table structure for `itdragon_sysrole`
-- ----------------------------
DROP TABLE IF EXISTS `itdragon_sysrole`;
CREATE TABLE `itdragon_sysrole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `available` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of itdragon_sysrole
-- ----------------------------
INSERT INTO itdragon_sysrole VALUES ('1', '', '超级管理员，拥有所有系统权限', 'admin');
INSERT INTO itdragon_sysrole VALUES ('2', '', '经理，拥有较多的系统权限', 'manager');
INSERT INTO itdragon_sysrole VALUES ('3', '', '普通职工，拥有最基本的系统权限', 'staff');
INSERT INTO itdragon_sysrole VALUES ('4', '', '其他，如访客，实习生权限等', 'other');

-- ----------------------------
-- Table structure for `itdragon_user_shiro`
-- ----------------------------
DROP TABLE IF EXISTS `itdragon_user_shiro`;
CREATE TABLE `itdragon_user_shiro` (
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
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of itdragon_user_shiro
-- ----------------------------
INSERT INTO itdragon_user_shiro VALUES ('1', 'itdragon', '2018-02-23 16:56:57', 'itdragon@git.com', '12349857999', '219b86a44556942f247a89fb267b60ed', 'github', 'fbaf6e6d-4c4e-4984-aa9e-794f304831c2', '2018-02-23 16:56:57', 'ITDragonAdmin', '1');
INSERT INTO itdragon_user_shiro VALUES ('2', 'manager', '2018-02-25 20:52:47', 'itdragon@git.com', '12349857888', 'c8bed6d8899908e75efbc68251d45275', 'cnblogs', '1e015cc8-c589-4243-a88b-b83be4ff07d5', '2018-02-25 20:52:47', 'ITDragonMannger', '1');
INSERT INTO itdragon_user_shiro VALUES ('3', 'user', '2018-02-25 20:57:48', 'itdragon@git.com', '12349857777', '8dda3bedf1385042d942725f77e27a5e', 'weixin', '31a5b90f-f3e6-41ad-b98c-4c181f6567ce', '2018-02-25 20:57:48', 'ITDragonStaff', '1');
INSERT INTO itdragon_user_shiro VALUES ('4', 'other-user-0', '2018-02-26 09:46:49', 'itdragon@git.com', '12349857777', '3c5b496a78cf8428149a4017e703ce40', 'weixin', 'd0391353-532f-462e-9d79-3f9e2bb3c57d', '2018-02-26 09:46:49', 'OtherStaff-0', '1');
INSERT INTO itdragon_user_shiro VALUES ('5', 'other-user-1', '2018-02-26 09:46:50', 'itdragon@git.com', '12349857776', 'cc3ce6e5e37d887f5e891f50c34877da', 'weixin', '4874ac0d-8e64-4dec-9fb1-017a77f3752e', '2018-02-26 09:46:50', 'OtherStaff-1', '1');
INSERT INTO itdragon_user_shiro VALUES ('6', 'other-user-2', '2018-02-26 09:46:50', 'itdragon@git.com', '12349857775', '72cfeae0476964baf1e248db9fc82fb9', 'weixin', '4082df3e-a755-4bde-a944-cea544297a43', '2018-02-26 09:46:50', 'OtherStaff-2', '1');
INSERT INTO itdragon_user_shiro VALUES ('7', 'other-user-3', '2018-02-26 09:46:50', 'itdragon@git.com', '12349857774', '5af787a5491b569ebb98d0c3537dd607', 'weixin', 'bdc7ed2f-91dd-4e8b-b78c-dcc1b44c6b11', '2018-02-26 09:46:50', 'OtherStaff-3', '1');
INSERT INTO itdragon_user_shiro VALUES ('8', 'other-user-4', '2018-02-26 09:46:50', 'itdragon@git.com', '12349857773', '0783d528c683eed36db87be10d2e4906', 'weixin', '75bd9168-883c-446b-96cf-2c8889e18aa0', '2018-02-26 09:46:50', 'OtherStaff-4', '1');
INSERT INTO itdragon_user_shiro VALUES ('9', 'other-user-5', '2018-02-26 09:46:50', 'itdragon@git.com', '12349857772', '4f6d862242493dd1a8e047cd520363ef', 'weixin', '2895ad67-cb80-461c-bb59-7db763a69798', '2018-02-26 09:46:50', 'OtherStaff-5', '1');
INSERT INTO itdragon_user_shiro VALUES ('10', 'other-user-6', '2018-02-26 09:46:50', 'itdragon@git.com', '12349857771', 'a407b961c9902634aae9a0f54e99e7f0', 'weixin', '169cdbef-92da-4149-bf84-1a8de13009d1', '2018-02-26 09:46:50', 'OtherStaff-6', '1');
INSERT INTO itdragon_user_shiro VALUES ('11', 'other-user-7', '2018-02-26 09:46:50', 'itdragon@git.com', '12349857770', '1d237185627e882a955cf20d37ecc249', 'weixin', 'f3d3a64e-e25e-429b-9dbf-91026458096b', '2018-02-26 09:46:50', 'OtherStaff-7', '1');
INSERT INTO itdragon_user_shiro VALUES ('12', 'other-user-8', '2018-02-26 09:46:51', 'itdragon@git.com', '12349857769', 'ebab18584d99a9757f802488983ac0ee', 'weixin', 'bd0d27c8-26b7-4838-8794-c6d9487ee99d', '2018-02-26 09:46:51', 'OtherStaff-8', '1');
INSERT INTO itdragon_user_shiro VALUES ('13', 'other-user-9', '2018-02-26 09:46:51', 'itdragon@git.com', '12349857768', '838e2e8237178ce03e2db81767f041ba', 'weixin', '81d74af7-8543-4b43-b6d3-4793cb34725c', '2018-02-26 09:46:51', 'OtherStaff-9', '1');
INSERT INTO itdragon_user_shiro VALUES ('14', 'other-user-10', '2018-02-26 09:46:51', 'itdragon@git.com', '12349857767', '3980f97cfefb010ef649e178709835a9', 'weixin', '86e2e417-8562-4e36-bb39-cb7850b8017d', '2018-02-26 09:46:51', 'OtherStaff-10', '1');
INSERT INTO itdragon_user_shiro VALUES ('15', 'other-user-11', '2018-02-26 09:46:51', 'itdragon@git.com', '12349857766', '72753f11616f259a0c817c883f88f685', 'weixin', 'ee0b1ada-c94b-403e-90ec-7b3bab81d502', '2018-02-26 09:46:51', 'OtherStaff-11', '1');
INSERT INTO itdragon_user_shiro VALUES ('16', 'other-user-12', '2018-02-26 09:46:51', 'itdragon@git.com', '12349857765', '3d94c51d98fe3417797639c2da0e4cda', 'weixin', '3de7b2ea-8a8a-4740-90d7-40acd8a2c2ef', '2018-02-26 09:46:51', 'OtherStaff-12', '1');
INSERT INTO itdragon_user_shiro VALUES ('17', 'other-user-13', '2018-02-26 09:46:51', 'itdragon@git.com', '12349857764', '50ecf8160275ecb6d80fe359aac54b27', 'weixin', '79c725d6-c108-4bc1-a2d8-7ce498fa3fb1', '2018-02-26 09:46:51', 'OtherStaff-13', '1');
INSERT INTO itdragon_user_shiro VALUES ('18', 'other-user-14', '2018-02-26 09:46:51', 'itdragon@git.com', '12349857763', 'd108e23ae38df625cac64c6219a391f7', 'weixin', '4e1e7cbc-8f16-4c68-aa07-d95857b7a684', '2018-02-26 09:46:51', 'OtherStaff-14', '1');
INSERT INTO itdragon_user_shiro VALUES ('19', 'other-user-15', '2018-02-26 09:46:51', 'itdragon@git.com', '12349857762', '52ba47cf884c919c1be0bbcb7a28b81a', 'weixin', 'ae372b97-7ea4-41c5-bd36-c0355e89fc44', '2018-02-26 09:46:51', 'OtherStaff-15', '1');
INSERT INTO itdragon_user_shiro VALUES ('20', 'other-user-16', '2018-02-26 09:46:51', 'itdragon@git.com', '12349857761', 'c30ebaeb69115b908da1eba60a5f2a35', 'weixin', 'e2a7b3a3-d5ee-4d6d-84e3-4bbb3235f6b8', '2018-02-26 09:46:51', 'OtherStaff-16', '1');

-- ----------------------------
-- Table structure for `sys_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  KEY `FK3qako9id89xn4m7sflof8p7on` (`permission_id`),
  KEY `FKrctgpx92nl8nvamfjpp0kuy9h` (`role_id`),
  CONSTRAINT `FK3qako9id89xn4m7sflof8p7on` FOREIGN KEY (`permission_id`) REFERENCES `itdragon_syspermission` (`id`),
  CONSTRAINT `FKrctgpx92nl8nvamfjpp0kuy9h` FOREIGN KEY (`role_id`) REFERENCES `itdragon_sysrole` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO sys_role_permission VALUES ('1', '1');
INSERT INTO sys_role_permission VALUES ('1', '2');
INSERT INTO sys_role_permission VALUES ('1', '3');
INSERT INTO sys_role_permission VALUES ('2', '4');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `uid` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `FKjs1iwg2vbcbu5qsvg1vkjqna4` (`role_id`),
  KEY `FK2ea66lfcddqvi7i41x8pkov26` (`uid`),
  CONSTRAINT `FK2ea66lfcddqvi7i41x8pkov26` FOREIGN KEY (`uid`) REFERENCES `itdragon_user_shiro` (`id`),
  CONSTRAINT `FKjs1iwg2vbcbu5qsvg1vkjqna4` FOREIGN KEY (`role_id`) REFERENCES `itdragon_sysrole` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO sys_user_role VALUES ('1', '1');
INSERT INTO sys_user_role VALUES ('2', '2');
INSERT INTO sys_user_role VALUES ('3', '3');
INSERT INTO sys_user_role VALUES ('4', '3');
INSERT INTO sys_user_role VALUES ('5', '3');
INSERT INTO sys_user_role VALUES ('6', '3');
INSERT INTO sys_user_role VALUES ('7', '3');
INSERT INTO sys_user_role VALUES ('8', '3');
INSERT INTO sys_user_role VALUES ('9', '3');
INSERT INTO sys_user_role VALUES ('10', '3');
INSERT INTO sys_user_role VALUES ('11', '3');
INSERT INTO sys_user_role VALUES ('12', '3');
INSERT INTO sys_user_role VALUES ('13', '3');
INSERT INTO sys_user_role VALUES ('14', '3');
INSERT INTO sys_user_role VALUES ('15', '3');
INSERT INTO sys_user_role VALUES ('16', '3');
INSERT INTO sys_user_role VALUES ('17', '3');
INSERT INTO sys_user_role VALUES ('18', '3');
INSERT INTO sys_user_role VALUES ('19', '3');
INSERT INTO sys_user_role VALUES ('20', '3');

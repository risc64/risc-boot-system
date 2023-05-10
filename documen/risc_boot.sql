/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : risc_boot

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 10/05/2023 23:21:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization`  (
  `organization_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'uid',
  `organization_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组织名称',
  `organization_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组织编码',
  `parent_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上级组织uid',
  `regist_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '统一社会信用代码',
  `artificial_person` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '法定代表人/负责人',
  `address` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '住址/住所地',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `contact` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所在地区（省）',
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所在地区（市）',
  `area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所在地区（区、县）',
  `create_user_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人uid',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `edit_user_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人uid',
  `edit_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`organization_uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '组织表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_organization
-- ----------------------------
INSERT INTO `sys_organization` VALUES ('0000', 'XX公司', 'STARDUS', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `permission_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限uid',
  `parent_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上一级权限uid',
  `permission_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名',
  `permission_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限编码',
  `permission_type` int NULL DEFAULT NULL COMMENT '权限类型（0菜单、1操作）',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方式(如GET,POST,PUT,DELETE)',
  `menu_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单路径（前端路由）',
  `permission_level` int NULL DEFAULT NULL COMMENT '权限等级',
  `sort` int NULL DEFAULT NULL COMMENT '排序',
  `icon_style` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标样式',
  `create_user_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人uid',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `edit_user_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人uid',
  `edit_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`permission_uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('00', NULL, '组织管理', 'sysOrganization', 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES ('00-01', '00', '查询', 'sysOrganization:query', 1, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES ('00-02', '00', '新增', 'sysOrganization:add', 1, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES ('00-03', '00', '修改', 'sysOrganization:update', 1, NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES ('00-04', '00', '删除', 'sysOrganization:delete', 1, NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES ('01', NULL, '角色管理', 'sysRole', 0, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES ('01-01', '01', '查询', 'sysRole:query', 1, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES ('01-02', '01', '新增', 'sysRole:add', 1, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES ('01-03', '01', '修改', 'sysRole:update', 1, NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES ('01-04', '01', '删除', 'sysRole:delete', 1, NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES ('02', NULL, '权限管理', 'sysPermission', 0, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES ('02-01', '02', '查询', 'sysPermission:query', 1, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES ('02-02', '02', '新增', 'sysPermission:add', 1, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES ('02-03', '02', '修改', 'sysPermission:update', 1, NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES ('02-04', '02', '删除', 'sysPermission:delete', 1, NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES ('03', NULL, '用户管理', 'sysUser', 0, NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES ('03-01', '03', '查询', 'sysUser:query', 1, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES ('03-02', '03', '新增', 'sysUser:add', 1, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES ('03-03', '03', '修改', 'sysUser:update', 1, NULL, NULL, NULL, 3, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES ('03-04', '03', '删除', 'sysUser:delete', 1, NULL, NULL, NULL, 4, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色uid',
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色编码',
  `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `deleteable_flag` tinyint NULL DEFAULT 1 COMMENT '(0表示不可删除 1表示可删除）',
  `role_type` int NULL DEFAULT NULL COMMENT '角色类型(0代表超级管理员  1其他)',
  `create_user_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人uid',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `edit_user_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人uid',
  `edit_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`role_uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('0000', '超级管理员', 'SuperAdmin', '超级管理员', 0, 0, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `role_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色uid',
  `permission_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限uid',
  PRIMARY KEY (`role_uid`, `permission_uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('0000', '00');
INSERT INTO `sys_role_permission` VALUES ('0000', '00-01');
INSERT INTO `sys_role_permission` VALUES ('0000', '00-02');
INSERT INTO `sys_role_permission` VALUES ('0000', '00-03');
INSERT INTO `sys_role_permission` VALUES ('0000', '00-04');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户uid',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `user_nick` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `pass_word` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '密码',
  `salt` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '盐值',
  `open_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公开uid',
  `sex` tinyint(1) NULL DEFAULT 0 COMMENT '性别（0男1女）',
  `user_status` int NULL DEFAULT 0 COMMENT '状态（-1删除、0激活、1禁用、2待验证）',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `profile_picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所在地区（省）',
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所在地区（市）',
  `area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所在地区（区、县）',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系地址',
  `create_user_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人uid',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人uid',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_uid`) USING BTREE,
  UNIQUE INDEX `partner_id`(`user_name` ASC) USING BTREE,
  UNIQUE INDEX `unique_mb`(`mobile` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('0000', 'admin', '超人', '$2a$10$iI/Z9Ahjh3mIeeI3NqSjC./irwwToB8U6hM7jQg/oXfzVEhI7mDbi', NULL, '', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_organization
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_organization`;
CREATE TABLE `sys_user_organization`  (
  `uer_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户uid',
  `organization_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织uid',
  PRIMARY KEY (`uer_uid`, `organization_uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户组织关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_organization
-- ----------------------------
INSERT INTO `sys_user_organization` VALUES ('0000', '0000');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户uid',
  `role_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色uid',
  PRIMARY KEY (`user_uid`, `role_uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('0000', '0000');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'uid',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` tinyint(1) NULL DEFAULT NULL COMMENT '性别（0男 1女）',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '测试表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('1', '陈浩南', 0, '2017-11-02 08:42:59');
INSERT INTO `test` VALUES ('2', 'bb1', 1, '2017-11-02 08:42:59');
INSERT INTO `test` VALUES ('3', '苏大', 0, '2017-11-01 08:43:03');
INSERT INTO `test` VALUES ('4', '王二小', 0, '2017-11-04 08:43:03');
INSERT INTO `test` VALUES ('5', '秦仓', 0, '2017-10-30 08:43:03');
INSERT INTO `test` VALUES ('6', '魏武', 0, '2017-11-06 08:43:03');
INSERT INTO `test` VALUES ('7', '111', 1, '2017-11-06 08:43:03');
INSERT INTO `test` VALUES ('8', '222', 2, '2017-11-06 08:43:03');
INSERT INTO `test` VALUES ('9', '333', 3, '2017-11-06 08:43:03');
INSERT INTO `test` VALUES ('d6351535b2e848e3a5457b6ed415d1c4', 'aa', 0, '2017-11-02 08:42:59');
INSERT INTO `test` VALUES ('e7c4486757484932a03ce4bccd95a649', 'bb', 1, '2017-11-02 08:42:59');

SET FOREIGN_KEY_CHECKS = 1;

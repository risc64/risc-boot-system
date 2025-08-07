/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80042 (8.0.42)
 Source Host           : localhost:3306
 Source Schema         : risc_boot

 Target Server Type    : MySQL
 Target Server Version : 80042 (8.0.42)
 File Encoding         : 65001

 Date: 07/08/2025 10:57:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for base_file
-- ----------------------------
DROP TABLE IF EXISTS `base_file`;
CREATE TABLE `base_file` (
  `uid` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键uid',
  `show_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '显示名',
  `file_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文件名',
  `file_path` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文件路径',
  `dfs_path` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'dfs文件路径',
  `temp_flag` tinyint DEFAULT '0' COMMENT '临时文件标识（0 否   1是）',
  `file_type` int DEFAULT '0' COMMENT '文件类型（0 通用文件  ）',
  `create_user_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人uid',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人uid',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`uid`),
  KEY `idx_file_name` (`file_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='文件表';

-- ----------------------------
-- Records of base_file
-- ----------------------------
BEGIN;
INSERT INTO `base_file` (`uid`, `show_name`, `file_name`, `file_path`, `dfs_path`, `temp_flag`, `file_type`, `create_user_uid`, `create_time`, `update_user_uid`, `update_time`) VALUES ('0001', '123.docx', '1sd.docx', NULL, NULL, 0, 0, NULL, NULL, NULL, NULL);
INSERT INTO `base_file` (`uid`, `show_name`, `file_name`, `file_path`, `dfs_path`, `temp_flag`, `file_type`, `create_user_uid`, `create_time`, `update_user_uid`, `update_time`) VALUES ('c3865a4d10c44e24987327e96344a925', '承办人.docx', '调解账号.docx', '/files/c3865a4d10c44e24987327e96344a925.docx', NULL, 0, 0, '0000', NULL, '0000', '2025-08-06 17:04:54');
COMMIT;

-- ----------------------------
-- Table structure for sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization` (
  `uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'uid',
  `organization_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '组织名称',
  `organization_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '组织编码',
  `parent_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '上级组织uid',
  `regist_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '统一社会信用代码',
  `artificial_person` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '法定代表人/负责人',
  `address` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '住址/住所地',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '电话',
  `contact` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系人',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '所在地区（省）',
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '所在地区（市）',
  `area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '所在地区（区、县）',
  `create_user_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人uid',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `edit_user_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人uid',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='组织表';

-- ----------------------------
-- Records of sys_organization
-- ----------------------------
BEGIN;
INSERT INTO `sys_organization` (`uid`, `organization_name`, `organization_code`, `parent_uid`, `regist_id`, `artificial_person`, `address`, `phone`, `contact`, `description`, `province`, `city`, `area`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('0000', '星尘集团', 'SartdusGrop', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-08-05 16:38:17', NULL, NULL);
INSERT INTO `sys_organization` (`uid`, `organization_name`, `organization_code`, `parent_uid`, `regist_id`, `artificial_person`, `address`, `phone`, `contact`, `description`, `province`, `city`, `area`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('0001', '星尘精密机械有限公司', 'Stardust Precision Machinery Co., LTD', '0000', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-08-05 16:38:25', NULL, NULL);
INSERT INTO `sys_organization` (`uid`, `organization_name`, `organization_code`, `parent_uid`, `regist_id`, `artificial_person`, `address`, `phone`, `contact`, `description`, `province`, `city`, `area`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('0002', '星尘科技有限公司', 'Stardust Technology Co. LTD', '0000', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-08-05 16:38:29', NULL, NULL);
INSERT INTO `sys_organization` (`uid`, `organization_name`, `organization_code`, `parent_uid`, `regist_id`, `artificial_person`, `address`, `phone`, `contact`, `description`, `province`, `city`, `area`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('0003', '星尘超星芯片有限公司', 'Stardust Superstar chip Co., LTD', '0000', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-08-05 16:38:35', NULL, NULL);
INSERT INTO `sys_organization` (`uid`, `organization_name`, `organization_code`, `parent_uid`, `regist_id`, `artificial_person`, `address`, `phone`, `contact`, `description`, `province`, `city`, `area`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('52cc95e38533413096a3d23fc139dbef', '凌云物流有限公司', 'Lingyun MaterialFlow', '58d8a795a32e4dbca7708c5d14e9f312', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-08-06 16:38:41', NULL, NULL);
INSERT INTO `sys_organization` (`uid`, `organization_name`, `organization_code`, `parent_uid`, `regist_id`, `artificial_person`, `address`, `phone`, `contact`, `description`, `province`, `city`, `area`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('58d8a795a32e4dbca7708c5d14e9f311', '凌云集团1', 'LingyunGroup', NULL, '', '张三', NULL, NULL, NULL, NULL, '北京', '北京市市辖区', '西城区', NULL, '2024-08-06 16:38:55', '0000', '2024-09-11 17:45:35');
INSERT INTO `sys_organization` (`uid`, `organization_name`, `organization_code`, `parent_uid`, `regist_id`, `artificial_person`, `address`, `phone`, `contact`, `description`, `province`, `city`, `area`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('58d8a795a32e4dbca7708c5d14e9f312', '凌云集团', 'LingyunGroup', NULL, '', '张三', NULL, NULL, NULL, NULL, '北京', '北京市市辖区', '西城区', NULL, '2025-08-06 15:39:12', NULL, '2024-08-06 17:12:11');
INSERT INTO `sys_organization` (`uid`, `organization_name`, `organization_code`, `parent_uid`, `regist_id`, `artificial_person`, `address`, `phone`, `contact`, `description`, `province`, `city`, `area`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('58d8a795a32e4dbca7708c5d14e9f313', '凌云集团3', NULL, NULL, 'LingyunGroup', '张三', NULL, NULL, NULL, NULL, '北京', '北京市市辖区', '西城区', NULL, NULL, NULL, NULL);
INSERT INTO `sys_organization` (`uid`, `organization_name`, `organization_code`, `parent_uid`, `regist_id`, `artificial_person`, `address`, `phone`, `contact`, `description`, `province`, `city`, `area`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('58d8a795a32e4dbca7708c5d14e9f315', '凌云集团5', NULL, NULL, 'LingyunGroup', '张三', NULL, NULL, NULL, NULL, '北京', '北京市市辖区', '西城区', NULL, NULL, NULL, NULL);
INSERT INTO `sys_organization` (`uid`, `organization_name`, `organization_code`, `parent_uid`, `regist_id`, `artificial_person`, `address`, `phone`, `contact`, `description`, `province`, `city`, `area`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('58d8a795a32e4dbca7708c5d14e9f316', '凌云集团6', NULL, NULL, 'LingyunGroup', '张三', NULL, NULL, NULL, NULL, '北京', '北京市市辖区', '西城区', NULL, NULL, NULL, NULL);
INSERT INTO `sys_organization` (`uid`, `organization_name`, `organization_code`, `parent_uid`, `regist_id`, `artificial_person`, `address`, `phone`, `contact`, `description`, `province`, `city`, `area`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('58d8a795a32e4dbca7708c5d14e9f317', '凌云集团7', NULL, NULL, 'LingyunGroup', '张三', NULL, NULL, NULL, NULL, '北京', '北京市市辖区', '西城区', NULL, NULL, NULL, NULL);
INSERT INTO `sys_organization` (`uid`, `organization_name`, `organization_code`, `parent_uid`, `regist_id`, `artificial_person`, `address`, `phone`, `contact`, `description`, `province`, `city`, `area`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('58d8a795a32e4dbca7708c5d14e9f318', '凌云集团8', NULL, NULL, 'LingyunGroup', '张三', NULL, NULL, NULL, NULL, '北京', '北京市市辖区', '西城区', NULL, NULL, NULL, NULL);
INSERT INTO `sys_organization` (`uid`, `organization_name`, `organization_code`, `parent_uid`, `regist_id`, `artificial_person`, `address`, `phone`, `contact`, `description`, `province`, `city`, `area`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('58d8a795a32e4dbca7708c5d14e9f319', '凌云集团9', NULL, NULL, 'LingyunGroup', '张三', NULL, NULL, NULL, NULL, '北京', '北京市市辖区', '西城区', NULL, NULL, NULL, NULL);
INSERT INTO `sys_organization` (`uid`, `organization_name`, `organization_code`, `parent_uid`, `regist_id`, `artificial_person`, `address`, `phone`, `contact`, `description`, `province`, `city`, `area`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('58d8a795a32e4dbca7708c5d14e9f320', '凌云集团10', NULL, NULL, 'LingyunGroup', '张三', NULL, NULL, NULL, NULL, '北京', '北京市市辖区', '西城区', NULL, NULL, NULL, NULL);
INSERT INTO `sys_organization` (`uid`, `organization_name`, `organization_code`, `parent_uid`, `regist_id`, `artificial_person`, `address`, `phone`, `contact`, `description`, `province`, `city`, `area`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('58d8a795a32e4dbca7708c5d14e9f321', '凌云集团11', 'LingyunGroup', NULL, '', '张三', NULL, NULL, NULL, NULL, '北京', '北京市市辖区', '西城区', NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限uid',
  `parent_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '上一级权限uid',
  `permission_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限名',
  `permission_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限编码',
  `permission_type` int DEFAULT NULL COMMENT '权限类型（0菜单、1操作）',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求方式(如GET,POST,PUT,DELETE)',
  `menu_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单路径（前端路由）',
  `menu_component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '前端页面',
  `permission_level` int DEFAULT NULL COMMENT '权限等级',
  `sort` int DEFAULT NULL COMMENT '排序',
  `icon_style` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图标样式',
  `create_user_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人uid',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `edit_user_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人uid',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='权限表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` (`uid`, `parent_uid`, `permission_name`, `permission_code`, `permission_type`, `request_method`, `menu_url`, `menu_component`, `permission_level`, `sort`, `icon_style`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('001', NULL, '首页', 'home', 0, NULL, '/home', 'home/Home.vue', NULL, 1, 'home', NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` (`uid`, `parent_uid`, `permission_name`, `permission_code`, `permission_type`, `request_method`, `menu_url`, `menu_component`, `permission_level`, `sort`, `icon_style`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('001-01', '001', '首页', 'home', 0, NULL, '/home', 'home/Home.vue', NULL, 1, 'home', NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` (`uid`, `parent_uid`, `permission_name`, `permission_code`, `permission_type`, `request_method`, `menu_url`, `menu_component`, `permission_level`, `sort`, `icon_style`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('998', NULL, '基础数据', 'baseData', 0, NULL, '/base', 'layout/index.vue', NULL, 998, 'system', NULL, '2024-08-09 16:35:12', NULL, NULL);
INSERT INTO `sys_permission` (`uid`, `parent_uid`, `permission_name`, `permission_code`, `permission_type`, `request_method`, `menu_url`, `menu_component`, `permission_level`, `sort`, `icon_style`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('998-01', '998', '文件管理', 'baseFile', 0, NULL, '/base/baseFile', 'base/BaseFile.vue', NULL, 1, 'file', NULL, '2024-08-09 16:35:12', NULL, NULL);
INSERT INTO `sys_permission` (`uid`, `parent_uid`, `permission_name`, `permission_code`, `permission_type`, `request_method`, `menu_url`, `menu_component`, `permission_level`, `sort`, `icon_style`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('998-01-01', '998-01', '查询', 'baseFile:query', 1, NULL, NULL, NULL, NULL, 1, NULL, NULL, '2024-08-09 16:35:12', NULL, NULL);
INSERT INTO `sys_permission` (`uid`, `parent_uid`, `permission_name`, `permission_code`, `permission_type`, `request_method`, `menu_url`, `menu_component`, `permission_level`, `sort`, `icon_style`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('998-01-02', '998-01', '新增', 'baseFile:add', 1, NULL, NULL, NULL, NULL, 2, NULL, NULL, '2024-08-09 16:35:12', NULL, NULL);
INSERT INTO `sys_permission` (`uid`, `parent_uid`, `permission_name`, `permission_code`, `permission_type`, `request_method`, `menu_url`, `menu_component`, `permission_level`, `sort`, `icon_style`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('998-01-03', '998-01', '修改', 'baseFile:update', 1, NULL, NULL, NULL, NULL, 3, NULL, NULL, '2024-08-09 16:35:12', NULL, NULL);
INSERT INTO `sys_permission` (`uid`, `parent_uid`, `permission_name`, `permission_code`, `permission_type`, `request_method`, `menu_url`, `menu_component`, `permission_level`, `sort`, `icon_style`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('998-01-04', '998-01', '删除', 'baseFile:delete', 1, NULL, NULL, NULL, NULL, 4, NULL, NULL, '2024-08-09 16:35:12', NULL, NULL);
INSERT INTO `sys_permission` (`uid`, `parent_uid`, `permission_name`, `permission_code`, `permission_type`, `request_method`, `menu_url`, `menu_component`, `permission_level`, `sort`, `icon_style`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('999', NULL, '用户权限', 'authUser', 0, NULL, '/authUser', 'layout/index.vue', NULL, 999, 'system', NULL, '2024-08-09 16:35:12', NULL, NULL);
INSERT INTO `sys_permission` (`uid`, `parent_uid`, `permission_name`, `permission_code`, `permission_type`, `request_method`, `menu_url`, `menu_component`, `permission_level`, `sort`, `icon_style`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('999-01', '999', '角色管理', 'sysRole', 0, NULL, '/authUser/sysRole', 'system/SysRole.vue', NULL, 1, 'peoples', NULL, '2024-08-09 16:35:12', NULL, NULL);
INSERT INTO `sys_permission` (`uid`, `parent_uid`, `permission_name`, `permission_code`, `permission_type`, `request_method`, `menu_url`, `menu_component`, `permission_level`, `sort`, `icon_style`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('999-01-01', '999-01', '查询', 'sysRole:query', 1, NULL, NULL, NULL, NULL, 1, NULL, NULL, '2024-08-09 16:35:12', NULL, NULL);
INSERT INTO `sys_permission` (`uid`, `parent_uid`, `permission_name`, `permission_code`, `permission_type`, `request_method`, `menu_url`, `menu_component`, `permission_level`, `sort`, `icon_style`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('999-01-02', '999-01', '新增', 'sysRole:add', 1, NULL, NULL, NULL, NULL, 2, NULL, NULL, '2024-08-09 16:35:12', NULL, NULL);
INSERT INTO `sys_permission` (`uid`, `parent_uid`, `permission_name`, `permission_code`, `permission_type`, `request_method`, `menu_url`, `menu_component`, `permission_level`, `sort`, `icon_style`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('999-01-03', '999-01', '修改', 'sysRole:update', 1, NULL, NULL, NULL, NULL, 3, NULL, NULL, '2024-08-09 16:35:12', NULL, NULL);
INSERT INTO `sys_permission` (`uid`, `parent_uid`, `permission_name`, `permission_code`, `permission_type`, `request_method`, `menu_url`, `menu_component`, `permission_level`, `sort`, `icon_style`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('999-01-04', '999-01', '删除', 'sysRole:delete', 1, NULL, NULL, NULL, NULL, 4, NULL, NULL, '2024-08-09 16:35:12', NULL, NULL);
INSERT INTO `sys_permission` (`uid`, `parent_uid`, `permission_name`, `permission_code`, `permission_type`, `request_method`, `menu_url`, `menu_component`, `permission_level`, `sort`, `icon_style`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('999-02', '999', '权限管理', 'sysPermission', 0, NULL, '/authUser/sysPermission', 'system/SysPermission.vue', NULL, 2, 'permission', NULL, '2024-08-09 16:35:12', NULL, NULL);
INSERT INTO `sys_permission` (`uid`, `parent_uid`, `permission_name`, `permission_code`, `permission_type`, `request_method`, `menu_url`, `menu_component`, `permission_level`, `sort`, `icon_style`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('999-02-01', '999-02', '查询', 'sysPermission:query', 1, NULL, NULL, NULL, NULL, 1, NULL, NULL, '2024-08-09 16:35:12', NULL, NULL);
INSERT INTO `sys_permission` (`uid`, `parent_uid`, `permission_name`, `permission_code`, `permission_type`, `request_method`, `menu_url`, `menu_component`, `permission_level`, `sort`, `icon_style`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('999-02-02', '999-02', '新增', 'sysPermission:add', 1, NULL, NULL, NULL, NULL, 2, NULL, NULL, '2024-08-09 16:35:12', NULL, NULL);
INSERT INTO `sys_permission` (`uid`, `parent_uid`, `permission_name`, `permission_code`, `permission_type`, `request_method`, `menu_url`, `menu_component`, `permission_level`, `sort`, `icon_style`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('999-02-03', '999-02', '修改', 'sysPermission:update', 1, NULL, NULL, NULL, NULL, 3, NULL, NULL, '2024-08-09 16:35:12', NULL, NULL);
INSERT INTO `sys_permission` (`uid`, `parent_uid`, `permission_name`, `permission_code`, `permission_type`, `request_method`, `menu_url`, `menu_component`, `permission_level`, `sort`, `icon_style`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('999-02-04', '999-02', '删除', 'sysPermission:delete', 1, NULL, NULL, NULL, NULL, 4, NULL, NULL, '2024-08-09 16:35:12', NULL, NULL);
INSERT INTO `sys_permission` (`uid`, `parent_uid`, `permission_name`, `permission_code`, `permission_type`, `request_method`, `menu_url`, `menu_component`, `permission_level`, `sort`, `icon_style`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('999-03', '999', '用户管理', 'sysUser', 0, NULL, '/authUser/sysUser', 'system/SysUser.vue', NULL, 3, 'user', NULL, '2024-08-09 16:35:12', NULL, NULL);
INSERT INTO `sys_permission` (`uid`, `parent_uid`, `permission_name`, `permission_code`, `permission_type`, `request_method`, `menu_url`, `menu_component`, `permission_level`, `sort`, `icon_style`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('999-03-01', '999-03', '查询', 'sysUser:query', 1, NULL, NULL, NULL, NULL, 1, NULL, NULL, '2024-08-09 16:35:12', NULL, NULL);
INSERT INTO `sys_permission` (`uid`, `parent_uid`, `permission_name`, `permission_code`, `permission_type`, `request_method`, `menu_url`, `menu_component`, `permission_level`, `sort`, `icon_style`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('999-03-02', '999-03', '新增', 'sysUser:add', 1, NULL, NULL, NULL, NULL, 2, NULL, NULL, '2024-08-09 16:35:12', NULL, NULL);
INSERT INTO `sys_permission` (`uid`, `parent_uid`, `permission_name`, `permission_code`, `permission_type`, `request_method`, `menu_url`, `menu_component`, `permission_level`, `sort`, `icon_style`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('999-03-03', '999-03', '修改', 'sysUser:update', 1, NULL, NULL, NULL, NULL, 3, NULL, NULL, '2024-08-09 16:35:12', NULL, NULL);
INSERT INTO `sys_permission` (`uid`, `parent_uid`, `permission_name`, `permission_code`, `permission_type`, `request_method`, `menu_url`, `menu_component`, `permission_level`, `sort`, `icon_style`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('999-03-04', '999-03', '删除', 'sysUser:delete', 1, NULL, NULL, NULL, NULL, 4, NULL, NULL, '2024-08-09 16:35:12', NULL, NULL);
INSERT INTO `sys_permission` (`uid`, `parent_uid`, `permission_name`, `permission_code`, `permission_type`, `request_method`, `menu_url`, `menu_component`, `permission_level`, `sort`, `icon_style`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('999-04', '999', '组织管理', 'sysOrganization', 0, NULL, '/authUser/sysOrganization', 'system/SysOrganization.vue', NULL, 0, 'organization', NULL, '2024-08-09 16:35:12', NULL, NULL);
INSERT INTO `sys_permission` (`uid`, `parent_uid`, `permission_name`, `permission_code`, `permission_type`, `request_method`, `menu_url`, `menu_component`, `permission_level`, `sort`, `icon_style`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('999-04-01', '999-04', '查询', 'sysOrganization:query', 1, NULL, NULL, NULL, NULL, 1, NULL, NULL, '2024-08-09 16:35:12', NULL, NULL);
INSERT INTO `sys_permission` (`uid`, `parent_uid`, `permission_name`, `permission_code`, `permission_type`, `request_method`, `menu_url`, `menu_component`, `permission_level`, `sort`, `icon_style`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('999-04-02', '999-04', '新增', 'sysOrganization:add', 1, NULL, NULL, NULL, NULL, 2, NULL, NULL, '2024-08-09 16:35:12', NULL, NULL);
INSERT INTO `sys_permission` (`uid`, `parent_uid`, `permission_name`, `permission_code`, `permission_type`, `request_method`, `menu_url`, `menu_component`, `permission_level`, `sort`, `icon_style`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('999-04-03', '999-04', '修改', 'sysOrganization:update', 1, NULL, NULL, NULL, NULL, 3, NULL, NULL, '2024-08-09 16:35:12', NULL, NULL);
INSERT INTO `sys_permission` (`uid`, `parent_uid`, `permission_name`, `permission_code`, `permission_type`, `request_method`, `menu_url`, `menu_component`, `permission_level`, `sort`, `icon_style`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('999-04-04', '999-04', '删除', 'sysOrganization:delete', 1, NULL, NULL, NULL, NULL, 4, NULL, NULL, '2024-08-09 16:35:12', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色uid',
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色编码',
  `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `deleteable_flag` tinyint DEFAULT '1' COMMENT '(0表示不可删除 1表示可删除）',
  `role_type` int DEFAULT NULL COMMENT '角色类型(0代表超级管理员  1其他)',
  `data_permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据权限',
  `create_user_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人uid',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `edit_user_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人uid',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`uid`, `role_name`, `role_code`, `description`, `deleteable_flag`, `role_type`, `data_permission`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('0000', '超级管理员', 'SuperAdmin', '超级管理员', 0, 0, '全部', NULL, '2024-08-16 09:41:06', '0000', NULL);
INSERT INTO `sys_role` (`uid`, `role_name`, `role_code`, `description`, `deleteable_flag`, `role_type`, `data_permission`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('0001', '业务员', 'Business', NULL, 1, 1, '个人', '0000', '2024-08-16 09:41:07', NULL, NULL);
INSERT INTO `sys_role` (`uid`, `role_name`, `role_code`, `description`, `deleteable_flag`, `role_type`, `data_permission`, `create_user_uid`, `create_time`, `edit_user_uid`, `edit_time`) VALUES ('0002', '运营', 'Operate', NULL, 1, 1, '组织', '0000', '2024-08-16 09:41:08', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `role_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色uid',
  `permission_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限uid',
  PRIMARY KEY (`role_uid`,`permission_uid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='角色权限表';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0000', '998');
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0000', '998-01');
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0000', '998-01-01');
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0000', '998-01-02');
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0000', '998-01-03');
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0000', '998-01-04');
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0000', '999');
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0000', '999-01');
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0000', '999-01-01');
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0000', '999-01-02');
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0000', '999-01-03');
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0000', '999-01-04');
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0000', '999-02');
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0000', '999-02-01');
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0000', '999-02-02');
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0000', '999-02-03');
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0000', '999-02-04');
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0000', '999-03');
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0000', '999-03-01');
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0000', '999-03-02');
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0000', '999-03-03');
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0000', '999-03-04');
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0000', '999-04');
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0000', '999-04-01');
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0000', '999-04-02');
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0000', '999-04-03');
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0000', '999-04-04');
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0001', '999');
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0001', '999-01');
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0001', '999-01-01');
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0001', '999-04');
INSERT INTO `sys_role_permission` (`role_uid`, `permission_uid`) VALUES ('0001', '999-04-01');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户uid',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `user_nick` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户名称',
  `pass_word` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '密码',
  `salt` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '盐值',
  `open_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公开uid',
  `sex` tinyint(1) DEFAULT '0' COMMENT '性别（0男1女）',
  `birthday` date DEFAULT NULL COMMENT '出生年月日',
  `user_status` int DEFAULT '0' COMMENT '状态（-1删除、0激活、1禁用、2待验证）',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `profile_picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '所在地区（省）',
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '所在地区（市）',
  `area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '所在地区（区、县）',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系地址',
  `create_user_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人uid',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人uid',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`uid`) USING BTREE,
  UNIQUE KEY `partner_id` (`user_name`) USING BTREE,
  UNIQUE KEY `unique_mb` (`mobile`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`uid`, `user_name`, `user_nick`, `pass_word`, `salt`, `open_uid`, `sex`, `birthday`, `user_status`, `email`, `profile_picture`, `mobile`, `province`, `city`, `area`, `address`, `create_user_uid`, `create_time`, `update_user_uid`, `update_time`) VALUES ('0000', 'admin', '超人', '$2a$10$iI/Z9Ahjh3mIeeI3NqSjC./irwwToB8U6hM7jQg/oXfzVEhI7mDbi', NULL, '', 0, NULL, 0, NULL, '/static/profilePicture/1.png', NULL, NULL, NULL, NULL, NULL, NULL, '2024-09-11 10:12:23', NULL, NULL);
INSERT INTO `sys_user` (`uid`, `user_name`, `user_nick`, `pass_word`, `salt`, `open_uid`, `sex`, `birthday`, `user_status`, `email`, `profile_picture`, `mobile`, `province`, `city`, `area`, `address`, `create_user_uid`, `create_time`, `update_user_uid`, `update_time`) VALUES ('580a5273f86442bdac1261bb2d435b58', 'admin1', '管理员', '$2a$10$UKJUAgLlRWZUd6Z8hhWYCOho20ogcjIIfTVCI44HW96BXp8QAc/1a', NULL, '91cb16d8bd08443cae19acf09c2def5b', NULL, NULL, -1, NULL, '/static/profilePicture/3f0386c4174e417a8197781df82559fb.png', NULL, NULL, NULL, NULL, NULL, '0000', NULL, '0000', '2024-09-15 12:11:11');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_organization
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_organization`;
CREATE TABLE `sys_user_organization` (
  `user_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户uid',
  `organization_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织uid',
  PRIMARY KEY (`user_uid`,`organization_uid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户组织关系表';

-- ----------------------------
-- Records of sys_user_organization
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_organization` (`user_uid`, `organization_uid`) VALUES ('0000', '0000');
INSERT INTO `sys_user_organization` (`user_uid`, `organization_uid`) VALUES ('580a5273f86442bdac1261bb2d435b58', '58d8a795a32e4dbca7708c5d14e9f312');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户uid',
  `role_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色uid',
  PRIMARY KEY (`user_uid`,`role_uid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户角色关系表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`user_uid`, `role_uid`) VALUES ('0000', '0000');
INSERT INTO `sys_user_role` (`user_uid`, `role_uid`) VALUES ('580a5273f86442bdac1261bb2d435b58', '0001');
COMMIT;

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'uid',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '姓名',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别（0男 1女）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='测试表';

-- ----------------------------
-- Records of test
-- ----------------------------
BEGIN;
INSERT INTO `test` (`uid`, `name`, `sex`, `create_time`) VALUES ('1', '陈浩南', 0, '2017-11-02 08:42:59');
INSERT INTO `test` (`uid`, `name`, `sex`, `create_time`) VALUES ('2', 'bb1', 1, '2017-11-02 08:42:59');
INSERT INTO `test` (`uid`, `name`, `sex`, `create_time`) VALUES ('3', '苏大', 0, '2017-11-01 08:43:03');
INSERT INTO `test` (`uid`, `name`, `sex`, `create_time`) VALUES ('4', '王二小', 0, '2017-11-04 08:43:03');
INSERT INTO `test` (`uid`, `name`, `sex`, `create_time`) VALUES ('5', '秦仓', 0, '2017-10-30 08:43:03');
INSERT INTO `test` (`uid`, `name`, `sex`, `create_time`) VALUES ('6', '魏武', 0, '2017-11-06 08:43:03');
INSERT INTO `test` (`uid`, `name`, `sex`, `create_time`) VALUES ('7', '111', 1, '2017-11-06 08:43:03');
INSERT INTO `test` (`uid`, `name`, `sex`, `create_time`) VALUES ('8', '222', 2, '2017-11-06 08:43:03');
INSERT INTO `test` (`uid`, `name`, `sex`, `create_time`) VALUES ('9', '333', 3, '2017-11-06 08:43:03');
INSERT INTO `test` (`uid`, `name`, `sex`, `create_time`) VALUES ('d6351535b2e848e3a5457b6ed415d1c4', 'aa', 0, '2017-11-02 08:42:59');
INSERT INTO `test` (`uid`, `name`, `sex`, `create_time`) VALUES ('e7c4486757484932a03ce4bccd95a649', 'bb', 1, '2017-11-02 08:42:59');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

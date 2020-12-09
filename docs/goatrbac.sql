/*
Navicat MySQL Data Transfer

Source Server         : 47.98.148.84
Source Server Version : 80013
Source Host           : 47.98.148.84:3306
Source Database       : goatrbac

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2020-12-09 09:26:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` int(32) NOT NULL AUTO_INCREMENT COMMENT 'id值',
  `parent_id` int(32) DEFAULT NULL COMMENT '上级部门',
  `ancestors` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '祖级列表',
  `dept_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `dept_sort` int(5) DEFAULT NULL COMMENT '排序',
  `dept_status` bit(1) NOT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('20', '0', '0', '美亚学校', '1', '', '2020-08-28 22:49:22', '2020-09-19 14:12:48');
INSERT INTO `sys_dept` VALUES ('29', '20', '0,20', '校长', '1', '', '2020-08-29 05:28:43', '2020-09-19 14:12:48');
INSERT INTO `sys_dept` VALUES ('36', '29', '0,20,29', '教师', '3', '', '2020-10-27 13:08:19', '2020-10-27 13:08:19');
INSERT INTO `sys_dept` VALUES ('37', '36', '0,20,29,36', '教师一组', '1', '', '2020-09-08 20:18:46', '2020-09-08 20:18:46');
INSERT INTO `sys_dept` VALUES ('38', '36', '0,20,29,36', '教师二组', '2', '', '2020-09-08 20:18:57', '2020-09-08 20:18:57');
INSERT INTO `sys_dept` VALUES ('39', '36', '0,20,29,36', '教师三组', '3', '', '2020-09-08 20:19:07', '2020-09-08 20:19:07');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `dict_id` int(32) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dict_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '字典名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '字典描述',
  `sort` int(32) DEFAULT NULL COMMENT '字典排序',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dict_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '性别', '性别字典', '1', 'admin', 'admin', '2020-11-07 15:06:18', '2020-11-07 15:06:20');

-- ----------------------------
-- Table structure for sys_dict_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_detail`;
CREATE TABLE `sys_dict_detail` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT 'id值',
  `dict_id` int(32) DEFAULT NULL COMMENT '字典id',
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '字典标签',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '字典值',
  `sort` int(32) DEFAULT NULL COMMENT '字典详情排序',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_dict_detail
-- ----------------------------
INSERT INTO `sys_dict_detail` VALUES ('1', '1', '男', '1', '1', null, null, null, null);
INSERT INTO `sys_dict_detail` VALUES ('2', '1', '女', '2', '2', null, null, null, null);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` int(32) NOT NULL AUTO_INCREMENT COMMENT 'id值',
  `parent_id` int(32) NOT NULL COMMENT '父级菜单id',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单图标',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'url',
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限',
  `sort` int(12) DEFAULT NULL COMMENT '排序',
  `type` tinyint(1) NOT NULL COMMENT '类型',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '工作空间', 'layui-icon layui-icon-console', '', '', '1', '0', '2020-07-13 20:14:26', '2020-07-14 14:38:28');
INSERT INTO `sys_menu` VALUES ('2', '1', '控制后台', 'layui-icon layui-icon-console', '/api/console', '', '2', '1', '2020-07-13 20:19:02', '2020-07-13 20:19:08');
INSERT INTO `sys_menu` VALUES ('3', '0', '系统管理', 'layui-icon layui-icon-set-fill', '', '', '3', '0', '2020-07-10 09:33:00', '2020-07-12 21:03:22');
INSERT INTO `sys_menu` VALUES ('4', '3', '用户管理', 'layui-icon layui-icon-username', '/api/user/index', 'user:list', '4', '1', '2020-07-10 09:33:33', '2020-07-13 09:30:12');
INSERT INTO `sys_menu` VALUES ('5', '3', '角色管理', 'layui-icon layui-icon layui-icon-user', '/api/role/index', 'role:list', '5', '1', '2020-07-10 09:34:17', '2020-11-04 06:28:36');
INSERT INTO `sys_menu` VALUES ('6', '3', '菜单管理', 'layui-icon layui-icon-vercode', '/api/menu/index', 'menu:list', '6', '1', '2020-07-10 09:34:50', '2020-07-10 09:34:53');
INSERT INTO `sys_menu` VALUES ('7', '0', '系统监控', 'layui-icon layui-icon-console', '', '', '7', '0', '2020-07-10 09:35:20', '2020-07-12 20:58:31');
INSERT INTO `sys_menu` VALUES ('8', '7', 'SQL监控', 'layui-icon layui-icon-chart', '/druid/login', '', '8', '1', '2020-07-10 09:35:50', '2020-07-10 09:35:53');
INSERT INTO `sys_menu` VALUES ('9', '7', '接口文档', 'layui-icon layui-icon-chart', '/swagger-ui.html', null, '9', '1', '2020-07-10 09:36:11', '2020-07-12 20:04:57');
INSERT INTO `sys_menu` VALUES ('10', '0', '错误页面', 'layui-icon layui-icon-auz', null, null, '10', '0', '2020-07-14 13:31:16', '2020-07-14 13:31:24');
INSERT INTO `sys_menu` VALUES ('11', '10', '403', 'layui-icon layui-icon layui-icon-face-cry', '/api/403', '', '11', '1', '2020-07-14 13:36:47', '2020-08-23 12:05:38');
INSERT INTO `sys_menu` VALUES ('12', '10', '404', 'layui-icon layui-icon-face-cry', '/api/404', null, '12', '1', '2020-07-14 13:37:22', '2020-07-14 13:37:25');
INSERT INTO `sys_menu` VALUES ('13', '10', '500', 'layui-icon layui-icon layui-icon layui-icon layui-icon layui-icon layui-icon-face-cry', '/api/500', '', '13', '1', '2020-07-14 13:38:09', '2020-08-27 14:59:07');
INSERT INTO `sys_menu` VALUES ('14', '4', '用户新增', null, null, 'user:add', '4', '2', '2020-07-10 09:36:41', '2020-07-10 09:36:44');
INSERT INTO `sys_menu` VALUES ('15', '4', '用户编辑', null, null, 'user:edit', '4', '2', '2020-07-10 09:37:16', '2020-07-10 09:37:18');
INSERT INTO `sys_menu` VALUES ('16', '4', '用户删除', null, null, 'user:del', '4', '2', '2020-07-10 09:37:38', '2020-07-10 09:37:40');
INSERT INTO `sys_menu` VALUES ('17', '5', '角色新增', null, null, 'role:add', '5', '2', '2020-07-10 09:38:02', '2020-07-10 09:38:05');
INSERT INTO `sys_menu` VALUES ('18', '5', '角色编辑', null, null, 'role:edit', '5', '2', '2020-07-10 09:38:30', '2020-07-10 09:38:32');
INSERT INTO `sys_menu` VALUES ('19', '5', '角色删除', null, null, 'role:del', '5', '2', '2020-07-10 09:38:51', '2020-07-10 09:38:54');
INSERT INTO `sys_menu` VALUES ('20', '6', '菜单新增', null, null, 'menu:add', '6', '2', '2020-07-10 09:39:16', '2020-07-10 09:39:21');
INSERT INTO `sys_menu` VALUES ('21', '6', '菜单修改', null, null, 'menu:edit', '6', '2', '2020-07-10 09:39:46', '2020-07-10 09:39:48');
INSERT INTO `sys_menu` VALUES ('22', '6', '菜单删除', null, null, 'menu:del', '6', '2', '2020-07-10 09:40:08', '2020-07-10 09:40:10');
INSERT INTO `sys_menu` VALUES ('35', '7', '操作日志', 'layui-icon-group', '/api/log/index', 'log:list', '7', '1', '2020-08-04 11:38:45', '2020-08-04 11:38:58');
INSERT INTO `sys_menu` VALUES ('36', '7', '异常日志', 'layui-icon-face-cry', '/api/log/error/index', 'errorLog:list', '7', '1', '2020-08-04 11:42:22', '2020-08-04 11:42:22');
INSERT INTO `sys_menu` VALUES ('66', '35', '日志删除', 'layui-icon ', '', 'log:del', '7', '2', '2020-08-09 15:16:03', '2020-08-09 15:16:03');
INSERT INTO `sys_menu` VALUES ('67', '36', '异常日志删除', 'layui-icon layui-icon layui-icon ', '', 'errorLog:del', '7', '2', '2020-08-09 15:16:30', '2020-08-09 15:16:59');
INSERT INTO `sys_menu` VALUES ('68', '3', '部门管理', 'layui-icon layui-icon layui-icon layui-icon-group', '/api/dept/index', 'dept:list', '7', '1', '2020-08-19 15:03:27', '2020-08-23 16:34:51');
INSERT INTO `sys_menu` VALUES ('69', '3', '岗位管理', 'layui-icon layui-icon layui-icon layui-icon layui-icon-group', '/api/post/index', 'post:list', '8', '1', '2020-08-19 16:31:46', '2020-12-08 14:50:19');
INSERT INTO `sys_menu` VALUES ('70', '0', '系统工具', 'layui-icon layui-icon layui-icon-app', '', '', '8', '0', '2020-08-19 18:43:13', '2020-08-20 11:43:19');
INSERT INTO `sys_menu` VALUES ('71', '70', '定时任务', 'layui-icon layui-icon-play', '/api/quartz/index', '', '9', '1', '2020-08-21 09:17:54', '2020-08-21 09:20:37');
INSERT INTO `sys_menu` VALUES ('75', '69', '班级新增', 'layui-icon ', '', 'post:add', '9', '2', '2020-08-23 16:32:59', '2020-08-23 16:32:59');
INSERT INTO `sys_menu` VALUES ('76', '69', '班级修改', 'layui-icon ', '', 'post:edit', '10', '2', '2020-08-23 16:33:36', '2020-08-23 16:33:36');
INSERT INTO `sys_menu` VALUES ('77', '69', '班级删除', 'layui-icon ', '', 'post:del', '10', '2', '2020-08-23 16:34:08', '2020-08-23 16:34:08');
INSERT INTO `sys_menu` VALUES ('78', '68', '部门新增', 'layui-icon ', '', 'dept:add', '8', '2', '2020-08-23 16:34:39', '2020-08-23 16:34:39');
INSERT INTO `sys_menu` VALUES ('79', '68', '部门修改', 'layui-icon ', '', 'dept:edit', '9', '2', '2020-08-23 16:35:18', '2020-08-23 16:35:18');
INSERT INTO `sys_menu` VALUES ('80', '68', '部门删除', 'layui-icon ', '', 'dept:del', '10', '2', '2020-08-23 16:35:41', '2020-08-23 16:35:41');
INSERT INTO `sys_menu` VALUES ('81', '7', '在线用户', 'layui-icon layui-icon layui-icon layui-icon-username', '/api/online/index', '', '7', '1', '2020-08-26 14:34:16', '2020-08-26 14:38:16');
INSERT INTO `sys_menu` VALUES ('82', '70', '代码生成', 'layui-icon layui-icon layui-icon-fonts-code', '/api/generator/index', '', '10', '1', '2020-08-26 14:35:10', '2020-08-26 14:37:56');
INSERT INTO `sys_menu` VALUES ('84', '3', '字典管理', 'layui-icon layui-icon layui-icon-cellphone-fine', '/api/dict/index', 'dict:list', '9', '1', '2020-08-29 16:34:59', '2020-09-19 15:58:07');
INSERT INTO `sys_menu` VALUES ('85', '84', '字典新增', 'layui-icon layui-icon layui-icon ', '', 'dict:add', '1', '2', '2020-08-29 16:36:25', '2020-09-19 15:57:10');
INSERT INTO `sys_menu` VALUES ('86', '84', '字典修改', 'layui-icon layui-icon ', '', 'dict:edit', '2', '2', '2020-08-29 16:37:28', '2020-09-19 17:02:23');
INSERT INTO `sys_menu` VALUES ('87', '84', '字典删除', 'layui-icon layui-icon ', '', 'dict:del', '3', '2', '2020-08-29 16:37:50', '2020-09-19 16:36:55');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `post_id` int(32) NOT NULL AUTO_INCREMENT COMMENT 'id值',
  `post_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位名称',
  `post_status` bit(1) DEFAULT NULL COMMENT '岗位状态',
  `post_sort` int(5) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `parent_id` int(32) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL COMMENT '部门id',
  PRIMARY KEY (`post_id`),
  UNIQUE KEY `sys_post_post_name_uindex` (`post_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='班级';

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES ('1', '三年一班', '', '5', '2020-08-19 11:14:55', '2020-11-30 13:35:25', '0', null);
INSERT INTO `sys_post` VALUES ('2', '二年一班', '', '3', '2020-08-19 11:15:30', '2020-11-30 13:35:10', '0', null);
INSERT INTO `sys_post` VALUES ('3', '二年二班', '', '4', '2020-08-19 11:16:19', '2020-11-30 13:35:17', '0', null);
INSERT INTO `sys_post` VALUES ('16', '三年二班', '', '6', '2020-08-28 02:32:15', '2020-11-30 13:35:31', '0', null);
INSERT INTO `sys_post` VALUES ('31', '一年二班', '', '2', '2020-08-28 23:45:37', '2020-11-30 13:34:57', null, null);
INSERT INTO `sys_post` VALUES ('34', '一年一班', '', '1', '2020-09-02 02:58:45', '2020-09-02 02:58:45', null, null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(32) NOT NULL AUTO_INCREMENT COMMENT 'id值',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `data_scope` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超管', '超级管理员，拥有所有权限', '2020-07-10 09:40:35', '2020-12-08 15:22:14', '1');
INSERT INTO `sys_role` VALUES ('15', '校长', '校长', '2020-10-27 13:06:47', '2020-12-08 15:22:20', '4');
INSERT INTO `sys_role` VALUES ('22', '教师', '教师', '2020-09-08 21:48:15', '2020-12-08 15:22:24', '4');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `role_id` int(32) NOT NULL COMMENT '角色id',
  `dept_id` int(32) NOT NULL COMMENT '部门id',
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户数据权限过滤';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` int(32) NOT NULL COMMENT '角色id',
  `menu_id` int(32) NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '3');
INSERT INTO `sys_role_menu` VALUES ('1', '4');
INSERT INTO `sys_role_menu` VALUES ('1', '5');
INSERT INTO `sys_role_menu` VALUES ('1', '6');
INSERT INTO `sys_role_menu` VALUES ('1', '14');
INSERT INTO `sys_role_menu` VALUES ('1', '15');
INSERT INTO `sys_role_menu` VALUES ('1', '16');
INSERT INTO `sys_role_menu` VALUES ('1', '17');
INSERT INTO `sys_role_menu` VALUES ('1', '18');
INSERT INTO `sys_role_menu` VALUES ('1', '19');
INSERT INTO `sys_role_menu` VALUES ('1', '20');
INSERT INTO `sys_role_menu` VALUES ('1', '21');
INSERT INTO `sys_role_menu` VALUES ('1', '22');
INSERT INTO `sys_role_menu` VALUES ('1', '68');
INSERT INTO `sys_role_menu` VALUES ('1', '69');
INSERT INTO `sys_role_menu` VALUES ('1', '75');
INSERT INTO `sys_role_menu` VALUES ('1', '76');
INSERT INTO `sys_role_menu` VALUES ('1', '77');
INSERT INTO `sys_role_menu` VALUES ('1', '78');
INSERT INTO `sys_role_menu` VALUES ('1', '79');
INSERT INTO `sys_role_menu` VALUES ('1', '80');
INSERT INTO `sys_role_menu` VALUES ('1', '84');
INSERT INTO `sys_role_menu` VALUES ('1', '85');
INSERT INTO `sys_role_menu` VALUES ('1', '86');
INSERT INTO `sys_role_menu` VALUES ('1', '87');
INSERT INTO `sys_role_menu` VALUES ('15', '3');
INSERT INTO `sys_role_menu` VALUES ('15', '4');
INSERT INTO `sys_role_menu` VALUES ('15', '14');
INSERT INTO `sys_role_menu` VALUES ('15', '15');
INSERT INTO `sys_role_menu` VALUES ('15', '16');
INSERT INTO `sys_role_menu` VALUES ('15', '68');
INSERT INTO `sys_role_menu` VALUES ('15', '69');
INSERT INTO `sys_role_menu` VALUES ('15', '75');
INSERT INTO `sys_role_menu` VALUES ('15', '76');
INSERT INTO `sys_role_menu` VALUES ('15', '77');
INSERT INTO `sys_role_menu` VALUES ('15', '78');
INSERT INTO `sys_role_menu` VALUES ('15', '79');
INSERT INTO `sys_role_menu` VALUES ('15', '80');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `user_id` int(32) NOT NULL COMMENT '用户id',
  `role_id` int(32) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('1', '1');
INSERT INTO `sys_role_user` VALUES ('32', '15');
INSERT INTO `sys_role_user` VALUES ('34', '15');
INSERT INTO `sys_role_user` VALUES ('49', '22');
INSERT INTO `sys_role_user` VALUES ('50', '22');
INSERT INTO `sys_role_user` VALUES ('51', '22');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(32) NOT NULL AUTO_INCREMENT COMMENT 'id值',
  `dept_id` int(32) DEFAULT NULL COMMENT '部门id',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `status` tinyint(1) NOT NULL COMMENT '状态 1启用 0禁用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '20', 'admin', '$2a$10$pAuzCLIe6Sl7kXfX6FEQ1uzM79V2njg.KtL9qawg9JkW7e1f417k2', '管理员', '18604232063', '642744551@qq.com', '1', '2020-07-10 09:42:03', '2020-08-23 16:24:34');
INSERT INTO `sys_user` VALUES ('32', '20', 'ljl', '$2a$10$pAuzCLIe6Sl7kXfX6FEQ1uzM79V2njg.KtL9qawg9JkW7e1f417k2', '李金龙', '15668500707', '15668500707@qq.com', '1', '2020-10-27 13:12:00', '2020-11-16 05:48:50');
INSERT INTO `sys_user` VALUES ('34', '20', 'zdk', '$2a$10$ydjyZ1vbygq8Jh18c4RNEuU3zoGtrMW0nCj99NumGmnPEVy49o0.C', '张大琨', '13050137977', '13050137977@qq.com', '1', '2020-10-27 13:34:23', '2020-11-16 05:49:02');
INSERT INTO `sys_user` VALUES ('49', '37', 'tfm', '$2a$10$DGydgjANREs5IpjbZvG3nOGYvu6mFVRs7c9chdAip5Aewn7JMiTnW', '田芳茗11 12', '18341325952', 'jinlongdakun@126.com', '1', '2020-10-29 11:32:11', '2020-11-18 06:24:26');
INSERT INTO `sys_user` VALUES ('50', '38', 'tmx', '$2a$10$gKS3eOR3TwHV0kpBCwoM/OpnkHo7preeiEZPr9.NQV8HjOSrW64Vq', '谭茗欣32', '13842378356', 'jinongdakun@126.com', '1', '2020-10-29 11:34:47', '2020-11-18 06:24:30');
INSERT INTO `sys_user` VALUES ('51', '39', 'lx', '$2a$10$/53mAFluYg873MMagEbw7.OgadBgiIxWMB9ozaEXd0wv8DMdQ6eWK', '刘希31', '13941352122', 'jinongdakun@126.com', '1', '2020-10-29 11:35:53', '2020-11-18 06:24:33');

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post` (
  `user_id` int(32) NOT NULL COMMENT '岗位id',
  `post_id` int(32) NOT NULL COMMENT '工作id',
  PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES ('49', '31');
INSERT INTO `sys_user_post` VALUES ('49', '34');
INSERT INTO `sys_user_post` VALUES ('50', '16');
INSERT INTO `sys_user_post` VALUES ('51', '1');

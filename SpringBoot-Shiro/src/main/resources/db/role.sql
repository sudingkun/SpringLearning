/*
 Navicat Premium Data Transfer

 Source Server         : 47.106.95.195
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 47.106.95.195:3306
 Source Schema         : spring

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 30/08/2019 10:41:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for shiro_role
-- ----------------------------
DROP TABLE IF EXISTS `shiro_role`;
CREATE TABLE `shiro_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父角色',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `enname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色英文名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shiro_role
-- ----------------------------
INSERT INTO `shiro_role` VALUES (1, 0, '超级管理员', 'admin');
INSERT INTO `shiro_role` VALUES (2, 1, '管理员', 'root');

SET FOREIGN_KEY_CHECKS = 1;

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

 Date: 30/08/2019 10:41:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for shiro_permission
-- ----------------------------
DROP TABLE IF EXISTS `shiro_permission`;
CREATE TABLE `shiro_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父权限',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
  `enName` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限英文名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '授权路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shiro_permission
-- ----------------------------
INSERT INTO `shiro_permission` VALUES (1, 0, '所有', 'all', '/');
INSERT INTO `shiro_permission` VALUES (2, 1, '新增', 'add', '/add');
INSERT INTO `shiro_permission` VALUES (3, 1, '删除', 'delete', '/delete');

SET FOREIGN_KEY_CHECKS = 1;

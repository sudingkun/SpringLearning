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

 Date: 30/08/2019 10:41:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for shiro_user
-- ----------------------------
DROP TABLE IF EXISTS `shiro_user`;
CREATE TABLE `shiro_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码，加密存储',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '加密的盐(使用用户名作为盐)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shiro_user
-- ----------------------------
INSERT INTO `shiro_user` VALUES (1, 'admin', '928bfd2577490322a6e19b793691467e', 'admin');
INSERT INTO `shiro_user` VALUES (2, 'root', '31f123ba21dc3d21a9b54a7a78f65829', 'root');

SET FOREIGN_KEY_CHECKS = 1;

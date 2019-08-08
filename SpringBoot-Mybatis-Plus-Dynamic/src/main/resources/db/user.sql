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

 Date: 01/08/2019 21:20:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `age` int(255) NULL DEFAULT NULL COMMENT '用户年龄',
  `deleted` int(255) NULL DEFAULT 0 COMMENT '逻辑删除字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, 'tom', 11, 0);
INSERT INTO `user` VALUES (3, 'tom', 12, 0);
INSERT INTO `user` VALUES (4, 'test', 11, 0);
INSERT INTO `user` VALUES (5, 'testMybatis', 20, 0);
INSERT INTO `user` VALUES (6, 'testMybatis1', 21, 0);
INSERT INTO `user` VALUES (7, 'testMybatis2', 22, 0);
INSERT INTO `user` VALUES (8, 'testMybatis3', 23, 0);

SET FOREIGN_KEY_CHECKS = 1;

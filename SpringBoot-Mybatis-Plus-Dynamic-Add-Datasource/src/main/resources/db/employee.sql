/*
 Navicat Premium Data Transfer

 Source Server         : 47.106.95.195
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 47.106.95.195:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 08/08/2019 15:15:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工姓名',
  `age` int(10) NULL DEFAULT NULL COMMENT '员工年龄',
  `salary` decimal(10, 2) NULL DEFAULT NULL COMMENT '工资',
  `dept_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('12', '1_tom', 20, 8000.00, '1');
INSERT INTO `employee` VALUES ('12345678912', '1_lisi', 21, 6000.00, '1');
INSERT INTO `employee` VALUES ('13456789452', '1_jerry', 18, 6666.00, '1');
INSERT INTO `employee` VALUES ('13456789456', '2_jack', 20, 5000.00, '2');

SET FOREIGN_KEY_CHECKS = 1;

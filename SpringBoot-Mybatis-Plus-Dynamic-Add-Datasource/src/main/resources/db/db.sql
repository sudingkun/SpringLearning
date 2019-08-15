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

 Date: 15/08/2019 17:24:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for db
-- ----------------------------
DROP TABLE IF EXISTS `db`;
CREATE TABLE `db`  (
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'name',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'username',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'password',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'url',
  `driver_class_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'driverClassName',
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'db' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of db
-- ----------------------------
INSERT INTO `db` VALUES ('dynamic_master', 'root', '123456', 'jdbc:mysql://we/dynamic_master?useUnicode=true&characterEncoding=utf-8&useSSL=false', 'com.mysql.jdbc.Driver');
INSERT INTO `db` VALUES ('slave', 'root', '123456', 'jdbc:mysql://we/test?useUnicode=true&characterEncoding=utf-8&useSSL=false', 'com.mysql.jdbc.Driver');

SET FOREIGN_KEY_CHECKS = 1;

/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50528
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50528
 File Encoding         : 65001

 Date: 23/08/2022 16:43:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bonus
-- ----------------------------
DROP TABLE IF EXISTS `bonus`;
CREATE TABLE `bonus`  (
  `ENAME` varchar(10) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `JOB` varchar(9) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `SAL` decimal(7, 2) NULL DEFAULT NULL,
  `COMM` decimal(7, 2) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept`  (
  `DEPTNO` int(2) NOT NULL,
  `DNAME` varchar(14) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `LOC` varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  PRIMARY KEY (`DEPTNO`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for emp
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp`  (
  `EMPNO` int(4) NOT NULL,
  `ENAME` varchar(10) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `JOB` varchar(9) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `MGR` int(4) NULL DEFAULT NULL,
  `HIREDATE` datetime NULL DEFAULT NULL,
  `SAL` decimal(7, 2) NULL DEFAULT NULL,
  `COMM` decimal(7, 2) NULL DEFAULT NULL,
  `DEPTNO` int(2) NULL DEFAULT NULL,
  PRIMARY KEY (`EMPNO`) USING BTREE,
  INDEX `FK_DEPTNO`(`DEPTNO`) USING BTREE,
  CONSTRAINT `FK_DEPTNO` FOREIGN KEY (`DEPTNO`) REFERENCES `dept` (`DEPTNO`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for salgrade
-- ----------------------------
DROP TABLE IF EXISTS `salgrade`;
CREATE TABLE `salgrade`  (
  `GRADE` int(2) NULL DEFAULT NULL,
  `LOSAL` decimal(7, 2) NULL DEFAULT NULL,
  `HISAL` decimal(7, 2) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;

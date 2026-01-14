/*
 Navicat Premium Dump SQL

 Source Server         : 20251014
 Source Server Type    : MySQL
 Source Server Version : 90300 (9.3.0)
 Source Host           : localhost:3306
 Source Schema         : school

 Target Server Type    : MySQL
 Target Server Version : 90300 (9.3.0)
 File Encoding         : 65001

 Date: 14/10/2025 11:00:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
    `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号（如用户名）',
    `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码（建议存储加密后的密码）',
    `role` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号等级/角色，如：student, teacher, admin',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `last_login_time` datetime NULL DEFAULT NULL COMMENT '最近登录时间',
    `deleted` int NOT NULL DEFAULT 0 COMMENT '删除标记，0表示未删除，1表示软删除，再删就是硬删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `username_unique`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户账号表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
    `number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学号',
    `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
    `sex` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
    `age` int NULL DEFAULT NULL COMMENT '年龄',
    `major` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '专业',
    PRIMARY KEY (`number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学生表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
    `number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '教师编号',
    `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '教师姓名',
    `sex` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
    `age` int NULL DEFAULT NULL COMMENT '年龄',
    `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '职称，如：教授、副教授、讲师',
    `department` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所属院系/部门',
    `hire_date` date NULL DEFAULT NULL COMMENT '入职日期',
    PRIMARY KEY (`number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '教师表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
    `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程号',
    `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课程名',
    `score` decimal(3, 1) NULL DEFAULT NULL COMMENT '学分',
    `teacher` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '任课教师',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '课程表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sc
-- ----------------------------
DROP TABLE IF EXISTS `sc`;
CREATE TABLE `sc`  (
    `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '选课号',
    `sno` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学号',
    `cid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课程号',
    `semester` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学期',
    `class_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上课时间',
    `grade` decimal(5, 2) NULL DEFAULT NULL COMMENT '成绩',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `sno`(`sno` ASC) USING BTREE,
    INDEX `cid`(`cid` ASC) USING BTREE,
    CONSTRAINT `sc_ibfk_1` FOREIGN KEY (`sno`) REFERENCES `student` (`number`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `sc_ibfk_2` FOREIGN KEY (`cid`) REFERENCES `course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学生选课表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, 'admin', 'admin123', 'admin', '2025-10-14 10:58:42', '2025-10-14 10:58:42', 0);
INSERT INTO `account` VALUES (2, 'student001', '123456', 'student', '2025-10-14 10:58:42', NULL, 0);
INSERT INTO `account` VALUES (3, 'teacher001', 'teacher123', 'teacher', '2025-10-14 10:58:42', NULL, 0);

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('2023001', '张三', '男', 20, '信息安全');
INSERT INTO `student` VALUES ('2023002', '李四', '女', 19, '计算机科学与技术');
INSERT INTO `student` VALUES ('2023003', '王五', '男', 21, '软件工程');
INSERT INTO `student` VALUES ('2023004', '赵六', '女', 20, '信息安全');
INSERT INTO `student` VALUES ('2023005', '孙七', '男', 19, '计算机科学与技术');

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('T001', '马老师', '男', 45, '教授', '数学与信息学院', '2005-08-15');
INSERT INTO `teacher` VALUES ('T002', '陈老师', '男', 50, '副教授', '计算机学院', '2000-09-01');
INSERT INTO `teacher` VALUES ('T003', '吴老师', '女', 40, '副教授', '软件学院', '2010-03-12');
INSERT INTO `teacher` VALUES ('T004', '郑老师', '男', 38, '讲师', '计算机学院', '2015-07-22');
INSERT INTO `teacher` VALUES ('T005', '李老师', '女', 42, '教授', '算法与智能学院', '2008-11-05');

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('C001', '信息安全数学基础', 3.0, '马老师');
INSERT INTO `course` VALUES ('C002', '计算机组成原理', 4.0, '陈老师');
INSERT INTO `course` VALUES ('C003', '软件工程导论', 3.5, '吴老师');
INSERT INTO `course` VALUES ('C004', '编程语言基础', 3.0, '郑老师');
INSERT INTO `course` VALUES ('C005', '算法设计与分析', 4.5, '李老师');

-- ----------------------------
-- Records of sc
-- ----------------------------
INSERT INTO `sc` VALUES ('S001', '2023001', 'C001', '2023-2024-1', '周一第3-4节', 55.00);
INSERT INTO `sc` VALUES ('S002', '2023001', 'C002', '2023-2024-1', '周二第1-2节', 65.00);
INSERT INTO `sc` VALUES ('S003', '2023002', 'C002', '2023-2024-1', '周二第1-2节', 75.00);
INSERT INTO `sc` VALUES ('S004', '2023002', 'C003', '2023-2024-1', '周三第3-4节', 85.00);
INSERT INTO `sc` VALUES ('S005', '2023003', 'C003', '2023-2024-1', '周三第3-4节', 95.00);
INSERT INTO `sc` VALUES ('S006', '2023003', 'C004', '2023-2024-1', '周四第1-2节', 100.00);

SET FOREIGN_KEY_CHECKS = 1;
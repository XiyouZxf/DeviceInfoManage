/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : db_deviceinfo

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-06-05 21:46:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_no` varchar(20) NOT NULL,
  `admin_pass` varchar(20) NOT NULL DEFAULT '654321',
  `admin_name` varchar(100) NOT NULL,
  `admin_sex` varchar(5) NOT NULL,
  `admin_tel_num` varchar(20) DEFAULT NULL,
  `admin_addr` varchar(200) DEFAULT NULL,
  `admin_email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `admin_no` (`admin_no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '654321', '管理员', '男', '12345678901', '陕西长安区', '123@qq.com');

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `depart_id` int(11) NOT NULL AUTO_INCREMENT,
  `depart_identity` varchar(20) NOT NULL,
  `depart_name` varchar(100) NOT NULL,
  `depart_includePeople` int(5) NOT NULL,
  `depart_tel_num` varchar(20) NOT NULL,
  `depart_status` varchar(10) DEFAULT '运营中',
  `depart_desc` varchar(100) NOT NULL,
  PRIMARY KEY (`depart_id`),
  UNIQUE KEY `depart_identity` (`depart_identity`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', 'KS10', '生产部', '20', '02984933365', '运营中', '这是一个生产部');
INSERT INTO `department` VALUES ('2', 'KS11', '维修部', '5', '02984931234', '运营中', '这是一个维修部');
INSERT INTO `department` VALUES ('3', 'KS12', '质检科', '10', '02912345678', '运营中', '这是一个质检科');
INSERT INTO `department` VALUES ('4', 'KS13', '勤务部', '10', '02912345121', '运营中', '这是一个勤务部');
INSERT INTO `department` VALUES ('5', 'KS14', '随机部', '0', '12315213131', '运营中', '随机');

-- ----------------------------
-- Table structure for `device`
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `dev_id` int(20) NOT NULL AUTO_INCREMENT,
  `dev_identity` varchar(20) NOT NULL,
  `dev_name` varchar(20) DEFAULT NULL,
  `dev_specification` varchar(20) DEFAULT NULL,
  `dev_type` varchar(20) DEFAULT NULL,
  `dev_manufacturer` varchar(20) DEFAULT NULL,
  `dev_value` int(20) DEFAULT NULL,
  `dev_departId` int(11) DEFAULT NULL,
  `dev_installaddr` varchar(20) DEFAULT NULL,
  `dev_usingstatus` varchar(20) DEFAULT NULL,
  `dev_operater` varchar(20) DEFAULT NULL,
  `dev_buytime` date DEFAULT NULL,
  `dev_maintaintime` date DEFAULT NULL,
  `dev_identification` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`dev_id`),
  UNIQUE KEY `dev_identity` (`dev_identity`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES ('1', 'C001', '电力变压器01', 'S7-1250KVA/10KV', '动力设备', '长江电力设备', '14780000', '1', '1#101', '在用', '张三', '2019-01-01', '2019-03-01', '一般设备');
INSERT INTO `device` VALUES ('2', 'C002', '电力变压器02', 'S7-1251KVA/10KV', '动力设备', '长江电力设备', '14780000', '2', '1#101', '在用', '张三', '2019-01-01', '2019-03-01', '一般设备');
INSERT INTO `device` VALUES ('3', 'C003', '机床', 'KSA151', '机械设备', '北京机械厂', '2151212', '3', '2#101', '在用', '李四', '2019-05-02', '2019-05-30', '小型设备');
INSERT INTO `device` VALUES ('4', 'C004', '机床01', 'KSA152', '机械设备', '西安机械厂', '1221215', '1', '1#101', '在用', '王五五', '2019-05-02', '2019-05-01', '一般设备');

-- ----------------------------
-- Table structure for `employee`
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_no` varchar(20) NOT NULL,
  `emp_pass` varchar(20) NOT NULL DEFAULT '123456',
  `emp_name` varchar(100) NOT NULL,
  `emp_sex` varchar(5) NOT NULL,
  `emp_tel_num` varchar(20) DEFAULT NULL,
  `emp_addr` varchar(200) DEFAULT NULL,
  `emp_email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`emp_id`),
  UNIQUE KEY `emp_no` (`emp_no`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='员工表';

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', '000001', '123456', '张三', '男', '13311111111', '长安南路563号', '111@163.com');
INSERT INTO `employee` VALUES ('2', '000002', '123456', '李四', '男', '13322222222', '莲湖路22号', '222@21cn.com');
INSERT INTO `employee` VALUES ('3', '000003', '123456', '王五', '男', '13333333333', '东五路33号', '333@yeah.net');
INSERT INTO `employee` VALUES ('4', '000004', '123456', '刘六', '男', '13344444444', '西一路44号', '444@sina.com.cn');
INSERT INTO `employee` VALUES ('5', '000005', '123456', '郑七', '男', '13355555555', '南稍门55号', '555@tom.com');
INSERT INTO `employee` VALUES ('7', 'test01', '123456', '小赵', '女', '13312345678', '长安南路563号', 'xiaozhao@163.com');
INSERT INTO `employee` VALUES ('8', 'test02', '123456', '小王', '女', '13312345678', '五路口23号', 'xiaowang@yeah.net');
INSERT INTO `employee` VALUES ('9', 'test03', '123456', '小田', '男', '15712345678', '北大街123号', 'xiaotian@sina.com.cn');
INSERT INTO `employee` VALUES ('10', 'test04', '123456', '小胡', '男', '17812345678', '吉祥村65号', 'xiaohu@tom.com');
INSERT INTO `employee` VALUES ('11', 'test05', '123456', '小钱', '男', '15612345678', '西斜七路90号', 'xiaoqian@yahoo.com.cn');
INSERT INTO `employee` VALUES ('12', 'guest01', '123456', '老赵', '男', '13312345678', '长安南路001号', 'laozhao@163.com');
INSERT INTO `employee` VALUES ('13', 'guest02', '123456', '老王', '男', '13312345678', '五路口002号', 'laowang@yeah.net');
INSERT INTO `employee` VALUES ('14', 'guest03', '123456', '老田', '男', '15712345678', '北大街003号', 'laotian@sina.com.cn');
INSERT INTO `employee` VALUES ('15', 'guest04', '123456', '老胡', '男', '17812345678', '吉祥村004号', 'laohu@tom.com');
INSERT INTO `employee` VALUES ('20', 'guest06', '123456', '张欣瑶', '女', '13377777777', '长安南路77号', 'zxy@163.com');
INSERT INTO `employee` VALUES ('21', '000006', '老小', '123456', '女', '13411111111', '长安南路77号', '123@qq.com');

-- ----------------------------
-- Table structure for `examineschedule`
-- ----------------------------
DROP TABLE IF EXISTS `examineschedule`;
CREATE TABLE `examineschedule` (
  `exam_id` int(11) NOT NULL AUTO_INCREMENT,
  `exam_status` varchar(10) NOT NULL,
  `exam_devId` int(20) NOT NULL,
  `exam_way` varchar(20) NOT NULL,
  `exam_period` varchar(20) NOT NULL,
  `exam_lasttime` date NOT NULL,
  `exam_nexttime` date NOT NULL,
  `exam_desc` varchar(100) NOT NULL,
  PRIMARY KEY (`exam_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of examineschedule
-- ----------------------------
INSERT INTO `examineschedule` VALUES ('1', '待送检', '1', '第三方', '一周', '2019-01-01', '2019-06-01', '检测计划1');
INSERT INTO `examineschedule` VALUES ('2', '待送检', '2', '第三方', '一月', '2019-05-03', '2019-06-01', '检测计划2');
INSERT INTO `examineschedule` VALUES ('5', '已检验', '3', '第三方', '一周', '2019-01-01', '2019-01-01', '检测计划5');

-- ----------------------------
-- Table structure for `report`
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report` (
  `report_id` int(11) NOT NULL AUTO_INCREMENT,
  `report_no` varchar(20) NOT NULL,
  `report_name` varchar(20) NOT NULL,
  `report_serialNumber` varchar(20) NOT NULL,
  `dev_id` int(20) NOT NULL,
  `report_identifyDepart` varchar(20) NOT NULL,
  `report_identifier` varchar(20) NOT NULL,
  `report_identifyTime` date NOT NULL,
  PRIMARY KEY (`report_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of report
-- ----------------------------
INSERT INTO `report` VALUES ('1', 'A001', '电力变压器01报告', 'B001', '1', '陕西省计量科学研究院', '张某', '2019-05-02');
INSERT INTO `report` VALUES ('2', 'A002', '机床01报告', 'C001', '4', '陕西省计量科学研究院', '李某', '2019-04-05');
INSERT INTO `report` VALUES ('3', 'A003', '电力变压器02报告', 'B002', '2', '陕西省计量局', '胡某', '2019-04-03');

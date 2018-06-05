/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50521
Source Host           : localhost:3306
Source Database       : smarthome

Target Server Type    : MYSQL
Target Server Version : 50521
File Encoding         : 65001

Date: 2012-05-22 21:50:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tab_user`
-- ----------------------------
DROP TABLE IF EXISTS `tab_user`;
CREATE TABLE `tab_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `userAccount` varchar(20) NOT NULL,
  `userName` varchar(20) NOT NULL,
  `userPsw` varchar(45) NOT NULL,
  `userStatus` int(10) unsigned NOT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `mobile` varchar(30) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `addTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_user
-- ----------------------------
INSERT INTO `tab_user` VALUES ('1', 'xz', '张山', '123456', '0', null, null, null, null);
INSERT INTO `tab_user` VALUES ('2', 'xz111', '张山11', '123456', '0', null, null, null, null);
INSERT INTO `tab_user` VALUES ('3', 'xziiii', '张山1155', '123456', '0', null, null, null, null);
INSERT INTO `tab_user` VALUES ('4', 'xzbbb', '张山bb1155', '123456', '0', null, null, null, null);
INSERT INTO `tab_user` VALUES ('5', 'xzbbb', '张山bb1155', '123456', '0', null, null, null, null);
INSERT INTO `tab_user` VALUES ('6', 'xzbbb', '张山bb1155', '123456', '0', null, null, null, null);
INSERT INTO `tab_user` VALUES ('7', 'xzbbb', '张山bb1155', '123456', '0', null, null, null, null);
INSERT INTO `tab_user` VALUES ('8', 'xzbbb', '张山bb1155', '123456', '0', null, null, null, null);
INSERT INTO `tab_user` VALUES ('9', 'xzbbb', '张山bb1155', '123456', '0', null, null, null, null);
INSERT INTO `tab_user` VALUES ('10', 'xzbbb', '张山bb1155', '123456', '0', null, null, null, null);
INSERT INTO `tab_user` VALUES ('11', '7xzeee1', '张山eee1', '123456', '0', null, null, null, null);
INSERT INTO `tab_user` VALUES ('12', 'xzbbb', '张山bb1155', '123456', '0', null, null, null, null);
INSERT INTO `tab_user` VALUES ('13', 'xzbbb', '张山bb1155', '123456', '0', null, null, null, null);

-- ----------------------------
-- Table structure for `t_advertisement`
-- ----------------------------
DROP TABLE IF EXISTS `t_advertisement`;
CREATE TABLE `t_advertisement` (
  `ad_id` int(12) NOT NULL,
  `type_code` varchar(8) DEFAULT NULL,
  `ad_code` varchar(20) DEFAULT NULL,
  `ad_name` varchar(50) DEFAULT NULL,
  `ad_content` varchar(400) DEFAULT NULL,
  `ad_desc` varchar(200) DEFAULT NULL,
  `ad_url` varchar(400) DEFAULT NULL,
  `ad_type_code` varchar(8) DEFAULT NULL,
  `ad_media_type` varchar(50) DEFAULT NULL,
  `ad_target` char(16) DEFAULT NULL COMMENT '16位二进制表示，0：不投放，1：投放，第1位：门口机，第2位：门口机：围墙机，等等',
  `org_id` int(12) DEFAULT NULL COMMENT '如果为空，广告针对所有小区；否则针对具体小区',
  `status` char(1) DEFAULT NULL COMMENT '0：提交未审核，1：已审核，2：审核不通过，3：已发布，4：已停止',
  `applyed_user` varchar(50) DEFAULT NULL,
  `applyed_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `approved_user` varchar(50) DEFAULT NULL,
  `approved_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `published_user` varchar(50) DEFAULT NULL,
  `published_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ad_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_advertisement
-- ----------------------------

-- ----------------------------
-- Table structure for `t_ad_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_ad_type`;
CREATE TABLE `t_ad_type` (
  `type_code` varchar(8) NOT NULL,
  `type_desc` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_ad_type
-- ----------------------------

-- ----------------------------
-- Table structure for `t_charge_calmode`
-- ----------------------------
DROP TABLE IF EXISTS `t_charge_calmode`;
CREATE TABLE `t_charge_calmode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `name` varchar(20) NOT NULL COMMENT '计算方式名称',
  `reamrk` text COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='收费计算方式';

-- ----------------------------
-- Records of t_charge_calmode
-- ----------------------------
INSERT INTO `t_charge_calmode` VALUES ('2', '按平方米', null);
INSERT INTO `t_charge_calmode` VALUES ('3', '按户计算', '按户计算');
INSERT INTO `t_charge_calmode` VALUES ('4', '月', '月');
INSERT INTO `t_charge_calmode` VALUES ('5', '按点计算', '按点计算');

-- ----------------------------
-- Table structure for `t_charge_data`
-- ----------------------------
DROP TABLE IF EXISTS `t_charge_data`;
CREATE TABLE `t_charge_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `owner_name` varchar(10) DEFAULT NULL COMMENT '业主姓名',
  `roomId` bigint(20) DEFAULT NULL COMMENT '房号',
  `homeId` bigint(20) DEFAULT NULL COMMENT '楼栋名称',
  `total_money` float DEFAULT NULL COMMENT '总金额',
  `monetaryUnit` varchar(10) DEFAULT NULL COMMENT '货币单位',
  `charge_time` varchar(30) DEFAULT NULL COMMENT '收费时间',
  `isproduct_detail` varchar(2) DEFAULT NULL COMMENT '是否已产生清单',
  `create_time` datetime DEFAULT NULL COMMENT '录入时间',
  `userId` bigint(20) DEFAULT NULL COMMENT '录入者',
  `comId` bigint(20) DEFAULT NULL COMMENT '所属小区',
  `remark` text COMMENT '描述',
  PRIMARY KEY (`id`),
  KEY `roomId_household_info` (`roomId`),
  KEY `homeId_building_cell_info` (`homeId`),
  KEY `userId_pauser` (`userId`),
  KEY `comId_system_group` (`comId`),
  CONSTRAINT `comId_system_group` FOREIGN KEY (`comId`) REFERENCES `t_system_group` (`group_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `roomId_household_info` FOREIGN KEY (`roomId`) REFERENCES `t_hm_cell_household_info` (`ID`),
  CONSTRAINT `userId_pauser` FOREIGN KEY (`userId`) REFERENCES `t_pa_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='收费数据表';

-- ----------------------------
-- Records of t_charge_data
-- ----------------------------
INSERT INTO `t_charge_data` VALUES ('25', 'jans', '1', '1', null, '人民币', null, '1', '2012-05-21 12:48:32', null, null, null);
INSERT INTO `t_charge_data` VALUES ('26', 'DEHUA', '1', '1', '5356.3', '人民币', '2012-05', '2', '2012-05-21 00:00:00', null, null, null);
INSERT INTO `t_charge_data` VALUES ('27', 'uede', '1', '1', null, '人民币', '2012-03', '1', '2012-05-21 20:55:17', null, null, null);
INSERT INTO `t_charge_data` VALUES ('28', 'janso', '1', '1', null, '人民币', '2012-05', '1', '2012-05-21 21:38:48', null, null, null);
INSERT INTO `t_charge_data` VALUES ('29', '333', '1', '2', null, '人民币', '2012-05', '1', '2012-05-22 10:01:26', null, null, null);
INSERT INTO `t_charge_data` VALUES ('30', '444', '1', '1', null, '人民币', '2012-05', '1', '2012-05-22 10:04:02', null, null, null);
INSERT INTO `t_charge_data` VALUES ('31', '444', '1', '2', null, '人民币', '2012-05', '1', '2012-05-22 10:29:31', null, null, null);
INSERT INTO `t_charge_data` VALUES ('32', '444', '1', '1', null, '人民币', '2012-05', '1', '2012-05-22 10:32:09', null, null, null);
INSERT INTO `t_charge_data` VALUES ('33', '22', '1', '1', null, '人民币', '2012-05', '1', '2012-05-22 10:36:57', null, null, null);
INSERT INTO `t_charge_data` VALUES ('36', '妹毁', '1', '2', '1974.4', '人民币', '2012-05', '1', '2012-05-22 00:00:00', null, null, null);

-- ----------------------------
-- Table structure for `t_charge_detail`
-- ----------------------------
DROP TABLE IF EXISTS `t_charge_detail`;
CREATE TABLE `t_charge_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `print_sn` varchar(30) DEFAULT NULL COMMENT '打印流水号',
  `charge_status` varchar(2) DEFAULT NULL COMMENT '缴费状态',
  `charge_time` varchar(30) DEFAULT NULL COMMENT '缴费时间',
  `infoId` bigint(20) DEFAULT NULL COMMENT '催缴费通知ID',
  `chargeDataId` bigint(20) NOT NULL COMMENT '收费数据ID',
  `remark` text COMMENT '描述',
  PRIMARY KEY (`id`),
  KEY `chargeDataId_charge_data` (`chargeDataId`),
  CONSTRAINT `chargeDataId_charge_data` FOREIGN KEY (`chargeDataId`) REFERENCES `t_charge_data` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='收费清单列表';

-- ----------------------------
-- Records of t_charge_detail
-- ----------------------------
INSERT INTO `t_charge_detail` VALUES ('10', '94266859', '1', '2012-05-22 14:13:12', null, '25', null);
INSERT INTO `t_charge_detail` VALUES ('13', '55070203', '1', '2012-05-22 15:24:02', null, '27', null);
INSERT INTO `t_charge_detail` VALUES ('14', '55070218', '1', '2012-05-22 20:41:39', null, '28', null);
INSERT INTO `t_charge_detail` VALUES ('15', '55070218', '1', '2012-05-22 15:24:39', null, '29', null);
INSERT INTO `t_charge_detail` VALUES ('16', '55070234', '1', '2012-05-22 15:24:32', null, '30', null);
INSERT INTO `t_charge_detail` VALUES ('17', '55070234', '1', '2012-05-22 15:24:24', null, '31', null);
INSERT INTO `t_charge_detail` VALUES ('18', '55070250', '1', '2012-05-22 15:24:12', null, '32', null);
INSERT INTO `t_charge_detail` VALUES ('19', '55070250', '1', '2012-05-22 14:04:04', null, '33', null);
INSERT INTO `t_charge_detail` VALUES ('21', '56490890', '1', '2012-05-22 14:04:19', null, '36', null);

-- ----------------------------
-- Table structure for `t_charge_monetaryunit`
-- ----------------------------
DROP TABLE IF EXISTS `t_charge_monetaryunit`;
CREATE TABLE `t_charge_monetaryunit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `monetary_code` varchar(20) NOT NULL COMMENT '货币代码',
  `code_name` varchar(20) NOT NULL COMMENT '单位名称',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='收费货币单位表';

-- ----------------------------
-- Records of t_charge_monetaryunit
-- ----------------------------
INSERT INTO `t_charge_monetaryunit` VALUES ('1', 'RMB', '人民币', null);
INSERT INTO `t_charge_monetaryunit` VALUES ('3', '$', '美元', '美元');
INSERT INTO `t_charge_monetaryunit` VALUES ('7', '$f', '欧元', '指定');
INSERT INTO `t_charge_monetaryunit` VALUES ('8', '欧元', ' $f', '欧元');

-- ----------------------------
-- Table structure for `t_charge_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_charge_type`;
CREATE TABLE `t_charge_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `name` varchar(100) NOT NULL COMMENT '收费项目名称',
  `calModeId` bigint(20) DEFAULT NULL COMMENT '计算方式',
  `standard` float NOT NULL COMMENT '收费标准',
  `monetaryUnitId` bigint(20) DEFAULT NULL COMMENT '货币单位ID',
  `charge_mode` varchar(10) DEFAULT NULL COMMENT '收费方式',
  `remark` text COMMENT '备注',
  `comId` bigint(20) DEFAULT NULL COMMENT '所属小区ID',
  `userId` bigint(20) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `calUnit` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `calModeId_calmode` (`calModeId`),
  KEY `monetaryUnitId_monetaryunit` (`monetaryUnitId`),
  KEY `comId_systemgroup` (`comId`),
  KEY `userId_pa_user` (`userId`),
  CONSTRAINT `calModeId_calmode` FOREIGN KEY (`calModeId`) REFERENCES `t_charge_calmode` (`id`),
  CONSTRAINT `comId_systemgroup` FOREIGN KEY (`comId`) REFERENCES `t_system_group` (`group_no`),
  CONSTRAINT `monetaryUnitId_monetaryunit` FOREIGN KEY (`monetaryUnitId`) REFERENCES `t_charge_monetaryunit` (`id`),
  CONSTRAINT `userId_pa_user` FOREIGN KEY (`userId`) REFERENCES `t_pa_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='收费项目表';

-- ----------------------------
-- Records of t_charge_type
-- ----------------------------
INSERT INTO `t_charge_type` VALUES ('10', '水费', '3', '2.3', '1', null, '水费', null, null, null, '立方');
INSERT INTO `t_charge_type` VALUES ('11', '网费', '3', '1', '1', null, '网费', null, null, null, 'KB');
INSERT INTO `t_charge_type` VALUES ('12', '物业费', '3', '400', '1', null, '每户按每月400元交', null, null, null, '月');
INSERT INTO `t_charge_type` VALUES ('13', '网费', '3', '1200', '1', null, '每户提供电信4M上网', null, null, null, '年');
INSERT INTO `t_charge_type` VALUES ('14', '娱乐费', '4', '50', '1', null, '小区定期各种娱乐运动开支', null, null, null, '年');

-- ----------------------------
-- Table structure for `t_charge_type_result`
-- ----------------------------
DROP TABLE IF EXISTS `t_charge_type_result`;
CREATE TABLE `t_charge_type_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `chargeTypeId` bigint(20) DEFAULT NULL COMMENT '收费项目名称',
  `start_total` float DEFAULT NULL COMMENT '开始指标',
  `end_total` float DEFAULT NULL COMMENT '结束指标',
  `actual_total` float DEFAULT NULL COMMENT '实际指标',
  `play_money` float DEFAULT NULL COMMENT '缴费金额',
  `remark` text COMMENT '备注',
  `chargeDataId` bigint(20) DEFAULT NULL,
  `chargeDetailId` bigint(20) DEFAULT NULL,
  `calUnit` varchar(10) DEFAULT NULL,
  `chargeTypeName` varchar(100) DEFAULT NULL,
  `calMode` varchar(20) DEFAULT NULL,
  `standard` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `chargeTypeId_charge_type` (`chargeTypeId`),
  KEY `chargeDetailId_chargeDetail` (`chargeDetailId`),
  KEY `chargeDataId_chargetData` (`chargeDataId`),
  CONSTRAINT `chargeDataId_chargetData` FOREIGN KEY (`chargeDataId`) REFERENCES `t_charge_data` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `chargeTypeId_charge_type` FOREIGN KEY (`chargeTypeId`) REFERENCES `t_charge_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8 COMMENT='收费项目结果表';

-- ----------------------------
-- Records of t_charge_type_result
-- ----------------------------
INSERT INTO `t_charge_type_result` VALUES ('45', null, '10', '521', '511', null, null, '25', '10', '立方', '水费', '按户计算', '2.3');
INSERT INTO `t_charge_type_result` VALUES ('46', null, '2', '5544', '5542', null, null, '25', '10', 'KB', '网费', '按户计算', '1');
INSERT INTO `t_charge_type_result` VALUES ('47', null, '10', '2512', '2502', '2502', '', '26', null, 'KB', '网费', '按户计算', '1');
INSERT INTO `t_charge_type_result` VALUES ('48', null, '10', '1251', '1241', '2854.3', '', '26', null, '立方', '水费', '按户计算', '2.3');
INSERT INTO `t_charge_type_result` VALUES ('49', null, '12', '52', '40', '1', null, '27', '13', 'KB', '网费', '按户计算', '1');
INSERT INTO `t_charge_type_result` VALUES ('50', null, null, null, null, '1200', null, '27', '13', '年', '网费', '按户计算', '1200');
INSERT INTO `t_charge_type_result` VALUES ('51', null, null, null, null, '50', null, '27', '13', '年', '娱乐费', '月', '50');
INSERT INTO `t_charge_type_result` VALUES ('52', null, null, null, null, '400', null, '27', '13', '月', '物业费', '按户计算', '400');
INSERT INTO `t_charge_type_result` VALUES ('53', null, '10', '25', '15', '2.3', null, '27', '13', '立方', '水费', '按户计算', '2.3');
INSERT INTO `t_charge_type_result` VALUES ('54', null, '555', '5555', '5000', '1', null, '28', '14', 'KB', '网费', '按户计算', '1');
INSERT INTO `t_charge_type_result` VALUES ('55', null, null, null, null, '400', null, '28', '14', '月', '物业费', '按户计算', '400');
INSERT INTO `t_charge_type_result` VALUES ('56', null, '44', '5555', '5511', '2.3', null, '28', '14', '立方', '水费', '按户计算', '2.3');
INSERT INTO `t_charge_type_result` VALUES ('57', null, null, null, null, '50', null, '28', '14', '年', '娱乐费', '月', '50');
INSERT INTO `t_charge_type_result` VALUES ('58', null, null, null, null, '1200', null, '28', '14', '年', '网费', '按户计算', '1200');
INSERT INTO `t_charge_type_result` VALUES ('59', null, null, null, null, '50', null, '29', '15', '年', '娱乐费', '月', '50');
INSERT INTO `t_charge_type_result` VALUES ('60', null, null, null, null, '400', null, '29', '15', '月', '物业费', '按户计算', '400');
INSERT INTO `t_charge_type_result` VALUES ('61', null, '33', '3333', '3300', '1', null, '29', '15', 'KB', '网费', '按户计算', '1');
INSERT INTO `t_charge_type_result` VALUES ('62', null, '333', '3333', '3000', '2.3', null, '29', '15', '立方', '水费', '按户计算', '2.3');
INSERT INTO `t_charge_type_result` VALUES ('63', null, null, null, null, '1200', null, '29', '15', '年', '网费', '按户计算', '1200');
INSERT INTO `t_charge_type_result` VALUES ('64', null, '44', '4444', '400', '2.3', null, '30', '16', '立方', '水费', '按户计算', '2.3');
INSERT INTO `t_charge_type_result` VALUES ('65', null, null, null, null, '400', null, '30', '16', '月', '物业费', '按户计算', '400');
INSERT INTO `t_charge_type_result` VALUES ('66', null, null, null, null, '50', null, '30', '16', '年', '娱乐费', '月', '50');
INSERT INTO `t_charge_type_result` VALUES ('67', null, '444', '4444', '4000', '1', null, '30', '16', 'KB', '网费', '按户计算', '1');
INSERT INTO `t_charge_type_result` VALUES ('68', null, null, null, null, '1200', null, '30', '16', '年', '网费', '按户计算', '1200');
INSERT INTO `t_charge_type_result` VALUES ('69', null, '44', '444', '400', '1', null, '31', '17', 'KB', '网费', '按户计算', '1');
INSERT INTO `t_charge_type_result` VALUES ('70', null, '44', '444', '400', '2.3', null, '31', '17', '立方', '水费', '按户计算', '2.3');
INSERT INTO `t_charge_type_result` VALUES ('71', null, null, null, null, '50', null, '31', '17', '年', '娱乐费', '月', '50');
INSERT INTO `t_charge_type_result` VALUES ('72', null, null, null, null, '1200', null, '31', '17', '年', '网费', '按户计算', '1200');
INSERT INTO `t_charge_type_result` VALUES ('73', null, null, null, null, '400', null, '31', '17', '月', '物业费', '按户计算', '400');
INSERT INTO `t_charge_type_result` VALUES ('74', null, null, null, null, '50', null, '32', '18', '年', '娱乐费', '月', '50');
INSERT INTO `t_charge_type_result` VALUES ('75', null, '44', '444', '400', '1', null, '32', '18', 'KB', '网费', '按户计算', '1');
INSERT INTO `t_charge_type_result` VALUES ('76', null, '44', '444', '400', '2.3', null, '32', '18', '立方', '水费', '按户计算', '2.3');
INSERT INTO `t_charge_type_result` VALUES ('77', null, null, null, null, '400', null, '32', '18', '月', '物业费', '按户计算', '400');
INSERT INTO `t_charge_type_result` VALUES ('78', null, null, null, null, '1200', null, '32', '18', '年', '网费', '按户计算', '1200');
INSERT INTO `t_charge_type_result` VALUES ('79', null, null, null, null, '1200', null, '33', '19', '年', '网费', '按户计算', '1200');
INSERT INTO `t_charge_type_result` VALUES ('80', null, '22222', '2222220', '2200000', '1', null, '33', '19', 'KB', '网费', '按户计算', '1');
INSERT INTO `t_charge_type_result` VALUES ('81', null, null, null, null, '400', null, '33', '19', '月', '物业费', '按户计算', '400');
INSERT INTO `t_charge_type_result` VALUES ('82', null, '2', '222', '220', '2.3', null, '33', '19', '立方', '水费', '按户计算', '2.3');
INSERT INTO `t_charge_type_result` VALUES ('90', null, null, null, null, '50', null, null, null, '年', '娱乐费', '月', '50');
INSERT INTO `t_charge_type_result` VALUES ('91', null, '2', '500', '498', '1145.4', '', '36', '21', '立方', '水费', '按户计算', '2.3');
INSERT INTO `t_charge_type_result` VALUES ('92', null, null, null, null, '400', '', '36', '21', '月', '物业费', '按户计算', '400');
INSERT INTO `t_charge_type_result` VALUES ('93', null, '4', '433', '429', '429', '', '36', '21', 'KB', '网费', '按户计算', '1');
INSERT INTO `t_charge_type_result` VALUES ('94', null, null, null, null, '50', null, null, null, '年', '娱乐费', '月', '50');

-- ----------------------------
-- Table structure for `t_complaint_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_complaint_type`;
CREATE TABLE `t_complaint_type` (
  `type_code` varchar(8) NOT NULL,
  `type_desc` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_complaint_type
-- ----------------------------

-- ----------------------------
-- Table structure for `t_db_test`
-- ----------------------------
DROP TABLE IF EXISTS `t_db_test`;
CREATE TABLE `t_db_test` (
  `a` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_db_test
-- ----------------------------

-- ----------------------------
-- Table structure for `t_device`
-- ----------------------------
DROP TABLE IF EXISTS `t_device`;
CREATE TABLE `t_device` (
  `device_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '设备编号',
  `device_code` varchar(60) COLLATE utf8_bin NOT NULL COMMENT '设备编码',
  `device_name` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '设备名称',
  `device_alias` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '可以用来标识哪个门禁',
  `device_pwd` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '设备密码',
  `device_type` char(3) COLLATE utf8_bin NOT NULL,
  `device_status` char(1) COLLATE utf8_bin NOT NULL,
  `district_id` int(12) DEFAULT NULL,
  `area_id` int(15) DEFAULT NULL,
  `area_name` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `building_id` bigint(20) DEFAULT NULL,
  `building_name` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `unit_id` bigint(20) DEFAULT NULL,
  `unit_name` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `district_gate_flag` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '0：直属小区大门；1：非直属小区大门',
  `created_user` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_user` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `updated_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `device_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `device_mac` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `login_userid` bigint(20) DEFAULT NULL,
  `login_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  `devicebg_url` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `devicering_url` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_device
-- ----------------------------

-- ----------------------------
-- Table structure for `t_device_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_device_info`;
CREATE TABLE `t_device_info` (
  `id` bigint(20) NOT NULL COMMENT '设备信息id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '主键id',
  `device_code_id` bigint(20) NOT NULL COMMENT '设备Id',
  `device_name` varchar(100) NOT NULL COMMENT '设备名称',
  `device_pwd` varchar(50) NOT NULL COMMENT '设备密码',
  `device_ip` varchar(50) NOT NULL COMMENT '设备MAC地址',
  `resolution` varchar(50) DEFAULT NULL COMMENT '设备分辨率',
  `software_version` varchar(20) DEFAULT NULL COMMENT '软件版本信息',
  `device_type` char(3) DEFAULT NULL,
  `device_login_time` datetime NOT NULL,
  `device_status` int(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_device_info
-- ----------------------------

-- ----------------------------
-- Table structure for `t_device_no`
-- ----------------------------
DROP TABLE IF EXISTS `t_device_no`;
CREATE TABLE `t_device_no` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deviceNo` varchar(20) DEFAULT NULL,
  `deviceId` bigint(20) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pk_device_code` (`deviceNo`),
  KEY `t_device_No` (`deviceId`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_device_no
-- ----------------------------
INSERT INTO `t_device_no` VALUES ('7', 'G20120511165445P', null, null);
INSERT INTO `t_device_no` VALUES ('8', 'g20120511165454I', '2', null);
INSERT INTO `t_device_no` VALUES ('9', 'h20120511165527y', null, null);
INSERT INTO `t_device_no` VALUES ('10', 'U20120511170140s', null, null);
INSERT INTO `t_device_no` VALUES ('11', 'z20120512105451O', null, null);
INSERT INTO `t_device_no` VALUES ('12', 'q20120512111422s', null, null);
INSERT INTO `t_device_no` VALUES ('13', 'd20120512113336Z', null, null);
INSERT INTO `t_device_no` VALUES ('14', 'm20120512113607M', null, null);
INSERT INTO `t_device_no` VALUES ('15', 'M20120514140334L', null, null);
INSERT INTO `t_device_no` VALUES ('16', 'c20120514140422U', null, null);
INSERT INTO `t_device_no` VALUES ('17', 'R20120514144146V', null, null);
INSERT INTO `t_device_no` VALUES ('18', 'h20120518143303V', null, null);
INSERT INTO `t_device_no` VALUES ('19', 'y20120521115457o', null, null);
INSERT INTO `t_device_no` VALUES ('20', 'm20120521115903X', null, null);
INSERT INTO `t_device_no` VALUES ('21', 'g20120521115934D', null, null);
INSERT INTO `t_device_no` VALUES ('22', 'l20120521120042r', null, null);

-- ----------------------------
-- Table structure for `t_device_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_device_type`;
CREATE TABLE `t_device_type` (
  `device_type` char(3) NOT NULL,
  `device_desc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`device_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_device_type
-- ----------------------------

-- ----------------------------
-- Table structure for `t_hdfs_task`
-- ----------------------------
DROP TABLE IF EXISTS `t_hdfs_task`;
CREATE TABLE `t_hdfs_task` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `fileName` varchar(100) NOT NULL,
  `fileFormat` varchar(20) NOT NULL,
  `uploadPath` varchar(255) NOT NULL,
  `hdfsPath` varchar(255) NOT NULL,
  `taskStatus` int(11) NOT NULL,
  `taskAddTime` time NOT NULL,
  `taskFinishTime` time DEFAULT NULL,
  `fileSize` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_hdfs_task
-- ----------------------------

-- ----------------------------
-- Table structure for `t_hm_building_cell_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_hm_building_cell_info`;
CREATE TABLE `t_hm_building_cell_info` (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `CODE` int(3) DEFAULT NULL,
  `NAME` varchar(20) DEFAULT NULL,
  `COORDINATE` varchar(20) DEFAULT NULL,
  `CREATE_USER_ID` bigint(20) DEFAULT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `BUILDING_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='¥ֵĵԪϢ';

-- ----------------------------
-- Records of t_hm_building_cell_info
-- ----------------------------

-- ----------------------------
-- Table structure for `t_hm_cell_household_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_hm_cell_household_info`;
CREATE TABLE `t_hm_cell_household_info` (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `CODE` int(3) DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `SIZE_ID` int(11) DEFAULT NULL,
  `HOUSING_PURPOSE` int(1) DEFAULT NULL COMMENT '0: סլ, 1: ',
  `COORDINATE` varchar(20) DEFAULT NULL,
  `AREA` decimal(6,2) DEFAULT NULL,
  `OWNER_NAME` varchar(10) DEFAULT NULL,
  `HOUSING_STATUS` int(1) DEFAULT NULL COMMENT '0: δס, 1: ס',
  `KEY_STATUS` int(1) DEFAULT NULL COMMENT '0: , 1: ҵ',
  `CHECK_IN_DATE` date DEFAULT NULL,
  `CREATE_USER_ID` bigint(20) DEFAULT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CELL_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_12` (`SIZE_ID`),
  KEY `FK_Reference_8` (`CELL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='ԪֵķϢ';

-- ----------------------------
-- Records of t_hm_cell_household_info
-- ----------------------------
INSERT INTO `t_hm_cell_household_info` VALUES ('1', '22', '22', null, '222', '2', '22.00', '22', '22', '222', null, null, '2012-05-18 14:20:51', null);

-- ----------------------------
-- Table structure for `t_hm_cell_size_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_hm_cell_size_info`;
CREATE TABLE `t_hm_cell_size_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROOM` int(11) DEFAULT NULL,
  `HALL` int(11) DEFAULT NULL,
  `PLAN` varchar(256) DEFAULT NULL,
  `CREATE_USER_ID` bigint(20) DEFAULT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CELL_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_5` (`CELL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ԪĻϢ';

-- ----------------------------
-- Records of t_hm_cell_size_info
-- ----------------------------

-- ----------------------------
-- Table structure for `t_hm_housing_charge_setting`
-- ----------------------------
DROP TABLE IF EXISTS `t_hm_housing_charge_setting`;
CREATE TABLE `t_hm_housing_charge_setting` (
  `HOUSEHOLD_ID` bigint(20) NOT NULL,
  `CHARGE_TYPE_ID` bigint(20) NOT NULL COMMENT 'FK t_charge_type.id',
  PRIMARY KEY (`HOUSEHOLD_ID`,`CHARGE_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='浣忔埧鏀惰垂璁剧疆';

-- ----------------------------
-- Records of t_hm_housing_charge_setting
-- ----------------------------

-- ----------------------------
-- Table structure for `t_hm_housing_district_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_hm_housing_district_info`;
CREATE TABLE `t_hm_housing_district_info` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CODE` bigint(6) DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `FLOOR_PLAN` varchar(256) DEFAULT NULL COMMENT 'URLַ',
  `CONSTRUCTION_AREA` decimal(15,2) DEFAULT NULL,
  `PROPERTY_COMPANY_ID` int(11) DEFAULT NULL,
  `PROPERTY_COMPANY_ADDRESS` varchar(100) DEFAULT NULL,
  `DISTRICT` varchar(100) DEFAULT NULL COMMENT 'ַ',
  `GROUP_ID` bigint(20) DEFAULT NULL COMMENT 'FK t_system_group(group_no)',
  `DEVELOPER` varchar(100) DEFAULT NULL,
  `CREATE_USER_ID` bigint(20) DEFAULT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_7` (`PROPERTY_COMPANY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='СϢ';

-- ----------------------------
-- Records of t_hm_housing_district_info
-- ----------------------------

-- ----------------------------
-- Table structure for `t_hm_housing_district_region_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_hm_housing_district_region_info`;
CREATE TABLE `t_hm_housing_district_region_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CODE` int(2) DEFAULT NULL,
  `NAME` varchar(20) DEFAULT NULL,
  `COORDINATE` varchar(20) DEFAULT NULL,
  `CREATE_USER_ID` bigint(20) DEFAULT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `HOUSING_DISTRICT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_2` (`HOUSING_DISTRICT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='СֵϢ';

-- ----------------------------
-- Records of t_hm_housing_district_region_info
-- ----------------------------

-- ----------------------------
-- Table structure for `t_hm_property_company_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_hm_property_company_info`;
CREATE TABLE `t_hm_property_company_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) DEFAULT NULL,
  `PROFILE` varchar(1024) DEFAULT NULL,
  `CONTACT` varchar(50) DEFAULT NULL,
  `CHARGE` varchar(20) DEFAULT NULL,
  `CREATE_USER_ID` bigint(20) DEFAULT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ҵ˾Ϣ';

-- ----------------------------
-- Records of t_hm_property_company_info
-- ----------------------------

-- ----------------------------
-- Table structure for `t_hm_region_building_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_hm_region_building_info`;
CREATE TABLE `t_hm_region_building_info` (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `CODE` int(3) DEFAULT NULL,
  `NAME` varchar(20) DEFAULT NULL,
  `COVER_AREA` decimal(12,2) DEFAULT NULL,
  `CONSTRUCTION_AREA` decimal(12,2) DEFAULT NULL,
  `COORDINATE` varchar(20) DEFAULT NULL,
  `CREATE_USER_ID` bigint(20) DEFAULT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `REGION_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_3` (`REGION_ID`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`REGION_ID`) REFERENCES `t_hm_housing_district_region_info` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='򻮷ֵ¥Ϣ';

-- ----------------------------
-- Records of t_hm_region_building_info
-- ----------------------------
INSERT INTO `t_hm_region_building_info` VALUES ('1', '44', '44', '44.00', '4.00', '44', null, '2012-05-18 17:16:30', null);
INSERT INTO `t_hm_region_building_info` VALUES ('2', '14552', '54', '455.00', '555.00', '55', null, '2012-05-22 15:14:14', null);

-- ----------------------------
-- Table structure for `t_leave_words`
-- ----------------------------
DROP TABLE IF EXISTS `t_leave_words`;
CREATE TABLE `t_leave_words` (
  `id` bigint(20) NOT NULL,
  `caller` varchar(50) NOT NULL,
  `caller_ip` varchar(20) NOT NULL,
  `leave_words_time` datetime NOT NULL,
  `leave_words_lenth` int(11) NOT NULL,
  `uploadTime` datetime NOT NULL,
  `photo_file_one` varchar(255) DEFAULT NULL,
  `photo_file_two` varchar(255) DEFAULT NULL,
  `photo_file_three` varchar(255) DEFAULT NULL,
  `audio_file` varchar(255) DEFAULT NULL,
  `vedio_file` varchar(255) DEFAULT NULL,
  `userName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_leave_words
-- ----------------------------

-- ----------------------------
-- Table structure for `t_local_hdfs`
-- ----------------------------
DROP TABLE IF EXISTS `t_local_hdfs`;
CREATE TABLE `t_local_hdfs` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `localPath` varchar(255) NOT NULL,
  `hdfsPath` varchar(255) NOT NULL,
  `fileName` varchar(255) NOT NULL,
  `fileSize` bigint(20) NOT NULL,
  `transferWay` varchar(10) NOT NULL,
  `addTime` time NOT NULL,
  `fileFormat` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_local_hdfs
-- ----------------------------

-- ----------------------------
-- Table structure for `t_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `menu_code` varchar(10) NOT NULL,
  `role_code` varchar(10) DEFAULT NULL,
  `parent_code` varchar(10) DEFAULT NULL,
  `menu_name` varchar(40) DEFAULT NULL,
  `menu_url` varchar(300) DEFAULT NULL,
  `status` char(1) DEFAULT NULL COMMENT '0：表示显示，1：表示隐藏',
  `menu_desc` varchar(200) DEFAULT NULL,
  `updated_user` varchar(50) DEFAULT NULL,
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`menu_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `t_menu_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_menu_role`;
CREATE TABLE `t_menu_role` (
  `menu_code` varchar(10) NOT NULL,
  `role_code` varchar(10) NOT NULL,
  PRIMARY KEY (`menu_code`,`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu_role
-- ----------------------------

-- ----------------------------
-- Table structure for `t_operation_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_operation_log`;
CREATE TABLE `t_operation_log` (
  `log_id` bigint(20) NOT NULL,
  `operate_user` varchar(50) NOT NULL,
  `operate_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `operate_result` char(2) NOT NULL,
  `menu_code` varchar(10) NOT NULL,
  `operation_code` char(4) NOT NULL,
  `ip` varchar(50) DEFAULT NULL,
  `remark` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_operation_log
-- ----------------------------

-- ----------------------------
-- Table structure for `t_org`
-- ----------------------------
DROP TABLE IF EXISTS `t_org`;
CREATE TABLE `t_org` (
  `org_id` int(12) NOT NULL,
  `org_name` varchar(40) NOT NULL,
  `country` varchar(80) DEFAULT NULL,
  `province` varchar(80) NOT NULL,
  `city` varchar(80) NOT NULL,
  `county` varchar(80) NOT NULL,
  `street` varchar(100) NOT NULL,
  `district` varchar(100) NOT NULL,
  `org_desc` varchar(200) DEFAULT NULL,
  `created_user` varchar(50) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_user` varchar(50) DEFAULT NULL,
  `updated_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_org
-- ----------------------------

-- ----------------------------
-- Table structure for `t_pa_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_pa_user`;
CREATE TABLE `t_pa_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `district_id` int(12) NOT NULL COMMENT '小区id',
  `department` varchar(50) DEFAULT NULL,
  `post` varchar(50) DEFAULT NULL COMMENT '职务',
  `user_name` varchar(50) DEFAULT NULL COMMENT '管理员名称',
  `id_card` varchar(30) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `gender` char(1) DEFAULT NULL COMMENT '性别(0:男 1:女 2:未知)',
  `degree` varchar(50) DEFAULT NULL COMMENT '学历',
  `major` varchar(50) DEFAULT NULL,
  `photo_path` varchar(200) DEFAULT NULL COMMENT '照片',
  `intro` varchar(200) DEFAULT NULL COMMENT '简介',
  `mobile_phone` varchar(20) DEFAULT NULL COMMENT '移动电话',
  `office_phone` varchar(20) DEFAULT NULL COMMENT '办公室电话',
  `email` varchar(50) DEFAULT NULL,
  `created_user` varchar(50) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_user` varchar(50) DEFAULT NULL,
  `updated_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `addr` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物业管理用户信息表';

-- ----------------------------
-- Records of t_pa_user
-- ----------------------------

-- ----------------------------
-- Table structure for `t_pm_attendance`
-- ----------------------------
DROP TABLE IF EXISTS `t_pm_attendance`;
CREATE TABLE `t_pm_attendance` (
  `attendance_id` bigint(20) NOT NULL,
  `sys_group_id` int(12) NOT NULL,
  `duty_topic` varchar(30) NOT NULL,
  `duty_content` varchar(200) DEFAULT NULL,
  `duty_staff` varchar(200) NOT NULL,
  `location` varchar(40) NOT NULL,
  `duty_date` date NOT NULL,
  `begin_time` time NOT NULL,
  `end_time` time NOT NULL,
  `created_user` varchar(50) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_user` varchar(50) DEFAULT NULL,
  `updated_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`attendance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_pm_attendance
-- ----------------------------

-- ----------------------------
-- Table structure for `t_pm_contact`
-- ----------------------------
DROP TABLE IF EXISTS `t_pm_contact`;
CREATE TABLE `t_pm_contact` (
  `contact_id` int(15) NOT NULL,
  `sys_group_id` int(12) NOT NULL,
  `service_name` varchar(40) NOT NULL,
  `service_content` varchar(200) NOT NULL,
  `office_phone` varchar(200) NOT NULL,
  `mobile_phone` varchar(200) DEFAULT NULL,
  `office_address` varchar(80) NOT NULL,
  `created_user` varchar(50) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_user` varchar(50) DEFAULT NULL,
  `updated_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`contact_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_pm_contact
-- ----------------------------

-- ----------------------------
-- Table structure for `t_pm_group`
-- ----------------------------
DROP TABLE IF EXISTS `t_pm_group`;
CREATE TABLE `t_pm_group` (
  `group_id` int(15) NOT NULL,
  `org_id` int(12) NOT NULL,
  `group_name` varchar(40) NOT NULL,
  `group_desc` varchar(200) DEFAULT NULL,
  `created_user` varchar(50) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_user` varchar(50) DEFAULT NULL,
  `updated_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_pm_group
-- ----------------------------

-- ----------------------------
-- Table structure for `t_qrtz_blob_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `t_qrtz_blob_triggers`;
CREATE TABLE `t_qrtz_blob_triggers` (
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `TRIGGER_NAME` (`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `t_qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `t_qrtz_triggers` (`TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `t_qrtz_calendars`
-- ----------------------------
DROP TABLE IF EXISTS `t_qrtz_calendars`;
CREATE TABLE `t_qrtz_calendars` (
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for `t_qrtz_cron_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `t_qrtz_cron_triggers`;
CREATE TABLE `t_qrtz_cron_triggers` (
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `TRIGGER_NAME` (`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `t_qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `t_qrtz_triggers` (`TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_qrtz_cron_triggers
-- ----------------------------
INSERT INTO `t_qrtz_cron_triggers` VALUES ('publishSoftwaresTrigger', 'DEFAULT', '0 1/5 * * * ?', 'Asia/Shanghai');
INSERT INTO `t_qrtz_cron_triggers` VALUES ('test_doTime', 'DEFAULT', '0 0/1 * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for `t_qrtz_fired_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `t_qrtz_fired_triggers`;
CREATE TABLE `t_qrtz_fired_triggers` (
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `IS_VOLATILE` varchar(1) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_STATEFUL` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `t_qrtz_job_details`
-- ----------------------------
DROP TABLE IF EXISTS `t_qrtz_job_details`;
CREATE TABLE `t_qrtz_job_details` (
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_VOLATILE` varchar(1) NOT NULL,
  `IS_STATEFUL` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_qrtz_job_details
-- ----------------------------
INSERT INTO `t_qrtz_job_details` VALUES ('publishSoftwaresJob', 'SoftwareUpgrade', null, 'com.biencloud.smarthome.service.common.spring.quartz.BeanInvokingJobDetailFactoryBean$StatefulBeanInvokingJob', '0', '0', '1', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C77080000001000000003740009617267756D656E74737074000A7461726765744265616E740016736F667477617265557067726164655365727669636574000C7461726765744D6574686F647400167075626C69736854696D696E67536F667477617265737800);
INSERT INTO `t_qrtz_job_details` VALUES ('test_jobtask', 'DEFAULT', null, 'com.biencloud.smarthome.service.quartz.MethodInvokingJobDetailFactoryBean$MethodInvokingJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000274000C7461726765744F626A65637473720030636F6D2E6269656E636C6F75642E736D617274686F6D652E736572766963652E71756172747A2E51756172747A4A6F6200000000000000010200014C000F737973506172616D536572766963657400434C636F6D2F6269656E636C6F75642F736D617274686F6D652F736572766963652F737973706172616D2F736572766963652F49537973506172616D536572766963653B7870737D000000040041636F6D2E6269656E636C6F75642E736D617274686F6D652E736572766963652E737973706172616D2E736572766963652E49537973506172616D536572766963650035636F6D2E6269656E636C6F75642E736D617274686F6D652E736572766963652E626173652E736572766963652E495365727669636500236F72672E737072696E676672616D65776F726B2E616F702E537072696E6750726F787900296F72672E737072696E676672616D65776F726B2E616F702E6672616D65776F726B2E41647669736564787200176A6176612E6C616E672E7265666C6563742E50726F7879E127DA20CC1043CB0200014C0001687400254C6A6176612F6C616E672F7265666C6563742F496E766F636174696F6E48616E646C65723B7870737200346F72672E737072696E676672616D65776F726B2E616F702E6672616D65776F726B2E4A646B44796E616D6963416F7050726F78794CC4B4710EEB96FC0200035A000D657175616C73446566696E65645A000F68617368436F6465446566696E65644C0007616476697365647400324C6F72672F737072696E676672616D65776F726B2F616F702F6672616D65776F726B2F41647669736564537570706F72743B787000007372002E6F72672E737072696E676672616D65776F726B2E616F702E6672616D65776F726B2E50726F7879466163746F727913CAB53ADE0F4D55020000787200356F72672E737072696E676672616D65776F726B2E616F702E6672616D65776F726B2E50726F787943726561746F72537570706F7274836DB9C7978EF8BE0200035A00066163746976654C000F616F7050726F7879466163746F72797400334C6F72672F737072696E676672616D65776F726B2F616F702F6672616D65776F726B2F416F7050726F7879466163746F72793B4C00096C697374656E6572737400104C6A6176612F7574696C2F4C6973743B787200306F72672E737072696E676672616D65776F726B2E616F702E6672616D65776F726B2E41647669736564537570706F727424CB8A3CFAA4C5750200065A000B70726546696C74657265645B000C61647669736F7241727261797400225B4C6F72672F737072696E676672616D65776F726B2F616F702F41647669736F723B4C001361647669736F72436861696E466163746F72797400374C6F72672F737072696E676672616D65776F726B2F616F702F6672616D65776F726B2F41647669736F72436861696E466163746F72793B4C000861647669736F727371007E00154C000A696E746572666163657371007E00154C000C746172676574536F757263657400264C6F72672F737072696E676672616D65776F726B2F616F702F546172676574536F757263653B7872002D6F72672E737072696E676672616D65776F726B2E616F702E6672616D65776F726B2E50726F7879436F6E6669678B4BF3E6A7E0F76F0200055A000B6578706F736550726F78795A000666726F7A656E5A00066F70617175655A00086F7074696D697A655A001070726F7879546172676574436C6173737870000000000001757200225B4C6F72672E737072696E676672616D65776F726B2E616F702E41647669736F723BDF830DADD21E8474020000787000000001737200586F72672E737072696E676672616D65776F726B2E7472616E73616374696F6E2E696E746572636570746F722E4265616E466163746F72795472616E73616374696F6E417474726962757465536F7572636541647669736F722EE1529C217AD8FF0200024C0008706F696E746375747400504C6F72672F737072696E676672616D65776F726B2F7472616E73616374696F6E2F696E746572636570746F722F5472616E73616374696F6E417474726962757465536F75726365506F696E746375743B4C001A7472616E73616374696F6E417474726962757465536F757263657400484C6F72672F737072696E676672616D65776F726B2F7472616E73616374696F6E2F696E746572636570746F722F5472616E73616374696F6E417474726962757465536F757263653B787200426F72672E737072696E676672616D65776F726B2E616F702E737570706F72742E41627374726163744265616E466163746F7279506F696E7463757441647669736F72FAC362A1492488020200024C000E6164766963654265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000B6265616E466163746F727974002F4C6F72672F737072696E676672616D65776F726B2F6265616E732F666163746F72792F4265616E466163746F72793B787200376F72672E737072696E676672616D65776F726B2E616F702E737570706F72742E4162737472616374506F696E7463757441647669736F72A1BAB15A600C25A90200014C00056F726465727400134C6A6176612F6C616E672F496E74656765723B7870707400446F72672E737072696E676672616D65776F726B2E7472616E73616374696F6E2E696E746572636570746F722E5472616E73616374696F6E496E746572636570746F722330737200636F72672E737072696E676672616D65776F726B2E6265616E732E666163746F72792E737570706F72742E44656661756C744C69737461626C654265616E466163746F72792453657269616C697A65644265616E466163746F72795265666572656E6365189229C6D5ACBEF80200014C0002696471007E002278707400476F72672E737072696E676672616D65776F726B2E7765622E636F6E746578742E5765624170706C69636174696F6E436F6E746578743A2F736D617274686F6D65736572766963657372005A6F72672E737072696E676672616D65776F726B2E7472616E73616374696F6E2E696E746572636570746F722E4265616E466163746F72795472616E73616374696F6E417474726962757465536F7572636541647669736F7224311DD9F5942D8F32C80200014C000674686973243074005A4C6F72672F737072696E676672616D65776F726B2F7472616E73616374696F6E2F696E746572636570746F722F4265616E466163746F72795472616E73616374696F6E417474726962757465536F7572636541647669736F723B7872004E6F72672E737072696E676672616D65776F726B2E7472616E73616374696F6E2E696E746572636570746F722E5472616E73616374696F6E417474726962757465536F75726365506F696E746375747703D923E75B5482020000787071007E00267372004F6F72672E737072696E676672616D65776F726B2E7472616E73616374696F6E2E616E6E6F746174696F6E2E416E6E6F746174696F6E5472616E73616374696F6E417474726962757465536F7572636566D824771CB77BDB0200025A00117075626C69634D6574686F64734F6E6C794C0011616E6E6F746174696F6E5061727365727374000F4C6A6176612F7574696C2F5365743B787001737200176A6176612E7574696C2E4C696E6B656448617368536574D86CD75A95DD2A1E020000787200116A6176612E7574696C2E48617368536574BA44859596B8B7340300007870770C000000043F400000000000027372004C6F72672E737072696E676672616D65776F726B2E7472616E73616374696F6E2E616E6E6F746174696F6E2E537072696E675472616E73616374696F6E416E6E6F746174696F6E506172736572BE8EC9942BB9467302000078707372004A6F72672E737072696E676672616D65776F726B2E7472616E73616374696F6E2E616E6E6F746174696F6E2E456A62335472616E73616374696F6E416E6E6F746174696F6E506172736572A9C2DE43C63A6DB40200007870787372003C6F72672E737072696E676672616D65776F726B2E616F702E6672616D65776F726B2E44656661756C7441647669736F72436861696E466163746F727954DD6437E24E71F70200007870737200146A6176612E7574696C2E4C696E6B65644C6973740C29535D4A608822030000787077040000000171007E002678737200136A6176612E7574696C2E41727261794C6973747881D21D99C7619D03000149000473697A6578700000000277040000000A76720041636F6D2E6269656E636C6F75642E736D617274686F6D652E736572766963652E737973706172616D2E736572766963652E49537973506172616D53657276696365C71F9CB61FB92202020000787076720035636F6D2E6269656E636C6F75642E736D617274686F6D652E736572766963652E626173652E736572766963652E49536572766963650000000000000000000000787078737200346F72672E737072696E676672616D65776F726B2E616F702E7461726765742E53696E676C65746F6E546172676574536F757263657D556EF5C7F8FABA0200014C00067461726765747400124C6A6176612F6C616E672F4F626A6563743B787073720049636F6D2E6269656E636C6F75642E736D617274686F6D652E736572766963652E737973706172616D2E736572766963652E696D706C2E537973506172616D53657276696365496D706C1375FB1BB9138D10020000787001737200386F72672E737072696E676672616D65776F726B2E616F702E6672616D65776F726B2E44656661756C74416F7050726F7879466163746F7279B5698782D62D4E1702000078707371007E003B7704000000007874000C7461726765744D6574686F64740004776F726B7800);

-- ----------------------------
-- Table structure for `t_qrtz_job_listeners`
-- ----------------------------
DROP TABLE IF EXISTS `t_qrtz_job_listeners`;
CREATE TABLE `t_qrtz_job_listeners` (
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `JOB_LISTENER` varchar(200) NOT NULL,
  PRIMARY KEY (`JOB_NAME`,`JOB_GROUP`,`JOB_LISTENER`),
  KEY `JOB_NAME` (`JOB_NAME`,`JOB_GROUP`),
  CONSTRAINT `t_qrtz_job_listeners_ibfk_1` FOREIGN KEY (`JOB_NAME`, `JOB_GROUP`) REFERENCES `t_qrtz_job_details` (`JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_qrtz_job_listeners
-- ----------------------------

-- ----------------------------
-- Table structure for `t_qrtz_locks`
-- ----------------------------
DROP TABLE IF EXISTS `t_qrtz_locks`;
CREATE TABLE `t_qrtz_locks` (
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_qrtz_locks
-- ----------------------------
INSERT INTO `t_qrtz_locks` VALUES ('CALENDAR_ACCESS');
INSERT INTO `t_qrtz_locks` VALUES ('JOB_ACCESS');
INSERT INTO `t_qrtz_locks` VALUES ('MISFIRE_ACCESS');
INSERT INTO `t_qrtz_locks` VALUES ('STATE_ACCESS');
INSERT INTO `t_qrtz_locks` VALUES ('TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for `t_qrtz_paused_trigger_grps`
-- ----------------------------
DROP TABLE IF EXISTS `t_qrtz_paused_trigger_grps`;
CREATE TABLE `t_qrtz_paused_trigger_grps` (
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for `t_qrtz_scheduler_state`
-- ----------------------------
DROP TABLE IF EXISTS `t_qrtz_scheduler_state`;
CREATE TABLE `t_qrtz_scheduler_state` (
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_qrtz_scheduler_state
-- ----------------------------
INSERT INTO `t_qrtz_scheduler_state` VALUES ('xiaozhen1335275384937', '1335275425843', '20000');

-- ----------------------------
-- Table structure for `t_qrtz_simple_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `t_qrtz_simple_triggers`;
CREATE TABLE `t_qrtz_simple_triggers` (
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `TRIGGER_NAME` (`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `t_qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `t_qrtz_triggers` (`TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `t_qrtz_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `t_qrtz_triggers`;
CREATE TABLE `t_qrtz_triggers` (
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `IS_VOLATILE` varchar(1) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `JOB_NAME` (`JOB_NAME`,`JOB_GROUP`),
  CONSTRAINT `t_qrtz_triggers_ibfk_1` FOREIGN KEY (`JOB_NAME`, `JOB_GROUP`) REFERENCES `t_qrtz_job_details` (`JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_qrtz_triggers
-- ----------------------------
INSERT INTO `t_qrtz_triggers` VALUES ('publishSoftwaresTrigger', 'DEFAULT', 'publishSoftwaresJob', 'SoftwareUpgrade', '0', null, '1335442560000', '-1', '5', 'WAITING', 'CRON', '1335442284000', '0', null, '0', '');
INSERT INTO `t_qrtz_triggers` VALUES ('test_doTime', 'DEFAULT', 'test_jobtask', 'DEFAULT', '0', null, '1335275460000', '1335275400000', '5', 'WAITING', 'CRON', '1335275319000', '0', null, '0', '');

-- ----------------------------
-- Table structure for `t_qrtz_trigger_listeners`
-- ----------------------------
DROP TABLE IF EXISTS `t_qrtz_trigger_listeners`;
CREATE TABLE `t_qrtz_trigger_listeners` (
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `TRIGGER_LISTENER` varchar(200) NOT NULL,
  PRIMARY KEY (`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_LISTENER`),
  KEY `TRIGGER_NAME` (`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `t_qrtz_trigger_listeners_ibfk_1` FOREIGN KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `t_qrtz_triggers` (`TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_qrtz_trigger_listeners
-- ----------------------------

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `role_code` varchar(10) NOT NULL,
  `menu_code` varchar(10) DEFAULT NULL,
  `is_default` char(1) NOT NULL COMMENT '0：是；1：否',
  `status` char(1) NOT NULL COMMENT '0：正常；1：禁用',
  `role_desc` varchar(50) DEFAULT NULL,
  `created_user` varchar(50) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_user` varchar(50) DEFAULT NULL,
  `updated_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------

-- ----------------------------
-- Table structure for `t_sms_receiver`
-- ----------------------------
DROP TABLE IF EXISTS `t_sms_receiver`;
CREATE TABLE `t_sms_receiver` (
  `receive_id` bigint(20) NOT NULL,
  `send_id` bigint(20) NOT NULL,
  `receive_user_type_id` int(2) NOT NULL COMMENT '0ҵ1ҵû2ϵͳû',
  `receive_user_id` bigint(20) DEFAULT NULL COMMENT 'ܡҵϵͳû',
  `receive_device_id` bigint(20) DEFAULT NULL,
  `receive_phone` varchar(30) DEFAULT NULL,
  `sys_group_id` int(12) DEFAULT NULL,
  `status` int(1) NOT NULL COMMENT '0գ1ѽգ2δ',
  PRIMARY KEY (`receive_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Žշ';

-- ----------------------------
-- Records of t_sms_receiver
-- ----------------------------

-- ----------------------------
-- Table structure for `t_software_upgrade`
-- ----------------------------
DROP TABLE IF EXISTS `t_software_upgrade`;
CREATE TABLE `t_software_upgrade` (
  `software_id` int(10) NOT NULL,
  `device_type` char(3) DEFAULT NULL,
  `software_code` varchar(10) DEFAULT NULL,
  `software_name` varchar(40) DEFAULT NULL,
  `version_name` varchar(60) DEFAULT NULL,
  `version` char(1) DEFAULT NULL,
  `save_path` varchar(200) DEFAULT NULL,
  `software_desc` varchar(200) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '0：已上传，1：已审核，2：审核不通过，3：已发布',
  `applyed_user` varchar(50) DEFAULT NULL,
  `applyed_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `approved_user` varchar(50) DEFAULT NULL,
  `approved_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `published_user` varchar(50) DEFAULT NULL,
  `published_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`software_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_software_upgrade
-- ----------------------------

-- ----------------------------
-- Table structure for `t_system_group`
-- ----------------------------
DROP TABLE IF EXISTS `t_system_group`;
CREATE TABLE `t_system_group` (
  `group_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(50) NOT NULL,
  `deep` tinyint(4) NOT NULL,
  `group_parentNo` varchar(50) NOT NULL,
  `hasChild` tinyint(4) NOT NULL,
  `create_user` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` text,
  PRIMARY KEY (`group_no`)
) ENGINE=InnoDB AUTO_INCREMENT=12022167 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_system_group
-- ----------------------------
INSERT INTO `t_system_group` VALUES ('1111520', '中国', '0', '', '1', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('11000000', '北京市', '1', '1111520', '1', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('11010100', '东城区', '3', '11000000', '0', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('11010200', '西城区', '3', '11000000', '0', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('11010300', '崇文区', '3', '11000000', '0', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('11010400', '宣武区', '3', '11000000', '0', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('11010500', '广东省', '3', '1111520', '0', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('11010600', '丰台区', '3', '11010500', '0', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('11010700', '石景山区', '3', '11010500', '0', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('11010800', '海淀区', '3', '11010500', '0', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('11010900', '门头沟区2', '3', '11010500', '1', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('11011100', '房山区', '3', '11010500', '0', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('11011200', '深圳市', '3', '11010500', '0', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('11011300', '顺义区', '3', '11011200', '1', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('11011600', '景田', '3', '11011500', '0', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('11011700', '市政大院社区', '3', '11011600', '0', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('11022800', '密云县', '3', '11011500', '0', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('11022900', '延庆县', '3', '11000000', '1', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('12000000', '天津市', '1', '00000000', '1', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('12010100', '和平区', '3', '12000000', '0', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('12010200', '河东区', '3', '12000000', '0', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('12010300', '河西区', '3', '12000000', '0', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('12010400', '南开区', '3', '12000000', '0', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('12010500', '河北区', '3', '12000000', '0', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('12010600', '红桥区', '3', '12000000', '0', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('12010700', '塘沽区', '3', '12000000', '0', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('12010800', '东莞市', '3', '11010500', '0', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('12010900', '大港区', '3', '12010800', '1', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('12011000', '东丽区', '3', '12010800', '0', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('12011100', '西青区', '3', '12010800', '0', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('12011200', '津南区', '3', '12010800', '0', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('12011300', '北辰区', '3', '12010800', '0', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('12011400', '武清区', '3', '12000000', '0', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('12011500', '宝坻区', '3', '12000000', '0', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('12022100', '宁河县', '3', '12000000', '0', 'admin', '2011-05-09 11:41:55', null, null, null);
INSERT INTO `t_system_group` VALUES ('12022101', '华区', '1', '11010400', '0', null, '2012-05-09 20:26:41', null, '2012-05-09 20:26:41', null);
INSERT INTO `t_system_group` VALUES ('12022102', '华区', '1', '11022800', '0', null, '2012-05-09 21:04:48', null, '2012-05-09 21:04:48', null);
INSERT INTO `t_system_group` VALUES ('12022103', '华区', '1', '11022800', '0', null, '2012-05-09 21:13:55', null, '2012-05-09 21:13:55', null);
INSERT INTO `t_system_group` VALUES ('12022104', '华区2', '1', '12010900', '1', null, '2012-05-09 21:15:34', null, '2012-05-09 21:15:34', null);
INSERT INTO `t_system_group` VALUES ('12022106', '华区', '1', '11010800', '1', null, '2012-05-10 10:02:36', null, '2012-05-10 10:02:36', null);
INSERT INTO `t_system_group` VALUES ('12022107', '华区', '1', '11011500', '0', null, '2012-05-10 10:04:44', null, '2012-05-10 10:04:44', null);
INSERT INTO `t_system_group` VALUES ('12022108', '华区', '1', '11022800', '0', null, '2012-05-10 10:15:25', null, '2012-05-10 10:15:25', null);
INSERT INTO `t_system_group` VALUES ('12022109', '华区', '1', '11022800', '0', null, '2012-05-10 10:21:22', null, '2012-05-10 10:21:22', null);
INSERT INTO `t_system_group` VALUES ('12022110', '华区', '1', '11022800', '0', null, '2012-05-10 10:23:35', null, '2012-05-10 10:23:35', null);
INSERT INTO `t_system_group` VALUES ('12022111', '华区', '1', '11022800', '0', null, '2012-05-10 10:26:42', null, '2012-05-10 10:26:42', null);
INSERT INTO `t_system_group` VALUES ('12022112', '华区', '1', '12022102', '0', null, '2012-05-10 10:36:53', null, '2012-05-10 10:36:53', null);
INSERT INTO `t_system_group` VALUES ('12022114', '小莲社区市', '5', '12022102', '0', 'admin', '2012-05-10 10:41:32', 'admin', '2012-05-10 10:41:32', null);
INSERT INTO `t_system_group` VALUES ('12022115', '小莲社区', '1', '11011600', '0', 'admin', '2012-05-10 10:43:09', 'admin', '2012-05-10 10:43:09', null);
INSERT INTO `t_system_group` VALUES ('12022118', '小莲社区', '1', '12022108', '0', 'admin', '2012-05-10 10:52:51', 'admin', '2012-05-10 10:52:51', null);
INSERT INTO `t_system_group` VALUES ('12022121', '小莲社区', '1', '11011700', '0', 'admin', '2012-05-10 11:03:34', 'admin', '2012-05-10 11:03:34', null);
INSERT INTO `t_system_group` VALUES ('12022126', '小莲社区', '3', '12022113', '0', 'admin', '2012-05-10 11:06:30', 'admin', '2012-05-10 11:06:30', null);
INSERT INTO `t_system_group` VALUES ('12022129', '湖北省2', '1', '1111520', '1', 'admin', '2012-05-10 12:37:52', 'admin', '2012-05-10 12:37:52', null);
INSERT INTO `t_system_group` VALUES ('12022131', '湖菊县', '2', '12022130', '0', 'admin', '2012-05-10 13:52:55', 'admin', '2012-05-10 13:52:55', null);
INSERT INTO `t_system_group` VALUES ('12022132', '上村小区', '2', '12022117', '0', 'admin', '2012-05-10 14:02:02', 'admin', '2012-05-10 14:02:02', null);
INSERT INTO `t_system_group` VALUES ('12022133', '上村小区', '2', '12022117', '1', 'admin', '2012-05-10 14:02:15', 'admin', '2012-05-10 14:02:15', null);
INSERT INTO `t_system_group` VALUES ('12022134', '上村小区', '2', '12022105', '0', 'admin', '2012-05-10 14:03:29', 'admin', '2012-05-10 14:03:29', null);
INSERT INTO `t_system_group` VALUES ('12022135', '上村小区', '3', '12022133', '0', 'admin', '2012-05-10 14:59:11', 'admin', '2012-05-10 14:59:11', null);
INSERT INTO `t_system_group` VALUES ('12022136', '上村小区', '3', '12022133', '0', 'admin', '2012-05-10 14:59:22', 'admin', '2012-05-10 14:59:22', null);
INSERT INTO `t_system_group` VALUES ('12022137', '上村小区', '3', '12022133', '0', 'admin', '2012-05-10 14:59:45', 'admin', '2012-05-10 14:59:45', null);
INSERT INTO `t_system_group` VALUES ('12022138', '上村小区', '3', '12022133', '0', 'admin', '2012-05-10 15:00:00', 'admin', '2012-05-10 15:00:00', null);
INSERT INTO `t_system_group` VALUES ('12022139', '上村小区', '3', '12022133', '0', '', '2012-05-10 15:19:53', '', '2012-05-10 15:19:53', null);
INSERT INTO `t_system_group` VALUES ('12022159', '', '0', '-1', '0', '', '2012-05-10 20:02:48', '', '2012-05-10 20:02:48', null);
INSERT INTO `t_system_group` VALUES ('12022160', '', '4', '11011300', '0', '', '2012-05-10 20:03:01', '', '2012-05-10 20:03:01', null);
INSERT INTO `t_system_group` VALUES ('12022161', '', '2', '12022106', '0', '', '2012-05-10 20:10:25', '', '2012-05-10 20:10:25', null);
INSERT INTO `t_system_group` VALUES ('12022162', '', '4', '11010900', '0', '', '2012-05-10 20:10:34', '', '2012-05-10 20:10:34', null);
INSERT INTO `t_system_group` VALUES ('12022163', '', '4', '11022900', '0', '', '2012-05-10 20:11:57', '', '2012-05-10 20:11:57', null);
INSERT INTO `t_system_group` VALUES ('12022164', '小莲社区', '2', '12022129', '0', '', '2012-05-10 21:21:35', '', '2012-05-10 21:21:35', null);
INSERT INTO `t_system_group` VALUES ('12022165', '上村小区', '2', '12022104', '0', '', '2012-05-10 21:27:27', '', '2012-05-10 21:27:27', null);
INSERT INTO `t_system_group` VALUES ('12022166', '小莲社区2', '2', '12022104', '0', '', '2012-05-10 21:28:03', '', '2012-05-10 21:28:03', null);

-- ----------------------------
-- Table structure for `t_system_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_system_log`;
CREATE TABLE `t_system_log` (
  `log_id` bigint(20) NOT NULL,
  `operate_user` varchar(50) DEFAULT NULL,
  `operate_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `menu_code` varchar(10) NOT NULL,
  `operation_code` char(4) NOT NULL,
  `error_location` varchar(200) DEFAULT NULL,
  `error_msg` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_system_log
-- ----------------------------

-- ----------------------------
-- Table structure for `t_system_param`
-- ----------------------------
DROP TABLE IF EXISTS `t_system_param`;
CREATE TABLE `t_system_param` (
  `param_code` varchar(20) NOT NULL,
  `param_value` varchar(200) NOT NULL,
  `param_desc` varchar(200) DEFAULT NULL,
  `updated_user` varchar(50) DEFAULT NULL,
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`param_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_system_param
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_type`;
CREATE TABLE `t_user_type` (
  `type_id` char(2) NOT NULL COMMENT '01：业主，02：物业，03：系统',
  `type_desc` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_type
-- ----------------------------

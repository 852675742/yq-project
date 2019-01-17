SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for message_record
-- ----------------------------
DROP TABLE IF EXISTS `message_record`;
CREATE TABLE `message_record` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `message_no` varchar(80) NOT NULL COMMENT '消息号唯一',
  `sender_name` varchar(60) NOT NULL COMMENT '消息发送端名称',
  `status` varchar(20) NOT NULL DEFAULT 'NEW' COMMENT '消息状态',
  `operator` varchar(50) DEFAULT NULL COMMENT '操作人',
  `payload` longtext COMMENT '消息详情,参数',
  `operation_id` int(11) NOT NULL COMMENT '本次操作ID',
  `doc_no` varchar(100) DEFAULT NULL COMMENT '关联单据号',
  `chain_no` varchar(150) NOT NULL COMMENT '链条号,用于标识一组操作',
  `date_created` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `last_updated` datetime(6) DEFAULT NULL COMMENT '最后处理时间',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `message_record_message_no_unique_index` (`message_no`),
  KEY `message_record_operation_id_index` (`operation_id`),
  KEY `message_record_chain_no_index` (`chain_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='待处理消息';

-- ----------------------------
-- Table structure for operation_definition
-- ----------------------------
DROP TABLE IF EXISTS `operation_definition`;
CREATE TABLE `operation_definition` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `method_name` varchar(80) NOT NULL COMMENT '接口名称',
  `service_name` varchar(80) NOT NULL COMMENT '服务类名称',
  `date_created` datetime NOT NULL COMMENT '创建时间',
  `last_updated` datetime NOT NULL COMMENT '最后修改时间',
  `creator_name` varchar(50) NOT NULL COMMENT '创建人名称',
  `description` varchar(150) DEFAULT NULL COMMENT '接口描述',
  `enabled` bit(1) NOT NULL DEFAULT b'1' COMMENT '启用/禁用',
  `need_send_message` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否需要发送消息',
  `destination` varchar(150) DEFAULT NULL COMMENT '队列名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8 COMMENT='操作定义,标识接口';

-- ----------------------------
-- Table structure for operation_log
-- ----------------------------
DROP TABLE IF EXISTS `operation_log`;
CREATE TABLE `operation_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `record_id` bigint(11) NOT NULL COMMENT '待处理消息ID',
  `date_created` datetime(6) NOT NULL COMMENT '创建日期',
  `operator` varchar(50) DEFAULT NULL COMMENT '操作人名称',
  `status` varchar(20) DEFAULT NULL COMMENT '执行本次操作后状态名称',
  `detail` text COMMENT '处理详情',
  PRIMARY KEY (`id`),
  KEY `operation_log_record_id_index` (`record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17443 DEFAULT CHARSET=utf8 COMMENT='操作日志';

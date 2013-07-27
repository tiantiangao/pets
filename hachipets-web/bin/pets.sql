CREATE TABLE `Pets_GlobalConfig` (
  `id` int(11) NOT NULL auto_increment COMMENT '自增主键',
  `name` varchar(50) NOT NULL COMMENT '配置名称',
  `config` varchar(50) NOT NULL COMMENT '配置的值',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `IX_NAME` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

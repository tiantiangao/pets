CREATE TABLE `Pets_GlobalConfig` (
  `id` int(11) NOT NULL auto_increment COMMENT '自增主键',
  `name` varchar(50) NOT NULL COMMENT '配置名称',
  `config` varchar(50) NOT NULL COMMENT '配置的值',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `IX_NAME` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into `Pets_GlobalConfig` (`id`, `name`, `config`) values('1','projectName','Hachi宠物网');

CREATE TABLE `Pets_Movie` (
  `id` int(11) NOT NULL auto_increment COMMENT '自增主键',
  `name` varchar(30) default NULL COMMENT '影片名称',
  `desc` varchar(500) default NULL COMMENT '影片描述',
  `pic` varchar(100) default NULL COMMENT '影片图片地址',
  `director` varchar(50) default NULL COMMENT '影片导演',
  `actor` varchar(100) default NULL COMMENT '影片主演',
  `region` varchar(15) default NULL COMMENT '影片地区',
  `length` smallint(6) default 0 COMMENT '影片片长',
  `release` datetime default NULL COMMENT '影片上映日期',
  `year` smallint(6) default 0 COMMENT '影片所属年代',
  `addTime` datetime default NULL COMMENT '记录添加时间',
  PRIMARY KEY  (`id`),
  KEY `IX_Name` (`name`),
  KEY `IX_Release` (`release`),
  KEY `IX_Region_Name` (`region`,`name`),
  KEY `IX_Region_Release` (`region`,`release`),
  KEY `IX_Year_Name` (`year`,`name`),
  KEY `IX_Year_Release` (`year`,`release`),
  KEY `IX_Region_Year_Name` (`region`, `year`,`name`),
  KEY `IX_Region_Year_Release` (`region`, `year`,`release`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `Pets_Movie` (`id`, `name`, `desc`, `pic`, `director`, `actor`, `region`, `length`, `release`, `year`, `addTime`)
VALUES (1, '忠犬八公的故事', '理察基尔饰演的大学教授帕克在小镇在车站上偶遇一只可怜的小秋田犬，它孤苦无依的身影惹起他的怜 悯，虽然妻子（琼·艾伦饰）极力反对，并想尽办法要把它送走，但看到丈夫和女儿对它无微不至照顾和由衷喜爱，终于决定让它成为家庭一员，帕克为它取名“八公”。', NULL, '莱塞·霍尔斯道姆', '理查·基尔 / 萨拉·罗默尔 / 琼·艾伦', '美国', 93, '2009-12-18 00:00:00', 2009, NOW());

CREATE TABLE `Pets_Movie_Info` (
  `id` int(11) NOT NULL auto_increment COMMENT '自增主键',
  `movieId` int(11) NOT NULL COMMENT '宠物电影记录的ID',
  `infoType` tinyint(4) NOT NULL COMMENT '宠物电影信息网站类型(豆瓣、MTime等)',
  `refer` varchar(30) NOT NULL COMMENT '宠物电影信息网站关联',
  PRIMARY KEY  (`id`),
  KEY `IX_MovieID` (`movieId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Pets_Movie_Play` (
  `id` int(11) NOT NULL auto_increment COMMENT '自增主键',
  `movieId` int(11) NOT NULL COMMENT '宠物电影信息记录ID',
  `playType` tinyint(4) NOT NULL COMMENT '宠物电影播放网站类型(1-优酷，2-土豆，3-爱奇艺, 4-腾讯, 5-PPTV, 6-乐视等)',
  `address` varchar(100) NOT NULL COMMENT '宠物电影播放网站关联地址',
  PRIMARY KEY  (`id`),
  KEY `IX_MovieID` (`movieId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Pets_Movie_Hot` (
  `id` int(11) NOT NULL auto_increment COMMENT '自增主键',
  `movieId` int(11) NOT NULL COMMENT '宠物电影信息记录ID',
  `hot` int(11) NOT NULL COMMENT '宠物电影信息记录的热门度',
  PRIMARY KEY  (`id`),
  KEY `IX_Hot_MovieID` (`hot`, `movieId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Pets_Movie_Recommend` (
  `id` int(11) NOT NULL auto_increment COMMENT '自增主键',
  `movieId` int(11) NOT NULL COMMENT '宠物电影信息记录ID',
  `addTime` datetime default NULL COMMENT '推荐日期',
  PRIMARY KEY  (`id`),
  KEY `IX_Date_MovieID` (`addTime`, `movieId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `Pets_Movie_Recommend` (`movieId`, `addTime`)
  VALUES (1, NOW());


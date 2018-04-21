CREATE TABLE `hospital` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` char(50) NOT NULL COMMENT '医院名称',
  `level` char(2) DEFAULT NULL COMMENT '医院等级',
  `city` char(1) DEFAULT NULL COMMENT '医院所在城市',
  `lonAndLat` text COMMENT '医院经纬度，json表示。暂时没有用到',
  `latitude` double DEFAULT NULL COMMENT '纬度',
  `longitude` double DEFAULT NULL COMMENT '经度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=182 DEFAULT CHARSET=utf8


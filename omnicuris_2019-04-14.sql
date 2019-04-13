# Dump of table customer_info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `customer_info`;

CREATE TABLE `customer_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '',
  `email` varchar(50) NOT NULL DEFAULT '',
  `phone` varchar(20) NOT NULL DEFAULT '',
  `password` varchar(250) NOT NULL DEFAULT '',
  `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

LOCK TABLES `customer_info` WRITE;
/*!40000 ALTER TABLE `customer_info` DISABLE KEYS */;

INSERT INTO `customer_info` (`id`, `name`, `email`, `phone`, `password`, `created_on`)
VALUES
	(1,'test','s@gmail.com','8050097750','ababababab','2019-04-13 18:42:30'),
	(5,'ashwani','b@gmail.com','1234567890','sssss','2019-04-13 18:42:30'),
	(8,'sssssssssss','e@gmail.com','1234567890','sssss','2019-04-13 18:47:50');

/*!40000 ALTER TABLE `customer_info` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table inventory_info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `inventory_info`;

CREATE TABLE `inventory_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `product_id_fk` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

LOCK TABLES `inventory_info` WRITE;
/*!40000 ALTER TABLE `inventory_info` DISABLE KEYS */;

INSERT INTO `inventory_info` (`id`, `product_id`, `quantity`, `created_on`)
VALUES
	(1,1,20,'2019-04-13 19:34:06'),
	(2,2,20,'2019-04-13 19:34:52'),
	(4,1,10,'2019-04-13 21:18:48');

/*!40000 ALTER TABLE `inventory_info` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table order_info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `order_info`;

CREATE TABLE `order_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `cid_fk` (`customer_id`),
  KEY `pid_fk` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

LOCK TABLES `order_info` WRITE;
/*!40000 ALTER TABLE `order_info` DISABLE KEYS */;

INSERT INTO `order_info` (`id`, `product_id`, `customer_id`, `quantity`, `created_on`)
VALUES
	(3,1,1,10,'2019-04-13 21:06:50'),
	(4,1,1,10,'2019-04-13 21:07:59'),
	(5,1,1,10,'2019-04-13 21:12:43'),
	(6,2,5,1,'2019-04-13 21:12:46');

/*!40000 ALTER TABLE `order_info` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table product_info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `product_info`;

CREATE TABLE `product_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '',
  `description` varchar(250) NOT NULL DEFAULT '',
  `actual_price` int(11) NOT NULL,
  `offered_price` int(11) NOT NULL,
  `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

LOCK TABLES `product_info` WRITE;
/*!40000 ALTER TABLE `product_info` DISABLE KEYS */;

INSERT INTO `product_info` (`id`, `name`, `description`, `actual_price`, `offered_price`, `created_on`)
VALUES
	(1,'tesssss','fvyafvuudbdfubuafdbufdufdgu',300,260,'2019-04-13 19:34:05'),
	(2,'ssssssss','fdskjhdfjkhjghgghfdgj',100,80,'2019-04-13 19:34:51'),
	(3,'gggggg','fdskjhdfjkhjghgghfdgj',100,80,'2019-04-13 20:07:00');

/*!40000 ALTER TABLE `product_info` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

CREATE TABLE `cases` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `location_id` int(11) NOT NULL,
  `reporting_date` date DEFAULT NULL,
  `confirmed` bigint(20) NOT NULL,
  `deaths` int(20) DEFAULT NULL,
  `recovered` bigint(20) DEFAULT NULL,
  `reporting_timestamp` timestamp NULL DEFAULT NULL,
  `file_name` varchar(250) NOT NULL,
  `created_At` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `file_name` (`file_name`)
) ENGINE=InnoDB AUTO_INCREMENT=24708 DEFAULT CHARSET=latin1;

CREATE TABLE `location` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `country` varchar(250) NOT NULL,
  `state` varchar(250) DEFAULT NULL,
  `latitude` decimal(10,5) DEFAULT NULL,
  `longitude` decimal(10,5) DEFAULT NULL,
  `file_name` varchar(250) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=521 DEFAULT CHARSET=latin1;

CREATE TABLE `age_wise_cases` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `location_id` int(11) NOT NULL,
  `age` int(2) NOT NULL,
  `reporting_date` timestamp NULL DEFAULT NULL,
  `confirmed` bigint(20) NOT NULL,
  `deaths` bigint(20) unsigned zerofill NOT NULL,
  `recovered` bigint(20) unsigned zerofill NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
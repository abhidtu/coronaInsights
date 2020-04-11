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
  KEY `file_name` (`file_name`),
  KEY `composite_unique_index` (`location_id`,`reporting_timestamp`,`recovered`,`confirmed`,`deaths`,`file_name`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

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

CREATE TABLE `country_wise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reporting_date` date NOT NULL,
  `country` varchar(250) NOT NULL DEFAULT '',
  `confirmed` bigint(20) NOT NULL DEFAULT '0',
  `deaths` int(11) NOT NULL DEFAULT '0',
  `recovered` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4790 DEFAULT CHARSET=utf8;

CREATE TABLE `properties` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `key` varchar(100) NOT NULL DEFAULT '',
  `value` varchar(200) DEFAULT '',
  `value_modified_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `state_wise` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `reporting_date` date NOT NULL,
  `country` varchar(11) NOT NULL,
  `state` varchar(11) NOT NULL DEFAULT '',
  `confirmed` bigint(20) NOT NULL DEFAULT '0',
  `deaths` int(11) NOT NULL DEFAULT '0',
  `recovered` bigint(20) NOT NULL DEFAULT '0',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1531 DEFAULT CHARSET=utf8;
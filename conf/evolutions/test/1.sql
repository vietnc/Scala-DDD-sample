CREATE TABLE article (
  id varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  title varchar(225) COLLATE utf8_unicode_ci DEFAULT NULL,
  content text COLLATE utf8_unicode_ci,
  email varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  created_date datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
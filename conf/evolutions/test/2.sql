# --- !Ups
CREATE TABLE user (
  id varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  email varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  password varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
);
insert into `user` values("12323","vietngc@gmail.com", MD5("123"));

# --- !Downs
Drop table user;
/* 2016-12-15 begin */

/* user table begin */
create table tbwoziuser(id varchar(36) primary key,
username varchar(25),
password varchar(25),
registdate date,
lastlogindate date,
email varchar(25))ENGINE=MyISAM DEFAULT CHARSET=utf8;
/* user table end */

/* 2016-12-15 end */

/* 2016-12-16 begin */
/*   alert user table begin */
ALTER TABLE `wozi`.`tbwoziuser` 
CHANGE COLUMN `registdate` `registdate` DATETIME NULL DEFAULT NULL ,
CHANGE COLUMN `lastlogindate` `lastlogindate` DATETIME NULL DEFAULT NULL ;
/*   alert user table end   */
/* 2016-12-16 end   */
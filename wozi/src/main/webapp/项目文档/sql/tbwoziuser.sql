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

/* 2016-12-17 begin */
table name : "tb_wozi_note_menu",
字段:id   parent   text(默认表示名称)  flag（0表示目录，1表示文件) ref_user(关联用户)
create table tb_wozi_note_menu (
	id varchar(36) primary key,
	parent varchar(36) default "#",
	text varchar(36),
	flag varchar(1) default 0,
    ref_user varchar(36) ,
    constraint foreign key (ref_user) references tbwoziuser(id) ON delete cascade
    )engine=innoDB default charset=utf8
    
   更改 tb_wozi_note_menu 表 添加一个状态字段 isDelete  0表示未删除状态  1表示删除状态
   	ALTER TABLE `wozi`.`tb_wozi_note_menu` 
	ADD COLUMN `isDelete` VARCHAR(1) NULL DEFAULT 0 AFTER `ref_user`;

/* 2016-12-17 end */

/* 2016-12-18 begin */
	ALTER TABLE `wozi`.`tb_wozi_note_menu` 
	ADD COLUMN `e_node_id` VARCHAR(45) NULL AFTER `isDelete`;
	
	/* 主键字段加长 */
	ALTER TABLE `wozi`.`tb_wozi_note_menu` 
	CHANGE COLUMN `id` `id` VARCHAR(100) NOT NULL ;
	
	ALTER TABLE `wozi`.`tb_wozi_note_menu` 
	CHANGE COLUMN `parent` `parent` VARCHAR(100) NULL DEFAULT '#' ;

/* 2016-12-18 end */






-- -----------------------------------------------------
-- Schema account_db
-- -----------------------------------------------------
CREATE DATABASE `account_db`
	DEFAULT CHARACTER SET utf8
	DEFAULT COLLATE utf8_general_ci;
USE 'account_db';

-- -----------------------------------------------------
-- Table t_account_user
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS t_account_user
(
   uuid                 bigint(19) NOT NULL AUTO_INCREMENT comment 'uuid',
   create_time          datetime DEFAULT CURRENT_TIMESTAMP comment '创建时间',
   usable_flag          int default 0 comment '停用标识 1-停用，0-可用  默认0',
   mobile               varchar(15) comment '手机号',
   login_pwd            varchar(128) comment '登录密码',
   pwd_salt             varchar(128) comment '密码盐',
   PRIMARY KEY (`uuid`)
);
-- -----------------------------------------------------
-- Table `account`.`t_account_user_balance_tcc`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS t_account_wx_info
(
   uuid                 bigint(19) comment 'uuid',
   user_id              bigint(19) comment '用户id',
   appid                varchar(50) comment 'appid',
   openid               varchar(50) comment 'openid',
   session_key          varchar(200) comment 'session_key',
   PRIMARY KEY (`uuid`)
);
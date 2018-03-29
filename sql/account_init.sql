-- -----------------------------------------------------
-- Schema account_db
-- -----------------------------------------------------
CREATE DATABASE `account_db`
	DEFAULT CHARACTER SET utf8
	DEFAULT COLLATE utf8_general_ci;
USE 'account_db';

-- -----------------------------------------------------
-- Table `account_db`.`t_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `account_db`.`t_account_user`(  
  `id` BIGINT(19) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'uuid',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '跟新时间',
  `delete_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '删除时间',
  `delete_flag` INT DEFAULT 0 COMMENT '0-正常，1-已删除',
  `mobile` VARCHAR(20) COMMENT '手机号',
  `login_pwd` VARCHAR(128) COMMENT '登录密码',
  `pwd_salt` VARCHAR(128) COMMENT '密码盐',
  `balance` BIGINT(19) DEFAULT 10000000 COMMENT '余额, 单位分',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uq_t_account_user` (`mobile`)
);

-- -----------------------------------------------------
-- Table `account`.`t_account_user_balance_tcc`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `account_db`.`t_account_user_balance_tcc`(  
  `id` BIGINT(19) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'uuid',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '跟新时间',
  `delete_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '删除时间',
  `delete_flag` INT DEFAULT 0 COMMENT '0-正常，1-已删除',
  `expire_time` DATETIME DEFAULT '1970-01-01 00:00:00' COMMENT '过期时间',
  `amount` BIGINT  COMMENT '预留资源金额',
  `status` int DEFAULT 0 COMMENT '0为try, 1为confirm完成',
  `t_account_user_id` BIGINT(19) UNSIGNED COMMENT 't_account_user表主键',
  PRIMARY KEY (`id`),
  INDEX `fk_t_account_user_tcc_status_exptime` (`status` ASC , `expire_time` ASC)
);
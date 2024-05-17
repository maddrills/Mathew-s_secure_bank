-- E:\Mega Project\Mathew-s_secure_bank\The Application\db\MySQL
-- new db brantch 
DROP DATABASE IF EXISTS `mathew_bank_db`;

CREATE DATABASE `mathew_bank_db`;

USE `mathew_bank_db`;

-- foreign key to be added to this table in alter table form  
CREATE TABLE `user`(
`u_id` INTEGER NOT NULL AUTO_INCREMENT,
`user_name` VARCHAR(70) NOT NULL,
`password` VARCHAR(68) NOT NULL,
-- fk to uder_account 
`user_account_id` INTEGER UNIQUE NOT NULL, 
CONSTRAINT `user_table_pk` PRIMARY KEY(`u_id`)
)ENGINE = 'Innodb', AUTO_INCREMENT = 1, DEFAULT CHARSET 'latin1';


CREATE TABLE `user_details`(
`ud_id` INTEGER NOT NULL AUTO_INCREMENT,
`phone_number` CHAR(10) CHECK (count(`phone_number`) = 10),
`DOB` DATE NOT NULL,
`age` TINYINT NOT NULL,
`email` VARCHAR(100) NOT NULL,
`u_id` INTEGER NOT NULL UNIQUE,
CONSTRAINT `user_details_table_pk` PRIMARY KEY (`ud_id`),
CONSTRAINT `user_details_table_foreign_key_u_id` FOREIGN KEY(`u_id`) REFERENCES `user`(`u_id`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 1, DEFAULT CHARSET 'latin1';

CREATE TABLE `notification`(
`not_id` INTEGER NOT NULL AUTO_INCREMENT,
`message` VARCHAR(250),
`u_id` INTEGER NOt NULL,
CONSTRAINT `Notification_table_pk` PRIMARY KEY (`not_id`),
CONSTRAINT `Notification_table_foreign_key_u_id` FOREIGN KEY(`u_id`) REFERENCES `user`(`u_id`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 1, DEFAULT CHARSET 'latin1';


CREATE TABLE `user_lones`(
`user_lones_id` INTEGER NOT NULL AUTO_INCREMENT,
`issue_date` DATETIME NOT NULL,
`aoorovel_date` DATETIME,
`return_date` DATETIME,
-- foreign key  
`u_id` INTEGER NOT NULL,
`lone_type_id` INTEGER NOT NULL,
-- 
CONSTRAINT `user_lones_table_pk` PRIMARY KEY (`user_lones_id`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 1, DEFAULT CHARSET 'latin1';


-- USER ROLES ROLE GOES HERE 
CREATE TABLE `roles`(
`role_id` INTEGER NOT NULL AUTO_INCREMENT,
`role_name` VARCHAR(20) NOT NULL,
CONSTRAINT `roles_pk` PRIMARY KEY (`role_id`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 1, DEFAULT CHARSET 'latin1';



CREATE TABLE `user_roles`(
`u_id` INTEGER NOT NULL,
`role_id` INTEGER NOT NULL,
CONSTRAINT `user_roles_pk` PRIMARY KEY(`u_id`,`role_id`),
CONSTRAINT `user_roles_users_fk` FOREIGN KEY (`u_id`) REFERENCES `users`(`u_id`),
CONSTRAINT `user_roles_roles_fk` FOREIGN KEY (`role_id`) REFERENCES `roles`(`role_id`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 1, DEFAULT CHARSET 'latin1';


CREATE TABLE `checking`(
`chk_account_number` INTEGER NOt NULL AUTO_INCREMENT,
`hold` BOOLEAN NOT NULL DEFAULT 0,
`active` BOOLEAN NOT NULL DEFAULT 0,
`amount` DOUBLE NOT NULl DEFAULT 0 CHECK(`amount` >= 0.0),
`next_interest_on`DATETIME NOT NULL,
`created_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT `checking_pk` PRIMARY KEY (`chk_account_number`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 101000001, DEFAULT CHARSET 'latin1';

CREATE TABLE `savings`(
`s_account_number` INTEGER NOt NULL AUTO_INCREMENT,
`hold` BOOLEAN NOT NULL DEFAULT 0,
`active` BOOLEAN NOT NULL DEFAULT 0,
`amount` DOUBLE NOT NULl DEFAULT 0 CHECK(`amount` >= 0.0),
`next_interest_on`DATETIME NOT NULL,
`created_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT `savings_pk` PRIMARY KEY (`s_account_number`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 102000001, DEFAULT CHARSET 'latin1';

CREATE TABLE `build_up`(
`b_account_number` INTEGER NOt NULL AUTO_INCREMENT,
`hold` BOOLEAN NOT NULL DEFAULT 0,
`active` BOOLEAN NOT NULL DEFAULT 0,
`amount` DOUBLE NOT NULl DEFAULT 0 CHECK(`amount` >= 100000.0),
`next_interest_on`DATETIME NOT NULL,
`created_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
`can_withdraw` BOOLEAN NOT NULL DEFAULT 0,
CONSTRAINT `checking_pk` PRIMARY KEY (`b_account_number`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 103000001, DEFAULT CHARSET 'latin1';


CREATE TABLE `joint_accounts`(
`joint_acount_id` INTEGER NOT NULL AUTO_INCREMENT,
`active` BOOLEAN NOT NULL DEFAULT 0,
CONSTRAINT `joint_accounts_pk` PRIMARY KEY (`joint_acount_id`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 1, DEFAULT CHARSET 'latin1';

CREATE TABLE `busniss_acount`(
`business_ac_no` INTEGER NOT NULL AUTO_INCREMENT,
`amount` DOUBLE NOT NULl DEFAULT 0 CHECK(`amount` >= 0.0),
`people_count_limit` INTEGER DEFAULT 10 CHECK(`people_count_limit` >= 1),
`min_amount` DOUBLE NOT NULl DEFAULT 0 CHECK(`amount` >= 0.0),
`drw_limit` DOUBLE NOT NULl DEFAULT 0 CHECK(`amount` >= 0.0),
`busniss_name` VARCHAR(100) NOT NULL ,
CONSTRAINT `busniss_acount_pk` PRIMARY KEY (`business_ac_no`),
-- FOREIGN KEY SECTION 
`joint_acount_id` INTEGER NOT NULL,
`creator` INTEGER NOT NULL
)ENGINE = 'Innodb' AUTO_INCREMENT = 104000001, DEFAULT CHARSET 'latin1';

CREATE TABLE `spouse_acount`(
`sa_ac_no` INTEGER NOT NULL AUTO_INCREMENT, 
`amount` DOUBLE NOT NULl DEFAULT 0 CHECK(`amount` >= 0.0),
-- FOREIGN KEY SECTION 
`joint_acc` INTEGER NOT NULL
)ENGINE = 'Innodb' AUTO_INCREMENT = 105000001, DEFAULT CHARSET 'latin1';

CREATE TABLE `busniss_people`(
`b_id` INTEGER NOT NULL AUTO_INCREMENT,
-- FOREIGN KEY SECTION 
`user_id` INTEGER,
`business_ac_no` INTEGER
)ENGINE = 'Innodb' AUTO_INCREMENT = 1, DEFAULT CHARSET 'latin1';

CREATE TABLE `spouse`(
`s_id` INTEGER NOT NULL AUTO_INCREMENT,
`husband_user_id` INTEGER UNIQUE NOT NULL,
`wife_user_id` INTEGER UNIQUE NOT NULL
)ENGINE = 'Innodb' AUTO_INCREMENT = 1, DEFAULT CHARSET 'latin1';

CREATE TABLE `user_acount`(
`u_acc_id` INTEGER NOT NULL AUTO_INCREMENT,
-- all fks go here
`s_ac_no` INTEGER NOT NULL UNIQUE,
`ack_ac_no` INTEGER UNIQUE,
`b_ac_no` INTEGER UNIQUE,
`joint_ac_id` INTEGER UNIQUE
)ENGINE = 'Innodb' AUTO_INCREMENT = 1, DEFAULT CHARSET 'latin1';

-- ADMIN SSSTUFF --

CREATE TABLE `lone_type`(

);







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
`phone_number` CHAR(10) CHECK (length(`phone_number`)= 10),
`DOB` DATE NOT NULL,
`age` TINYINT NOT NULL,
`email` VARCHAR(100) NOT NULL,
`u_id` INTEGER NOT NULL UNIQUE,
CONSTRAINT `user_details_table_pk` PRIMARY KEY (`ud_id`)
-- CONSTRAINT `user_details_table_foreign_key_u_id` FOREIGN KEY(`u_id`) REFERENCES `user`(`u_id`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 1, DEFAULT CHARSET 'latin1';

-- test above
-- INSERT INTO `user_details` (`phone_number`,`DOB`,`age`,`email`,`u_id`) VALUES ('1234567890', '2012-10-04',4,'swdssas',1);
-- INSERT INTO `user_details` (`phone_number`,`DOB`,`age`,`email`,`u_id`) VALUES ('1234567901', '2012-10-04',5,'swdsassas',6);


CREATE TABLE `notification`(
`not_id` INTEGER NOT NULL AUTO_INCREMENT,
`message` VARCHAR(250),
`u_id` INTEGER NOt NULL,
CONSTRAINT `Notification_table_pk` PRIMARY KEY (`not_id`)
-- CONSTRAINT `Notification_table_foreign_key_u_id` FOREIGN KEY(`u_id`) REFERENCES `user`(`u_id`)
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
CONSTRAINT `user_roles_pk` PRIMARY KEY(`u_id`,`role_id`)
-- CONSTRAINT `user_roles_users_fk` FOREIGN KEY (`u_id`) REFERENCES `users`(`u_id`),
-- CONSTRAINT `user_roles_roles_fk` FOREIGN KEY (`role_id`) REFERENCES `roles`(`role_id`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 1, DEFAULT CHARSET 'latin1';


CREATE TABLE `checking`(
`chk_account_number` INTEGER NOt NULL AUTO_INCREMENT,
`hold` BOOLEAN NOT NULL DEFAULT 0,
`active` BOOLEAN NOT NULL DEFAULT 0,
`amount` DOUBLE NOT NULl DEFAULT 0.00 CHECK(`amount` >= 0.00),
`next_interest_on`DATETIME NOT NULL,
`created_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT `checking_pk` PRIMARY KEY (`chk_account_number`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 101000001, DEFAULT CHARSET 'latin1';

CREATE TABLE `savings`(
`s_account_number` INTEGER NOt NULL AUTO_INCREMENT,
`hold` BOOLEAN NOT NULL DEFAULT 0,
`active` BOOLEAN NOT NULL DEFAULT 0,
`amount` DOUBLE NOT NULl DEFAULT 0.00 CHECK(`amount` >= 0.00),
`next_interest_on`DATETIME NOT NULL,
`created_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT `savings_pk` PRIMARY KEY (`s_account_number`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 102000001, DEFAULT CHARSET 'latin1';

CREATE TABLE `build_up`(
`b_account_number` INTEGER NOt NULL AUTO_INCREMENT,
`hold` BOOLEAN NOT NULL DEFAULT 0,
`active` BOOLEAN NOT NULL DEFAULT 0,
`amount` DOUBLE NOT NULl DEFAULT 0.00 CHECK(`amount` >= 100000.00),
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
`min_amount` DOUBLE NOT NULl DEFAULT 0.00 CHECK(`min_amount` >= 0.00),
`drw_limit` DOUBLE NOT NULl DEFAULT 0.00 CHECK(`drw_limit` >= 0.00),
`busniss_name` VARCHAR(100) NOT NULL ,
CONSTRAINT `busniss_acount_pk` PRIMARY KEY (`business_ac_no`),
-- FOREIGN KEY SECTION 
`joint_acount_id` INTEGER NOT NULL,
`creator` INTEGER NOT NULL
)ENGINE = 'Innodb' AUTO_INCREMENT = 104000001, DEFAULT CHARSET 'latin1';

CREATE TABLE `spouse_acount`(
`sa_ac_no` INTEGER NOT NULL AUTO_INCREMENT, 
`amount` DOUBLE NOT NULl DEFAULT 0.00 CHECK(`amount` >= 0.00),
-- FOREIGN KEY SECTION 
`joint_acc` INTEGER NOT NULL,
CONSTRAINT `spouse_acount_pk` PRIMARY KEY (`sa_ac_no`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 105000001, DEFAULT CHARSET 'latin1';

CREATE TABLE `busniss_people`(
`b_id` INTEGER NOT NULL AUTO_INCREMENT,
-- FOREIGN KEY SECTION 
`user_id` INTEGER,
`business_ac_no` INTEGER,
CONSTRAINT `busniss_people_pk` PRIMARY KEY (`b_id`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 1, DEFAULT CHARSET 'latin1';

CREATE TABLE `spouse`(
`s_id` INTEGER NOT NULL AUTO_INCREMENT,
`husband_user_id` INTEGER UNIQUE NOT NULL,
`wife_user_id` INTEGER UNIQUE NOT NULL,
CONSTRAINT `spouse_pk` PRIMARY KEY (`s_id`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 1, DEFAULT CHARSET 'latin1';

CREATE TABLE `user_acount`(
`u_acc_id` INTEGER NOT NULL AUTO_INCREMENT,
-- all fks go here
`s_ac_no` INTEGER UNIQUE NOT NULL ,
`ack_ac_no` INTEGER UNIQUE,
`b_ac_no` INTEGER UNIQUE,
`joint_ac_id` INTEGER UNIQUE,
CONSTRAINT `user_acount_pk` PRIMARY KEY (`u_acc_id`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 1, DEFAULT CHARSET 'latin1';

-- ADMIN SSSTUFF --

CREATE TABLE `employee`(
`emp_id` INTEGER NOT NULL AUTO_INCREMENT,
`password` VARCHAR(68) NOT NULL,
-- self refer
`reports_to` INTEGER,
CONSTRAINT `employee_pk` PRIMARY KEY (`emp_id`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 1000001, DEFAULT CHARSET 'latin1';

CREATE TABLE `emp_details`(
`ed_id` INTEGER NOT NULL AUTO_INCREMENT,
`phone_number` CHAR(10) CHECK (length(`phone_number`) = 10),
`full_name` VARCHAR(62) NOT NULL,
`email` VARCHAR(70) NOT NULL UNIQUE,
`dob` DATE NOT NULL,
`salary` DOUBLE NOT NULL CHECK (`salary` >= 0.00),
-- FK goes here
`emp_id` INTEGER,
CONSTRAINT `emp_details_pk` PRIMARY KEY (`ed_id`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 1000001, DEFAULT CHARSET 'latin1';

-- composit primary key 
CREATE TABLE `emp_roles`(
`emp_id` INTEGER NOT NULL,
`role_id` INTEGER NOT NULL,
CONSTRAINT `emp_roles_roles_pk` PRIMARY KEY(`emp_id`,`role_id`)
)ENGINE = 'Innodb', DEFAULT CHARSET 'latin1';


CREATE TABLE `lone_type`(
`lone_type_id` INTEGER NOT NULL AUTO_INCREMENT,
`amount` DOUBLE NOT NULl DEFAULT 0.00 CHECK(`amount` >= 0.00),
`return_date` DATETIME NOT NULL,
CONSTRAINT `lone_type_pk` PRIMARY KEY (`lone_type_id`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 1, DEFAULT CHARSET 'latin1';


CREATE TABLE `time_space`(
`account_type` VARCHAR(30) NOT NULL,
`second` SMALLINT NOT NULL DEFAULT 0 CHECK(`second` >=0),
`min` SMALLINT NOT NULL DEFAULT 0 CHECK(`min` >=0),
`hour` SMALLINT NOT NULL DEFAULT 0 CHECK(`hour` >=0),
`days` SMALLINT NOT NULL DEFAULT 0 CHECK(`days` >=0),
`months` SMALLINT NOT NULL DEFAULT 0 CHECK(`months` >=0),
`years` SMALLINT NOT NULL DEFAULT 0 CHECK(`years` >=0 AND `years` < 100)
)ENGINE = 'Innodb' , DEFAULT CHARSET 'latin1';




-- CREATE TABLE `user_acount`(
-- `u_acc_id` INTEGER NOT NULL AUTO_INCREMENT,
-- -- all fks go here
-- `s_ac_no` INTEGER NOT NULL UNIQUE,
-- `ack_ac_no` INTEGER UNIQUE,
-- `b_ac_no` INTEGER UNIQUE,
-- `joint_ac_id` INTEGER UNIQUE,
-- CONSTRAINT `user_acount_pk` PRIMARY KEY (`u_acc_id`)
-- )ENGINE = 'Innodb' AUTO_INCREMENT = 1, DEFAULT CHARSET 'latin1';



-- users foreign keys
 -- ALTER TABLE `user_acount` ADD CONSTRAINT `user_acount_unique` UNIQUE (`s_ac_no`,`ack_ac_no`,`b_ac_no`,`joint_ac_id`);
ALTER TABLE `user_acount` ADD CONSTRAINT `user_acount_fk_to_savings` FOREIGN KEY(`s_ac_no`) REFERENCES `savings`(`s_account_number`);
ALTER TABLE `user_acount` ADD CONSTRAINT `user_acount_fk_to_checking` FOREIGN KEY(`ack_ac_no`) REFERENCES `checking`(`chk_account_number`);
ALTER TABLE `user_acount` ADD CONSTRAINT `user_acount_fk_to_build_up` FOREIGN KEY(`b_ac_no`) REFERENCES `build_up`(`b_account_number`);
ALTER TABLE `user_acount` ADD CONSTRAINT `user_acount_fk_to_joint_accounts` FOREIGN KEY(`joint_ac_id`) REFERENCES `joint_accounts`(`joint_acount_id`);


DESC `user_acount`;
-- E:\Mega Project\Mathew-s_secure_bank\The Application\db\MySQL
-- new db brantch 
DROP DATABASE IF EXISTS `mathew_bank_db`;

CREATE DATABASE `mathew_bank_db`;

USE `mathew_bank_db`;

-- foreign key to be added to this table in alter table form  
CREATE TABLE `user`(
`u_id` INTEGER NOT NULL AUTO_INCREMENT,
`user_name` VARCHAR(70) UNIQUE NOT NULL,
`password` VARCHAR(68) NOT NULL,
-- fk to uder_account 
`user_account_id` INTEGER UNIQUE NOT NULL,
`branch_id` INTEGER NOT NULL,
CONSTRAINT `user_table_pk` PRIMARY KEY(`u_id`)
)ENGINE = 'Innodb', AUTO_INCREMENT = 1, DEFAULT CHARSET 'latin1';


CREATE TABLE `transactions`(
`id` INTEGER NOT NULL AUTO_INCREMENT,
`to_account_number` INTEGER NOT NULL,
`from_account_number` INTEGER NOT NULL,
`transaction_date` DATETIME DEFAULT now(),
`deposited` BOOLEAN NOT NULL DEFAULT 0,
`amount` DOUBLE NOT NULL,
`u_id` INTEGER NOT NULL,
CONSTRAINT `transactions_pk` PRIMARY KEY(`id`)
)ENGINE = 'Innodb', AUTO_INCREMENT = 1, DEFAULT CHARSET 'latin1';


CREATE TABLE `user_details`(
`ud_id` INTEGER NOT NULL AUTO_INCREMENT,
`full_name` VARCHAR(60) NOT NULL,
`phone_number` CHAR(10) CHECK (length(`phone_number`)= 10),
`DOB` DATE NOT NULL,
`age` TINYINT NOT NULL CHECK(`age` >= 5),
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
`account_type` VARCHAR(30) DEFAULT 'checking',
`frozen` BOOLEAN NOT NULL DEFAULT 0,
CONSTRAINT `checking_pk` PRIMARY KEY (`chk_account_number`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 101000001, DEFAULT CHARSET 'latin1';

CREATE TABLE `savings`(
`s_account_number` INTEGER NOt NULL AUTO_INCREMENT,
`hold` BOOLEAN NOT NULL DEFAULT 0,
`active` BOOLEAN NOT NULL DEFAULT 0,
`amount` DOUBLE NOT NULl DEFAULT 0.00 CHECK(`amount` >= 0.00),
`next_interest_on`DATETIME NOT NULL,
`created_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
`account_type` VARCHAR(30) DEFAULT 'savings',
`frozen` BOOLEAN NOT NULL DEFAULT 0,
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
`account_type` VARCHAR(30) DEFAULT 'build_up',
`frozen` BOOLEAN NOT NULL DEFAULT 0,
CONSTRAINT `checking_pk` PRIMARY KEY (`b_account_number`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 103000001, DEFAULT CHARSET 'latin1';


CREATE TABLE `joint_accounts`(
`joint_acount_id` INTEGER NOT NULL AUTO_INCREMENT,
`active` BOOLEAN NOT NULL DEFAULT 0,
CONSTRAINT `joint_accounts_pk` PRIMARY KEY (`joint_acount_id`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 1, DEFAULT CHARSET 'latin1';

CREATE TABLE `business_account`(
`business_ac_no` INTEGER NOT NULL AUTO_INCREMENT,
`amount` DOUBLE NOT NULl DEFAULT 0 CHECK(`amount` >= 0.0),
`people_count_limit` INTEGER DEFAULT 10 CHECK(`people_count_limit` >= 1),
`min_amount` DOUBLE NOT NULl DEFAULT 0.00 CHECK(`min_amount` >= 0.00),
`drw_limit` DOUBLE NOT NULl DEFAULT 0.00 CHECK(`drw_limit` >= 0.00),
`busniss_name` VARCHAR(100) NOT NULL ,
`account_type` VARCHAR(30) DEFAULT 'busniss_acount',
`frozen` BOOLEAN NOT NULL DEFAULT 0,
CONSTRAINT `busniss_acount_pk` PRIMARY KEY (`business_ac_no`),
-- FOREIGN KEY SECTION 
`joint_acount_id` INTEGER NOT NULL,
`creator` INTEGER NOT NULL
)ENGINE = 'Innodb' AUTO_INCREMENT = 104000001, DEFAULT CHARSET 'latin1';

CREATE TABLE `spouse_account`(
`sa_ac_no` INTEGER NOT NULL AUTO_INCREMENT, 
`amount` DOUBLE NOT NULl DEFAULT 0.00 CHECK(`amount` >= 0.00),
`frozen` BOOLEAN NOT NULL DEFAULT 0,
-- FOREIGN KEY SECTION 
`joint_account_id` INTEGER NOT NULL,
`account_type` VARCHAR(30) DEFAULT 'spouse_acount',
CONSTRAINT `spouse_acount_pk` PRIMARY KEY (`sa_ac_no`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 105000001, DEFAULT CHARSET 'latin1';

CREATE TABLE `business_people`(
`b_id` INTEGER NOT NULL AUTO_INCREMENT,
-- FOREIGN KEY SECTION 
`user_joint_acount` INTEGER,
`business_ac_no` INTEGER,
CONSTRAINT `busniss_people_pk` PRIMARY KEY (`b_id`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 1, DEFAULT CHARSET 'latin1';

CREATE TABLE `spouse`(
`s_id` INTEGER NOT NULL AUTO_INCREMENT,
`husband_joint_acount` INTEGER UNIQUE NOT NULL,
`wife_joint_acount` INTEGER UNIQUE NOT NULL,
`sa_ac_no` INTEGER UNIQUE NOT NULL,
CONSTRAINT `spouse_pk` PRIMARY KEY (`s_id`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 1, DEFAULT CHARSET 'latin1';

CREATE TABLE `user_account`(
`u_acc_id` INTEGER NOT NULL AUTO_INCREMENT,
-- all fks go here
`s_ac_no` INTEGER UNIQUE NOT NULL ,
`ack_ac_no` INTEGER UNIQUE,
`b_ac_no` INTEGER UNIQUE,
`joint_ac_id` INTEGER UNIQUE,
`frozen` BOOLEAN NOT NULL DEFAULT 0,
CONSTRAINT `user_acount_pk` PRIMARY KEY (`u_acc_id`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 1, DEFAULT CHARSET 'latin1';

-- ADMIN SSSTUFF --

CREATE TABLE `employee`(
`emp_id` INTEGER NOT NULL AUTO_INCREMENT,
`password` VARCHAR(68) NOT NULL,
-- self refer
`reports_to` INTEGER,
`branch_id` INTEGER,
`salary_account` INTEGER,
CONSTRAINT `employee_pk` PRIMARY KEY (`emp_id`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 1000001, DEFAULT CHARSET 'latin1';

CREATE TABLE `emp_details`(
`ed_id` INTEGER NOT NULL AUTO_INCREMENT,
`phone_number` CHAR(10) UNIQUE CHECK (length(`phone_number`) = 10),
`full_name` VARCHAR(62) NOT NULL,
`email` VARCHAR(70) UNIQUE NOT NULL UNIQUE,
`dob` DATE NOT NULL,
`salary` DOUBLE NOT NULL CHECK (`salary` >= 0.00),
-- FK goes here
`emp_id` INTEGER,
CONSTRAINT `emp_details_pk` PRIMARY KEY (`ed_id`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 1, DEFAULT CHARSET 'latin1';

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
`created_by` INTEGER NOT NULL,
`active` BOOLEAN DEFAULT 0 NOT NULL,
CONSTRAINT `lone_type_pk` PRIMARY KEY (`lone_type_id`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 1, DEFAULT CHARSET 'latin1';


CREATE TABLE `time_space`(
`account_type` VARCHAR(30) NOT NULL,
`second` SMALLINT NOT NULL DEFAULT 0 CHECK(`second` >=0),
`min` SMALLINT NOT NULL DEFAULT 0 CHECK(`min` >=0),
`hour` SMALLINT NOT NULL DEFAULT 0 CHECK(`hour` >=0),
`days` SMALLINT NOT NULL DEFAULT 0 CHECK(`days` >=0),
`months` SMALLINT NOT NULL DEFAULT 0 CHECK(`months` >=0),
`years` SMALLINT NOT NULL DEFAULT 0 CHECK(`years` >=0 AND `years` < 100),
`base_interest_rate` FLOAT NOT NULL DEFAULT 0.00,
CONSTRAINT `time_space_pk` PRIMARY KEY (`account_type`)
)ENGINE = 'Innodb' , DEFAULT CHARSET 'latin1';




CREATE TABLE `branch`(
`branch_id` INTEGER NOT NULL AUTO_INCREMENT,
`branch_name` VARCHAR(70) NOT NULL,
`state` VARCHAR(30) NOT NULL,
`country` VARCHAR(60) NOT NULL,
`open` BOOLEAN DEFAULT 0 NOT NULL,
`branch_manager` INTEGER,
CONSTRAINT `branch_pk` PRIMARY KEY (`branch_id`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 1, DEFAULT CHARSET 'latin1';




CREATE TABLE `user_application`(
`application_number` INTEGER NOT NULL AUTO_INCREMENT,
`full_name` VARCHAR(70) NOT NULL,
`phone_number` CHAR(10) CHECK (length(`phone_number`) = 10),
`dob` DATE NOT NULL,
`age` TINYINT NOT NULL CHECK(`age` >= 5),
`email` VARCHAR(100) NOT NULL,
`applyed_date` DATETIME DEFAULT now(),
`status` BOOLEAN DEFAULT 0 NOT NULL,
`approved_by` INTEGER,
`created_user_id` INTEGER,
`branch_id` INTEGER NOT NULL,
CONSTRAINT `user_application_pk` PRIMARY KEY (`application_number`)
)ENGINE = 'Innodb' AUTO_INCREMENT = 101, DEFAULT CHARSET 'latin1';

CREATE TABLE `website_visited`(
`visited_date` DATETIME
)ENGINE = 'Innodb' , DEFAULT CHARSET 'latin1';


-- users foreign keys
 -- ALTER TABLE `user_acount` ADD CONSTRAINT `user_acount_unique` UNIQUE (`s_ac_no`,`ack_ac_no`,`b_ac_no`,`joint_ac_id`);
ALTER TABLE `user_account` ADD CONSTRAINT `user_acount_fk_to_savings` FOREIGN KEY(`s_ac_no`) REFERENCES `savings`(`s_account_number`);
ALTER TABLE `user_account` ADD CONSTRAINT `user_acount_fk_to_checking` FOREIGN KEY(`ack_ac_no`) REFERENCES `checking`(`chk_account_number`);
ALTER TABLE `user_account` ADD CONSTRAINT `user_acount_fk_to_build_up` FOREIGN KEY(`b_ac_no`) REFERENCES `build_up`(`b_account_number`);
ALTER TABLE `user_account` ADD CONSTRAINT `user_acount_fk_to_joint_accounts` FOREIGN KEY(`joint_ac_id`) REFERENCES `joint_accounts`(`joint_acount_id`);
DESC `user_account`;

ALTER TABLE `business_account` ADD CONSTRAINT `business_acount_fk_to_joint_accounts` FOREIGN KEY(`joint_acount_id`) REFERENCES `joint_accounts`(`joint_acount_id`);
DESC `business_account`;

ALTER TABLE `spouse_account` ADD CONSTRAINT `spouse_acount_fk_to_joint_accounts` FOREIGN KEY(`joint_account_id`) REFERENCES `joint_accounts`(`joint_acount_id`);
DESC `spouse_account`;


ALTER TABLE `business_people` ADD CONSTRAINT `business_people_fk_to_joint_accounts` FOREIGN KEY(`user_joint_acount`) REFERENCES `joint_accounts`(`joint_acount_id`);
ALTER TABLE `business_people` ADD CONSTRAINT `business_people_fk_to_busniss_account` FOREIGN KEY(`business_ac_no`) REFERENCES `business_account`(`business_ac_no`);
DESC `business_people`;

ALTER TABLE `spouse` ADD CONSTRAINT `spouseH_fk_to_joint_accounts` FOREIGN KEY(`husband_joint_acount`) REFERENCES `joint_accounts`(`joint_acount_id`);
ALTER TABLE `spouse` ADD CONSTRAINT `spouseW_fk_to_joint_accounts` FOREIGN KEY(`wife_joint_acount`) REFERENCES `joint_accounts`(`joint_acount_id`);
ALTER TABLE `spouse` ADD CONSTRAINT `spouse_fk_to_spouse_account` FOREIGN KEY(`sa_ac_no`) REFERENCES `spouse_account`(`sa_ac_no`);
DESC `spouse`;

ALTER TABLE `user` ADD CONSTRAINT `user_fk_to_joint_accounts` FOREIGN KEY(`user_account_id`) REFERENCES `user_account`(`u_acc_id`);
ALTER TABLE `user` ADD CONSTRAINT `user_fk_to_branch` FOREIGN KEY(`branch_id`) REFERENCES `branch`(`branch_id`);
DESC `user`;

ALTER TABLE `user_details` ADD CONSTRAINT `user_details_fk_to_user` FOREIGN KEY(`u_id`) REFERENCES `user`(`u_id`);
DESC `user_details`;

ALTER TABLE `notification` ADD CONSTRAINT `notification_fk_to_user` FOREIGN KEY(`u_id`) REFERENCES `user`(`u_id`);
DESC `notification`;

ALTER TABLE `user_lones` ADD CONSTRAINT `user_lones_fk_to_user` FOREIGN KEY(`u_id`) REFERENCES `user`(`u_id`);
ALTER TABLE `user_lones` ADD CONSTRAINT `user_lones_loneType_fk_to_user` FOREIGN KEY(`lone_type_id`) REFERENCES `lone_type`(`lone_type_id`);
DESC `user_lones`;

ALTER TABLE `user_roles` ADD CONSTRAINT `user_lones_U_fk_to_user` FOREIGN KEY(`u_id`) REFERENCES `user`(`u_id`);
ALTER TABLE `user_roles` ADD CONSTRAINT `user_lones_R_fk_to_user` FOREIGN KEY(`role_id`) REFERENCES `roles`(`role_id`);
DESC `user_roles`;

ALTER TABLE `transactions` ADD CONSTRAINT `user_fk_transactions` FOREIGN KEY(`u_id`) REFERENCES `user`(`u_id`);
DESC `transactions`;


-- employee details alter 



ALTER TABLE `emp_details` ADD CONSTRAINT `emp_details_fk_to_employee` FOREIGN KEY(`emp_id`) REFERENCES `employee`(`emp_id`);
ALTER TABLE `emp_details` ADD CONSTRAINT `emp_details_fk_to_savings` FOREIGN KEY(`emp_id`) REFERENCES `employee`(`emp_id`);
DESC `emp_details`;

ALTER TABLE `employee` ADD CONSTRAINT `employee_fk_to_employee` FOREIGN KEY(`reports_to`) REFERENCES `employee`(`emp_id`);
ALTER TABLE `employee` ADD CONSTRAINT `employee_fk_to_savings` FOREIGN KEY(`salary_account`) REFERENCES `savings`(`s_account_number`);
DESC `employee`;

ALTER TABLE `lone_type` ADD CONSTRAINT `lone_type_fk_to_employee` FOREIGN KEY(`created_by`) REFERENCES `employee`(`emp_id`);
DESC `lone_type`;

ALTER TABLE `emp_roles` ADD CONSTRAINT `emp_roles_fk_to_employee` FOREIGN KEY(`emp_id`) REFERENCES `employee`(`emp_id`);
ALTER TABLE `emp_roles` ADD CONSTRAINT `emp_roles_fk_to_roles` FOREIGN KEY(`role_id`) REFERENCES `roles`(`role_id`);
DESC `emp_roles` ;

ALTER TABLE `branch` ADD CONSTRAINT `branch_to_employee` FOREIGN KEY(`branch_manager`) REFERENCES `employee`(`emp_id`); 
DESC `branch`;

ALTER TABLE `user_application` ADD CONSTRAINT `user_application_fk_to_employee` FOREIGN KEY(`approved_by`) REFERENCES `employee`(`emp_id`);
ALTER TABLE `user_application` ADD CONSTRAINT `user_application_fk_to_user` FOREIGN KEY(`created_user_id`) REFERENCES `user`(`u_id`);
ALTER TABLE `user_application` ADD CONSTRAINT `user_application_fk_to_branch` FOREIGN KEY(`branch_id`) REFERENCES `branch`(`branch_id`);
DESC `user_application`;


ALTER TABLE `savings` ADD CONSTRAINT `savings_fk_to_time_space` FOREIGN KEY(`account_type`) REFERENCES `time_space`(`account_type`);
DESC `savings`;
ALTER TABLE `checking` ADD CONSTRAINT `checking_to_time_space` FOREIGN KEY(`account_type`) REFERENCES `time_space`(`account_type`);
DESC `checking`;
ALTER TABLE `build_up` ADD CONSTRAINT `build_up_fk_to_time_space` FOREIGN KEY(`account_type`) REFERENCES `time_space`(`account_type`);
DESC `build_up`;

ALTER TABLE `business_account` ADD CONSTRAINT `business_account_fk_to_time_space` FOREIGN KEY(`account_type`) REFERENCES `time_space`(`account_type`);
DESC `business_account`;
ALTER TABLE `spouse_account` ADD CONSTRAINT `spouse_account_fk_to_time_space` FOREIGN KEY(`account_type`) REFERENCES `time_space`(`account_type`);
DESC `spouse_account`;


USE `mathew_bank_db`;

DESC `time_space`;

SELECT * FROM `employee`;
SELECT * FROM `emp_details`;
DESCRIBE `emp_details`;
SELECT * FROM `emp_roles`;

INSERT `roles`(`role_name`) VALUES ('ROLE_admin');
SELECT * FROM `roles`;


-- E:\Mega Project\Mathew-s_secure_bank\The Application\db\MySQL
-- new db brantch 
CREATE DATABASE IF NOT EXISTS `mathew_bank_db`;

USE `mathew_bank_db`;

-- foreign key to be added to this table in alter table form  
CREATE TABLE `user`(
`u_id` INTEGER NOT NULL AUTO_INCREMENT,
`user_name` VARCHAR(70) NOT NULL,
`password` VARCHAR(68) NOT NULL
);
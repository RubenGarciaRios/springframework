-- ----------------------- --
-- "DEV" DATABASE CREATION --
-- ----------------------- --
CREATE DATABASE IF NOT EXISTS dev
	DEFAULT CHARACTER SET = utf8
	DEFAULT COLLATE = utf8_general_ci;
-- ------------------- --
-- "DEV" USER CREATION --
-- ------------------- --
CREATE USER IF NOT EXISTS 'dev'@'localhost'
	IDENTIFIED BY '*01#_DBA@dev_#09*';
GRANT ALL PRIVILEGES ON dev.* TO 'dev'@'localhost';
FLUSH PRIVILEGES;
-- ## --
USE dev;
-- ## --
-- ------------ --
-- "DEV" TABLES --
-- ------------ --
/* enterprise */
CREATE TABLE IF NOT EXISTS enterprises (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	name VARCHAR( 20 ) NOT NULL,
	CONSTRAINT pk_enterprises PRIMARY KEY ( id ),
	CONSTRAINT uq_enterprises_name UNIQUE ( name )
) ENGINE = InnoDB;
/* departament */
CREATE TABLE IF NOT EXISTS departaments (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	id_enterprise INT UNSIGNED NOT NULL,
	code VARCHAR( 5 ) NOT NULL,
	name VARCHAR( 20 ) NOT NULL,
	CONSTRAINT pk_departaments PRIMARY KEY ( id ),
	CONSTRAINT fk_departaments_enterprises FOREIGN KEY ( id_enterprise )
		REFERENCES enterprises ( id )
		ON UPDATE CASCADE
) ENGINE = InnoDB;
/* employee */
CREATE TABLE IF NOT EXISTS employees (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	id_departament INT UNSIGNED NOT NULL,
	name VARCHAR( 20 ) NOT NULL,
	firstname VARCHAR( 20 ) NOT NULL,
	lastname VARCHAR( 20 ) NULL,
	email VARCHAR( 30 ) NULL,
	phone VARCHAR( 30 ) NULL,
	gender char( 1 ) NOT NULL,
	birthdate DATE NOT NULL,
	incorporationdate DATE NOT NULL,
	leavingdate DATE NULL DEFAULT NULL,
	active BIT( 1 ) NOT NULL DEFAULT 1,
	CONSTRAINT pk_employees PRIMARY KEY ( id ),
	CONSTRAINT fk_employees_departaments FOREIGN KEY ( id_departament )
		REFERENCES departaments ( id )
		ON UPDATE CASCADE
) ENGINE = InnoDB;
/* project */
CREATE TABLE IF NOT EXISTS projects (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	name VARCHAR( 20 ) NOT NULL,
	startdate DATE NOT NULL,
	enddate DATE NULL,
	active BIT( 1 ) NOT NULL DEFAULT 1,
	CONSTRAINT pk_projects PRIMARY KEY ( id )
) ENGINE = InnoDB;
/* employee_project */
CREATE TABLE IF NOT EXISTS employees_projects (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	id_employee INT UNSIGNED NOT NULL,
	id_project INT UNSIGNED NOT NULL,
	CONSTRAINT pk_employees_projects PRIMARY KEY ( id ),
	CONSTRAINT fk_employees_projects_employees FOREIGN KEY ( id_employee )
		REFERENCES employees ( id )
		ON UPDATE CASCADE,
	CONSTRAINT fk_employees_projects_projects FOREIGN KEY ( id_project )
		REFERENCES projects ( id )
		ON UPDATE CASCADE
) ENGINE = InnoDB;
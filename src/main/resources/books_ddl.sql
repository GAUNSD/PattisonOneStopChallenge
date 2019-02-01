USE pattisonbooks;

DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
	`isbn` VARCHAR(255),
	`title` TEXT,
	`subtitle` TEXT,
	`author` TEXT,
	`published` DATE,
	`publisher` TEXT,
	`pages` INT,
	`description` VARCHAR(255),
	`website` TEXT,

	PRIMARY KEY (`isbn`)
);
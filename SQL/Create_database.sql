-- MySQL Script generated by MySQL Workbench
-- 04/30/17 22:06:44
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema sweetnews
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sweetnews
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sweetnews` DEFAULT CHARACTER SET utf8 ;
USE `sweetnews` ;

-- -----------------------------------------------------
-- Table `sweetnews`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sweetnews`.`category` (
  `CategoryId` INT(11) NOT NULL AUTO_INCREMENT,
  `CategoryDescription` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`CategoryId`),
  INDEX `category_desc_index` (`CategoryDescription` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sweetnews`.`source`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sweetnews`.`source` (
  `SourceId` VARCHAR(256) NOT NULL,
  `Name` VARCHAR(60) NULL DEFAULT NULL,
  `Description` VARCHAR(500) NULL DEFAULT NULL,
  `Url` VARCHAR(2000) NULL DEFAULT NULL,
  `Category` VARCHAR(45) NULL DEFAULT NULL,
  `Language` VARCHAR(45) NULL DEFAULT NULL,
  `Country` VARCHAR(45) NULL DEFAULT NULL,
  `UrlLogo` VARCHAR(2000) NULL DEFAULT NULL,
  `ApprovalStatus` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`SourceId`),
  INDEX `source_category_idx` (`Category` ASC),
  CONSTRAINT `source_category`
    FOREIGN KEY (`Category`)
    REFERENCES `sweetnews`.`category` (`CategoryDescription`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sweetnews`.`article`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sweetnews`.`article` (
  `ArticleId` INT(11) NOT NULL AUTO_INCREMENT,
  `Author` VARCHAR(65) NULL DEFAULT NULL,
  `Title` VARCHAR(256) NULL DEFAULT NULL,
  `Url` VARCHAR(2000) NULL DEFAULT NULL,
  `UrlToImage` VARCHAR(2000) NULL DEFAULT NULL,
  `PublishTime` VARCHAR(45) NULL DEFAULT NULL,
  `SourceId` VARCHAR(256) NULL DEFAULT NULL,
  `Description` VARCHAR(3000) NULL DEFAULT NULL,
  PRIMARY KEY (`ArticleId`),
  INDEX `article_source_idx` (`SourceId` ASC),
  CONSTRAINT `article_source`
    FOREIGN KEY (`SourceId`)
    REFERENCES `sweetnews`.`source` (`SourceId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 505
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sweetnews`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sweetnews`.`user` (
  `Name` VARCHAR(256) NOT NULL,
  `Email` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`Email`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sweetnews`.`source_added`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sweetnews`.`source_added` (
  `SourceId` VARCHAR(256) NOT NULL,
  `AdminAdded` VARCHAR(256) NULL DEFAULT NULL,
  PRIMARY KEY (`SourceId`),
  INDEX `admin_login_idx` (`AdminAdded` ASC),
  CONSTRAINT `admin_login`
    FOREIGN KEY (`AdminAdded`)
    REFERENCES `sweetnews`.`user` (`Email`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `admin_source`
    FOREIGN KEY (`SourceId`)
    REFERENCES `sweetnews`.`source` (`SourceId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sweetnews`.`user_preference`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sweetnews`.`user_preference` (
  `UserName` VARCHAR(256) NOT NULL,
  `Category` VARCHAR(45) NOT NULL,
  INDEX `user_pref_email_idx` (`UserName` ASC),
  INDEX `user_pref_cat_idx` (`Category` ASC),
  CONSTRAINT `user_pref_cat`
    FOREIGN KEY (`Category`)
    REFERENCES `sweetnews`.`category` (`CategoryDescription`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `user_pref_email`
    FOREIGN KEY (`UserName`)
    REFERENCES `sweetnews`.`user` (`Email`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sweetnews`.`userlogin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sweetnews`.`userlogin` (
  `UserName` VARCHAR(256) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`UserName`),
  CONSTRAINT `email_username`
    FOREIGN KEY (`UserName`)
    REFERENCES `sweetnews`.`user` (`Email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


insert into category ( CategoryDescription ) values ('business');
insert into category ( CategoryDescription ) values ('politics');
insert into category ( CategoryDescription ) values ('technology');
insert into category ( CategoryDescription ) values ('travel');
insert into category ( CategoryDescription ) values ('sport');
insert into category ( CategoryDescription ) values ('general');
insert into category ( CategoryDescription ) values ('music');
insert into category ( CategoryDescription ) values ('entertainment');
insert into category ( CategoryDescription ) values ('gaming');
insert into category ( CategoryDescription ) values ('science-and-nature');


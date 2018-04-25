-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema fog
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema fog
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fog` DEFAULT CHARACTER SET utf8 ;
USE `fog` ;

-- -----------------------------------------------------
-- Table `fog`.`tile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`tile` (
  `idtile` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `price` DOUBLE NULL,
  PRIMARY KEY (`idtile`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fog`.`cladding`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`cladding` (
  `idtile` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `price` DOUBLE NULL,
  PRIMARY KEY (`idtile`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fog`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`order` (
  `idorder` INT NOT NULL AUTO_INCREMENT,
  `confirmed` TINYINT NOT NULL DEFAULT 0,
  `date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NULL,
  `email` VARCHAR(45) NOT NULL,
  `phonenumber` INT NULL,
  `length` INT NOT NULL,
  `width` INT NOT NULL,
  `height` INT NOT NULL,
  `roofangle` DOUBLE NULL,
  `shed_length` INT NULL,
  `shed_width` INT NULL,
  `tile_idtile` INT NOT NULL,
  `floor_idtile` INT NOT NULL,
  PRIMARY KEY (`idorder`),
  INDEX `fk_order_tile1_idx` (`tile_idtile` ASC),
  INDEX `fk_order_floor1_idx` (`floor_idtile` ASC),
  CONSTRAINT `fk_order_tile1`
    FOREIGN KEY (`tile_idtile`)
    REFERENCES `fog`.`tile` (`idtile`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_floor1`
    FOREIGN KEY (`floor_idtile`)
    REFERENCES `fog`.`cladding` (`idtile`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

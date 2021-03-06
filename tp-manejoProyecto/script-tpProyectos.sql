SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `test` DEFAULT CHARACTER SET latin1 ;
USE `test` ;

-- -----------------------------------------------------
-- Table `test`.`Complejidades`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `test`.`Complejidades` (
  `idComplejidad` INT(11) NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(60) NOT NULL ,
  `porcentaje` DOUBLE NOT NULL ,
  PRIMARY KEY (`idComplejidad`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `test`.`Impuestos`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `test`.`Impuestos` (
  `idImpuesto` INT(11) NOT NULL AUTO_INCREMENT ,
  `descripcion` VARCHAR(45) NOT NULL ,
  `porcentaje` DOUBLE NOT NULL ,
  PRIMARY KEY (`idImpuesto`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `test`.`Proyectos`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `test`.`Proyectos` (
  `idProyecto` INT(11) NOT NULL AUTO_INCREMENT ,
  PRIMARY KEY (`idProyecto`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `test`.`Tareas`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `test`.`Tareas` (
  `idTarea` INT(11) NOT NULL AUTO_INCREMENT ,
  `tiempo` INT(11) NOT NULL ,
  `tareaPadre` INT(11) NULL DEFAULT NULL ,
  `idProyecto` INT(11) NOT NULL ,
  `idComplejidad` INT(11) NOT NULL ,
  `idTareaPadre` INT(11) NOT NULL ,
  PRIMARY KEY (`idTarea`) ,
  INDEX `fk_Tareas_Proyectos1` (`idProyecto` ASC) ,
  INDEX `fk_Tareas_Complejidades1` (`idComplejidad` ASC) ,
  INDEX `fk_Tareas_Tareas1` (`idTareaPadre` ASC) ,
  CONSTRAINT `fk_Tareas_Proyectos1`
    FOREIGN KEY (`idProyecto` )
    REFERENCES `test`.`Proyectos` (`idProyecto` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Tareas_Complejidades1`
    FOREIGN KEY (`idComplejidad` )
    REFERENCES `test`.`Complejidades` (`idComplejidad` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Tareas_Tareas1`
    FOREIGN KEY (`idTareaPadre` )
    REFERENCES `test`.`Tareas` (`idTarea` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `test`.`ImpuestosPorTareas`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `test`.`ImpuestosPorTareas` (
  `idTarea` INT(11) NOT NULL ,
  `idImpuesto` INT(11) NOT NULL ,
  INDEX `fk_ImpuestosPorTareas_Tareas1` (`idTarea` ASC) ,
  CONSTRAINT `fk_ImpuestosPorTareas_Tareas1`
    FOREIGN KEY (`idTarea` )
    REFERENCES `test`.`Tareas` (`idTarea` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ImpuestosPorTareas_Impuestos1`
    FOREIGN KEY (`idImpuesto` )
    REFERENCES `test`.`Impuestos` (`idImpuesto` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
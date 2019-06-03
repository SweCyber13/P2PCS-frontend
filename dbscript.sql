-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema P2PCS
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema P2PCS
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `P2PCS` DEFAULT CHARACTER SET latin1 ;
USE `P2PCS` ;

-- -----------------------------------------------------
-- Table `P2PCS`.`MEDAGLIE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `P2PCS`.`MEDAGLIE` (
  `Nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Nome`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `P2PCS`.`MISSIONI`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `P2PCS`.`MISSIONI` (
  `Titolo` VARCHAR(45) NOT NULL,
  `Descrizione` TEXT NOT NULL,
  `Premio_rank` INT(11) NOT NULL DEFAULT '0',
  `Premio_buono` INT(11) NOT NULL DEFAULT '0',
  `Premio_medaglia` VARCHAR(45) NULL DEFAULT NULL,
  `Soglia_completamento` DOUBLE NOT NULL,
  PRIMARY KEY (`Titolo`),
  INDEX `Mission_medal_idx` (`Premio_medaglia` ASC) VISIBLE,
  CONSTRAINT `Mission_medal`
    FOREIGN KEY (`Premio_medaglia`)
    REFERENCES `P2PCS`.`MEDAGLIE` (`Nome`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `P2PCS`.`UTENTI_REGISTRATI`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `P2PCS`.`UTENTI_REGISTRATI` (
  `Username` VARCHAR(25) NOT NULL,
  `Nome` VARCHAR(25) NOT NULL,
  `Cognome` VARCHAR(45) NOT NULL,
  `Mail` VARCHAR(50) NOT NULL,
  `Punti_rank` INT(11) NOT NULL DEFAULT '0',
  `Punti_buoni` INT(11) NOT NULL DEFAULT '0',
  `Eta` INT(11) NULL DEFAULT NULL,
  `Sesso` ENUM('F', 'M') NULL DEFAULT NULL,
  `Citta` VARCHAR(30) NULL DEFAULT NULL,
  `Data_rilascio_p` DATE NULL DEFAULT NULL,
  `Occupazione` VARCHAR(45) NULL DEFAULT NULL,
  `Numero_patente` CHAR(10) NULL DEFAULT NULL,
  PRIMARY KEY (`Username`),
  UNIQUE INDEX `Mail_UNIQUE` (`Mail` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `P2PCS`.`AVANZAMENTO_MISSIONI`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `P2PCS`.`AVANZAMENTO_MISSIONI` (
  `Utente` VARCHAR(45) NOT NULL,
  `Missione` VARCHAR(45) NOT NULL,
  `Completata` TINYINT(4) NOT NULL DEFAULT '0',
  `Avanzamento` DOUBLE NOT NULL,
  PRIMARY KEY (`Utente`, `Missione`),
  INDEX `Username_mission_idx` (`Utente` ASC) VISIBLE,
  INDEX `Mission_username_idx` (`Missione` ASC) VISIBLE,
  CONSTRAINT `Mission_username`
    FOREIGN KEY (`Missione`)
    REFERENCES `P2PCS`.`MISSIONI` (`Titolo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Username_mission`
    FOREIGN KEY (`Utente`)
    REFERENCES `P2PCS`.`UTENTI_REGISTRATI` (`Username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `P2PCS`.`MACCHINE_UTENTI`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `P2PCS`.`MACCHINE_UTENTI` (
  `Targa` CHAR(7) NOT NULL,
  `Proprietario` VARCHAR(25) NOT NULL,
  `Marca` VARCHAR(45) NOT NULL,
  `Modello` VARCHAR(45) NOT NULL,
  `Anno_produzione` YEAR(4) NOT NULL,
  `Cavalli` INT(11) NOT NULL,
  `Cilindrata` INT(11) NOT NULL,
  `Raggio_percorrenza` INT(11) NOT NULL,
  `Kilometraggio` INT(11) NOT NULL,
  `Tariffa_oraria` DOUBLE NOT NULL,
  `Latitudine` DOUBLE NOT NULL,
  `Longitudine` DOUBLE NOT NULL,
  `Indirizzo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Targa`),
  INDEX `Proprietario_idx` (`Proprietario` ASC) VISIBLE,
  CONSTRAINT `Proprietario`
    FOREIGN KEY (`Proprietario`)
    REFERENCES `P2PCS`.`UTENTI_REGISTRATI` (`Username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `P2PCS`.`CALENDARIO_DISPONIBILITA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `P2PCS`.`CALENDARIO_DISPONIBILITA` (
  `Id_disponibilita` INT(11) NOT NULL AUTO_INCREMENT,
  `Macchina` CHAR(7) NOT NULL,
  `Giorno` DATE NOT NULL,
  `Stringa_disponibilita` CHAR(96) NOT NULL,
  PRIMARY KEY (`Id_disponibilita`, `Macchina`, `Giorno`),
  UNIQUE INDEX `Propietario` (`Id_disponibilita` ASC, `Macchina` ASC, `Giorno` ASC) VISIBLE,
  INDEX `Targa_idx` (`Macchina` ASC) VISIBLE,
  CONSTRAINT `Targa`
    FOREIGN KEY (`Macchina`)
    REFERENCES `P2PCS`.`MACCHINE_UTENTI` (`Targa`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `P2PCS`.`COUPONS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `P2PCS`.`COUPONS` (
  `Id_coupon` INT(11) NOT NULL,
  `Soglia_punti` INT(11) NOT NULL,
  `Nome_azienda` VARCHAR(45) NOT NULL,
  `Titolo` VARCHAR(45) NOT NULL,
  `Descrizione` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`Id_coupon`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `P2PCS`.`EVENTI`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `P2PCS`.`EVENTI` (
  `Id_eventi` INT(11) NOT NULL AUTO_INCREMENT,
  `Nome_azienda` VARCHAR(45) NOT NULL,
  `Titolo_offerta` VARCHAR(45) NOT NULL,
  `Descrizione` MEDIUMTEXT NOT NULL,
  PRIMARY KEY (`Id_eventi`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `P2PCS`.`MEDAGLIE_OTTENUTE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `P2PCS`.`MEDAGLIE_OTTENUTE` (
  `Utente` VARCHAR(45) NOT NULL,
  `Medaglia` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Utente`, `Medaglia`),
  INDEX `Medal_username_idx` (`Medaglia` ASC) VISIBLE,
  CONSTRAINT `Medal_username`
    FOREIGN KEY (`Medaglia`)
    REFERENCES `P2PCS`.`MEDAGLIE` (`Nome`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Username_medal`
    FOREIGN KEY (`Utente`)
    REFERENCES `P2PCS`.`UTENTI_REGISTRATI` (`Username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `P2PCS`.`PRENOTAZIONI`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `P2PCS`.`PRENOTAZIONI` (
  `Id_prenotazioni` INT(11) NOT NULL AUTO_INCREMENT,
  `Proprietario` VARCHAR(25) NOT NULL,
  `Richiedente` VARCHAR(25) NOT NULL,
  `Targa` CHAR(7) NOT NULL,
  `Data_inizio` DATE NOT NULL,
  `Ora_inizio` TIME NOT NULL,
  `Data_fine` DATE NOT NULL,
  `Ora_fine` TIME NOT NULL,
  `Stato` ENUM('P', 'A', 'C', 'T') NOT NULL,
  `Costo` DOUBLE NOT NULL,
  `Indirizzo_partenza` VARCHAR(45) NOT NULL,
  `Indirizzo_arrivo` VARCHAR(45) NOT NULL,
  `Latitudine_partenza` DOUBLE NOT NULL,
  `Longitudine_partenza` DOUBLE NOT NULL,
  `Latitudine_arrivo` DOUBLE NOT NULL,
  `Longitudine_arrivo` DOUBLE NOT NULL,
  PRIMARY KEY (`Id_prenotazioni`),
  INDEX `P_proprietario_idx` (`Proprietario` ASC) VISIBLE,
  INDEX `P_richiedente_idx` (`Richiedente` ASC) VISIBLE,
  INDEX `P_targa_idx` (`Targa` ASC) VISIBLE,
  CONSTRAINT `P_proprietario`
    FOREIGN KEY (`Proprietario`)
    REFERENCES `P2PCS`.`UTENTI_REGISTRATI` (`Username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `P_richiedente`
    FOREIGN KEY (`Richiedente`)
    REFERENCES `P2PCS`.`UTENTI_REGISTRATI` (`Username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `P_targa`
    FOREIGN KEY (`Targa`)
    REFERENCES `P2PCS`.`MACCHINE_UTENTI` (`Targa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `P2PCS`.`RECENSIONI`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `P2PCS`.`RECENSIONI` (
  `Id_viaggio` INT(11) NOT NULL,
  `Recensore` VARCHAR(25) NOT NULL,
  `Recensito` VARCHAR(45) NOT NULL,
  `Voto` ENUM('1', '2', '3', '4', '5') NOT NULL,
  `Testo` VARCHAR(512) NULL DEFAULT NULL,
  PRIMARY KEY (`Id_viaggio`, `Recensore`),
  INDEX `Recensore_idx` (`Recensore` ASC) VISIBLE,
  INDEX `Recensito_idx` (`Recensito` ASC) VISIBLE,
  CONSTRAINT `Id_prenotazione`
    FOREIGN KEY (`Id_viaggio`)
    REFERENCES `P2PCS`.`PRENOTAZIONI` (`Id_prenotazioni`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Recensito`
    FOREIGN KEY (`Recensito`)
    REFERENCES `P2PCS`.`UTENTI_REGISTRATI` (`Username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Recensore`
    FOREIGN KEY (`Recensore`)
    REFERENCES `P2PCS`.`UTENTI_REGISTRATI` (`Username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

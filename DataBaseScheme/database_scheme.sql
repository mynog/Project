-- MySQL Workbench Forward Engineering

/* SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0; */
/* SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0; */
/* SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES'; */

-- -----------------------------------------------------
-- Schema music_cloud
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS music_cloud ;

-- -----------------------------------------------------
-- Schema music_cloud
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS music_cloud DEFAULT CHARACTER SET utf8 ;
USE music_cloud ;

-- -----------------------------------------------------
-- Table `music_cloud`.`track`
-- -----------------------------------------------------
DROP TABLE IF EXISTS music_cloud.track ;

CREATE TABLE IF NOT EXISTS music_cloud.track (
  id INT NOT NULL,
  title VARCHAR(1024) NULL,
  artist VARCHAR(1024) NULL,
  album VARCHAR(1024) NULL,
  year INT NULL,
  filename VARCHAR(1024) NOT NULL,
  duration VARCHAR(45) NULL,
  rating INT NULL,
  PRIMARY KEY (id))
;


-- -----------------------------------------------------
-- Table `music_cloud`.`genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS music_cloud.genre ;

CREATE TABLE IF NOT EXISTS music_cloud.genre (
  id INT NOT NULL,
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (id))
;

CREATE UNIQUE INDEX name_UNIQUE ON music_cloud.genre (name ASC);


-- -----------------------------------------------------
-- Table `music_cloud`.`track_has_genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS music_cloud.track_has_genre ;

CREATE TABLE IF NOT EXISTS music_cloud.track_has_genre (
  track_id INT NOT NULL,
  genre_id INT NOT NULL,
  PRIMARY KEY (track_id, genre_id),
  CONSTRAINT fk_track_has_genre_track
    FOREIGN KEY (track_id)
    REFERENCES music_cloud.track (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_track_has_genre_genre1
    FOREIGN KEY (genre_id)
    REFERENCES music_cloud.genre (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_track_has_genre_genre1_idx ON music_cloud.track_has_genre (genre_id ASC);

CREATE INDEX fk_track_has_genre_track_idx ON music_cloud.track_has_genre (track_id ASC);


-- -----------------------------------------------------
-- Table `music_cloud`.`account_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS music_cloud.account_info ;

CREATE TABLE IF NOT EXISTS music_cloud.account_info (
  id INT NOT NULL,
  firstname VARCHAR(45) NULL,
  lastname VARCHAR(45) NULL,
  nick VARCHAR(45) NULL,
  birthday DATE NULL,
  PRIMARY KEY (id))
;


-- -----------------------------------------------------
-- Table `music_cloud`.`account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS music_cloud.account ;

CREATE TABLE IF NOT EXISTS music_cloud.account (
  id INT NOT NULL,
  email VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  account_info_id INT NOT NULL,
  date_create TIMESTAMP(0) NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_account_account_info1
    FOREIGN KEY (account_info_id)
    REFERENCES music_cloud.account_info (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_account_account_info1_idx ON music_cloud.account (account_info_id ASC);


-- -----------------------------------------------------
-- Table `music_cloud`.`friends`
-- -----------------------------------------------------
DROP TABLE IF EXISTS music_cloud.friends ;

CREATE TABLE IF NOT EXISTS music_cloud.friends (
  account_id INT NOT NULL,
  friend_id INT NOT NULL,
  PRIMARY KEY (account_id, friend_id),
  CONSTRAINT fk_account_info_has_account_info_account_info1
    FOREIGN KEY (account_id)
    REFERENCES music_cloud.account_info (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_account_info_has_account_info_account_info2
    FOREIGN KEY (friend_id)
    REFERENCES music_cloud.account_info (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_account_info_has_account_info_account_info2_idx ON music_cloud.friends (friend_id ASC);

CREATE INDEX fk_account_info_has_account_info_account_info1_idx ON music_cloud.friends (account_id ASC);


-- -----------------------------------------------------
-- Table `music_cloud`.`mood`
-- -----------------------------------------------------
DROP TABLE IF EXISTS music_cloud.mood ;

CREATE TABLE IF NOT EXISTS music_cloud.mood (
  id INT NOT NULL,
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (id))
;

CREATE UNIQUE INDEX name_UNIQUE ON music_cloud.mood (name ASC);


-- -----------------------------------------------------
-- Table `music_cloud`.`track_has_mood`
-- -----------------------------------------------------
DROP TABLE IF EXISTS music_cloud.track_has_mood ;

CREATE TABLE IF NOT EXISTS music_cloud.track_has_mood (
  track_id INT NOT NULL,
  mood_id INT NOT NULL,
  account_info_id INT NOT NULL,
  PRIMARY KEY (track_id, mood_id, account_info_id),
  CONSTRAINT fk_track_has_mood_track1
    FOREIGN KEY (track_id)
    REFERENCES music_cloud.track (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_track_has_mood_mood1
    FOREIGN KEY (mood_id)
    REFERENCES music_cloud.mood (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_track_has_mood_mood1
    FOREIGN KEY (account_info_id)
    REFERENCES music_cloud.account_info (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_track_has_mood_mood1_idx ON music_cloud.track_has_mood (mood_id ASC);

CREATE INDEX fk_track_has_mood_track1_idx ON music_cloud.track_has_mood (track_id ASC);

CREATE INDEX fk_track_has_mood_mood1_idx1 ON music_cloud.track_has_mood (account_info_id ASC);


-- -----------------------------------------------------
-- Table `music_cloud`.`tracklist`
-- -----------------------------------------------------
DROP TABLE IF EXISTS music_cloud.tracklist ;

CREATE TABLE IF NOT EXISTS music_cloud.tracklist (
  id INT NOT NULL,
  name VARCHAR(45) NOT NULL,
  date_create TIMESTAMP(0) NULL,
  account_info_id INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_tracklist_account_info1
    FOREIGN KEY (account_info_id)
    REFERENCES music_cloud.account_info (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_tracklist_account_info1_idx ON music_cloud.tracklist (account_info_id ASC);


-- -----------------------------------------------------
-- Table `music_cloud`.`tracklist_has_track`
-- -----------------------------------------------------
DROP TABLE IF EXISTS music_cloud.tracklist_has_track ;

CREATE TABLE IF NOT EXISTS music_cloud.tracklist_has_track (
  tracklist_id INT NOT NULL,
  track_id INT NOT NULL,
  PRIMARY KEY (tracklist_id, track_id),
  CONSTRAINT fk_tracklist_has_track_tracklist1
    FOREIGN KEY (tracklist_id)
    REFERENCES music_cloud.tracklist (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_tracklist_has_track_track1
    FOREIGN KEY (track_id)
    REFERENCES music_cloud.track (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_tracklist_has_track_track1_idx ON music_cloud.tracklist_has_track (track_id ASC);

CREATE INDEX fk_tracklist_has_track_tracklist1_idx ON music_cloud.tracklist_has_track (tracklist_id ASC);


-- -----------------------------------------------------
-- Table `music_cloud`.`message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS music_cloud.message ;

CREATE TABLE IF NOT EXISTS music_cloud.message (
  id INT NOT NULL,
  create TIMESTAMP(0) NOT NULL,
  text VARCHAR(1024) NOT NULL,
  parent_id INT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_message_message1
    FOREIGN KEY (parent_id)
    REFERENCES music_cloud.message (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_message_message1_idx ON music_cloud.message (parent_id ASC);


-- -----------------------------------------------------
-- Table `music_cloud`.`account_info_has_message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS music_cloud.account_info_has_message ;

CREATE TABLE IF NOT EXISTS music_cloud.account_info_has_message (
  account_info_id INT NOT NULL,
  message_id INT NOT NULL,
  PRIMARY KEY (account_info_id, message_id),
  CONSTRAINT fk_account_info_has_message_account_info1
    FOREIGN KEY (account_info_id)
    REFERENCES music_cloud.account_info (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_account_info_has_message_message1
    FOREIGN KEY (message_id)
    REFERENCES music_cloud.message (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_account_info_has_message_message1_idx ON music_cloud.account_info_has_message (message_id ASC);

CREATE INDEX fk_account_info_has_message_account_info1_idx ON music_cloud.account_info_has_message (account_info_id ASC);


-- -----------------------------------------------------
-- Table `music_cloud`.`rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS music_cloud.rating ;

CREATE TABLE IF NOT EXISTS music_cloud.rating (
  track_id INT NOT NULL,
  account_info_id INT NOT NULL,
  value VARCHAR(45) NULL,
  PRIMARY KEY (track_id, account_info_id),
  CONSTRAINT fk_rating_track1
    FOREIGN KEY (track_id)
    REFERENCES music_cloud.track (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_rating_account_info1
    FOREIGN KEY (account_info_id)
    REFERENCES music_cloud.account_info (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_rating_track1_idx ON music_cloud.rating (track_id ASC);

CREATE INDEX fk_rating_account_info1_idx ON music_cloud.rating (account_info_id ASC);


-- -----------------------------------------------------
-- Table `music_cloud`.`comments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS music_cloud.comments ;

CREATE TABLE IF NOT EXISTS music_cloud.comments (
  id INT NOT NULL,
  track_id INT NOT NULL,
  text VARCHAR(45) NULL,
  parent_id INT NOT NULL,
  order VARCHAR(45) NULL,
  account_info_id INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_comments_track1
    FOREIGN KEY (track_id)
    REFERENCES music_cloud.track (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_comments_comments1
    FOREIGN KEY (parent_id)
    REFERENCES music_cloud.comments (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_comments_account_info1
    FOREIGN KEY (account_info_id)
    REFERENCES music_cloud.account_info (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_comments_track1_idx ON music_cloud.comments (track_id ASC);

CREATE INDEX fk_comments_comments1_idx ON music_cloud.comments (parent_id ASC);

CREATE INDEX fk_comments_account_info1_idx ON music_cloud.comments (account_info_id ASC);


-- -----------------------------------------------------
-- Table `music_cloud`.`more_track_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS music_cloud.more_track_info ;

CREATE TABLE IF NOT EXISTS music_cloud.more_track_info (
  id INT NOT NULL,
  track_id INT NOT NULL,
  text VARCHAR(45) NULL,
  account_info_id INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_more_track_info_track1
    FOREIGN KEY (track_id)
    REFERENCES music_cloud.track (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_more_track_info_account_info1
    FOREIGN KEY (account_info_id)
    REFERENCES music_cloud.account_info (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_more_track_info_track1_idx ON music_cloud.more_track_info (track_id ASC);

CREATE INDEX fk_more_track_info_account_info1_idx ON music_cloud.more_track_info (account_info_id ASC);


-- -----------------------------------------------------
-- Table `music_cloud`.`account_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS music_cloud.account_role ;

CREATE TABLE IF NOT EXISTS music_cloud.account_role (
  id INT NOT NULL,
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (id))
;


-- -----------------------------------------------------
-- Table `music_cloud`.`account_has_account_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS music_cloud.account_has_account_role ;

CREATE TABLE IF NOT EXISTS music_cloud.account_has_account_role (
  account_id INT NOT NULL,
  account_role_id INT NOT NULL,
  PRIMARY KEY (account_id, account_role_id),
  CONSTRAINT fk_account_has_account_role_account1
    FOREIGN KEY (account_id)
    REFERENCES music_cloud.account (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_account_has_account_role_account_role1
    FOREIGN KEY (account_role_id)
    REFERENCES music_cloud.account_role (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_account_has_account_role_account_role1_idx ON music_cloud.account_has_account_role (account_role_id ASC);

CREATE INDEX fk_account_has_account_role_account1_idx ON music_cloud.account_has_account_role (account_id ASC);


/* SET SQL_MODE=@OLD_SQL_MODE; */
/* SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS; */
/* SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS; */


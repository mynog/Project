-- -----------------------------------------------------
-- Scheme music_cloud
-- -----------------------------------------------------

DROP SCHEMA IF EXISTS music_cloud CASCADE;
CREATE SCHEMA IF NOT EXISTS music_cloud;

-- -----------------------------------------------------
-- Create hibernate_sequence
-- -----------------------------------------------------

DROP SEQUENCE IF EXISTS hibernate_sequence;
CREATE SEQUENCE hibernate_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE hibernate_sequence
  OWNER TO viktor_kulygin;

-- -----------------------------------------------------
-- Table `track`
-- -----------------------------------------------------
DROP TABLE IF EXISTS track ;

CREATE TABLE IF NOT EXISTS track (
  id INT NOT NULL DEFAULT nextval('hibernate_sequence'),
  title VARCHAR(1024) NULL,
  artist VARCHAR(1024) NULL,
  album VARCHAR(1024) NULL,
  year INT NULL,
  filename VARCHAR(1024) NOT NULL,
  duration VARCHAR(45) NULL,
  rating DOUBLE PRECISION NULL,
  PRIMARY KEY (id))
;


-- -----------------------------------------------------
-- Table `genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS genre ;

CREATE TABLE IF NOT EXISTS genre (
  id INT NOT NULL DEFAULT nextval('hibernate_sequence'),
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (id))
;

CREATE UNIQUE INDEX name_UNIQUE ON genre (name ASC);


-- -----------------------------------------------------
-- Table `track_has_genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS track_has_genre ;

CREATE TABLE IF NOT EXISTS track_has_genre (
  track_id INT NOT NULL,
  genre_id INT NOT NULL,
  PRIMARY KEY (track_id, genre_id),
  CONSTRAINT fk_track_has_genre_track
    FOREIGN KEY (track_id)
    REFERENCES track (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_track_has_genre_genre1
    FOREIGN KEY (genre_id)
    REFERENCES genre (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_track_has_genre_genre1_idx ON track_has_genre (genre_id ASC);

CREATE INDEX fk_track_has_genre_track_idx ON track_has_genre (track_id ASC);


-- -----------------------------------------------------
-- Table `account_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS account_info ;

CREATE TABLE IF NOT EXISTS account_info (
  id INT NOT NULL DEFAULT nextval('hibernate_sequence'),
  firstname VARCHAR(45) NULL,
  lastname VARCHAR(45) NULL,
  nick VARCHAR(45) NULL,
  birthday DATE NULL,
  PRIMARY KEY (id))
;


-- -----------------------------------------------------
-- Table `account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS account ;

CREATE TABLE IF NOT EXISTS account (
  id INT NOT NULL DEFAULT nextval('hibernate_sequence'),
  email VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  account_info_id INT NULL,
  date_create TIMESTAMP NOT NULL DEFAULT current_timestamp,
  PRIMARY KEY (id),
  CONSTRAINT fk_account_account_info1
    FOREIGN KEY (account_info_id)
    REFERENCES account_info (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_account_account_info1_idx ON account (account_info_id ASC);


-- -----------------------------------------------------
-- Table `friends`
-- -----------------------------------------------------
DROP TABLE IF EXISTS friends ;

CREATE TABLE IF NOT EXISTS friends (
  account_id INT NOT NULL,
  friend_id INT NOT NULL,
  PRIMARY KEY (account_id, friend_id),
  CONSTRAINT fk_account_info_has_account_info_account_info1
    FOREIGN KEY (account_id)
    REFERENCES account_info (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_account_info_has_account_info_account_info2
    FOREIGN KEY (friend_id)
    REFERENCES account_info (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_account_info_has_account_info_account_info2_idx ON friends (friend_id ASC);

CREATE INDEX fk_account_info_has_account_info_account_info1_idx ON friends (account_id ASC);


-- -----------------------------------------------------
-- Table `mood`
-- -----------------------------------------------------
DROP TABLE IF EXISTS mood ;

CREATE TABLE IF NOT EXISTS mood (
  id INT NOT NULL DEFAULT nextval('hibernate_sequence'),
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (id))
;

CREATE UNIQUE INDEX name_UNIQUE2 ON mood (name ASC);


-- -----------------------------------------------------
-- Table `track_has_mood`
-- -----------------------------------------------------
DROP TABLE IF EXISTS track_has_mood ;

CREATE TABLE IF NOT EXISTS track_has_mood (
  track_id INT NOT NULL,
  mood_id INT NOT NULL,
  account_info_id INT NOT NULL,
  PRIMARY KEY (track_id, mood_id, account_info_id),
  CONSTRAINT fk_track_has_mood_track1
    FOREIGN KEY (track_id)
    REFERENCES track (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_track_has_mood_mood1
    FOREIGN KEY (mood_id)
    REFERENCES mood (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_track_has_mood_account_info1
    FOREIGN KEY (account_info_id)
    REFERENCES account_info (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_track_has_mood_mood1_idx ON track_has_mood (mood_id ASC);

CREATE INDEX fk_track_has_mood_track1_idx ON track_has_mood (track_id ASC);

CREATE INDEX fk_track_has_mood_account_info1_idx ON track_has_mood (account_info_id ASC);


-- -----------------------------------------------------
-- Table `tracklist`
-- -----------------------------------------------------
DROP TABLE IF EXISTS tracklist ;

CREATE TABLE IF NOT EXISTS tracklist (
  id INT NOT NULL DEFAULT nextval('hibernate_sequence'),
  name VARCHAR(45) NOT NULL,
  date_create TIMESTAMP NOT NULL DEFAULT current_timestamp,
  account_info_id INT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_tracklist_account_info1
    FOREIGN KEY (account_info_id)
    REFERENCES account_info (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_tracklist_account_info1_idx ON tracklist (account_info_id ASC);


-- -----------------------------------------------------
-- Table `tracklist_has_track`
-- -----------------------------------------------------
DROP TABLE IF EXISTS tracklist_has_track ;

CREATE TABLE IF NOT EXISTS tracklist_has_track (
  tracklist_id INT NOT NULL,
  track_id INT NOT NULL,
  PRIMARY KEY (tracklist_id, track_id),
  CONSTRAINT fk_tracklist_has_track_tracklist1
    FOREIGN KEY (tracklist_id)
    REFERENCES tracklist (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_tracklist_has_track_track1
    FOREIGN KEY (track_id)
    REFERENCES track (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_tracklist_has_track_track1_idx ON tracklist_has_track (track_id ASC);

CREATE INDEX fk_tracklist_has_track_tracklist1_idx ON tracklist_has_track (tracklist_id ASC);

-- -----------------------------------------------------
-- Table `chat`
-- -----------------------------------------------------
DROP TABLE IF EXISTS chat ;

CREATE TABLE IF NOT EXISTS chat (
  id INT NOT NULL DEFAULT nextval('hibernate_sequence'),
  PRIMARY KEY (id))
;

-- -----------------------------------------------------
-- Table `message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS message ;

CREATE TABLE IF NOT EXISTS message (
  id INT NOT NULL DEFAULT nextval('hibernate_sequence'),
  create_message TIMESTAMP NOT NULL DEFAULT current_timestamp,
  text VARCHAR(1024) NOT NULL,
  chat_id INT NOT NULL,
  PRIMARY KEY (id, chat_id),
  CONSTRAINT fk_message_chat1
    FOREIGN KEY (chat_id)
    REFERENCES chat (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_message_chat1_idx ON message (chat_id ASC);

-- -----------------------------------------------------
-- Table `rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS rating ;

CREATE TABLE IF NOT EXISTS rating (
  id INT NOT NULL DEFAULT nextval('hibernate_sequence'),
  track_id INT NOT NULL,
  account_info_id INT NOT NULL,
  rating_value INT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_rating_track1
    FOREIGN KEY (track_id)
    REFERENCES track (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_rating_account_info1
    FOREIGN KEY (account_info_id)
    REFERENCES account_info (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_rating_track1_idx ON rating (track_id ASC);

CREATE INDEX fk_rating_account_info1_idx ON rating (account_info_id ASC);


-- -----------------------------------------------------
-- Table `comments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS comments ;

CREATE TABLE IF NOT EXISTS comments (
  id INT NOT NULL DEFAULT nextval('hibernate_sequence'),
  track_id INT NULL,
  text VARCHAR(45) NULL,
  parent_id INT NULL,
  order_comments INT NULL,
  account_info_id INT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_comments_track1
    FOREIGN KEY (track_id)
    REFERENCES track (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_comments_comments1
    FOREIGN KEY (parent_id)
    REFERENCES comments (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_comments_account_info1
    FOREIGN KEY (account_info_id)
    REFERENCES account_info (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_comments_track1_idx ON comments (track_id ASC);

CREATE INDEX fk_comments_comments1_idx ON comments (parent_id ASC);

CREATE INDEX fk_comments_account_info1_idx ON comments (account_info_id ASC);


-- -----------------------------------------------------
-- Table `more_track_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS more_track_info ;

CREATE TABLE IF NOT EXISTS more_track_info (
  id INT NOT NULL DEFAULT nextval('hibernate_sequence'),
  track_id INT NULL,
  text VARCHAR(45) NOT NULL,
  account_info_id INT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_more_track_info_track1
    FOREIGN KEY (track_id)
    REFERENCES track (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_more_track_info_account_info1
    FOREIGN KEY (account_info_id)
    REFERENCES account_info (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_more_track_info_track1_idx ON more_track_info (track_id ASC);

CREATE INDEX fk_more_track_info_account_info1_idx ON more_track_info (account_info_id ASC);


-- -----------------------------------------------------
-- Table `account_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS account_role ;

CREATE TABLE IF NOT EXISTS account_role (
  id INT NOT NULL DEFAULT nextval('hibernate_sequence'),
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (id))
;


-- -----------------------------------------------------
-- Table `account_has_account_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS account_has_account_role ;

CREATE TABLE IF NOT EXISTS account_has_account_role (
  account_id INT NOT NULL,
  account_role_id INT NOT NULL,
  PRIMARY KEY (account_id, account_role_id),
  CONSTRAINT fk_account_has_account_role_account1
    FOREIGN KEY (account_id)
    REFERENCES account (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_account_has_account_role_account_role1
    FOREIGN KEY (account_role_id)
    REFERENCES account_role (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_account_has_account_role_account_role1_idx ON account_has_account_role (account_role_id ASC);

CREATE INDEX fk_account_has_account_role_account1_idx ON account_has_account_role (account_id ASC);

-- -----------------------------------------------------
-- Table `account_info_has_track`
-- -----------------------------------------------------

DROP TABLE IF EXISTS account_info_has_track ;

CREATE TABLE IF NOT EXISTS account_info_has_track (
  track_id INT NOT NULL,
  account_info_id INT NOT NULL,
  PRIMARY KEY (track_id, account_info_id),
  CONSTRAINT fk_account_info_has_track_track1
    FOREIGN KEY (track_id)
    REFERENCES track (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_account_info_has_track_account_info1
    FOREIGN KEY (account_info_id)
    REFERENCES account_info (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_account_info_has_track_track1_idx ON account_info_has_track (track_id ASC);

CREATE INDEX fk_account_info_has_track_account_info1_idx ON account_info_has_track (account_info_id ASC);

-- -----------------------------------------------------
-- Table `chat_has_account_info`
-- -----------------------------------------------------

DROP TABLE IF EXISTS chat_has_account_info ;

CREATE TABLE IF NOT EXISTS chat_has_account_info (
  chat_id INT NOT NULL,
  account_info_id INT NOT NULL,
  PRIMARY KEY (chat_id, account_info_id),
  CONSTRAINT fk_chat_has_account_info_chat1
    FOREIGN KEY (chat_id)
    REFERENCES chat (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_chat_has_account_info_account_info1
    FOREIGN KEY (account_info_id)
    REFERENCES account_info (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_chat_has_account_info_account_info1_idx ON chat_has_account_info (account_info_id ASC);

CREATE INDEX fk_chat_has_account_info_chat1_idx ON chat_has_account_info (chat_id ASC);

-- -----------------------------------------------------
-- Create trigger update_rating
-- -----------------------------------------------------

CREATE FUNCTION update_rating() RETURNS TRIGGER AS $update_rating$
BEGIN
  UPDATE track SET rating = (SELECT round(avg(rating_value), 2)
                  	    FROM rating, track
                            WHERE track.id = rating.track_id 
                            AND rating.track_id = NEW.track_id)
                            WHERE track.id = NEW.track_id;
  RETURN NEW;
END;
$update_rating$ LANGUAGE plpgsql;
CREATE TRIGGER update_rating AFTER INSERT OR UPDATE ON rating
FOR EACH ROW EXECUTE PROCEDURE update_rating();

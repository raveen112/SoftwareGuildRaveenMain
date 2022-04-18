DROP DATABASE IF EXISTS guessthenumberdb;

CREATE DATABASE guessthenumberdb;

USE guessthenumberdb; 

CREATE TABLE game(
	game_id INT AUTO_INCREMENT primary key,
    answer CHAR(4),
    status CHAR(20)
    
    );
    
CREATE TABLE round(
	round_id INT AUTO_INCREMENT primary key,
	guess CHAR(4),
    timeLog DATETIME,
    result VARCHAR(20),
    game_id INT,
    CONSTRAINT fk_game_id FOREIGN KEY (game_id) REFERENCES game(game_id));
    
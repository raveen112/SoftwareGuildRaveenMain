DROP DATABASE IF EXISTS guessthenumberdbtest;

CREATE DATABASE guessthenumberdbtest;

USE guessthenumberdbtest; 

CREATE TABLE game(
	game_id INT AUTO_INCREMENT primary key,
    status CHAR(20),
    answer CHAR(4)
    );
    
CREATE TABLE round(
	round_id INT AUTO_INCREMENT primary key,
	guess CHAR(4),
    timeLog DATETIME,
    result VARCHAR(20),
    game_id INT,
    CONSTRAINT fk_game_id FOREIGN KEY (game_id) REFERENCES game(game_id));
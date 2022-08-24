DROP DATABASE IF EXISTS tomVOA;
CREATE DATABASE tomVOA;

USE tomVOA;

CREATE TABLE associate(
id INT PRIMARY KEY AUTO_INCREMENT,
login VARCHAR(12) NOT NULL UNIQUE,
`name` VARCHAR(50) NOT NULL
);

CREATE TABLE issues(
issue_id INT PRIMARY KEY AUTO_INCREMENT, 
complaint VARCHAR(255) NOT NULL,
`date` DATE,
`status` BOOL,
id INT,
FOREIGN KEY (id)
REFERENCES associate (id)
);



INSERT INTO associate VALUES(1, "nomaan","Nomaan Muhammad");
INSERT INTO associate VALUES(2, "mitva","Mitva Patel");
INSERT INTO associate VALUES(3, "shmke","Mukesh Sharma");
INSERT INTO associate VALUES(4, "jancastr","Jan Castro");
INSERT INTO issues VALUES(1, "This is a really tough job","2022-01-03", true, 1);
INSERT INTO issues VALUES(2, "Need safety shoes","2022-01-06", false, 1);



SELECT a.* FROM associate a JOIN issues i
ON i.id= a.id
WHERE i.id= 1 GROUP BY a.id;

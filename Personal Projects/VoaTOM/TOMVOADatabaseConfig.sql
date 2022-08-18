DROP DATABASE IF EXISTS tomVOA;
CREATE DATABASE tomVOA;

USE tomVOA;

CREATE TABLE associate(
id INT PRIMARY KEY AUTO_INCREMENT,
login VARCHAR(12) NOT NULL,
`name` VARCHAR(50) NOT NULL,
issue_id INT
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



INSERT INTO associate VALUES(1, "nomaan","Nomaan Muhammad", "2");
INSERT INTO issues VALUES(1, "This is a really tough job","2022-01-03", true, 1);
INSERT INTO issues VALUES(2, "Need safety shoes","2022-01-06", false, 1);


-- SELECT * FROM associate a
-- LEFT JOIN issues i
-- ON i.issue_id = a.issue_id
-- WHERE i.issue_id = 1;

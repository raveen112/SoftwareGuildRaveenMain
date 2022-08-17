DROP DATABASE IF EXISTS tomVOA;
CREATE DATABASE tomVOA;

USE tomVOA;


CREATE TABLE issues(
issue_id INT PRIMARY KEY AUTO_INCREMENT, 
complaint VARCHAR(255) NOT NULL,
`date` DATE,
`status` BOOL
);

CREATE TABLE associate(
id INT PRIMARY KEY AUTO_INCREMENT,
login VARCHAR(12) NOT NULL,
`name` VARCHAR(50) NOT NULL,
issue_id INT,
FOREIGN KEY (issue_id)
REFERENCES issues (issue_id)
);

-- INSERT INTO issues VALUES("2022-01-03", "This is a really tough job", true);
-- INSERT INTO associate VALUES(1, "nomaan","Nomaan Muhammad",1);

-- SELECT * FROM associate a
-- LEFT JOIN issues i
-- ON i.issue_id = a.issue_id
-- WHERE i.issue_id = 1;

DROP DATABASE IF EXISTS superSightings;

CREATE DATABASE superSightings;

USE superSightings;

CREATE TABLE location(
locationId int PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL,
`description` VARCHAR(50) NOT NULL,
address VARCHAR(50) NOT NULL,
longitude VARCHAR(10),
latitude VARCHAR(10)
);

CREATE TABLE superPower(
superPowerId INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40) NOT NULL
);

CREATE TABLE superPeople(
superId INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL,
`description` VARCHAR(50) NOT NULL,
superPowerId INT NOT NULL,
CONSTRAINT FOREIGN KEY fk_superPowerId(superPowerId)
REFERENCES superPower(superPowerId)
);

CREATE TABLE sighting(
sightingId INT PRIMARY KEY AUTO_INCREMENT,
`date` DATETIME NOT NULL,
locationId INT NOT NULL,
superId INT NOT NULL,
CONSTRAINT FOREIGN KEY fk_locationId(locationId)
	REFERENCES location(locationId),
CONSTRAINT FOREIGN KEY fk_superId(superId)
	REFERENCES superPeople(superId)
);

CREATE TABLE superOrg(
orgId INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL,
`description` VARCHAR(50) NOT NULL,
address VARCHAR(50),
contact VARCHAR(25),
superId INT NOT NULL
);

CREATE TABLE superPeopleOrg (
superId INT NOT NULL,
orgId INT NOT NULL,
CONSTRAINT PRIMARY KEY pk_superPeopleOrg(superId, orgId),

CONSTRAINT FOREIGN KEY fk_heroId(superId)
	REFERENCES superPeople(superId),
    
CONSTRAINT FOREIGN KEY fk_orgId(orgId)
	REFERENCES superOrg(orgId)
);





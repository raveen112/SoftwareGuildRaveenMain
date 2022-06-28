DROP DATABASE IF EXISTS superSightingsTest;

CREATE DATABASE superSightingsTest;

USE superSightingsTest;

CREATE TABLE location(
locationId int PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL,
`description` VARCHAR(50) NOT NULL,
address VARCHAR(50) NOT NULL,
longitude VARCHAR(40),
latitude VARCHAR(40)
);

CREATE TABLE super_power(
superPowerId INT PRIMARY KEY AUTO_INCREMENT,
superPowerName VARCHAR(40) NOT NULL
);

CREATE TABLE super_people(
superId INT PRIMARY KEY AUTO_INCREMENT,
heroName VARCHAR(50) NOT NULL,
heroDescription VARCHAR(50) NOT NULL,
superPowerId INT NOT NULL,
FOREIGN KEY (superPowerId)
REFERENCES super_power(superPowerId)
);

CREATE TABLE sightings(
sightingId INT PRIMARY KEY AUTO_INCREMENT,
`date` DATE NOT NULL,
locationId INT NOT NULL,
superId INT NOT NULL,
FOREIGN KEY (locationId)
	REFERENCES location(locationId),
FOREIGN KEY (superId)
	REFERENCES super_people(superId)
);

CREATE TABLE super_org(
orgId INT PRIMARY KEY AUTO_INCREMENT,
orgName VARCHAR(50) NOT NULL,
orgDescription VARCHAR(50) NOT NULL,
address VARCHAR(50),
contact VARCHAR(25)
);

CREATE TABLE super_people_org (
superId INT NOT NULL,
orgId INT NOT NULL,
PRIMARY KEY (superId, orgId),

 FOREIGN KEY (superId)
	REFERENCES super_people(superId),
    
 FOREIGN KEY (orgId)
	REFERENCES super_org(orgId)
);

-- SELECT o.* FROM super_people h 
-- JOIN super_people_org ho
-- ON h.superId = ho.superId 
-- JOIN super_org o 
-- ON ho.orgId = o.orgId 
-- WHERE h.superId = 1;

-- SELECT * FROM sightings WHERE sightingId= 1;

-- SELECT DISTINCT h.* FROM super_people h 
--                 JOIN super_people_org ho 
--                 ON h.superId = ho.superId
--                 WHERE orgId = 1;
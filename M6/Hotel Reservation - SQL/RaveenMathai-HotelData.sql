USE HotelReservation;

INSERT INTO roomType(room_type, standard_occupancy, max_occupancy, base_price, extra_person) VALUES
('Double', 2,	4,	199.99,	 10.0),
('Double',	2,	4,	174.99, 10.0),
('Double',	2,	4,	199.99,	 10.0),
('Double',	2,	4,	174.99,	 10.0),
('Single',	2,	2,	174.99	, null),
('Single',	2,	2,	149.99	 ,null),
('Single',	2,	2,	174.99,	 null),
('Single',	2,	2,	149.99	, null),
('Double',	2,	4,	199.99	, 10.0),
('Double',	2,	4,	174.99	, 10.0),
('Double',	2,	4,	199.99	, 10.0),
('Double',	2,	4,	174.99	, 10.0),
('Single',	2,	2,	174.99	, null),
('Single',	2,	2,	149.99	, null),
('Single',	2,	2,	174.99	 ,null),
('Single',	2,	2, 149.99	, null),
('Suite'	,3,	8	,399.99	, 20.0),
('Suite',	3	,8,	399.99	, 20.0);


INSERT INTO room(room_type_id, room_number, ada_status) VALUES
(1,201, 0),
(2,202,	1),
(3,203,	0),
(4,204,	1),
(5,205,	0),
(6,206,	1),
(7,207, 0),
(8,208,	1),
(9, 301, 0),
(10,302, 1),
(11,303, 0),
(12,304, 1),
(13,305, 0),
(14,306, 1),
(15,307, 0),
(16,308, 1),
(17,401, 1),
(18,402, 1);
    

INSERT INTO guest(first_name, last_name, address, city, state, zip, contact_number) VALUES
	('Raveen','Mathai', '265 Enfield Place', 'Etobicoke', 'ON', 'M8Z0G3', '(672) 281-2934'),
	('Mack', 'Simmer',	'379 Old Shore Street',	'Council Bluffs', 'IA',	'51501', '(291) 553-0508'),
	('Bettyann', 'Seery', '750 Wintergreen Dr.', 'Wasilla', 'AK',	'99654', '(478) 277-9632'),
	('Duane', 'Cullison', '9662 Foxrun Lane', 'Harlingen', 'TX', '78552', '(308) 494-0198'),
	('Karie', 'Yang', '9378 W. Augusta Ave.', 'West Deptford', 'NJ', '08096', '(214) 730-0298'),
	('Aurore', 'Lipton', '762 Wild Rose Street', 'Saginaw', 'MI', '48601', '(377) 507-0974'),
	('Zachery', 'Luechtefeld',	'7 Poplar Dr.',	'Arvada', 'CO',	'80003', '(814) 485-2615'),
	('Jeremiah', 'Pendergrass', '70 Oakwood St.', 'Zion', 'IL', '60099', '(279) 491-0960'),
	('Walter', 'Holaway',	'7556 Arrowhead St.', 'Cumberland', 'RI', '02864', '(446) 396-6785'),
	('Wilfred', 'Vise', '77 West Surrey Street', 'Oswego', 'NY', '13126', '(834) 727-1001'),
	('Maritza', 'Tilton', '939 Linda Rd.',	'Burke', 'VA',	'22015', '(446) 351-6860'),
	('Joleen', 'Tison', '87 Queen St.', 'Drexel Hill', 'PA', '19026', '(231) 893-2755'); 
    
INSERT INTO amenities (type, additional_cost) values 
('Microwave', 0), 
("Jacuzzi", 25), 
("Refrigerator", 0), 
("Oven", 0);

INSERT INTO reservations (adults, children, start_date, end_date, total_cost, guest_id) VALUES 
	(1, 0, '2023-2-2', '2023-2-4', 299.98, 2), 
	(2, 1, '2023-2-5', '2023-2-10', 999.95, 3), 
	(2, 0, '2023-2-22', '2023-2-24', 349.98, 4), 
	(2, 2, '2023-3-6', '2023-3-7', 199.99, 5), 
	(1, 1, '2023-3-17', '2023-3-20', 524.97, 1), 
	(3, 0, '2023-3-18', '2023-3-23', 924.95, 6),
	(2, 2, '2023-3-29', '2023-3-31', 349.98, 7),
	(2, 0, '2023-3-31', '2023-4-5', 874.95, 8),
	(1, 0, '2023-4-9', '2023-4-13', 799.96, 9),
	(1, 1, '2023-4-23', '2023-4-24', 174.99, 10),
	(2, 4, '2023-5-30', '2023-6-2', 1199.97, 11),
	(2, 0, '2023-6-10', '2023-6-14', 599.96, 12),
	(1, 0, '2023-6-10', '2023-6-14', 599.96, 12),
	(3, 0, '2023-6-17', '2023-6-18', 184.99, 6),
	(2, 0, '2023-6-28', '2023-7-2', 699.96, 1),
	(3, 1, '2023-7-13', '2023-7-14', 184.99, 9),
	(4, 2, '2023-7-18', '2023-7-21', 1259.97, 10),
	(2, 1, '2023-7-28', '2023-7-29', 199.99, 3),
	(1, 0, '2023-8-30', '2023-9-1', 349.98, 3),
	(2, 0, '2023-9-16', '2023-9-17', 149.99, 2),
	(2, 2, '2023-9-13', '2023-9-15', 399.98, 5),
	(2, 2, '2023-11-22', '2023-11-25', 1199.97, 4),
	(2, 0, '2023-11-22', '2023-11-25', 449.97, 2),
	(2, 2, '2023-11-22', '2023-11-25', 599.97, 2),
	(2, 0, '2023-12-24', '2023-12-28', 699.96, 11);

INSERT INTO amenitiesrooms(room_id, amenities_id) VALUES
	(1, 1),
	(1, 2),
	(2, 3),
	(3, 1),
	(3, 2),
	(4, 3),
	(5, 1),
	(5, 3),
	(5, 2),
	(6, 1),
	(6, 3),
	(7, 1),
	(7, 3),
	(7, 2),
	(8, 1),
	(8, 3),
	(9, 1),
	(9, 2),
	(10, 3),
	(11, 1),
	(11, 2),
	(12, 3),
	(13, 1),
	(13, 3),
	(13, 2),
	(14, 1),
	(14, 3),
	(15, 1),
	(15, 3),
	(15, 2),
	(16, 1),
	(16, 3),
	(17, 1),
	(17, 3),
	(17, 4),
	(18, 1),
	(18, 3),
	(18, 4);


INSERT INTO roomreservation (reservation_id, room_id) VALUES
	(1, 16),
	(2, 3),
	(3, 13),
	(4, 1),
	(5, 15),
	(6, 10),
	(7, 2),
	(8, 12),
	(9, 9),
	(10, 7),
	(11, 17),
	(12, 6),
	(13, 8),
	(14, 12),
	(15, 5),
	(16, 4),
	(17, 17),
	(18, 11),
	(19, 13),
	(20, 8),
	(21, 3),
	(22, 17),
	(23, 6),
	(24, 9),
	(25, 10);

SELECT * FROM guest
WHERE last_name = 'Pendergrass';


DELETE FROM guest
WHERE guest_id = 8;


DELETE FROM reservations 
WHERE guest_id = 8;




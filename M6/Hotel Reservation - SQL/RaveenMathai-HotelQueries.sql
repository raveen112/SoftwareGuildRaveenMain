-- 1. Write a query that returns a list of reservations that end in July 2023, including 
--    the name of the guest, the room number(s), and the reservation dates.

-- SELECT CONCAT(first_name, " " ,last_name) AS `Name`, room_number, start_date, end_date FROM reservations r
-- LEFT JOIN guest g ON r.guest_id = g.guest_id
-- LEFT JOIN roomReservation re ON r.reservation_id = re.reservation_id
-- LEFT JOIN room ro ON re.room_id = ro.room_id
-- WHERE r.end_date >= '2023-07-01' AND r.end_date <= '2023-07-31' ;

-- (4 rows)
-- guest_name		room_number		start_date	end_date
-- Raveen Mathai		205			6/28/2023	7/2/2023
-- Walter Holaway		204			7/13/2023	7/14/2023
-- Wilfred Vise			401			7/18/2023	7/21/2023
-- Bettyann Seery		303			7/28/2023	7/29/2023

-- -----------------------------------------------------------------------------
-- 2. Write a query that returns a list of all reservations for rooms with a jacuzzi, 
--    displaying the guest's name, the room number, and the dates of the reservation.

-- SELECT CONCAT(first_name, " " ,last_name) as `Name`, room_number, start_date, end_date FROM reservations r
-- LEFT JOIN guest g ON r.guest_id = g.guest_id
-- INNER JOIN roomReservation rr ON r.reservation_id = rr.reservation_id 
-- INNER JOIN room d ON rr.room_id = d.room_id
-- INNER JOIN roomType rt ON d.room_type_id = rt.room_type_id
-- INNER JOIN amenitiesRooms ar ON d.room_id = ar.room_id
-- INNER JOIN amenities a ON a.amenities_id = ar.amenities_id
-- WHERE `type` = "Jacuzzi";

-- (11 rows)
-- Name				room_number		start_date		end_date
-- Karie Yang			201			3/6/2023		3/7/2023
-- Bettyann Seery		203			2/5/2023		2/10/2023
-- Karie Yang			203			9/13/2023		9/15/2023
-- Raveen Mathai		205			6/28/2023		7/2/2023
-- Wilfred Vise			207			4/23/2023		4/24/2023
-- Walter Holaway		301			4/9/2023		4/13/2023
-- Mack Simmer			301			11/22/2023		11/25/2023
-- Bettyann Seery		303			7/28/2023		7/29/2023
-- Duane Cullison		305			2/22/2023		2/24/2023
-- Bettyann Seery		305			8/30/2023		9/1/2023
-- Raveen Mathai		307			3/17/2023		3/20/2023

-- ----------------------------------------------------------------------------

-- 3. Write a query that returns all the rooms reserved for a specific guest, 
-- 	  including the guest's name, the room(s) reserved, the starting date of the 
--    reservation, and how many people were included in the reservation. 
--    (Choose a guest's name from the existing data.)

-- SELECT CONCAT(first_name, " " ,last_name) as `Name`, room_number, start_date AS ReservationDate, adults, children FROM reservations r
-- LEFT JOIN guest g ON r.guest_id = g.guest_id
-- INNER JOIN roomReservation rr ON r.reservation_id = rr.reservation_id 
-- INNER JOIN room d ON rr.room_id = d.room_id
-- WHERE CONCAT(first_name, " " ,last_name) = 'Mack Simmer';
 
 -- (4 rows --  Mack Simmer)
-- name		room_number		ReservationDate		adults		children
-- Mack Simmer		308				2/2/2023			1			0
-- Mack Simmer		208				9/16/2023			2			0
-- Mack Simmer		206				11/22/2023			2			0
-- Mack Simmer		301				11/22/2023			2			2

-- ----------------------------------------------------------------------------------
  
-- 4. Write a query that returns a list of rooms, reservation ID, and per-room cost for 
-- 	  each reservation. The results should include all rooms, whether or not there is a 
--    reservation associated with the room.

-- SELECT room_number,r.reservation_id, total_cost FROM reservations r
-- INNER JOIN roomReservation re ON r.reservation_id = re.reservation_id
-- RIGHT OUTER JOIN room ro ON re.room_id = ro.room_id;

-- (26 Rows)
-- room_number	reservation_id	total_cost
-- 	201			4				200
-- 	202			7				350
-- 	203			2				1000
-- 	203			21				400
-- 	204			16				185
-- 	205			15				700
-- 	206			12				600
-- 	206			23				450
-- 	207			10				175
-- 	208			13				600
-- 	208			20				150
-- 	301			9				800
-- 	301			24				600
-- 	302			6				925
-- 	302			25				700
-- 	303			18				200
-- 	304			14				185
-- 	305			3				350
-- 	305			19				350
-- 	306			NULL			NULL
-- 	307			5				525
-- 	308			1				300
-- 	401			11				1200
-- 	401			17				1260
-- 	401			22				1200
-- 	402			NULL			NULL

-- -------------------------------------------------------------------------------

-- 5. Write a query that returns all the rooms accommodating at least three guests 
--    and that are reserved on any date in April 2023.

-- SELECT room_number, 
-- CONCAT(first_name, " " ,last_name) AS `Name`,
-- max_occupancy,
-- start_date FROM reservations r
-- LEFT JOIN guest g ON r.guest_id = g.guest_id
-- INNER JOIN roomReservation re ON r.reservation_id = re.reservation_id
-- INNER JOIN room d ON re.room_id = d.room_id
-- INNER JOIN roomType rt ON d.room_type_id = rt.room_type_id
-- WHERE MONTH(start_date) = 4 AND max_occupancy >=3
-- GROUP BY room_number;

-- (1 row)
-- room_number   	 guest_name  	 max_occupancy   	 start_date
-- 	301		Walter Holaway   			4			 2023-04-09

-- --------------------------------------------------------------------------

-- 6. Write a query that returns a list of all guest names and the number of reservations per guest, 
--    sorted starting with the guest with the most reservations and then by the guest's last name.

-- SELECT 
-- Last_name,
-- COUNT(r.reservation_id) AS total_reservations
-- FROM guest g
-- INNER JOIN reservations r ON g.guest_id = r.guest_id
-- GROUP BY r.guest_id
-- ORDER by total_reservations DESC;

-- (11 rows)
-- LastName	total_reservations
-- Simmer			4
-- Seery			3
-- Mathai			2
-- Cullison			2
-- Yang				2
-- Lipton			2
-- Holaway			2
-- Vise				2
-- Tilton			2
-- Tison			2
-- Luechtefeld			1

-- -----------------------------------------------------------------------------------------
-- 7. Write a query that displays the name, address, and phone number of a guest based on their 
--    phone number. (Choose a phone number from the existing data.)

-- SELECT contact_number AS PhoneNumber, CONCAT(first_name, " " ,last_name) AS `Name` , address AS Address from GUEST
-- WHERE contact_number  = '(478) 277-9632';

-- (1 row)
-- PhoneNumber 		Name			Address
-- (478) 277-9632	Bettyann Seery		750 Wintergreen Dr.
DROP DATABASE IF EXISTS HotelReservation;

CREATE DATABASE HotelReservation;

USE HotelReservation;

CREATE TABLE guest (
	guest_id INT PRIMARY KEY auto_increment,
    `name` VARCHAR(100) NOT NULL,
    address VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(3) NOT NULL,
    zip CHAR(10) NOT NULL,
    contact_number VARCHAR(30) NOT NULL
);

CREATE TABLE room (
	room_id INT NOT NULL PRIMARY KEY auto_increment,
    room_number CHAR(10) NOT NULL,
    room_type CHAR(10) NOT NULL,
    ada_status CHAR(4) NOT NULL,
    standard_occupancy INT NOT NULL,
    max_occupancy INT NOT NULL,
    base_price INT NOT NULL,
    extra_Person INT
);

CREATE TABLE reservations(
	reservation_id INT NOT NULL PRIMARY KEY auto_increment,
    guest_id INT,
    guest_name VARCHAR(50) NOT NULL,
    adults INT NOT NULL,
    children INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    total_cost INT NOT NULL,
    CONSTRAINT fk_guest_reservation
		FOREIGN KEY(guest_id)
			REFERENCES guest(guest_id) ON DELETE CASCADE
);

CREATE TABLE amenities(
	amenities_id INT NOT NULL PRIMARY KEY auto_increment,
    type VARCHAR(20) NOT NULL
);

-- Amenities room bridge table
CREATE TABLE amenitiesRooms(
	amenities_id INT NOT NULL,
    room_id INT NOT NULL,
    PRIMARY KEY pk_roomAmenity (room_id, amenities_id),
		FOREIGN KEY fk_roomAmenity_room(room_id) 
			REFERENCES room(room_id),
		FOREIGN KEY fk_roomAmenity_amenity(amenities_id)
			REFERENCES amenities(amenities_id) ON DELETE CASCADE
);

-- Room reservation bridge table
CREATE TABLE roomReservation(
	reservation_id INT NOT NULL,
    room_id INT NOT NULL,
    PRIMARY KEY pk_roomReservation(reservation_id, room_id),
		FOREIGN KEY fk_roomReservation_reservation (reservation_id)
				REFERENCES reservations(reservation_id) ON DELETE CASCADE,
		FOREIGN KEY fk_roomReservation_room(room_id)
				REFERENCES room(room_id) ON DELETE CASCADE
);


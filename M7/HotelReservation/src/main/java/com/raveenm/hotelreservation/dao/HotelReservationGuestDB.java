/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.hotelreservation.dao;

import com.raveenm.hotelreservation.model.Guest;
import java.util.List;

/**
 *
 * @author ravee
 */
public interface HotelReservationGuestDB {

    Guest addGuest();

    List<Guest> getAllGuests();

    Guest removeGuest();

    Guest updateGuest();

}

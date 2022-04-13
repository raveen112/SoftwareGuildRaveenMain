/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.jdbctcomplexexample.dao;

import com.sg.jdbctcomplexexample.App.TestApplicationConfiguration;
import com.sg.jdbctcomplexexample.entity.Employee;
import com.sg.jdbctcomplexexample.entity.Meeting;
import com.sg.jdbctcomplexexample.entity.Room;
import java.util.List;
import org.junit.Before;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author ravee
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class RoomDaoDBTest {

    @Autowired
    RoomDao roomDao;

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    MeetingDao meetingDao;

    public RoomDaoDBTest() {
    }

    @Before
    public void setUp() {
        List<Room> rooms = roomDao.getAllRooms();
        for (Room room : rooms) {
            roomDao.deleteRoomById(room.getId());
        }

        List<Employee> employees = employeeDao.getAllEmployees();
        for (Employee employee : employees) {
            employeeDao.deleteEmployeeById(employee.getId());
        }

        List<Meeting> meetings = meetingDao.getAllMeetings();
        for (Meeting meeting : meetings) {
            meetingDao.deleteMeetingById(meeting.getId());
        }
    }

    /**
     * Test of getAllRooms method, of class RoomDaoDB.
     */
    @Test
    public void testGetAllRooms() {
        Room room = new Room();
        room.setName("Test Room");
        room.setDescription("Test Room Description");
        roomDao.addRoom(room);

        Room room2 = new Room();
        room2.setName("Test Room 2");
        room2.setDescription("Test Room 2");
        roomDao.addRoom(room2);

        List<Room> rooms = roomDao.getAllRooms();

        assertEquals(2, rooms.size());
        assertTrue(rooms.contains(room));
        assertTrue(rooms.contains(room2));
    }

    /**
     * Test of getRoomById method, of class RoomDaoDB.
     */
    @Test
    public void testAddGetRoom() {
        Room room = new Room();
        room.setName("Test Room");
        room.setDescription("Test Room Description");
        room = roomDao.addRoom(room);

        Room fromDao = roomDao.getRoomById(room.getId());

        assertEquals(room, fromDao);
    }

//    /**
//     * Test of updateRoom method, of class RoomDaoDB.
//     */
//    @Test
//    public void testUpdateRoom() {
//        
//    }
//
//    /**
//     * Test of deleteRoomById method, of class RoomDaoDB.
//     */
//    @Test
//    public void testDeleteRoomById() {
//    }

}

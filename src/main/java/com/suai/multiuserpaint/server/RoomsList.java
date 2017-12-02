/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suai.multiuserpaint.server;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author nikit
 */
public class RoomsList {
    private Map<String, Room> rooms = new HashMap<String, Room>();
    
    public void add(Room room) 
    {
        System.out.println( "Комната " + room +" создана" );
        rooms.put(room.toString(), room);
    }
        
    public void add(User admin, String name) 
    {
        System.out.println( "Комната " + name +" создана" );
        rooms.put(name , new Room(admin, name));

    }

    
    public String[] getRooms() 
    {
        return rooms.keySet().toArray(new String[0]);
    }
    
    public Room getRoom(String name)
    {
        return rooms.get(name);
    }
}

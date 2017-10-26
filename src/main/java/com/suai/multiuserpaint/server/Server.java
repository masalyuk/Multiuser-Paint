/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suai.multiuserpaint.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.LinkedList;

/**
 *
 * @author nikit
 */
public class Server {
    private static RoomsList rooms = new RoomsList();
    private static int port = 7124;
    public static final int height = 600;
     public static final int width = 600;
    
    // Width and height of Canvas
    
    public static void main(String argv[])
    {
        try 
        {
            System.out.println("Сервер запущен");

            ServerSocket socketListener = new ServerSocket(port);
            while (true) 
            {
                Socket user = null;
                while(user == null) 
                      user = socketListener.accept();
                            
                //new ClientThread(client); //Создаем новый поток, которому передаем сокет
            }
	} 
        catch (SocketException e) 
        {
            System.err.println("So.8cket exception");
            e.printStackTrace();
        } 
        catch (IOException e) 
        {
            System.err.println("I/O exception");  
            e.printStackTrace();
	}
    }
    public static void createRoom(User user, String name)
    {
        rooms.add(user, name);
    }
    public synchronized static RoomsList getRoomList() 
    {
		return rooms;
    }
    
}

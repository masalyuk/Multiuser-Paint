/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suai.multiuserpaint.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.LinkedList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author nikit
 */
public class Server{
    private static RoomsList rooms = new RoomsList();
    private static int port = 7000;
    public static final int height = 600;
    public static final int width = 600;
     
 
    
    // Width and height of Canvas
    
    public static void main(String argv[])
    {
        try 
        {
            
            
            ServerSocket socketListener = new ServerSocket(port);
            System.out.println("Сервер запущен " + socketListener.getInetAddress().toString() + port);
            
            while (true) 
            {
                Socket user = null;
                
                while(user == null) 
                      user = socketListener.accept();
                            
                new UserThread(user); //Создаем новый поток, которому передаем сокет
            }
	} 
        catch (SocketException e) 
        {
            System.err.println("So.8cket exception");
        } 
        catch (IOException e) 
        {
            System.err.println("I/O exception");  
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

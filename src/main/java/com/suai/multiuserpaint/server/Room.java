/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suai.multiuserpaint.server;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import java.util.LinkedList;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 *
 * @author nikit
 */
public class Room {
    
    private String name;
    private static int id = 0;
    private int idRoom;
    
    private ArrayList<User> users;
    private User admin;
    
    private Canvas canvas;
    
    public Room(User admin,String name)
    {
        idRoom = ++id;
        this.name = name;
        this.admin = admin;
        addUser(this.admin);
        
        //высоту и ширину мб считывать с файла конфигураций
        canvas = new Canvas(Server.width, Server.height);
    }

    
    
    public WritableImage getSnapshot()
    {
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);  
        return canvas.snapshot(params, null);
    }
    public void addUser(User user)
    {
        users.add(user);
    }
    
    private void broadcast(ArrayList<User> users, WritableImage image) 
    {
	try 
        {
            for (User user : users)
                user.getThisObjectOutputStream().writeObject(image); 
	} 
        catch (SocketException e) 
        {
 
            //обработать диссконект пользователя
            
	} 
        catch (IOException e) 
        {
            e.printStackTrace();
	}
    }
}

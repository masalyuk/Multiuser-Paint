/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suai.multiuserpaint.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author nikit
 */
public class UserThread extends Thread{
    private Socket socket;
    private String name;
    private ObjectOutputStream outputStream ;
    private ObjectInputStream inputStream;
    private Room room;
    private User user;
    private static int id = -1;
    
    public UserThread(Socket socket) 
    {
        this.id++;
        this.socket = socket;
        System.out.println("Join: "  + socket.getInetAddress().toString());
        this.start();
    }
    
    public void run() 
    {
        try
        {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream  = new ObjectInputStream(socket.getInputStream());
            
            //отправляем клинту список текущих комнат
            outputStream.writeObject(id);
            outputStream.writeObject(Server.getRoomList().getRooms());
            
            //получаем ответ о создании или подключении к комнате
            String[] answer = (String[])inputStream.readObject();
            
            if(answer[0].equals("n"))
            {
                System.out.println("Client: " + (id-1) + " creating room with name: " + answer[1]);
                user = new User(socket,outputStream, inputStream);
                room = new Room(user, answer[1]);                
                Server.getRoomList().add(room);
            }
            else
            {
                room =  Server.getRoomList().getRoom(answer[1]);
                user = new User(socket,outputStream, inputStream);
                room.join(user);
            } 
            
            while(true)
            {
                String[] changes = (String[])inputStream.readObject();
                room.addAction(user, changes);
                
            }
	}
        catch(IOException e)
        {
            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(UserThread.class.getName()).log(Level.SEVERE, null, ex);
            }
           System.out.println("User:" + user.getSocket().getInetAddress() + " EXEPTION: " + e.getMessage());
        } catch (ClassNotFoundException ex) 
        {
            
        } 

    }
}

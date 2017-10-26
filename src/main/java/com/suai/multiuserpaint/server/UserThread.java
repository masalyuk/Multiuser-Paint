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
    
    public UserThread(Socket socket) 
    {
        this.socket = socket;
        this.start();
    }
    
    public void run() 
    {
        try
        {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream  = new ObjectInputStream(socket.getInputStream());
            
            //отправляем клинту список текущих комнат
            outputStream.writeObject(Server.getRoomList().getRooms());
            
            //получаем ответ о создании или подключении к комнате
            String answer = (String)inputStream.readObject();
            
            //мб добавить регулярки
            if(answer.split(" ")[0].equals("n"))
            {
                room = new Room(new User(socket,outputStream, inputStream), answer.split(" ")[1]);
                Server.getRoomList().add(room);
            }
            else
            {
                room =  Server.getRoomList().getRoom(answer.split(" ")[1]);
                room.join(new User(socket,outputStream, inputStream));
            }
               

            while(true)
            {
                //получаем обновления рисования 
                String changes = (String)inputStream.readObject();
                room.update(changes);
     
                //отсылаем комнате
            }
            
        

	}
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) 
        {
            
        }

    }
}

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
        
	}
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
        //отправить список комнат
        
        //узнать создает или подключается к комнате
        
        //получаем обновления рисования 
        
        //отсылаем комнате
    }
}

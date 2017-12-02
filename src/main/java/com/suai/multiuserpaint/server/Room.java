
package com.suai.multiuserpaint.server;

import com.suai.multiuserpaint.history.History;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nikit
 */
public class Room{
    
    private String name;
    private static int id = 0;
    private int idRoom;
    private History history = new History();
    
    private ArrayList<User> users = new ArrayList();
    private User admin;
    
    
    public Room(User admin, String name)
    {
        idRoom = ++id;
        this.name = name;
        this.admin = admin;
        join(admin);
    }
 
    // подключившумуся отправляем текущее состояине холста
    public void join(User user)
    {
        users.add(user);
        history.addUser(user);
        try {
            System.out.println("К комнате " + idRoom + "присоединился" + user);
            user.getThisObjectOutputStream().writeObject(history.getHistory());
        } catch (IOException ex) {
            System.out.println("Join(): " + id + ex.getMessage());
        }
    }
    
    
    private void broadcast(String changes[]) 
    {
        User tmp = null;
	try 
        {
            for (User user : users)
            {
                tmp = user;
                if(changes == null)
                    System.out.println("ИЗМЕНЕНИЯ НУЛЛ ");
                
                user.getThisObjectOutputStream().writeObject(changes); 
                
            }
                
	} 
        catch (SocketException e) 
        {
            users.remove(tmp);
            System.out.println("Socket ex:broadcast " + e.getMessage());
	} 
        catch (IOException e) 
        {
            users.remove(tmp);
            System.out.println("IO ex: broadcast " + e.getMessage());
        }
    }
    
    public String toString()
    {
        return name;
    }
    
    public int getId()
    {
        return id;
    }

    void addAction(User user, String[] changes) {
        history.addAction(user, changes);
        broadcast(changes);
        
    }

}

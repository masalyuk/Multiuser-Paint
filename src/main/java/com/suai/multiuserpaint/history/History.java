/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suai.multiuserpaint.history;

import com.suai.multiuserpaint.server.User;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;



//замутить синхонизацию
public class History implements Serializable{

    private HashMap<User, LinkedList<String[]>> actions = new HashMap<>();

    public History() {
    
    }
    
    public void addUser(User user)
    {
        actions.put(user, new LinkedList<String []>());
    }
    
    public void addAction(User user, String[] action)
    {
        actions.get(user).add(action);

    }
    
    
    public LinkedList<String[]> getHistory()
    {
        LinkedList<String[]> historys = new LinkedList<>();
        
        for(User user : actions.keySet())
            for(String[] action : actions.get(user))
                historys.add(action);
        
        return historys;
    }
    //возможно добавть метод который возвратит строчку со всеми действиями
    
            
            
    

}

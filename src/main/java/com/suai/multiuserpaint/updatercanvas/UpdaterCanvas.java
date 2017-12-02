package com.suai.multiuserpaint.updatercanvas;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author nikit
 */
public class UpdaterCanvas implements  Serializable{
  
    transient Canvas canvas;

    transient GraphicsContext graphicsContext;
      //координаты для рисования
    private double xOld;
    private double yOld;
    
    public UpdaterCanvas(Canvas canvas)
    {

        this.canvas = canvas;
        this.graphicsContext = canvas.getGraphicsContext2D();
        
    }
    
    public UpdaterCanvas(GraphicsContext contex)
    {

        this.graphicsContext = contex;
        this.canvas = this.graphicsContext.getCanvas();
    }
    public void updateCanvas(String[] changes)
    {
        switch(changes[0])
        {
            case "OnMousePressed":
                OnMousePressed(changes); 
                break;
            case "OnMouseDragged":
                OnMouseDragged(changes);
                break;
        }        
    }
    //формат OnMousePressed x, y
    public void OnMousePressed(String[] action)
    {
        xOld = Double.parseDouble(action[1]);
        yOld = Double.parseDouble(action[2]);
        
    }
    
    //формат OnMousePressed x, y, size, color 
    public void OnMouseDragged(String[] action)
    {
        
        double x = Double.parseDouble(action[1]);
        double y = Double.parseDouble(action[2]);
        double size = Double.parseDouble(action[3]);
        String color = action[4];
        graphicsContext.setLineWidth(size);
        graphicsContext.setStroke(Color.web(color));
        graphicsContext.strokeLine(x, y, xOld, yOld);
        
        xOld = x;
        yOld = y;
    }

}

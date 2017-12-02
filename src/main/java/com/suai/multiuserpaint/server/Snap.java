package com.suai.multiuserpaint.server;
import com.suai.multiuserpaint.server.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Snap implements Initializable {
private  User user;
//private static Canvas canvas = new Canvas();
public static WritableImage snapshot(Canvas canvas)
{
    
    SnapshotParameters params = new SnapshotParameters();
    params.setFill(Color.TRANSPARENT);
    WritableImage image= canvas.snapshot(params, null);
    System.out.println("S-S" + image);
    return image;
}

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
}
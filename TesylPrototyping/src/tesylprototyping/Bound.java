/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesylprototyping;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Kolbe
 */
public class Bound {
    private Rectangle body;
    private final double size = 50;
    private Vector pos;
    private double upperBound,lowerBound,leftBound,rightBound;
    private final TesylPoint child;
    
    protected Bound(TesylPoint child){
        this.child = child;
        setupBody();
    }
    
    protected void setupBody(){
        this.body = new Rectangle();
        body.setWidth(size);
        body.setHeight(size);
        body.setStroke(Color.BLACK);
        //CHANGED IT HERE
        body.setStrokeWidth(0);
        body.setOpacity(0);
        body.setFill(Color.RED);
        setPos(child.getPosition());
    }
    
    protected Rectangle getBody(){
        return this.body;
    }
    
    protected void setPos(Vector pos){
        this.pos = pos;
    }
    
    protected void update(){
        child.update();
        updatePos(child.getPosition());
    }
    
    
    private void updatePos(Vector pos){
        double x = pos.x-size/2;
        double y = pos.y-size/2;
        body.setX(x);
        body.setY(y);
    }
}

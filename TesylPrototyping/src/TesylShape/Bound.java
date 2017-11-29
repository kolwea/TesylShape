/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TesylShape;

import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

/**
 *
 * @author Kolbe
 */
public abstract class Bound {
    protected Line body;
    
    protected Bound(){
    }
        
    protected abstract void applyBound(Point point);
    
    protected abstract Shape getBody();
    
    protected boolean checkBound(Point point) {
        boolean collisionDetected = false;
        Shape intersect = Shape.intersect(body, point.getBody());
        if (intersect.getBoundsInLocal().getWidth() != -1) {
            collisionDetected = true;
            System.out.println("did it");
        }
        
        return collisionDetected;
    }
    
    
}

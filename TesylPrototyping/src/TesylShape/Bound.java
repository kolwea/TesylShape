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
    
    protected Bound(){
    }
        
    protected abstract void applyBound(Point point);
    
    protected abstract Shape getBody();
    
    protected abstract boolean checkBound(Point point);
    
    protected abstract void updateState();
    
    protected abstract void update();
    
}

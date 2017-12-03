/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesylprototyping;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 *
 * @author Kolbe
 */
public abstract class TesylBound {

    protected boolean dynamic;
    protected Line bound;
    protected Vector start, end;
    protected double slope, intercept, angle;

    protected Color bodyColor = Color.AQUA;
    protected double strokeWidth = 3.0;

    protected TesylBound() {
        setupBody();
    }
////////////////////////////////////////////////////////INITIALIZATION FUNCTIONS//////////////////////////////////////////////////////

    protected void setupBody() {
        bound = new Line();
        bound.setStrokeWidth(strokeWidth);
        bound.setFill(bodyColor);
    }

    protected abstract void setEndPoints(Object uno, Object dos);

    protected abstract void updateState();

    protected abstract void createBind();

    protected void checkIntersection(TesylPoint curr) {
        if (bound.getBoundsInParent().intersects(curr.getBody().getBoundsInParent())){
            System.out.println("Hit: " + curr.getIndex());
            reflectPoint(curr);
        }
    }

/////////////////////////////////////////////////////CLASS FUNCTIONS/////////////////////////////////////////////////////////////////    
    protected Node getBody() {
        return this.bound;
    }
//////////////////////////////////////////////////////HELPER FUNCTIONS///////////////////////////////////////////////////////////

    private void reflectPoint(TesylPoint curr){
        double pointAngle = curr.getAngle();
        if(pointAngle >= angle)
            curr.setAngle(pointAngle - angle);
        else
            curr.setAngle(angle - pointAngle);
    }   
    

}

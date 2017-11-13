/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesylprototyping;

/**
 *
 * @author Kolbe
 */
public class StaticBound extends TesylBound {

    public StaticBound() {
        super();
    }

    @Override
    protected void setEndPoints(Object uno, Object dos) {
        start = (Vector) uno;
        end = (Vector) dos;

        double x1 = start.x;
        double x2 = end.x;
        double y1 = start.y;
        double y2 = end.y;

        slope = (y1 - y2) / (x1 - x2);
        intercept = y1 - (slope * x1);
        angle = Math.toDegrees(Math.atan(slope));
        System.out.println("Line angle: " + angle);
        createBind();
    }

    @Override
    protected void updateState(){
        
    }

    @Override
    protected void createBind() {
        bound.setStartX(end.x);
        bound.setStartY(end.y);
        bound.setEndX(start.x);
        bound.setEndY(start.y);
    }
    
    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TesylShape;

import Tools.Vector;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

/**
 *
 * @author Kolbe
 */
public class StaticBound extends Bound {

    private double angle = 25;
    private Vector start, end;
    
    protected StaticBound(Vector start, Vector end) {
        super();
        this.start = start;
        this.end = end;
        setup();
    }

    @Override
    protected void applyBound(Point point) {
        double x,y;
        
        x = point.getAngle() - angle;
        y = angle - x;
        if(y > 360)
            y -= 360;
        point.setVelocity(Vector.angleToVector(y));
    }

    private void setup() {
        this.body = new Line();
        body.setStartX(start.x);
        body.setStartY(start.y);
        body.setEndX(end.x);
        body.setEndY(end.y);

//        body.setStroke(Paint.valueOf(Color.pink.toString()));
        body.setStrokeWidth(2.0);
    }

    @Override
    protected Shape getBody() {
        return this.body;
    }
}

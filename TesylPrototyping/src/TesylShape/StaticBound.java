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

    private double angle;
    private Vector start, end;

    protected StaticBound(Vector start, Vector end) {
        super();
        this.start = start;
        this.end = end;
        setup();
        updateState();
    }

    @Override
    protected void applyBound(Point point) {
        double x, y;
        x = point.getAngle() - angle;
        y = angle - x;
        if (y <= 0) {
            y += 360;
        }
        if (y > 360) {
            y -= 360;
        }
        point.setVelocity(y);
    }

    @Override
    protected Shape getBody() {
        return this.body;
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
    protected void updateState() {
        double distStartO = start.distance(new Vector(0, 0));
        double distEndO = end.distance(new Vector(0, 0));
        double distStartE = start.distance(new Vector(500, 500));
        double distEndE = end.distance(new Vector(500, 500));
        Vector head, tail;
        if (distStartO + distEndO <= distStartE + distEndE) {
            if (distStartO < distEndO) {
                head = start;
                tail = end;
            } else {
                head = end;
                tail = start;
            }
        } else {
            if (distStartE > distEndE) {
                head = start;
                tail = end;
            } else {
                head = end;
                tail = start;
            }
        }

        double deltaX = head.x - tail.x;
        double deltaY = -head.y - (-tail.y);
        double rad = Math.atan2(deltaY, deltaX);

        double deg = rad * (180 / Math.PI);

        if (deg <= 0) {
            deg += 360;
        } else if (deg > 360) {
            deg -= 360;
        }
        angle = deg;
        System.out.println(angle);

    }

}

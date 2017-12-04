/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TS2;

import Tools.Vector;
import java.util.ArrayList;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

/**
 *
 * @author Kolbe
 */
public class StaticBound extends Bound {

    private double angle;
    private Vector start, end;
    private Line body;
    private ArrayList<Point> ignore;

    protected StaticBound(Vector start, Vector end) {
        super();
        this.start = start;
        this.end = end;
        setup();
        updateState();
    }

    @Override
    protected void applyBound(Point point) {
        if (ignore == null || !ignore.contains(point)) {
            double x, y;
            x = point.getAngle() - angle;
            y = angle - x;
            if (y <= 0) {
                y += 360;
            }
            if (y > 360) {
                y -= 360;
            }
            point.reuptake = 0;
            point.setVelocity(y);
        }
    }

    protected void applyBound(Face face) {
        double x, y;
        x = face.getVelcity() - angle;
        y = angle - x;
        if (y <= 0) {
            y += 360;
        }
        if (y > 360) {
            y -= 360;
        }
        face.setVelocity(y);

    }

    @Override
    protected Shape getBody() {
        return this.body;
    }

    @Override
    protected void updateState() {
        double distStartO = start.distance(new Vector(0, 0));
        double distEndO = end.distance(new Vector(0, 0));

        Vector head, tail;
        if (distStartO < distEndO) {
            head = start;
            tail = end;
        } else {
            head = end;
            tail = start;
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
    }

    @Override
    protected boolean checkBound(Point point) {
        boolean collisionDetected = false;
        Shape intersect = Shape.intersect(body, point.getBody());
        if (intersect.getBoundsInLocal().getWidth() != -1) {
            collisionDetected = true;
        }

        return collisionDetected;
    }

    protected boolean checkBound(Face face) {
        boolean collisionDetected = false;
        Shape intersect = Shape.intersect(body, face.body);
        if (intersect.getBoundsInLocal().getWidth() != -1) {
            collisionDetected = true;
        }

        return collisionDetected;
    }

    protected void ignore(Point ig) {
        if (ignore == null) {
            ignore = new ArrayList<>();
        }
        ignore.add(ig);
    }

    protected void unignore(Point ign) {
        if (ignore == null); else {
            if (ignore.contains(ign)) {
                ignore.remove(ign);
            }
        }
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
    protected void update() {
    }

}

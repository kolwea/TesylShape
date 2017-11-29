/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TesylShape;

import Tools.Vector;
import javafx.beans.binding.Bindings;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

/**
 *
 * @author Kolbe
 */
public class DynamicBound extends Bound {

    private Vector start, end;
    private Point pOne, pTwo;
    private double angle;

    protected DynamicBound(Point start, Point end){
        this.pOne = start;
        this.pTwo  = end;
        this.start = start.getPosition();
        this.end = end.getPosition();
        setup();
    }
    
    @Override
    protected boolean checkBound(Point point) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        System.out.println(y);

        point.setVelocity(Vector.angleToVector(y));    }

    @Override
    protected Shape getBody() {
        return this.body;
    }

    @Override
    protected void updateState() {
        start = pOne.getPosition();
        end = pTwo.getPosition();

        double distStart = start.distance(new Vector(0, 0));
        double distEnd = end.distance(new Vector(0, 0));
        Vector head, tail;
        if (distStart > distEnd) {
            head = start;
            tail = end;
        } else {
            head = end;
            tail = start;
        }

        double deltaX = tail.x - head.x;
        double deltaY = -tail.y - (-head.y);
        double rad = Math.atan2(deltaY, deltaX);

        double deg = rad * (180 / Math.PI);
        System.out.println(angle);

        if (deg <= 0) {
            deg += 360;
        }
        if (deg > 360) {
            deg -= 360;
        }
        angle = deg;

    }

    private void setup() {
        start = pOne.getPosition();
        end = pTwo.getPosition();

        body = new Line();

        if (pOne.getBody().getParent() == null) {
            System.out.println("Tis null");
        } else {
            Pane parent = (Pane) pOne.getBody().getParent();
            body.startXProperty().bind(Bindings.createDoubleBinding(() -> {
                Bounds b = pOne.getBody().getBoundsInParent();
                return b.getMinX() + b.getWidth() / 2;
            }, pOne.getBody().boundsInParentProperty()));
            body.startYProperty().bind(Bindings.createDoubleBinding(() -> {
                Bounds b = pOne.getBody().getBoundsInParent();
                return b.getMinY() + b.getHeight() / 2;
            }, pOne.getBody().boundsInParentProperty()));
            body.endXProperty().bind(Bindings.createDoubleBinding(() -> {
                Bounds b = pTwo.getBody().getBoundsInParent();
                return b.getMinX() + b.getWidth() / 2;
            }, pTwo.getBody().boundsInParentProperty()));
            body.endYProperty().bind(Bindings.createDoubleBinding(() -> {
                Bounds b = pTwo.getBody().getBoundsInParent();
                return b.getMinY() + b.getHeight() / 2;
            }, pTwo.getBody().boundsInParentProperty()));
            parent.getChildren().add(body);
            
            body.toBack();
        }
        body.setStrokeWidth(2.0);
    }

}

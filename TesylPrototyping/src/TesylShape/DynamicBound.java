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
    private Line body;

    protected DynamicBound(Point start, Point end) {
        this.pOne = start;
        this.pTwo = end;
        this.start = start.getPosition();
        this.end = end.getPosition();
        setup();
    }

    @Override
    protected boolean checkBound(Point point) {
        boolean collisionDetected = false;
        if (!point.equals(pOne) && !point.equals(pTwo)) {
            this.updateState();
            Shape intersect = Shape.intersect(body, point.getBody());
            if (intersect.getBoundsInLocal().getWidth() != -1) {
                collisionDetected = true;
            }
        }
        return collisionDetected;
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

    @Override
    protected void updateState() {
        double distStartO = start.distance(new Vector(0, 0));
        double distEndO = end.distance(new Vector(0, 0));
        double distStartE = start.distance(new Vector(500, 500));
        double distEndE = end.distance(new Vector(500, 500));
        Vector head, tail;
//        if (distStartO + distEndO <= distStartE + distEndE) {
            if (distStartO < distEndO) {
                head = start;
                tail = end;
            } else {
                head = end;
                tail = start;
            }
//        } else {
//            if (distStartE > distEndE) {
//                head = start;
//                tail = end;
//            } else {
//                head = end;
//                tail = start;
//            }
//        }
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

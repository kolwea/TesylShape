/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TesylShape;

import Tools.Vector;
import java.util.ArrayList;
import java.util.Enumeration;
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

    private Point pOne, pTwo, curr;
    private double angle;
    private Line body;
    private ArrayList<Point> ignore;
    private Orientation dir;
    private int minMax;

    protected DynamicBound(Point one, Point two, Orientation bind) {
        this.pOne = one;
        this.pTwo = two;
//        this.ignore(one);
////        this.ignore(two);
        dir = bind;
        body = new Line();
        if (Orientation.HORIZONTAL == bind) {
            if (one.getBody().getCenterY() > two.getBody().getCenterY()) {
                minMax = 0;
            } else {
                minMax = 1;
            }
        } else {
            if (one.getBody().getCenterX() > two.getBody().getCenterX()) {
                minMax = 1;
            } else {
                minMax = 0;
            }
        }
        curr = pOne;
        setup();
        Pane pane = (Pane) pOne.getBody().getParent();
        pane.getChildren().add(body);

    }

    @Override
    protected boolean checkBound(Point point) {
        boolean collisionDetected = false;
        if (!ignore.contains(point)) {
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
            point.setVelocity(y);
        }
    }

    @Override
    protected Shape getBody() {
        return this.body;
    }

    @Override
    protected void updateState() {
        setup();
        Vector start = new Vector(body.getStartX(), body.getStartY());
        Vector end = new Vector(body.getEndX(), body.getEndY());
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
        angle = deg;
    }

    protected void ignore(Point ig) {
        if (ignore == null) {
            ignore = new ArrayList<>();
        }
        if (!ignore.contains(ig)) {
            ignore.add(ig);
        }
    }

    protected void unignore(Point ign) {
        if (ignore == null); else {
            if (ignore.contains(ign)) {
                ignore.remove(ign);
            }
        }
    }

    private void setup() {
        switch (minMax) {
            case 0:
                if (dir == Orientation.HORIZONTAL) {
                    if (pOne.getBody().getCenterY() < pTwo.getBody().getCenterY()) {
                        curr = pOne;
                        this.ignore(pOne);
                        this.unignore(pTwo);
                    } else {
                        curr = pTwo;
                        this.unignore(pOne);
                        this.ignore(pTwo);
                    }
                } else {
                    if (pOne.getBody().getCenterX() < pTwo.getBody().getCenterX()) {
                        curr = pOne;
                        this.ignore(pOne);
                        this.unignore(pTwo);
                    } else {
                        curr = pTwo;
                        this.unignore(pOne);
                        this.ignore(pTwo);
                    }
                }
                break;
            case 1:
                if (dir == Orientation.HORIZONTAL) {
                    if (pOne.getBody().getCenterY() > pTwo.getBody().getCenterY()) {
                        curr = pOne;
                        this.ignore(pOne);
                        this.unignore(pTwo);
                    } else {
                        curr = pTwo;
                        this.unignore(pOne);
                        this.ignore(pTwo);
                    }
                } else {
                    if (pOne.getBody().getCenterX() > pTwo.getBody().getCenterX()) {
                        curr = pOne;
                        this.ignore(pOne);
                        this.unignore(pTwo);
                    } else {
                        curr = pTwo;
                        this.unignore(pOne);
                        this.ignore(pTwo);
                    }
                }
                break;
        }
        if (pOne.getBody().getParent() == null) {
            System.out.println("Tis null");
        } else {
            Pane parent = (Pane) pOne.getBody().getParent();
            if (dir == Orientation.VERTICAL) {
                body.startXProperty().bind(Bindings.createDoubleBinding(() -> {
                    Bounds b = curr.getBody().getBoundsInParent();
                    return b.getMinX() + b.getWidth() / 2;
                }, curr.getBody().boundsInParentProperty()));
                body.endXProperty().bind(Bindings.createDoubleBinding(() -> {
                    Bounds b = curr.getBody().getBoundsInParent();
                    return b.getMinX() + b.getWidth() / 2;
                }, curr.getBody().boundsInParentProperty()));
                body.setStartY(0);
                body.setEndY(parent.getMinHeight());
            } else if (dir == Orientation.HORIZONTAL) {
                body.startYProperty().bind(Bindings.createDoubleBinding(() -> {
                    Bounds b = curr.getBody().getBoundsInParent();
                    return b.getMinY() + b.getWidth() / 2;
                }, curr.getBody().boundsInParentProperty()));
                body.endYProperty().bind(Bindings.createDoubleBinding(() -> {
                    Bounds b = curr.getBody().getBoundsInParent();
                    return b.getMinY() + b.getWidth() / 2;
                }, curr.getBody().boundsInParentProperty()));
                body.setStartX(0);
                body.setEndX(parent.getMinWidth());
            }
            body.toBack();
        }
        body.setStrokeWidth(2.0);
    }

}

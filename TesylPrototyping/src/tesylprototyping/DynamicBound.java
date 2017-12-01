/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesylprototyping;

import TesylShape.Face;
import javafx.beans.binding.Bindings;
import javafx.geometry.Bounds;

/**
 *
 * @author Kolbe
 */
public class DynamicBound extends TesylBound {

    private double strokeWidth = 3.0;
    private double slope, intercept;
    private TesylPoint pointA, pointB;
    private Face face;

    protected DynamicBound(Face face) {
        super();
        this.face = face;
    }

//////////////////////////////////////////////////////////TEST FUNCTIONS////////////////////////////////////////////////////////////
    protected double boundFunctionX(double x) {
        return slope * x + intercept;
    }

    protected double boundFunctionY(double y) {
        return (y - intercept) / slope;
    }

    private void checkWithinBound(Vector position) {

    }

    @Override
    protected void createBind() {
        bound.setStrokeWidth(strokeWidth);
        bound.startXProperty().bind(Bindings.createDoubleBinding(() -> {
            Bounds b = pointA.getBody().getBoundsInParent();
            return b.getMinX() + b.getWidth() / 2;
        }, pointA.getBody().boundsInParentProperty()));
        bound.startYProperty().bind(Bindings.createDoubleBinding(() -> {
            Bounds b = pointA.getBody().getBoundsInParent();
            return b.getMinY() + b.getHeight() / 2;
        }, pointA.getBody().boundsInParentProperty()));
        bound.endXProperty().bind(Bindings.createDoubleBinding(() -> {
            Bounds b = pointB.getBody().getBoundsInParent();
            return b.getMinX() + b.getWidth() / 2;
        }, pointB.getBody().boundsInParentProperty()));
        bound.endYProperty().bind(Bindings.createDoubleBinding(() -> {
            Bounds b = pointB.getBody().getBoundsInParent();
            return b.getMinY() + b.getHeight() / 2;
        }, pointB.getBody().boundsInParentProperty()));
    }

    @Override
    protected void updateState() {
        start = pointA.getPosition();
        end = pointB.getPosition();
        double x1 = start.x;
        double x2 = end.x;
        double y1 = start.y;
        double y2 = end.y;

        slope = (y1 - y2) / (x1 - x2);
        intercept = y1 - (slope * x1);
        angle = Math.toDegrees(Math.atan(slope));
    }

    @Override
    protected void setEndPoints(Object uno, Object dos) {
        TesylPoint first = (TesylPoint) uno;
        TesylPoint second = (TesylPoint) dos;

        this.pointA = first;
        this.pointB = second;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TesylShape;

import Tools.Vector;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 *
 * @author Kolbe
 */
public class Point {

    private Vector position;
    private Vector velocity;
    private double angle;
    private Circle body;
    protected double reuptake;
    protected int index;
    protected Text view;

    //Constructor functions/////////////////////////////////////////////////////
    protected Point(int index) {
        this.index = index;
        setup();
    }

    //Class functions///////////////////////////////////////////////////////////
    protected void setPosition(Vector pos) {
        this.position = pos;
        this.body.setCenterX(pos.x);
        this.body.setCenterX(pos.y);
    }

    protected Vector getPosition() {
        return this.position;
    }

    protected Vector getVelocity() {
        return this.velocity;
    }

    protected void setVelocity(double angle) {
        if (reuptake == 0) {
            this.angle = angle;
            this.velocity = Vector.angleToVector(angle);
            reuptake = 0;
        }
    }

    protected void setBody(Circle circle) {
        this.body = circle;
        this.position = new Vector(body.getCenterX(), body.getCenterY());
    }

    protected double getAngle() {
        return this.angle;
    }

    protected Circle getBody() {
        return this.body;
    }

    protected Text getLabel() {
        return this.view;
    }

    protected void update() {
        if (reuptake > 0) {
            reuptake--;
        }
        this.position = position.add(velocity.multiply(new Vector(0.5,0.5)));
        this.position = position;
        this.view.setX(position.x);
        this.view.setY(position.y);
        this.body.setCenterX(position.x);
        this.body.setCenterY(position.y);
    }

    //Helper functions//////////////////////////////////////////////////////////
    private void setup() {
        this.body = new Circle();
        this.setPosition(new Vector(50, 50));
        this.body.setRadius(10.0);
        this.angle = Math.random() *360;
        this.velocity = Vector.angleToVector(angle);

        this.view = new Text();
        view.setText(Integer.toString(index));
        view.setX(50);
        view.setY(50);
        view.setStroke(Paint.valueOf("BLUE"));
    }

}

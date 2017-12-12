/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TS3;

import Tools.Vector;
import javafx.scene.shape.Circle;

/**
 *
 * @author Kolbe
 */
public class Core {

    protected Tesyl parent;
    protected Circle body;
    protected Vector center;

    protected Core(Tesyl parent) {
        this.parent = parent;
        this.initialize();
    }

    protected void initialize() {
        if (body == null) {
            this.body = new Circle();
        }
        this.center = parent.frame.center;
        this.body.setCenterX(center.x);
        this.body.setCenterY(center.y);
        this.body.setRadius(parent.SHAPE_SIZE * parent.CORE_RATIO);
        this.body.setFill(parent.CORE_FILL);
        if (!parent.rootPane.getChildren().contains(body)) {
            parent.rootPane.getChildren().add(body);
        }
        
    }

}

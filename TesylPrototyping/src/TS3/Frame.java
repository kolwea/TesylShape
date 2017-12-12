/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TS3;

import Tools.Vector;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 *
 * @author Kolbe
 */
public class Frame {
    protected Vector center;
    protected Tesyl parent;
    protected Circle body,lilbody;
    
    
    protected Frame(Tesyl parent){
        this.parent = parent;
        this.center = new Vector(parent.rootPane.getMinWidth()/2,parent.rootPane.getMinHeight()/2);
        this.initialize();
    }
    
    protected void initialize(){
        if(this.body == null)
            this.body = new Circle();
        this.body.setCenterX(center.x);
        this.body.setCenterY(center.y);
        this.body.setRadius(parent.FRAME_RATIO*parent.SHAPE_SIZE);
        this.body.setFill(parent.FRAME_FILL);
        if(!parent.rootPane.getChildren().contains(body))
            parent.rootPane.getChildren().add(body);
        Line test = new Line();
        test.setStartX(0);
        test.setStartY(0);
        test.setEndX(100);
        test.setEndY(100);
        parent.rootPane.setClip(test);
     
    }
    
}

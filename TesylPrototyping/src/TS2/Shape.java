/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TS2;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Kolbe
 */
public class Shape {
    private Pane  pane;
    private Face face,face2;
    
    public Shape(){
        this.pane = new Pane();
        pane.setMinSize(500,500);
        face = new Face(pane);
        face2 = new Face(pane);
//        pane.getChildren().add(face.getPane());
    }
    
    public Pane getPane(){
        return this.pane;
    }
}

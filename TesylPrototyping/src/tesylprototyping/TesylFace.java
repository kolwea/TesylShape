/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesylprototyping;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 *
 * @author Kolbe
 */
public class TesylFace {
    private Polygon body;
    private final TesylPoint[] points;
    private Color faceColor;
    
    protected TesylFace(TesylPoint a, TesylPoint b, TesylPoint c, TesylPoint d){
        points = new TesylPoint[]{a,b,c,d};
        faceColor = Color.BLUE;
        setupBody();
        
    }
    
    private void setupBody(){
        body = new Polygon();
        body.getPoints().addAll(new Double[]{
          points[0].getBody().getCenterX(),points[0].getBody().getCenterY(),
          points[1].getBody().getCenterX(),points[1].getBody().getCenterY(),
          points[2].getBody().getCenterX(),points[2].getBody().getCenterY(),
          points[3].getBody().getCenterX(),points[3].getBody().getCenterY()
        });
        body.setOpacity(0.5);
        body.setFill(faceColor);
    }
    
    protected Polygon getBody(){
        return this.body;
    }
    
    protected void update(){
        body.getPoints().setAll(new Double[]{
          points[0].getBody().getCenterX(),points[0].getBody().getCenterY(),
          points[1].getBody().getCenterX(),points[1].getBody().getCenterY(),
          points[2].getBody().getCenterX(),points[2].getBody().getCenterY(),
          points[3].getBody().getCenterX(),points[3].getBody().getCenterY()
        });
    }
}

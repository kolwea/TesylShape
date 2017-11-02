/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesylprototyping;

import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


/**
 *
 * @author Kolbe
 */
public abstract class TesylBound {
    protected Line boundBody;
    protected ArrayList<TesylPoint> points;
    
    protected TesylBound(){
        setupBody();
    }
////////////////////////////////////////////////////////INITIALIZATION FUNCTIONS//////////////////////////////////////////////////////
    protected void setupBody(){
        boundBody = new Line();
        boundBody.setStrokeWidth(2.0);
        boundBody.setFill(Color.DARKRED);
    }
    
    protected abstract void setBound(Object uno, Object dos);
    
    protected void addConform(TesylPoint a){
        if(points == null)
            points = new ArrayList<>();
        points.add(a);
    }
/////////////////////////////////////////////////////CLASS FUNCTIONS/////////////////////////////////////////////////////////////////    
    protected Node getBody(){
        return this.boundBody;
    }
//////////////////////////////////////////////////////HELPER FUNCTIONS///////////////////////////////////////////////////////////

    protected abstract double boundFunctionX(double x);
    
    protected abstract double boundFunctionY(double y);

}

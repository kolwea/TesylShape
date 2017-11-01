/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesylprototyping;

import javafx.scene.Node;
import javafx.scene.shape.Line;


/**
 *
 * @author Kolbe
 */
public abstract class TesylBound {
    private Line boundBody;
    
    protected TesylBound(){
        
    }

    abstract void createBound();
    
    private Node getBody(){
        return this.boundBody;
    }
}

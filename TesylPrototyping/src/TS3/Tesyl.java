/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TS3;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author Kolbe
 */
public class Tesyl {

    protected final double SHAPE_SIZE = 20.0,
            CORE_RATIO = 0.5,
            FRAME_RATIO = 1.0;
    
    protected Color CORE_FILL = Color.YELLOWGREEN,
            FRAME_FILL = Color.BLACK;
    final String cssDefault = "-fx-border-color: blue;\n"
            + "-fx-border-insets: 5;\n"
            + "-fx-border-width: 3;\n"
            + "-fx-border-style: dashed;\n";

    protected Pane rootPane;
    protected Frame frame;
    protected Core core;
    protected Fins fins;
    protected Lens lens;

    public Tesyl() {
        this.initalize();
    }

    public void initalize() {
        rootPane = new Pane();
        rootPane.setMinSize(100, 100);
        rootPane.setStyle(cssDefault);
        frame = new Frame(this);
        core = new Core(this);
        fins = new Fins(this);
        lens = new Lens(this);
    }

    public Pane getShape() {
        return this.rootPane;
    }

}

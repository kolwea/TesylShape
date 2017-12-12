/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TS2;

import TS2.Shape;
import TS3.Tesyl;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Kolbe
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;

    @FXML
    AnchorPane vizPane;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO        
        Shape shape = new Shape();
        Shape shape2 = new Shape();
        
        Tesyl tess = new Tesyl();

        vizPane.getChildren().add(shape.getPane());
        vizPane.getChildren().add(shape2.getPane());
        vizPane.getChildren().add(tess.getShape());

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.nex.empresa;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Renzo
 */
public class EmpresaDialogController implements Initializable {

    @FXML
    private TextField boxNombre;
    @FXML
    private TextField boxRSocial;
    @FXML
    private TextField boxCUIT;
    @FXML
    private TextField boxObservacion;
    @FXML
    private TextField boxInfo;
    @FXML
    private TextField boxModelo;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void guardar(ActionEvent event) {
    }
    
}

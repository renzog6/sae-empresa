package ar.nex.app;

import ar.nex.empresa.EmpresaController;
import ar.nex.util.DialogController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Renzo
 */
public class HomeController implements Initializable {

    public HomeController() {
    }

    @FXML
    private BorderPane bpHome;

    @FXML
    private MenuButton mbEmpresa;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      initMenu();
    }
    
    private void initMenu() {
        mbEmpresa.getItems().get(0).setOnAction(e -> loadUI("empresa/Empresa"));
        //mbEquipo.getItems().get(1).setOnAction(e->loadUI("repuesto/Repuesto"));
    }

    public void loadUI(String ui) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/" + ui + ".fxml"));
            bpHome.getStylesheets().add("/fxml/" + ui + ".css");
            bpHome.setCenter(root);
        } catch (Exception ex) {
            DialogController.showException(ex);
        }
    }

    private void close(ActionEvent e) {
        ((Node) (e.getSource())).getScene().getWindow().hide();
    }
}

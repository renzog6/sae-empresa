package ar.nex.app;

import ar.nex.empresa.EmpresaController;
import ar.nex.entity.Empresa;
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
    private Button btnEmpresa;

    private Parent root;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            root = MainApp.getInstance().getRoot();
            //btnEmpresa.setOnAction(e -> loadUI("empresa/Empresa"));
            EmpresaController empresa = new EmpresaController();
            btnEmpresa.setOnAction(e->bpHome.setCenter(empresa.getRoot()));
        } catch (Exception ex) {
            DialogController.showException(ex);
        }
    }

    public void loadUI(String ui) {
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/" + ui + ".fxml"));
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

package ar.nex.empresa;

import ar.nex.entity.Empresa;
import ar.nex.entity.ubicacion.Direccion;
import ar.nex.ubicacion.DireccionEditController;
import ar.nex.util.DialogController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Renzo
 */
public class EmpresaDialogController implements Initializable {

    public EmpresaDialogController(Empresa empresa) {
        this.empresa = empresa;
    }

    @FXML
    private TextField boxNombre;
    @FXML
    private TextField boxRSocial;
    @FXML
    private TextField boxCUIT;
    @FXML
    private TextField boxObservacion;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button addDireccion;
    @FXML
    private Button addContacto;
    @FXML
    private TextField boxRubro;
    @FXML
    private Button addRubro;
    @FXML
    private TextField boxDireccion;
    @FXML
    private TextField boxContacto;

    private Empresa empresa;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initControls();
    }

    private void initControls() {
        try {
            btnCancelar.setOnAction(e -> ((Node) (e.getSource())).getScene().getWindow().hide());
            btnGuardar.setOnAction(e -> guardar(e));
            addDireccion.setOnAction(e -> addDirecion());

            if (empresa != null) {
                boxNombre.setText(empresa.getNombre());
                boxRSocial.setText(empresa.getRazonSocial());
                boxCUIT.setText(empresa.getCuit());

            } else {
                empresa = new Empresa();
                boxNombre.setText("nueva");
            }

        } catch (Exception ex) {
            DialogController.showException(ex);
        }
    }

    private void guardar(ActionEvent e) {
        try {

        } catch (Exception ex) {
            DialogController.showException(ex);
        } finally {
            ((Node) (e.getSource())).getScene().getWindow().hide();
        }
    }

    private void addDirecion() {
        try {
            Stage dialog = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ubicacion/DireccionEdit.fxml"));
            DireccionEditController controller = new DireccionEditController(new Direccion());
            loader.setController(controller);

            Scene scene = new Scene(loader.load());

            dialog.setScene(scene);
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.resizableProperty().setValue(Boolean.FALSE);

            dialog.showAndWait();

        } catch (IOException e) {
            System.err.print(e);

        }
    }
}

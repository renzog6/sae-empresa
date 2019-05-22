package ar.nex.empresa;

import ar.nex.app.MainApp;
import ar.nex.entity.Empresa;
import ar.nex.service.JpaService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Renzo
 */
public class EmpresaController implements Initializable {

    public EmpresaController() {
        this.filteredData = new FilteredList<>(data);
    }

    public Parent getRoot() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/empresa/Empresa.fxml"));
            root.setStyle("/fxml/empresa/Empresa.css");
        } catch (IOException ex) {
            Logger.getLogger(EmpresaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return root;
    }

    @FXML
    private BorderPane bpEmpresa;
    @FXML
    private TextField searchBox;
    
    @FXML
    private AnchorPane menuPane;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnAddModelo;
    @FXML
    private Button btnAddProvedor;
    @FXML
    private Button btnAddMarca;
    @FXML
    private Label lblModelo;
    @FXML
    private Label lblPedido;
    @FXML
    private Label lblCompra;

    private final ObservableList<Empresa> data = FXCollections.observableArrayList();
    private final FilteredList<Empresa> filteredData;
    private Empresa selectEmpresa;

    @FXML
    private TableView<Empresa> table;
    @FXML
    private TableColumn<?, ?> colEmpresa;
    @FXML
    private TableColumn<?, ?> colCuit;
    @FXML
    private TableColumn<Empresa, String> colLocalidad;
    @FXML
    private TableColumn<?, ?> colRubro;
    @FXML
    private TableColumn<?, ?> colInfo;
    @FXML
    private TableColumn<?, ?> colAccion;

    private JpaService jpa;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {            
            jpa = new JpaService();
            initTable();
            loadData();
        } catch (Exception e) {
            EmpresaUtils.showException(e);
        }
    }

    public void clearAll() {
        data.clear();
        searchBox.clear();
        selectEmpresa = null;
    }

    public void initTable() {
        colEmpresa.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCuit.setCellValueFactory(new PropertyValueFactory<>("cuit"));
        //colLocalidad.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colLocalidad.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Empresa, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Empresa, String> data) {
                StringProperty str = new SimpleStringProperty("NN");
                if ((data.getValue().getDomicilio() != null) && (data.getValue().getDomicilio().getLocalidad() != null)) {
                    str = new SimpleStringProperty(data.getValue().getDomicilio().getLocalidad().getNombre());
                }
                return str;
            }
        });
        colRubro.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colInfo.setCellValueFactory(new PropertyValueFactory<>("observacion"));
        //colAccion.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    }

    public void loadData() {
        try {
            clearAll();
            List<Empresa> lst = jpa.getEmpresa().findEmpresaEntities();
            lst.forEach((item) -> {
                data.add(item);
            });
            table.setItems(data);
        } catch (Exception e) {
            EmpresaUtils.showException(e);
        }
    }

    @FXML
    private void Search() {
        searchBox.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filteredData.setPredicate((Predicate<? super Empresa>) user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (user.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (user.getCuit().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Empresa> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }

    @FXML
    private void showOnClick(MouseEvent event) {
    }

}

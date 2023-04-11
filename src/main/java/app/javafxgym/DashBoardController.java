package app.javafxgym;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;

public class DashBoardController implements Initializable {

    @FXML
    private AnchorPane Anadir_Form;

    @FXML
    private Button Anadir_btn;

    @FXML
    private TableColumn<ClienteGym, String> ApellidoClienteTabla;

    @FXML
    private TableColumn<ClienteGym, Integer> ClienteIdTabla;

    @FXML
    private TableColumn<ClienteGym, Integer> DniClienteTabla;

    @FXML
    private TableColumn<ClienteGym, String> DomicilioClienteTabla;

    @FXML
    private TableColumn<?, Integer> EdadClienteTabla;

    @FXML
    private TableColumn<ClienteGym, Date> FechaNacimientoClienteTabla;

    @FXML
    private AnchorPane Inicio_Form;

    @FXML
    private Button Inicio_btn;

    @FXML
    private TextField Modificar_Apellido;

    @FXML
    private Button Modificar_Boton_Limpiar;

    @FXML
    private Button Modificar_Boton_Modificar;

    @FXML
    private TableColumn<?, ?> Modificar_Col_Apellido;

    @FXML
    private TableColumn<?, ?> Modificar_Col_Dni;

    @FXML
    private TableColumn<?, ?> Modificar_Col_Domicilio;

    @FXML
    private TableColumn<?, ?> Modificar_Col_Edad;

    @FXML
    private TableColumn<?, ?> Modificar_Col_FechaNacimiento;

    @FXML
    private TableColumn<?, ?> Modificar_Col_Nombre;

    @FXML
    private TableColumn<?, ?> Modificar_Col_ObraSocial;

    @FXML
    private TableColumn<?, ?> Modificar_Col_Telefono;

    @FXML
    private TableColumn<?, ?> Modificar_Col_TelefonoAuxiliar;

    @FXML
    private TextField Modificar_Dni;

    @FXML
    private TextField Modificar_Domicilio;

    @FXML
    private DatePicker Modificar_FechaNacimiento;

    @FXML
    private Label Modificar_Id;

    @FXML
    private TextField Modificar_Nombre;

    @FXML
    private TextField Modificar_ObraSocial;

    @FXML
    private TextField Modificar_Telefono;

    @FXML
    private TextField Modificar_TelefonoAuxiliar;

    @FXML
    private TableColumn<ClienteGym, String> NombreClienteTabla;

    @FXML
    private TableColumn<ClienteGym, String> ObraSocialClienteTabla;

    @FXML
    private AnchorPane Pagos_Form;

    @FXML
    private Button Pagos_btn;

    @FXML
    private Button Salir_btn;

    @FXML
    private TableView<ClienteGym> TablaPrincipalID;

    @FXML
    private TableColumn<ClienteGym, Integer> TelefonoAuxClienteTabla;

    @FXML
    private TableColumn<ClienteGym, Integer> TelefonoClienteTabla;

    @FXML
    private AnchorPane Ventana_Principal;

    @FXML
    private TextField CampoApellido;

    @FXML
    private TextField CampoDni;

    @FXML
    private TextField CampoDomicilio;

    @FXML
    private DatePicker CampoFNacimiento;

    @FXML
    private TextField CampoNombre;

    @FXML
    private TextField CampoObraSocial;

    @FXML
    private TextField CampoTelefono;

    @FXML
    private TextField CampoTelefonoAux;

    public void Cerrar(){
        System.exit(0);
    }

    public void Minimizar(){
        Stage stage = (Stage)Ventana_Principal.getScene().getWindow();
        stage.setIconified(true);
    }

    private double x = 0;
    private double y = 0;
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet resultado;
    private ObservableList<ClienteGym> ListaClientes;

    public void CambiarForm(ActionEvent event) {
        if (event.getSource() == Inicio_btn) {
            Inicio_Form.setVisible(true);
            Pagos_Form.setVisible(false);
            Anadir_Form.setVisible(false);
        } else if (event.getSource() == Pagos_btn) {
            Pagos_Form.setVisible(true);
            Inicio_Form.setVisible(false);
            Anadir_Form.setVisible(false);
        }
        else if(event.getSource() == Anadir_btn) {
            Anadir_Form.setVisible(true);
            Inicio_Form.setVisible(false);
            Pagos_Form.setVisible(false);
        }
    }

    public ObservableList<ClienteGym> ClientesDesdeDB(){
        ObservableList<ClienteGym> ListaClientes = observableArrayList();
        String sql = "SELECT C.*, TIMESTAMPDIFF(YEAR,c.FechaNacimiento,CURRENT_DATE()) as Edad  FROM clientes C";

        connect = database.connectdb();

        try{
            prepare =  connect.prepareStatement(sql);
            resultado = prepare.executeQuery();
            ClienteGym cliente;
            while(resultado.next())
            {
                cliente = new ClienteGym(
                        resultado.getInt("Id"),
                        resultado.getString("Nombre"),
                        resultado.getString("Apellido"),
                        resultado.getLong("Dni"),
                        resultado.getLong("Telefono"),
                        resultado.getLong("TelefonoAux"),
                        resultado.getString("ObraSocial"),
                        resultado.getString("Domicilio"),
                        resultado.getDate("FechaNacimiento"),
                        resultado.getInt("Edad")
                        );

                ListaClientes.add(cliente);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return ListaClientes;
    }

    public void AgregarAlumnoSeleccionado(){
        ClienteGym cliente = TablaPrincipalID.getSelectionModel().getSelectedItem();
        int num = TablaPrincipalID.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        CampoNombre.setText(cliente.getNombre());
        CampoApellido.setText(cliente.getApellido());
        CampoDni.setText(cliente.getDni().toString());
        CampoDomicilio.setText(cliente.getDomicilio());
        CampoTelefono.setText(cliente.getTelefono().toString());
        CampoFNacimiento.setValue(cliente.getFechaNacimiento());
        CampoTelefonoAux.setText(cliente.getObraSocial());
        CampoObraSocial.setText(cliente.getObraSocial());
    }

    public void MostrarClientes()
    {
        ListaClientes = ClientesDesdeDB();

        ClienteIdTabla.setCellValueFactory(new PropertyValueFactory<>("Id"));
        NombreClienteTabla.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        ApellidoClienteTabla.setCellValueFactory(new PropertyValueFactory<>("Apellido"));
        DniClienteTabla.setCellValueFactory(new PropertyValueFactory<>("Dni"));
        TelefonoClienteTabla.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
        TelefonoAuxClienteTabla.setCellValueFactory(new PropertyValueFactory<>("TelefonoAux"));
        ObraSocialClienteTabla.setCellValueFactory(new PropertyValueFactory<>("ObraSocial"));
        DomicilioClienteTabla.setCellValueFactory(new PropertyValueFactory<>("Domicilio"));
        FechaNacimientoClienteTabla.setCellValueFactory(new PropertyValueFactory<>("FechaNacimiento"));
        EdadClienteTabla.setCellValueFactory(new PropertyValueFactory<>("Edad"));
        TablaPrincipalID.setItems(ListaClientes);
    }

    public void CerrarSession(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Quiere cerrar sesión?");
        Optional<ButtonType> option = alert.showAndWait();
        try{
            if(option.isPresent() && option.get().equals(ButtonType.OK)){
                Ventana_Principal.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> stage.setOpacity(1));

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();

            }
        }
        catch (Exception e)
        {
           e.printStackTrace();
        }


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MostrarClientes();
    }
}

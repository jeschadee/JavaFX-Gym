package app.javafxgym;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {

    @FXML
    private AnchorPane Anadir_Form;

    @FXML
    private Button Anadir_btn;

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
    private AnchorPane Pagos_Form;

    @FXML
    private Button Pagos_btn;

    @FXML
    private Button Salir_btn;

    @FXML
    private AnchorPane Ventana_Principal;

    public void Cerrar(){
        System.exit(0);
    }

    public void Minimizar(){
        Stage stage = (Stage)Ventana_Principal.getScene().getWindow();
        stage.setIconified(true);
    }

    private double x = 0;
    private double y = 0;

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

    public void CerrarSession(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Quiere cerrar sesión?");
        Optional<ButtonType> option = alert.showAndWait();
        try{
            if(option.get().equals(ButtonType.OK)){
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

                root.setOnMouseReleased((MouseEvent event) ->{
                    stage.setOpacity(1);
                });

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

    }
}

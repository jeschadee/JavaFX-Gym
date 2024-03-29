package app.javafxgym;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;

public class DashBoardController implements Initializable {

    @FXML
    private AnchorPane Balance_Form;

    @FXML
    private Button Balance_btn;

    @FXML
    private TableColumn<ClienteGym, Integer> ClienteIdTabla;

    @FXML
    private TableColumn<ClienteGym, Integer> DniClienteTabla;

    @FXML
    private TableColumn<ClienteGym, String> DomicilioClienteTabla;

    @FXML
    private TableColumn<ClienteGym, Date> FechaNacimientoClienteTabla;

    @FXML
    private AnchorPane Inicio_Form;

    @FXML
    private Button Inicio_btn;
    @FXML
    private TextField FiltroCliente;

    @FXML
    private TextField FiltroClientePagos;

    @FXML
    private TextField Modificar_Pago;

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
    private TextField Modificar_Cantidad;

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
    private TextField Modificar_Fecha;

    @FXML
    private TextField Modificar_TelefonoAuxiliar;

    @FXML
    private TableColumn<ClienteGym, String> NombreClienteTabla;

    @FXML
    private TableColumn<ClienteGym, String> ObraSocialClienteTabla;

    @FXML
    private TableColumn<ClienteGym, Date> FechaPagoClienteTabla;

    @FXML
    private TableColumn<ClienteGym, String> PagoClienteTabla;

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
    private ImageView CampoImage;

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

    @FXML
    private Button btn_Actualizar;

    @FXML
    private Button btn_Agregar;

    @FXML
    private Button btn_Eliminar;

    @FXML
    private Button btn_Limpiar;

    @FXML
    private TableView<PagosGym> PagoTabla;

    @FXML
    private TableColumn<?, ?> PagoDiasRestantes;

    @FXML
    private TextField Modificar_NuevaCantidad;

    @FXML
    private TableColumn<PagosGym, Integer> PagoTablaCantidad;

    @FXML
    private TableColumn<PagosGym, Date> PagoTablaFecha;

    @FXML
    private TableColumn<PagosGym, Integer> PagoTablaId;

    @FXML
    private TableColumn<PagosGym, String> PagoTablaNombre;

    @FXML
    private TableColumn<PagosGym, String> PagoTablaPago;

    @FXML
    private Label labelTotalActivos;

    @FXML
    private Label labelTotalClientes;

    @FXML
    private Label labelTotalInactivos;

    @FXML
    private ImageView pago_img;

    @FXML
    private BarChart<?, ?> GraficoTotal;


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
    private ObservableList<ClienteGym> listaClientes;
    private ObservableList<PagosGym> listaPagos;
    private Image image;
    private String RutaImg;
    public void CambiarForm(ActionEvent event) {
        Inicio_Form.setVisible(false);
        Pagos_Form.setVisible(false);
        Balance_Form.setVisible(false);

        if (event.getSource() == Inicio_btn) {
            Inicio_Form.setVisible(true);
            MostrarClientes();
        } else if (event.getSource() == Pagos_btn) {
            Pagos_Form.setVisible(true);
            MostrarPagos();
        }
        else if(event.getSource() == Balance_btn) {
            Balance_Form.setVisible(true);
            ActualizarLabelsTotales();
        }
    }

    public void ActualizarLabelsTotales(){
        TotalClientes();
        TotalInactivos();
        TotalActivos();
        GraficoTotalClientes();
    }

    public ObservableList<PagosGym> PagosDesdeDB() {
        ObservableList<PagosGym> ListaPagos = observableArrayList();
        String sql = "select c.IdUsuario ,c.ApeYNom, p.Cantidad, p.FechaPago, case when p.FechaPago is NOT null and DATE_ADD(p.FechaPago, INTERVAL 1 MONTH) >= CURDATE() then 1 else 0 end as yaPago, c.image, case when p.FechaPago is NOT null then DATEDIFF(DATE_ADD(p.FechaPago, INTERVAL 1 MONTH),CURDATE()) else 0 end AS DiasRestantes from clientes c left join (select max(p.IdPago) as IdPago, c.IdUsuario from clientes c inner join pagos p on c.IdUsuario = p.IdUsuario group by c.IdUsuario) cp on cp.IdUsuario = c.IdUsuario left join pagos p on cp.IdPago = P.IdPago";

        connect = database.connectdb();
        try{
            prepare =  connect.prepareStatement(sql);
            resultado = prepare.executeQuery();
            PagosGym pagosGym;
            while(resultado.next())
            {
                pagosGym = new PagosGym(
                        resultado.getInt("IdUsuario"),
                        resultado.getString("ApeYNom"),
                        resultado.getInt("Cantidad"),
                        resultado.getDate("FechaPago"),
                        resultado.getBoolean("YaPago"),
                        resultado.getInt("DiasRestantes"),
                        resultado.getString("image")
                );

                ListaPagos.add(pagosGym);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return ListaPagos;
    }

    public ObservableList<ClienteGym> ClientesDesdeDB(){
        ObservableList<ClienteGym> ListaClientes = observableArrayList();
        String sql =
                "SELECT c.IdUsuario,c.ApeYNom,c.Dni,c.Telefono,c.TelefonoAux,c.ObraSocial,c.Domicilio,c.FechaNacimiento,\n" +
                        "                case when p.FechaPago is NOT null and DATE_ADD(p.FechaPago, INTERVAL 1 MONTH) >= CURDATE()   \n" +
                        "                then 1                   \n" +
                        "                else 0                    \n" +
                        "                end as yaPago,p.FechaPago,c.image \n" +
                        "                FROM clientes c\n" +
                        "                left join (select max(p.IdPago) as IdPago, c.IdUsuario from clientes c inner join pagos p on c.IdUsuario = p.IdUsuario group by c.IdUsuario) cp on cp.IdUsuario = c.IdUsuario\n" +
                        "                LEFT JOIN pagos p on p.IdPago = cp.IdPago" ;
        connect = database.connectdb();

        try{
            prepare =  connect.prepareStatement(sql);
            resultado = prepare.executeQuery();
            ClienteGym cliente;
            while(resultado.next())
            {
                cliente = new ClienteGym(
                        resultado.getInt("IdUsuario"),
                        resultado.getString("ApeYNom"),
                        resultado.getLong("Dni"),
                        resultado.getLong("Telefono"),
                        resultado.getLong("TelefonoAux"),
                        resultado.getString("ObraSocial"),
                        resultado.getString("Domicilio"),
                        resultado.getDate("FechaNacimiento"),
                        resultado.getBoolean("YaPago"),
                        resultado.getDate("FechaPago"),
                        resultado.getString("image")
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

    public void ClickAlumnoSeleccionadoPago(){
        PagosGym pagosGym = PagoTabla.getSelectionModel().getSelectedItem();
        int num = PagoTabla.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        Modificar_Nombre.setText(pagosGym.getApeYNom());
        Modificar_Id.setText(pagosGym.getIdUsuario().toString());
        Modificar_Pago.setText(pagosGym.getYaPago());
        Modificar_Cantidad.setText(pagosGym.getMontoLocal());
        Modificar_Fecha.setText(pagosGym.getFechaPago() == null ? "" : pagosGym.getFechaPago().toString());
        String uri = "file:" + pagosGym.getImg();
        image = new Image(uri, 200,200,false,true);
        pago_img.setImage(image);
    }

    public void AgregarImagenCliente() {
        FileChooser open = new FileChooser();
        File file = open.showOpenDialog(Ventana_Principal.getScene().getWindow());

        if(file != null){
            RutaImg = file.getAbsolutePath();
            image = new Image(file.toURI().toString(),140,160,false,true);
            CampoImage.setImage(image);
        }
    }
    public void ClickAlumnoSeleccionado(){
        ClienteGym cliente = TablaPrincipalID.getSelectionModel().getSelectedItem();
        int num = TablaPrincipalID.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        CampoNombre.setText(cliente.getNombre());
        CampoDni.setText(cliente.getDni().toString());
        CampoDomicilio.setText(cliente.getDomicilio());
        CampoTelefono.setText(cliente.getTelefono().toString());
        CampoFNacimiento.setValue(cliente.getFechaNacimiento());
        CampoTelefonoAux.setText(cliente.getTelefonoAux().toString());
        CampoObraSocial.setText(cliente.getObraSocial());

        String uri = "file:" + cliente.getImage();
        image = new Image(uri, 140,160,false,true);
        CampoImage.setImage(image);
    }
    public void AgregarAlumno() {
        String sql = "INSERT INTO `clientes`(`ApeYNom`, `Dni`, `Telefono`, `TelefonoAux`, `ObraSocial`, `Domicilio`, `FechaNacimiento`,`image`) VALUES" +
                     "(?,?,?,?,?,?,?,?)";

        connect = database.connectdb();

        try{
            Alert alert;
            if(CampoNombre.getText().isEmpty() || CampoDni.getText().isEmpty() ||
               CampoDomicilio.getText().isEmpty() || CampoTelefono.getText().isEmpty() || CampoFNacimiento.getChronology().toString() == "" ||
               CampoTelefonoAux.getText().isEmpty() || CampoObraSocial.getText().isEmpty()
            ){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Por favor rellene todos los campos");
                alert.showAndWait();
                return;
            }
            else{
                prepare =  connect.prepareStatement(sql);
                prepare.setString(1,CampoNombre.getText());
                prepare.setString(2,CampoDni.getText());
                prepare.setString(3,CampoTelefono.getText());
                prepare.setString(4,CampoTelefonoAux.getText());
                prepare.setString(5,CampoObraSocial.getText());
                prepare.setString(6,CampoDomicilio.getText());
                prepare.setString(7,CampoFNacimiento.getValue().toString());

                String uri = RutaImg;
                uri = uri.replace("\\","\\\\");

                prepare.setString(8,uri);
                prepare.executeUpdate();

                MostrarClientes();
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Correcto.");
                alert.setHeaderText(null);
                alert.setContentText("¡Cliente añadido!");
                alert.showAndWait();
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void ActualizarAlumno() {
        Alert alert;
        ClienteGym cliente = TablaPrincipalID.getSelectionModel().getSelectedItem();
        int num = TablaPrincipalID.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("¡Debe seleccionar un usuario para actualizar!");
            alert.showAndWait();
            return;
        }

        String sql = "Update `clientes`set`ApeYNom`=?, `Dni`=?, `Telefono`=?, `TelefonoAux`=?, `ObraSocial`=?, `Domicilio`=?, `FechaNacimiento`=?, `image`=? WHERE" +
                " IdUsuario = ? ";

        connect = database.connectdb();

        try{
            if(CampoNombre.getText().isEmpty() || CampoDni.getText().isEmpty() ||
                    CampoDomicilio.getText().isEmpty() || CampoTelefono.getText().isEmpty() || CampoFNacimiento.getChronology().toString() == "" ||
                    CampoTelefonoAux.getText().isEmpty() || CampoObraSocial.getText().isEmpty()
            ){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Todos los campos deben estar llenos");
                alert.showAndWait();
                return;
            }
            else{
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmar");
                alert.setHeaderText(null);
                alert.setContentText("¿Esta seguro de actualizar al usuario: "+ cliente.getNombre() +"?");
                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)) {

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, CampoNombre.getText());
                    prepare.setString(2, CampoDni.getText());
                    prepare.setString(3, CampoTelefono.getText());
                    prepare.setString(4, CampoTelefonoAux.getText());
                    prepare.setString(5, CampoObraSocial.getText());
                    prepare.setString(6, CampoDomicilio.getText());
                    prepare.setString(7, CampoFNacimiento.getValue().toString());
                    String uri = RutaImg;
                    uri = uri.replace("\\","\\\\");

                    prepare.setString(8,uri);
                    prepare.setString(9, cliente.getId().toString());
                    prepare.executeUpdate();

                    MostrarClientes();

                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Correcto.");
                    alert.setHeaderText(null);
                    alert.setContentText("¡Cliente Actualizado!");
                    alert.showAndWait();
                }
                else{
                    return;
                }

            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void FiltroAlumno(){
        FilteredList<ClienteGym> filtro = new FilteredList<>(listaClientes, e->true );
        FiltroCliente.textProperty().addListener((Observable,oldValue,NewValue)->{
          filtro.setPredicate(predicateClienteData->{
              if(NewValue == null || NewValue.isEmpty())
              return true;

              String palabraBuscar = NewValue.toLowerCase();

              if(predicateClienteData.getId().toString().contains(palabraBuscar))
                  return true;
              else if (predicateClienteData.getNombre().toLowerCase().contains(palabraBuscar)) {
                  return true;
              }
              else if (predicateClienteData.getDomicilio().toLowerCase().contains(palabraBuscar)) {
                  return true;
              }
              else if (predicateClienteData.getDni().toString().contains(palabraBuscar)) {
                  return true;
              }
              else if (predicateClienteData.getObraSocial().toLowerCase().contains(palabraBuscar)) {
                  return true;
              }
              else if (predicateClienteData.getFechaNacimiento().toString().contains(palabraBuscar)) {
                  return true;
              }
              else if (predicateClienteData.getTelefono().toString().contains(palabraBuscar)) {
                  return true;
              }
              else if (predicateClienteData.getTelefonoAux().toString().contains(palabraBuscar)) {
                  return true;
              }
              else return  false;
          });
        });

        SortedList<ClienteGym> listaFiltrada = new SortedList<>(filtro);
        listaFiltrada.comparatorProperty().bind(TablaPrincipalID.comparatorProperty());
        TablaPrincipalID.setItems(listaFiltrada);
    }
    public void FiltroPagoAlumno(){
        FilteredList<PagosGym> filtro2 = new FilteredList<>(listaPagos, e->true );
        FiltroClientePagos.textProperty().addListener((Observador,valorViejo,valorNuevo)->{
            filtro2.setPredicate(x->{
                if(valorNuevo == null || valorNuevo.isEmpty())
                    return true;

                String palabraBuscar = valorNuevo.toLowerCase();

                if(x.getIdUsuario().toString().contains(palabraBuscar))
                    return true;
                else if (x.getApeYNom().toLowerCase().contains(palabraBuscar)) {
                    return true;
                }
                else if (x.getYaPago().toLowerCase().contains(palabraBuscar)) {
                    return true;
                }
                else if (x.getMontoLocal().toString().contains(palabraBuscar)) {
                    return true;
                }
                else if (x.getFechaPago() != null && x.getFechaPago().toString().contains(palabraBuscar)) {
                    return true;
                }
                else if (x.getDiasRestantes().toString().contains(palabraBuscar)) {
                    return true;
                }
                else return  false;
            });
        });

        SortedList<PagosGym> listaFiltrada2 = new SortedList<>(filtro2);
        listaFiltrada2.comparatorProperty().bind(PagoTabla.comparatorProperty());
        PagoTabla.setItems(listaFiltrada2);
    }
    public void EliminarAlumno() {
        Alert alert;
        ClienteGym cliente = TablaPrincipalID.getSelectionModel().getSelectedItem();
        int num = TablaPrincipalID.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("¡Debe seleccionar un usuario para poder eliminar!");
            alert.showAndWait();
            return;
        }
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmación");
        alert2.setHeaderText(null);
        alert2.setContentText("¿Quiere eliminar al usuario " + cliente.getNombre().toString() + "?");
        Optional<ButtonType> option = alert2.showAndWait();
        if(option.isPresent() && option.get().equals(ButtonType.OK)) {
            String sql = "DELETE FROM `clientes` WHERE" +
                    " IdUsuario = ? ";

            connect = database.connectdb();

            try {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, cliente.getId().toString());
                prepare.executeUpdate();

                MostrarClientes();

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Correcto.");
                alert.setHeaderText(null);
                alert.setContentText("Cliente " + cliente.getNombre().toString() + " Eliminado...");
                alert.showAndWait();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    public void MostrarClientes()
    {
        listaClientes = ClientesDesdeDB();
        ClienteIdTabla.setCellValueFactory(new PropertyValueFactory<>("Id"));
        NombreClienteTabla.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        DniClienteTabla.setCellValueFactory(new PropertyValueFactory<>("Dni"));
        TelefonoClienteTabla.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
        TelefonoAuxClienteTabla.setCellValueFactory(new PropertyValueFactory<>("TelefonoAux"));
        ObraSocialClienteTabla.setCellValueFactory(new PropertyValueFactory<>("ObraSocial"));
        DomicilioClienteTabla.setCellValueFactory(new PropertyValueFactory<>("Domicilio"));
        FechaNacimientoClienteTabla.setCellValueFactory(new PropertyValueFactory<>("FechaNacimiento"));
        PagoClienteTabla.setCellValueFactory(new PropertyValueFactory<>("YaPago"));
        FechaPagoClienteTabla.setCellValueFactory(new PropertyValueFactory<>("FechaPago"));
        TablaPrincipalID.setItems(listaClientes);

        LimpiarCampos();
        FiltroAlumno();

    }
    public void MostrarPagos()
    {
        listaPagos = PagosDesdeDB();
        PagoTablaId.setCellValueFactory(new PropertyValueFactory<>("IdUsuario"));
        PagoTablaNombre.setCellValueFactory(new PropertyValueFactory<>("ApeYNom"));
        PagoTablaCantidad.setCellValueFactory(new PropertyValueFactory<>("MontoLocal"));
        PagoTablaPago.setCellValueFactory(new PropertyValueFactory<>("YaPago"));
        PagoTablaFecha.setCellValueFactory(new PropertyValueFactory<>("FechaPago"));
        PagoDiasRestantes.setCellValueFactory(new PropertyValueFactory<>("DiasRestantes"));
        PagoTabla.setItems(listaPagos);

        LimpiarCamposPago();
        FiltroPagoAlumno();
    }

    public void TotalClientes(){
        String sql = "select count(*) as cantidadClientes from clientes";

        connect = database.connectdb();
        int cantidad = 0;
        try{
            prepare = connect.prepareStatement(sql);
            resultado = prepare.executeQuery();

            while (resultado.next()){
                cantidad = resultado.getInt("cantidadClientes");
            }
            labelTotalClientes.setText(String.valueOf(cantidad));
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void TotalActivos(){
        String sql = "select count(*) as cantidadClientes from clientes c left join (select max(p.IdPago) as IdPago, c.IdUsuario from clientes c inner join pagos p on c.IdUsuario = p.IdUsuario group by c.IdUsuario) cp on cp.IdUsuario = c.IdUsuario left join pagos p on p.IdPago = cp.IdPago where p.IdPago is not null";

        connect = database.connectdb();
        int cantidad = 0;
        try{
            prepare = connect.prepareStatement(sql);
            resultado = prepare.executeQuery();

            while (resultado.next()){
                cantidad = resultado.getInt("cantidadClientes");
            }
            labelTotalActivos.setText(String.valueOf(cantidad));
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void TotalInactivos(){
        String sql = "select count(*) as cantidadClientes from clientes c left join (select max(p.IdPago) as IdPago, c.IdUsuario from clientes c inner join pagos p on c.IdUsuario = p.IdUsuario group by c.IdUsuario) cp on cp.IdUsuario = c.IdUsuario left join pagos p on p.IdPago = cp.IdPago where p.IdPago is null";

        connect = database.connectdb();
        int cantidad = 0;
        try{
            prepare = connect.prepareStatement(sql);
            resultado = prepare.executeQuery();

            while (resultado.next()){
                cantidad = resultado.getInt("cantidadClientes");
            }
            labelTotalInactivos.setText(String.valueOf(cantidad));
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void GraficoTotalClientes(){
        GraficoTotal.getData().clear();
        String sql = "select CONCAT(UPPER(cast(monthname(FechaPago) as CHAR)),\" \", cast(year(FechaPago) as CHAR)) as fecha, count(*) as cantidad from pagos\n" +
                    "group by monthname(FechaPago), year(FechaPago)\n";

        connect = database.connectdb();
        try{
            XYChart.Series chart = new XYChart.Series();
            prepare = connect.prepareStatement(sql);
            resultado = prepare.executeQuery();

            while (resultado.next()){
                chart.getData().add(new XYChart.Data(resultado.getString("fecha"),resultado.getInt("cantidad")));
            }

            GraficoTotal.getData().add(chart);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void LimpiarCampos(){
        CampoNombre.setText("");
        CampoDni.setText("");
        CampoDomicilio.setText("");
        CampoTelefono.setText("");
        CampoFNacimiento.setValue(null);
        CampoTelefonoAux.setText("");
        CampoObraSocial.setText("");
        FiltroCliente.setText("");
        CampoImage.setImage(null);
        RutaImg = "";
        image = null;

    }
    public void LimpiarCamposPago(){
        Modificar_Nombre.setText("");
        Modificar_Pago.setText("");
        Modificar_Id.setText("");
        Modificar_Fecha.setText("");
        Modificar_Cantidad.setText("");
        Modificar_NuevaCantidad.setText("");
        pago_img.setImage(null);
        FiltroClientePagos.setText("");
    }
    public void IngresarPago(){
        PagosGym pagosGym = PagoTabla.getSelectionModel().getSelectedItem();
        int num = PagoTabla.getSelectionModel().getSelectedIndex();
        Alert alert;
        if ((num - 1) < -1) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("¡Debe seleccionar un usuario para agregar pago!");
            alert.showAndWait();
            return;
        }
        String sql = "INSERT INTO `pagos`(`IdUsuario`, `Cantidad`) VALUES" +
                "(?,?)";
        connect = database.connectdb();

        try{
            if( Modificar_NuevaCantidad.getText().isEmpty())
            {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Debe ingresar cantidad del nuevo pago.");
                alert.showAndWait();
                return;
            }
            else{
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmar");
                alert.setHeaderText(null);
                alert.setContentText("¿Esta seguro de agregar nuevo pago a: "+ pagosGym.getApeYNom() +"?");
                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)) {

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, pagosGym.getIdUsuario().toString());
                    prepare.setString(2, Modificar_NuevaCantidad.getText());
                    prepare.executeUpdate();

                    MostrarPagos();
                    LimpiarCamposPago();
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Correcto.");
                    alert.setHeaderText(null);
                    alert.setContentText("¡Cliente Actualizado!");
                    alert.showAndWait();
                }
                else{
                    return;
                }

            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

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
    public void InicializarGrillas(){
        Inicio_Form.setVisible(true);
        Pagos_Form.setVisible(true);
        Balance_Form.setVisible(true);
        Pagos_Form.setVisible(false);
        Balance_Form.setVisible(false);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        InicializarGrillas();
        MostrarClientes();
        MostrarPagos();
        GraficoTotalClientes();
    }
}
